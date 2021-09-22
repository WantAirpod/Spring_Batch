package com.example.TestSpringFramework.P381_EventFilteringItemProcessor;

import com.example.TestSpringFramework.P255_Custom.Main.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;



public class EventFilteringItemProcessor implements ItemProcessor<Customer,Customer> {
    @Override
    public Customer process(Customer item){
        //해당 값을 2로나눠 짝수이면 null아니면 해당 값을 반환한다.
        return Integer.parseInt(item.getZipCode())%2 ==0?null:item;
    }
}
