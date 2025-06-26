package service.impl;

import service.PaymentService;

import java.util.Random;

public class DummyPaymentService implements PaymentService {

    @Override
    public boolean processPayment(double amount) {
        return new Random().nextBoolean();
    }
}
