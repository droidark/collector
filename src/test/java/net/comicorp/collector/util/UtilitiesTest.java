package net.comicorp.collector.util;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;
import org.springframework.hateoas.PagedModel;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    @Test
    void testBuildSortCriteriaSingleSort() {
        // Arrange
        String[] sort = {"name", "asc"};

        // Act
        Sort result = Utilities.buildSortCriteria(sort);

        // Assert
        Sort expected = Sort.by(new Sort.Order(Sort.Direction.ASC, "name"));
        assertEquals(expected, result, "Sort criteria should match for single sort");
    }

    @Test
    void testBuildSortCriteriaMultipleSort() {
        // Arrange
        String[] sort = {"name,asc", "age,desc"};

        // Act
        Sort result = Utilities.buildSortCriteria(sort);

        // Assert
        Sort expected = Sort.by(
                new Sort.Order(Sort.Direction.ASC, "name"),
                new Sort.Order(Sort.Direction.DESC, "age")
        );
        assertEquals(expected, result, "Sort criteria should match for multiple sorts");
    }

    @Test
    void testBuildPage() {
        // Arrange
        int page = 1;
        int size = 10;
        String[] sort = {"name,asc", "age,desc"};

        // Act
        Pageable pageable = Utilities.buildPage(page, size, sort);

        // Assert
        Sort expectedSort = Sort.by(
                new Sort.Order(Sort.Direction.ASC, "name"),
                new Sort.Order(Sort.Direction.DESC, "age")
        );
        Pageable expectedPageable = PageRequest.of(page, size, expectedSort);
        assertEquals(expectedPageable, pageable, "Pageable should be built correctly with sorting");
    }

    @Test
    void testBuildPagedModel() {
        // Arrange
        List<String> content = Arrays.asList("Item1", "Item2");
        Page<String> page = new PageImpl<>(content, PageRequest.of(0, 2), 5);

        // Act
        PagedModel<String> pagedModel = Utilities.buildPagedModel(page);

        // Assert
        PagedModel.PageMetadata expectedMetadata = new PagedModel.PageMetadata(2, 0, 5, 3);
        PagedModel<String> expectedModel = PagedModel.of(content, expectedMetadata);
        assertEquals(expectedModel.getContent().size(), pagedModel.getContent().size(), "PagedModel content should match");
        assertEquals(Objects.requireNonNull(expectedModel.getMetadata()).getSize(), Objects.requireNonNull(pagedModel.getMetadata()).getSize(), "Page size should match");
        assertEquals(expectedModel.getMetadata().getNumber(), pagedModel.getMetadata().getNumber(), "Page number should match");
        assertEquals(expectedModel.getMetadata().getTotalElements(), pagedModel.getMetadata().getTotalElements(), "Total elements should match");
        assertEquals(expectedModel.getMetadata().getTotalPages(), pagedModel.getMetadata().getTotalPages(), "Total pages should match");
    }
}