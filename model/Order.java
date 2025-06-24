package model;

import java.util.*;

public class Order {
    private String orderId;
    private List<Product> products;
    private double totalAmount;
    private String status;

    public Order(String orderId, List<Product> products, double totalAmount) {
        this.orderId = orderId;
        this.products = products;
        this.totalAmount = totalAmount;
        this.status = "Pending";
    }

    public String getOrderId() { return orderId; }
    public List<Product> getProducts() { return products; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
}
