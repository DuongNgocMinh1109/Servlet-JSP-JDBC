package com.dnminh.sorting;

public class Sorter {
    private String sortField;
    private String sortBy;

    public Sorter(String sortField, String sortBy) {
        this.sortField = sortField;
        this.sortBy = sortBy;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
