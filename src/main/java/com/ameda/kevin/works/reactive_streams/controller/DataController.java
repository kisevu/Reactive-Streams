package com.ameda.kevin.works.reactive_streams.controller;
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
import com.ameda.kevin.works.reactive_streams.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class DataController {

    private final CustomerService customerService;

    @Autowired
    public DataController(CustomerService customerService) {
        this.customerService = customerService;

    }


    @PostMapping("/customer/create")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO dto){
        return new ResponseEntity<>(customerService.uploadCustomer(dto),HttpStatus.OK);
    }

    @PostMapping("/order/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDTO dto){
        return new ResponseEntity<>(customerService.placeOrder(dto),HttpStatus.OK);
    }

    @GetMapping("/customer/find-by-id")
    public ResponseEntity<?> findCustomerById(@RequestParam String customerId){
        return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

}

