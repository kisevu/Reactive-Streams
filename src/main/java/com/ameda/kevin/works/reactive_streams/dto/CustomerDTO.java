package com.ameda.kevin.works.reactive_streams.dto;
/*
*
@author ameda
@project reactive-streams
*
*/

import java.time.LocalDateTime;

public class CustomerDTO {

    private String customerName;
    private String job;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerName,String job) {
        this.customerName = customerName;
        this.job = job;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerName='" + customerName + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
