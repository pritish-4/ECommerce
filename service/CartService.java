package service;

import model.Cart;
import model.Product;

public interface CartService {
    void addToCart(Cart cart, Product product, int quantity);
    void removeFromCart(Cart cart, Product product);
    double calculateTotal(Cart cart);
}
