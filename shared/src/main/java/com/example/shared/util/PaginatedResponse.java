package com.example.shared.util;

import java.util.List;

/**
 * Generic wrapper for paginated API responses.
 */
public class PaginatedResponse<T> {

    private final List<T> content;
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;
    private final boolean hasNext;

    public PaginatedResponse(List<T> content, int page, int size,
                              long totalElements, int totalPages, boolean hasNext) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.hasNext = hasNext;
    }

    public List<T> getContent()       { return content; }
    public int getPage()              { return page; }
    public int getSize()              { return size; }
    public long getTotalElements()    { return totalElements; }
    public int getTotalPages()        { return totalPages; }
    public boolean isHasNext()        { return hasNext; }
}
