package com.ameda.kevin.works.reactive_streams.service.impl;
/*
*
@author ameda
@project reactive-streams
*
*/

import com.ameda.kevin.works.reactive_streams.repository.CustomerRepository;
import com.ameda.kevin.works.reactive_streams.collections.Customer;
import com.ameda.kevin.works.reactive_streams.collections.Order;
import com.ameda.kevin.works.reactive_streams.dto.CustomerDTO;
import com.ameda.kevin.works.reactive_streams.dto.OrderDTO;
import com.ameda.kevin.works.reactive_streams.repository.OrderRepository;
import com.ameda.kevin.works.reactive_streams.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final OrderRepository orderRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ReactiveMongoTemplate reactiveMongoTemplate,
                               OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.orderRepository = orderRepository;
    }

    @Override
    public Mono<Customer> uploadCustomer(CustomerDTO customerDTO) {
        Customer customer =
                new Customer(customerDTO.getCustomerName(), LocalDateTime.now(),customerDTO.getJob());
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> findCustomerById(String customerId) {
        Criteria criteria = Criteria.where("customerId").is(customerId);
        Query query = Query.query(criteria);
        return reactiveMongoTemplate.findOne(query,Customer.class)
                .log();
    }

    @Override
    public Mono<Order> placeOrder(OrderDTO dto) {
        Order order = new Order(dto.getCustomerId(),
                dto.getTotal(),
                dto.getDiscount());
        return orderRepository.save(order);
    }

    @Override
    public Mono<Map<String, Double>> calculateSummary() {
        return  reactiveMongoTemplate.findAll(Customer.class)
                 .flatMap(customer ->
                         Mono.zip(Mono.just(customer),
                                 calculateOrderSum(customer.getCustomerId())))
                 .collectMap(tuple2-> tuple2.getT1().getCustomerName(),
                         Tuple2::getT2);

    }

    private Mono<Double> calculateOrderSum(String customerId){
        Criteria criteria = Criteria.where("customerId").is(customerId);
        return reactiveMongoTemplate.find(Query.query(criteria),Order.class)
                .map(Order::getTotal)
                .reduce(0d,Double::sum);
    }


}
