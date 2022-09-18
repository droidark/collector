package xyz.krakenkat.collector.domain.repository.custom.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import xyz.krakenkat.collector.domain.model.Title;
import xyz.krakenkat.collector.domain.model.query.PaginatedTitle;
import xyz.krakenkat.collector.domain.repository.custom.CustomTitleRepository;
import xyz.krakenkat.collector.util.Utilities;

import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor
public class CustomTitleRepositoryImpl implements CustomTitleRepository {

    private MongoOperations mongoOperations;

    @Override
    public Page<Title> findAllByPublisherKey(String publisherKey, Pageable pageable) {
        Aggregation aggregation = Aggregation.newAggregation(
                // PUBLISHER LOOKUP
                Utilities.buildLookUp("publisher", "publisher", "_id", "publisherData"),
                Aggregation.match(Criteria.where("publisherData.key").is(publisherKey)),
                Utilities.buildFacet(pageable));

        AggregationResults<PaginatedTitle> result = mongoOperations.aggregate(aggregation, Title.class, PaginatedTitle.class);

        return this.buildPaginatedTitle(result, pageable);
    }

    @Override
    public Page<Title> findAllByUsername(String username, Pageable pageable) {
        Aggregation aggregation = Aggregation.newAggregation(
                // USER LOOKUP
                Utilities.buildLookUp("user", "_id", "items.titleId", "userData"),
                Aggregation.match(Criteria.where("userData.username").is(username)),
                Utilities.buildFacet(pageable));

        AggregationResults<PaginatedTitle> results = mongoOperations.aggregate(aggregation, Title.class, PaginatedTitle.class);

        return this.buildPaginatedTitle(results, pageable);

    }

    @Override
    public Optional<Title> findByPublisherKeyAndTitleKey(String publisherKey, String titleKey) {
        Aggregation aggregation = Aggregation.newAggregation(
                // PUBLISHER LOOKUP
                Utilities.buildLookUp("publisher", "publisher", "_id", "publisherData"),
                Aggregation.match(Criteria
                        .where("publisherData.key").is(publisherKey)
                        .and("key").is(titleKey)));

        AggregationResults<Title> result = mongoOperations.aggregate(aggregation, Title.class, Title.class);
        return Optional.ofNullable(result.getUniqueMappedResult());
    }

    private Page<Title> buildPaginatedTitle(AggregationResults<PaginatedTitle> paginatedTitles, Pageable pageable) {
        PaginatedTitle result = Optional
                .ofNullable(paginatedTitles.getUniqueMappedResult())
                .orElse(PaginatedTitle
                        .builder()
                        .pageInfo(Collections.emptyList())
                        .resultData(Collections.emptyList())
                        .build());

        return !result.getResultData().isEmpty()
                ? new PageImpl<>(
                result.getResultData(),
                pageable,
                result.getPageInfo().get(0).get("TotalRecords"))
                : Page.empty();
    }
}