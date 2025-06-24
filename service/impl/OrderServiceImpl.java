package service.impl;

import model.*;
import service.OrderService;
import service.PaymentService;
import util.DBConnection;
import util.IdGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order placeOrder(User user, PaymentService paymentService) {
        Cart cart = user.getCart();
        double totalAmount = cart.calculateTotal();

        boolean paymentSuccess = paymentService.processPayment(totalAmount);
        String orderId = IdGenerator.generateOrderId();
        String status = paymentSuccess ? "Confirmed" : "Failed";

        List<Product> productList = new ArrayList<>();
        for (Product p : cart.getItems().keySet()) {
            int quantity = cart.getItems().get(p);
            for (int i = 0; i < quantity; i++) {
                productList.add(p);
            }
        }

        Order order = new Order(orderId, productList, totalAmount);
        order.setStatus(status);

        if (paymentSuccess) {
            user.addOrder(order);
            cart.clearCart();
            saveOrderToDB(order, user.getUserId());
        }

        return order;
    }

    private void saveOrderToDB(Order order, String userId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO orders (order_id, user_id, total_amount, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, order.getOrderId());
            stmt.setString(2, userId);
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setString(4, order.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
