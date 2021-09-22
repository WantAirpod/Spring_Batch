package com.example.TestSpringFramework.P413_ItemPreparedStateMentSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ItemPreparedStateMentSetter<T> {
    void setValues(T item, PreparedStatement ps) throws SQLException;
}
