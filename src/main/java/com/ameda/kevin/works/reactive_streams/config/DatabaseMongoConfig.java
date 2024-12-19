package com.ameda.kevin.works.reactive_streams.config;
/*
*
@author ameda
@project reactive-streams
*
*/

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

//@EnableReactiveMongoRepositories
public class DatabaseMongoConfig extends AbstractReactiveMongoConfiguration {


    @Value("${spring.data.mongodb.database}")
    private String databaseName;


    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(){
        return new ReactiveMongoTemplate(mongoClient(),getDatabaseName());
    }
}
