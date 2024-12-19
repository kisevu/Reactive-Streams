package com.ameda.kevin.works.reactive_streams.collections;
/*
*
@author ameda
@project reactive-streams
*
*/

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "tbl_customers")
public class Customer {
    @Id
    private String customerId;
    private String customerName;
    private LocalDateTime uploadTime;
    private String job;

    public Customer() {
    }

    public Customer(String customerName, LocalDateTime uploadTime, String job) {
        this.customerName = customerName;
        this.uploadTime = uploadTime;
        this.job = job;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", uploadTime=" + uploadTime +
                ", job='" + job + '\'' +
                '}';
    }
}
