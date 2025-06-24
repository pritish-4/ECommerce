package service;

import model.Order;
import model.User;

public interface OrderService {
    Order placeOrder(User user, PaymentService paymentService);
}
