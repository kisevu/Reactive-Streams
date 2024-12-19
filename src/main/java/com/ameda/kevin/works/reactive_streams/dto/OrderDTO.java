package com.ameda.kevin.works.reactive_streams.dto;
/*
*
@author ameda
@project reactive-streams
*
*/

public class OrderDTO {

    private String customerId;
    private Double total;
    private Double discount;

    public OrderDTO() {
    }

    public OrderDTO(String customerId, Double total, Double discount) {
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

    @Override
    public String toString() {
        return "OrderDTO{" +
                "customerId='" + customerId + '\'' +
                ", total=" + total +
                ", discount=" + discount +
                '}';
    }
}
