package com.dnminh.dao;

import com.dnminh.mapper.RowMapper;

import java.util.List;

public interface GenericDAO<T> {
    List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
}
