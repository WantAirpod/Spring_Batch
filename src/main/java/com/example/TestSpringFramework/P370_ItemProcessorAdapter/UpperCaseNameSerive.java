package com.example.TestSpringFramework.P370_ItemProcessorAdapter;

import org.springframework.stereotype.Service;

@Service
public class UpperCaseNameSerive {
    public com.example.TestSpringFramework.P370_ItemProcessorAdapter.Customer upperCase(com.example.TestSpringFramework.P370_ItemProcessorAdapter.Customer customer){
        com.example.TestSpringFramework.P370_ItemProcessorAdapter.Customer newCustmoer =new Customer();
        newCustmoer.setFirstName(newCustmoer.getFirstName().toUpperCase());
        newCustmoer.setMiddlerIntial(newCustmoer.getMiddlerIntial().toUpperCase());
        newCustmoer.setLastName(newCustmoer.getLastName().toUpperCase());

        return newCustmoer;
    }
}
