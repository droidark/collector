package xyz.krakenkat.collector.domain.repository.custom.impl;

import xyz.krakenkat.collector.domain.model.Issue;
import xyz.krakenkat.collector.domain.model.PaginatedIssue;
import xyz.krakenkat.collector.domain.model.query.Ids;
import xyz.krakenkat.collector.domain.repository.custom.CustomIssueRepository;
import xyz.krakenkat.collector.util.Utilities;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.FacetOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.facet;

@AllArgsConstructor
public class CustomIssueRepositoryImpl implements CustomIssueRepository {

    private MongoOperations mongoOperations;

    @Override
    public Page<Issue> findAllByPublisherKeyAndTitleKey(
            String publisherKey,
            String titleKey,
            boolean variant,
            Pageable pageable) {

        long skip = Utilities.getSkip(pageable);
        long limit = pageable.getPageSize();

        LookupOperation titleLookupOperation = LookupOperation.newLookup()
                .from("title")
                .localField("title")
                .foreignField("_id")
                .as("titleData");

        LookupOperation publisherLookupOperation = LookupOperation.newLookup()
                .from("publisher")
                .localField("titleData.publisher")
                .foreignField("_id")
                .as("publisherData");

        FacetOperation facetOperation = facet(
                Aggregation.skip(skip),
                Aggregation.limit(limit))
                .as("resultData")
                .and(Aggregation.count().as("TotalRecords"))
                .as("pageInfo");

        Aggregation aggregation = Aggregation.newAggregation(
                titleLookupOperation,
                Aggregation.unwind("$titleData"),
                publisherLookupOperation,Aggregation.unwind("$publisherData"),
                Aggregation.match(Criteria
                        .where("publisherData.key").is(publisherKey)
                        .and("titleData.key").is(titleKey)
                        .and("variant").is(variant)),
                facetOperation
        );

        AggregationResults<PaginatedIssue> result = mongoOperations.aggregate(aggregation, Issue.class, PaginatedIssue.class);

        return this.buildPaginatedIssue(result, pageable);
    }

    @Override
    public Optional<Issue> findOneByPublisherKeyAndTitleKeyAndIssueKey(String publisherKey, String titleKey, String issueKey) {
        LookupOperation titleLookupOperation = LookupOperation.newLookup()
                .from("title")
                .localField("title")
                .foreignField("_id")
                .as("titleData");

        LookupOperation publisherLookupOperation = LookupOperation.newLookup()
                .from("publisher")
                .localField("titleData.publisher")
                .foreignField("_id")
                .as("publisherData");

        Aggregation aggregation = Aggregation.newAggregation(
                titleLookupOperation,
                Aggregation.unwind("$titleData"),
                publisherLookupOperation,Aggregation.unwind("$publisherData"),
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

        long skip = Utilities.getSkip(pageable);
        long limit = pageable.getPageSize();

        LookupOperation titleLookupOperation = LookupOperation.newLookup()
                .from("title")
                .localField("title")
                .foreignField("_id")
                .as("titleData");

        LookupOperation publisherLookupOperation = LookupOperation.newLookup()
                .from("publisher")
                .localField("titleData.publisher")
                .foreignField("_id")
                .as("publisherData");

        FacetOperation facetOperation = facet(
                Aggregation.skip(skip),
                Aggregation.limit(limit))
                .as("resultData")
                .and(Aggregation.count().as("TotalRecords"))
                .as("pageInfo");

        Aggregation aggregation = Aggregation.newAggregation(
                titleLookupOperation,
                Aggregation.unwind("$titleData"),
                publisherLookupOperation,
                Aggregation.unwind("$publisherData"),
                Aggregation.match(Criteria
                        .where("publisherData.key").is(publisherKey)
                        .and("titleData.key").is(titleKey)
                        .and("key").is(issueKey)
                        .and("variant").is(true)),
                facetOperation
        );

        AggregationResults<PaginatedIssue> result = mongoOperations.aggregate(aggregation, Issue.class, PaginatedIssue.class);

        return this.buildPaginatedIssue(result, pageable);
    }

    @Override
    public Page<Issue> findAllByUsernameAndTitleId(String username, ObjectId titleId, Pageable pageable) {
        long skip = Utilities.getSkip(pageable);
        long limit = pageable.getPageSize();

        LookupOperation userLookupOperation = LookupOperation.newLookup()
                .from("user")
                .localField("_id")
                .foreignField("items.issueId")
                .as("userData");

        FacetOperation facetOperation = facet(
                Aggregation.skip(skip),
                Aggregation.limit(limit))
                .as("resultData")
                .and(Aggregation.count().as("TotalRecords"))
                .as("pageInfo");

        Aggregation aggregation = Aggregation.newAggregation(
                userLookupOperation,
                Aggregation.match(Criteria
                        .where("userData.username").is(username)
                        .and("title").is(titleId)
                        .and("variant").is(false)),
                facetOperation);

        AggregationResults<PaginatedIssue> results = mongoOperations.aggregate(aggregation, Issue.class, PaginatedIssue.class);

        return this.buildPaginatedIssue(results, pageable);
    }

    @Override
    public Ids findPublisherIdAndTitleByIssueId(ObjectId issueId) {
        LookupOperation titleLookupOperation = LookupOperation.newLookup()
                .from("title")
                .localField("title")
                .foreignField("_id")
                .as("titleData");

        LookupOperation publisherLookupOperation = LookupOperation.newLookup()
                .from("publisher")
                .localField("titleData.publisher")
                .foreignField("_id")
                .as("publisherData");

        Aggregation aggregation = Aggregation.newAggregation(
                titleLookupOperation,
                Aggregation.unwind("$titleData"),
                publisherLookupOperation,
                Aggregation.unwind("$publisherData"),
                Aggregation.match(Criteria.where("_id").is(issueId)),
                Aggregation.replaceRoot().withDocument(new Document("publisherId", "$publisherData._id").append("titleId", "$titleData._id"))
        );

        AggregationResults<Ids> results = mongoOperations.aggregate(aggregation, Issue.class, Ids.class);

        return results.getUniqueMappedResult();
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
