package xyz.krakenkat.collector.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;

import java.util.ArrayList;
import java.util.List;

public class Utilities {

    public static Sort buildSortCriteria(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] split = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(split[1]), split[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }
        return Sort.by(orders);
    }

    public static <T> PagedModel<T> buildPagedModel(Page<T> page) {
        return PagedModel.of(page.getContent(), new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages()));
    }

    private static Sort.Direction getSortDirection(String direction) {
        return direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    private Utilities() {}
}
