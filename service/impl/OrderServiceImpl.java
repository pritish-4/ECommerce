package service.impl;

import dao.OrderDAO;
import dao.daoImpl.OrderDAOImpl;
import model.*;
import service.OrderService;
import service.PaymentService;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public Order placeOrder(User user, PaymentService paymentService) {
        Cart cart = user.getCart();
        double totalAmount = cart.calculateTotal();
        boolean paymentSuccess = paymentService.processPayment(totalAmount);

        String orderId = IdGenerator.generateOrderId();
        String status = paymentSuccess ? "Confirmed" : "Failed";

        List<Product> productList = new ArrayList<>();
        for (var entry : cart.getItems().entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            for (int i = 0; i < qty; i++) {
                productList.add(p);
            }
        }

        Order order = new Order(orderId, productList, totalAmount);
        order.setStatus(status);

        if (paymentSuccess) {
            user.addOrder(order);
            cart.clearCart();
            orderDAO.save(order, user.getUserId());

            // Shipment logic
            String shipmentId = IdGenerator.generateShipmentId();
            Shipment shipment = new Shipment(shipmentId, orderId);
            order.setShipment(shipment);
        }

        return order;
    }
}
