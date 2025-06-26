package service.impl;

import service.PaymentService;

public class DummyCashOnDelivery implements PaymentService {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Cash on Delivery for: " + amount+" accepted");
        return true;
    }
}
