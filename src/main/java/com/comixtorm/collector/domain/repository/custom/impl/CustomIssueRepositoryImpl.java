package com.comixtorm.collector.domain.repository.custom.impl;

import com.comixtorm.collector.domain.model.Issue;
import com.comixtorm.collector.domain.model.PaginatedIssue;
import com.comixtorm.collector.domain.model.query.Ids;
import com.comixtorm.collector.domain.repository.custom.CustomIssueRepository;
import com.comixtorm.collector.util.Utilities;
import lombok.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.HashMap;
import java.util.Map;
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

        return new PageImpl<>(result.getUniqueMappedResult().getResultData(),
                pageable,
                result.getUniqueMappedResult().getPageInfo().get(0).get("TotalRecords"));
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

        return new PageImpl<>(result.getUniqueMappedResult().getResultData(),
                pageable,
                result.getUniqueMappedResult().getPageInfo().get(0).get("TotalRecords"));
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
}
