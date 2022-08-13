package com.comixtorm.collector.domain.repository.custom.impl;

import com.comixtorm.collector.domain.model.PaginatedTitle;
import com.comixtorm.collector.domain.model.Title;
import com.comixtorm.collector.domain.repository.custom.CustomTitleRepository;
import com.comixtorm.collector.util.Utilities;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.FacetOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.facet;

@AllArgsConstructor
public class CustomTitleRepositoryImpl implements CustomTitleRepository {

    private MongoOperations mongoOperations;

    @Override
    public Page<Title> findAllByPublisherKey(String publisherKey, Pageable pageable) {

        long skip = Utilities.getSkip(pageable);
        long limit = pageable.getPageSize();

        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("publisher")
                .localField("publisher")
                .foreignField("_id")
                .as("publisherData");

        FacetOperation facetOperation = facet(
                Aggregation.skip(skip),
                Aggregation.limit(limit))
                .as("resultData")
                .and(Aggregation.count().as("TotalRecords"))
                .as("pageInfo");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperation,
                Aggregation.match(Criteria.where("publisherData.key").is(publisherKey)),
                facetOperation);

        AggregationResults<PaginatedTitle> result = mongoOperations.aggregate(aggregation, Title.class, PaginatedTitle.class);

        return new PageImpl<>(result.getMappedResults().get(0).getResultData(),
                pageable,
                result.getMappedResults().get(0).getPageInfo().get(0).get("TotalRecords"));
    }

    @Override
    public Page<Title> findAllByUsername(String username, Pageable pageable) {

        long skip = Utilities.getSkip(pageable);
        long limit = pageable.getPageSize();

        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("user")
                .localField("_id")
                .foreignField("items.titleId")
                .as("userData");

        FacetOperation facetOperation = facet(
                Aggregation.skip(skip),
                Aggregation.limit(limit))
                .as("resultData")
                .and(Aggregation.count().as("TotalRecords"))
                .as("pageInfo");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperation,
                Aggregation.match(Criteria.where("userData.username").is(username)),
                facetOperation);

        AggregationResults<PaginatedTitle> results = mongoOperations.aggregate(aggregation, Title.class, PaginatedTitle.class);

        return new PageImpl<>(results.getUniqueMappedResult().getResultData(),
                pageable,
                results.getUniqueMappedResult().getPageInfo().get(0).get("TotalRecords"));
    }

    @Override
    public Title findByPublisherKeyAndTitleKey(String publisherKey, String titleKey) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("publisher")
                .localField("publisher")
                .foreignField("_id")
                .as("publisherData");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperation,
                Aggregation.match(Criteria
                        .where("publisherData.key").is(publisherKey)
                        .and("key").is(titleKey)));

        AggregationResults<Title> result = mongoOperations.aggregate(aggregation, Title.class, Title.class);
        return result.getUniqueMappedResult();
    }
}