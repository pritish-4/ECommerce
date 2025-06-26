package service.impl;

import service.PaymentService;
import java.util.Random;

public class DummyCardPayment implements PaymentService {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Card payment of: " + amount);
        boolean success = new Random().nextBoolean();
        System.out.println(success ? "Card Payment Successful" : "Card Payment Failed");
        return success;
    }
}
