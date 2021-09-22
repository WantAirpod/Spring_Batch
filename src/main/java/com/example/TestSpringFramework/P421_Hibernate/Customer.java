package com.example.TestSpringFramework.P421_Hibernate;


import com.fasterxml.jackson.annotation.JsonTypeId;

import java.io.Serializable;

@Entity
@Table(name="customer")
public class Customer implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GernertedValue(strategy =GenertionType.IDENTITY)
    private long id;
    private String firstName;
    private String MiddleName;
    private String LastName;

}
