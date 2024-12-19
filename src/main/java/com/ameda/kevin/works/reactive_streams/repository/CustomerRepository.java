package com.ameda.kevin.works.reactive_streams.repository;
/*
*
@author ameda
@project reactive-streams
*
*/

import com.ameda.kevin.works.reactive_streams.collections.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer,Long> {
}
