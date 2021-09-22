package com.example.TestSpringFramework.P255_Custom.Mapper;

import com.example.TestSpringFramework.P255_Custom.Interface.FieldSetMapper;
import com.example.TestSpringFramework.P255_Custom.Model.Customer;
import org.springframework.batch.item.file.transform.FieldSet;

public class CustmerFiledSetMapper implements FieldSetMapper<Customer> {
    public Customer mapFiledSet(FieldSet fieldSet)
    {
        Customer custmer =new Customer();

        custmer.setAddressNumber(fieldSet.readString("addressNumer") + " " + fieldSet.readString("street"));
        custmer.setCity(fieldSet.readString("city"));
        custmer.setLastName(fieldSet.readString("lastName"));
        custmer.setMiddlerIntial(fieldSet.readString("middleInital"));
        custmer.setState(fieldSet.readString("state"));
        custmer.setState(fieldSet.readString("zipCode"));

        return custmer;
    }
}
