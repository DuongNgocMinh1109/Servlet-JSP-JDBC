package com.dnminh.paging;

import com.dnminh.sorting.Sorter;

public class PageRequest implements Pageable {
    private Integer page;
    private Integer itemEachPage;
    private Sorter sorter;

    public PageRequest(Integer page, Integer itemEachPage, Sorter sorter) {
        this.page = page;
        this.itemEachPage = itemEachPage;
        this.sorter = sorter;
    }

    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
        if (this.page != null && this.itemEachPage != null) {
            return (this.page - 1) * this.itemEachPage;
        }
        return null;
    }

    @Override
    public Integer getLimit() {
        return this.itemEachPage;
    }

    public Sorter getSorter() {
        if (this.sorter != null) {
            return this.sorter;
        }
        return null;
    }
}
