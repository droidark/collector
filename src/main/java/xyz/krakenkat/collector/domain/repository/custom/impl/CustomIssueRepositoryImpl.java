package xyz.krakenkat.collector.domain.repository.custom.impl;

import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import xyz.krakenkat.collector.domain.model.Issue;
import xyz.krakenkat.collector.domain.model.query.Ids;
import xyz.krakenkat.collector.domain.model.query.PaginatedIssue;
import xyz.krakenkat.collector.domain.repository.custom.CustomIssueRepository;
import xyz.krakenkat.collector.util.Utilities;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CustomIssueRepositoryImpl implements CustomIssueRepository {

    private MongoOperations mongoOperations;

    @Override
    public Page<Issue> findAllByPublisherKeyAndTitleKey(
            String publisherKey,
            String titleKey,
            boolean variant,
            Pageable pageable) {

        Aggregation aggregation = Aggregation.newAggregation(
                // TITLE LOOKUP
                Utilities.buildLookUp("title", "title", "_id", "titleData"),
                Aggregation.unwind("$titleData"),
                // PUBLISHER LOOKUP
                Utilities.buildLookUp("publisher", "titleData.publisher", "_id", "publisherData"),
                Aggregation.match(Criteria
                        .where("publisherData.key").is(publisherKey)
                        .and("titleData.key").is(titleKey)
                        .and("variant").is(variant)),
                Utilities.buildFacet(pageable)
        );

        AggregationResults<PaginatedIssue> result = mongoOperations.aggregate(aggregation, Issue.class, PaginatedIssue.class);

        return this.buildPaginatedIssue(result, pageable);
    }

    @Override
    public Optional<Issue> findOneByPublisherKeyAndTitleKeyAndIssueKey(String publisherKey, String titleKey, String issueKey) {
        Aggregation aggregation = Aggregation.newAggregation(
                // TITLE LOOKUP
                Utilities.buildLookUp("title", "title", "_id", "titleData"),
                Aggregation.unwind("$titleData"),
                // PUBLISHER LOOKUP
                Utilities.buildLookUp("publisher", "titleData.publisher", "_id", "publisherData"),
                Aggregation.unwind("$publisherData"),
                Aggregation.match(Criteria
                        .where("publisherData.key").is(publisherKey)
                        .and("titleData.key").is(titleKey)
                        .and("key").is(issueKey)
                        .and("variant").is(false))
        );

        AggregationResults<Issue> results = mongoOperations.aggregate(aggregation, Issue.class, Issue.class);

        return Optional.ofNullable(results.getUniqueMappedResult());
    }

    @Override
    public Page<Issue> findAllVariantsByPublisherKeyAndTitleKeyAndIssueKey(String publisherKey, String titleKey, String issueKey, Pageable pageable) {
        Aggregation aggregation = Aggregation.newAggregation(
                // TITLE LOOKUP
                Utilities.buildLookUp("title", "title", "_id", "titleData"),
                Aggregation.unwind("$titleData"),
                // PUBLISHER LOOKUP
                Utilities.buildLookUp("publisher", "titleData.publisher", "_id", "publisherData"),
                Aggregation.unwind("$publisherData"),
                Aggregation.match(Criteria
                        .where("publisherData.key").is(publisherKey)
                        .and("titleData.key").is(titleKey)
                        .and("key").is(issueKey)
                        .and("variant").is(true)),
                Utilities.buildFacet(pageable)
        );

        AggregationResults<PaginatedIssue> result = mongoOperations.aggregate(aggregation, Issue.class, PaginatedIssue.class);

        return this.buildPaginatedIssue(result, pageable);
    }

    @Override
    public Page<Issue> findAllByUsernameAndPublisherKeyAndTitleKey(String username, String publisherKey, String titleKey, boolean variant, Pageable pageable) {
        Aggregation aggregation = Aggregation.newAggregation(
                // USER LOOKUP
                Utilities.buildLookUp("user", "_id", "items.issueId", "userData"),
                Aggregation.unwind("$userData"),
                // TITLE LOOKUP
                Utilities.buildLookUp("title", "title", "_id", "titleData"),
                Aggregation.unwind("$titleData"),
                // PUBLISHER LOOKUP
                Utilities.buildLookUp("publisher", "titleData.publisher", "_id", "publisherData"),
                Aggregation.unwind("$publisherData"),
                Aggregation.match(Criteria
                        .where("userData.username").is(username)
                        .and("publisherData.key").is(publisherKey)
                        .and("titleData.key").is(titleKey)
                        .and("variant").is(variant)),
                Utilities.buildFacet(pageable));

        AggregationResults<PaginatedIssue> results = mongoOperations.aggregate(aggregation, Issue.class, PaginatedIssue.class);

        return this.buildPaginatedIssue(results, pageable);
    }

    @Override
    public List<Ids> findIdsByKeys(String publisherKey, String titleKey, List<String> issueKeys) {
        Aggregation aggregation = Aggregation.newAggregation(
                // TITLE LOOKUP
                Utilities.buildLookUp("title", "title", "_id", "titleData"),
                Aggregation.unwind("$titleData"),
                // PUBLISHER LOOKUP
                Utilities.buildLookUp("publisher", "titleData.publisher", "_id", "publisherData"),
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
                result.getPageInfo().get(0).get("TotalRecords"))
                : Page.empty();
    }
}