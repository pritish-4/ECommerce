package util;

import java.util.UUID;

public class IdGenerator {

    public static String generateUserId() {
        return "U" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public static String generateOrderId() {
        return "ORD" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
