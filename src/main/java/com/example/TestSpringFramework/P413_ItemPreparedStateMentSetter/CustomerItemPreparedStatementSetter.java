package com.example.TestSpringFramework.P413_ItemPreparedStateMentSetter;

import com.example.TestSpringFramework.P359_ItemProcessor.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerItemPreparedStatementSetter implements ItemPreparedStateMentSetter<Customer> {
    public void setValues(Customer customer, PreparedStatement ps) throws SQLException{
        ps.setString(1,customer.getFirstName());
        ps.setString(2,customer.getMiddlerIntial());
        ps.setString(3, customer.getLastName());
    }
}
