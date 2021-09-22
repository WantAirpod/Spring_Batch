package com.example.TestSpringFramework.P255_Custom.Interface;

import org.springframework.batch.item.file.transform.FieldSet;

import java.net.BindException;

public interface FieldSetMapper<T> {
    T mapFiledSet(FieldSet filedSet) throws BindException;
}
