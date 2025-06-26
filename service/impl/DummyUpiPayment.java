package service.impl;

import service.PaymentService;
import java.util.Random;

public class DummyUpiPayment implements PaymentService {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI payment of: " + amount);
        boolean success = new Random().nextBoolean();
        System.out.println(success ? "UPI Payment Successful" : "UPI Payment Failed");
        return success;
    }
}
