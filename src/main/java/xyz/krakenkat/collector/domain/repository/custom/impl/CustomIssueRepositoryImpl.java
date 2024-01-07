package xyz.krakenkat.collector.domain.repository.custom.impl;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import xyz.krakenkat.collector.domain.model.Issue;
import xyz.krakenkat.collector.domain.model.query.Ids;
import xyz.krakenkat.collector.domain.model.query.PaginatedIssue;
import xyz.krakenkat.collector.domain.repository.custom.CustomIssueRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static xyz.krakenkat.collector.util.Utilities.buildFacet;
import static xyz.krakenkat.collector.util.Utilities.buildLookUp;

@RequiredArgsConstructor
public class CustomIssueRepositoryImpl implements CustomIssueRepository {

    private final MongoOperations mongoOperations;

    @Override
    public Page<Issue> findAllByPublisherKeyAndTitleKey(
            String publisherKey,
            String titleKey,
            String variant,
            Pageable pageable) {

        Aggregation aggregation = Aggregation.newAggregation(
                // TITLE LOOKUP
                buildLookUp("title", "title", "_id", "titleData"),
                Aggregation.unwind("$titleData"),
                // PUBLISHER LOOKUP
                buildLookUp("publisher", "titleData.publisher", "_id", "publisherData"),
                buildVariantMatch(publisherKey, titleKey, variant),
                Aggregation.sort(pageable.getSort()),
                buildFacet(pageable)
        );

        AggregationResults<PaginatedIssue> result = mongoOperations.aggregate(aggregation, Issue.class, PaginatedIssue.class);

        return this.buildPaginatedIssue(result, pageable);
    }

    @Override
    public Page<Issue> findAllByPublisherKeyAndTitleKeyAndIssueKey(String publisherKey, String titleKey, String issueKey, String variant, Pageable pageable) {
        Aggregation aggregation = Aggregation.newAggregation(
                // TITLE LOOKUP
                buildLookUp("title", "title", "_id", "titleData"),
                Aggregation.unwind("$titleData"),
                // PUBLISHER LOOKUP
                buildLookUp("publisher", "titleData.publisher", "_id", "publisherData"),
                Aggregation.unwind("$publisherData"),
                buildVariantMatch(publisherKey, titleKey, issueKey, variant),
                Aggregation.sort(pageable.getSort()),
                buildFacet(pageable)
        );

        AggregationResults<PaginatedIssue> result = mongoOperations.aggregate(aggregation, Issue.class, PaginatedIssue.class);

        return buildPaginatedIssue(result, pageable);
    }

    @Override
    public Page<Issue> findAllByUsernameAndPublisherKeyAndTitleKey(String username, String publisherKey, String titleKey, boolean variant, Pageable pageable) {
        Aggregation aggregation = Aggregation.newAggregation(
                // USER LOOKUP
                buildLookUp("user", "_id", "items.issueId", "userData"),
                Aggregation.unwind("$userData"),
                // TITLE LOOKUP
                buildLookUp("title", "title", "_id", "titleData"),
                Aggregation.unwind("$titleData"),
                // PUBLISHER LOOKUP
                buildLookUp("publisher", "titleData.publisher", "_id", "publisherData"),
                Aggregation.unwind("$publisherData"),
                Aggregation.match(Criteria
                        .where("userData.username").is(username)
                        .and("publisherData.key").is(publisherKey)
                        .and("titleData.key").is(titleKey)
                        .and("variant").is(variant)),
                buildFacet(pageable));

        AggregationResults<PaginatedIssue> results = mongoOperations.aggregate(aggregation, Issue.class, PaginatedIssue.class);

        return this.buildPaginatedIssue(results, pageable);
    }

    @Override
    public List<Ids> findIdsByKeys(String publisherKey, String titleKey, List<String> issueKeys) {
        Aggregation aggregation = Aggregation.newAggregation(
                // TITLE LOOKUP
                buildLookUp("title", "title", "_id", "titleData"),
                Aggregation.unwind("$titleData"),
                // PUBLISHER LOOKUP
                buildLookUp("publisher", "titleData.publisher", "_id", "publisherData"),
                Aggregation.unwind("$publisherData"),
                Aggregation.match(Criteria
                        .where("publisherData.key").is(publisherKey)
                        .and("titleData.key").is(titleKey)
                        .and("key").in(issueKeys)),
                Aggregation.replaceRoot().withDocument(new Document("publisherId", "$publisherData._id")
                        .append("titleId", "$titleData._id")
                        .append("issueId", "$_id"))
        );

        AggregationResults<Ids> results = mongoOperations.aggregate(aggregation, Issue.class, Ids.class);

        return Optional.of(results.getMappedResults()).orElse(List.of());
    }

    private Page<Issue> buildPaginatedIssue(AggregationResults<PaginatedIssue> paginatedIssues, Pageable pageable) {
        PaginatedIssue result = Optional
                .ofNullable(paginatedIssues.getUniqueMappedResult())
                .orElse(PaginatedIssue
                        .builder()
                        .resultData(Collections.emptyList())
                        .pageInfo(Collections.emptyList())
                        .build());

        return !result.getResultData().isEmpty()
                ? new PageImpl<>(
                result.getResultData(),
                pageable,
                result.getPageInfo().getFirst().get("TotalRecords"))
                : Page.empty();
    }

    private MatchOperation buildVariantMatch(String publisherKey, String titleKey, String variant) {
        if (variant.equalsIgnoreCase("true") || variant.equalsIgnoreCase("false")) {
            return Aggregation.match(Criteria
                    .where("publisherData.key").is(publisherKey)
                    .and("titleData.key").is(titleKey)
                    .and("variant").is(Boolean.valueOf(variant)));
        }
        return Aggregation.match(Criteria
                .where("publisherData.key").is(publisherKey)
                .and("titleData.key").is(titleKey));
    }

    private MatchOperation buildVariantMatch(String publisherKey, String titleKey, String issueKey, String variant) {
        if (variant.equalsIgnoreCase("true") || variant.equalsIgnoreCase("false")) {
            return Aggregation.match(Criteria
                    .where("publisherData.key").is(publisherKey)
                    .and("titleData.key").is(titleKey)
                    .and("key").is(issueKey)
                    .and("variant").is(Boolean.valueOf(variant)));
        }
        return Aggregation.match(Criteria
                .where("publisherData.key").is(publisherKey)
                .and("titleData.key").is(titleKey)
                .and("key").is(issueKey));
    }
}