package com.example.TestSpringFramework.P359_ItemProcessor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Customer {


    @NotNull(message="First Name is required")
    @Pattern(regexp="[a-zA-Z]+", message ="First name must be alphabetcal")
    private String firstName;
    @Size(min=1 , max=1)
    private String middlerIntial;
    private String lastName;
    private String addressNumber;
    private String state;
    private String zipCode;

    public Customer() {
    }

    public Customer(@NotNull(message = "First Name is required") @Pattern(regexp = "[a-zA-Z]+", message = "First name must be alphabetcal") String firstName, @Size(min = 1, max = 1) String middlerIntial, String lastName, String addressNumber, String state, String zipCode, String street, String city) {
        this.firstName = firstName;
        this.middlerIntial = middlerIntial;
        this.lastName = lastName;
        this.addressNumber = addressNumber;
        this.state = state;
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "middlerIntial='" + middlerIntial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", addressNumber='" + addressNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    public void setMiddlerIntial(String middlerIntial) {
        this.middlerIntial = middlerIntial;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    private String street;
    private String city;

    public String getMiddlerIntial() {
        return middlerIntial;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }



}
