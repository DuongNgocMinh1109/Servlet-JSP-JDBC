package com.dnminh.paging;

import com.dnminh.sorting.Sorter;

public interface Pageable {
    Integer getPage();

    Integer getOffset();

    Integer getLimit();

    Sorter getSorter();
}
