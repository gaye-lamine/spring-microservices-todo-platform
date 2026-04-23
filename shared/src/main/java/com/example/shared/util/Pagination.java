package com.example.shared.util;

import org.springframework.data.domain.Page;

/**
 * Utility class for handling pagination.
 */
public class Pagination {

    /**
     * Creates a paginated response from a Page object.
     *
     * @param page the Page object containing the data
     * @param <T> the type of the data
     * @return a PaginatedResponse containing the data and pagination information
     */
    public static <T> PaginatedResponse<T> createPaginatedResponse(Page<T> page) {
        return new PaginatedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext()
        );
    }
}