package com.example.TestSpringFramework.P255_Custom.Main;

public class Customer {
    private String middlerIntial;
    private String lastName;
    private String addressNumber;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @Override
    public String toString() {
        return "Customer{" +
                "middlerIntial='" + middlerIntial + '\'' +
                ", lastName='" + lastName + '\'' +
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

    public String getMiddlerIntial() {
        return middlerIntial;
    }

    public String getLastName() {
        return lastName;
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

    public Customer() {
    }

    public Customer(String middlerIntial, String lastName, String addressNumber, String street, String city, String state, String zipCode) {
        this.middlerIntial = middlerIntial;
        this.lastName = lastName;
        this.addressNumber = addressNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
