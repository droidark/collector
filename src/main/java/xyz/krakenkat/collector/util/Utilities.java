package xyz.krakenkat.collector.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.FacetOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.facet;

public class Utilities {

    public static LookupOperation buildLookUp(String from, String localField, String foreignField, String as) {
        return LookupOperation
                .newLookup()
                .from(from)
                .localField(localField)
                .foreignField(foreignField)
                .as(as);
    }

    public static FacetOperation buildFacet(Pageable pageable) {
        return facet(
                Aggregation.skip(pageable.getPageNumber() > 0 ? ((long) pageable.getPageNumber() * pageable.getPageSize()) : 0L),
                Aggregation.limit(pageable.getPageSize()))
                .as("resultData")
                .and(Aggregation.count().as("TotalRecords"))
                .as("pageInfo");
    }

    private Utilities() {}
}
