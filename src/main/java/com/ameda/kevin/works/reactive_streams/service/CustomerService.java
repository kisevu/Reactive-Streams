package com.ameda.kevin.works.reactive_streams.service;
/*
*
@author ameda
@project reactive-streams
*
*/

import com.ameda.kevin.works.reactive_streams.collections.Customer;
import com.ameda.kevin.works.reactive_streams.collections.Order;
import com.ameda.kevin.works.reactive_streams.dto.CustomerDTO;
import com.ameda.kevin.works.reactive_streams.dto.OrderDTO;
import reactor.core.publisher.Mono;


public interface CustomerService {

    Mono<Customer> uploadCustomer(CustomerDTO customerDTO);

    Mono<Customer> findCustomerById(String customerId);

    Mono<Order> placeOrder(OrderDTO dto);
}
