package com.example.TestSpringFramework.P359_ItemProcessor;

import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamSupport;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

public class UniqueLastNameValidator extends ItemStreamSupport implements Validator<Customer> {
    @Override
    public void validate(Customer value) throws ValidationException{

    }

}
