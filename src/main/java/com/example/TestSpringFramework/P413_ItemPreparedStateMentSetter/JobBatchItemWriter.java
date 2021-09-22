package com.example.TestSpringFramework.P413_ItemPreparedStateMentSetter;

import com.example.TestSpringFramework.P359_ItemProcessor.Customer;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class JobBatchItemWriter {


    @Bean
    @StepScope
    public JdbcBatchItemWriter<Customer> jdbcCustomerWriter(DataSource dataSource) throws
            Exception {
        return new JdbcBatchItemWriterBuilder<Customer>()
                .dataSource(dataSource)
                .sql("INSERT INTO CUSTOMER (first_name, " + "middle_initial, " +
                        "last_name, VALUES (?,?,?)" )
                .itemPreparedStatementSetter(new CustomerItemPreparedStatementSetter())
                .build();
    }
}
