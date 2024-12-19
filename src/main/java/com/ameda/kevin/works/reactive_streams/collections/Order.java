package com.ameda.kevin.works.reactive_streams.collections;
/*
*
@author ameda
@project reactive-streams
*
*/

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "doc_orders")
public class Order {

    @Id
    private String orderId;
    private String customerId;
    private Double total;
    private Double discount;

    public Order() {
    }

    public Order(String customerId, Double total, Double discount) {
        this.customerId = customerId;
        this.total = total;
        this.discount = discount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", total=" + total +
                ", discount=" + discount +
                '}';
    }
}
