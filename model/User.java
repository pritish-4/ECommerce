package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private String email;
    private Cart cart;
    private List<Order> orderHistory;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.cart = new Cart();
        this.orderHistory = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Cart getCart() { return cart; }
    public List<Order> getOrderHistory() { return orderHistory; }

    public void addOrder(Order order) {
        orderHistory.add(order);
    }
}
