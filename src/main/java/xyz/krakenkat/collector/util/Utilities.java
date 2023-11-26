package xyz.krakenkat.collector.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.FacetOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;

import java.util.ArrayList;
import java.util.List;

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

    public static Sort buildSortCriteria(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }
        return Sort.by(orders);
    }

    private static Sort.Direction getSortDirection(String direction) {
        return direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    private Utilities() {}
}
