package service.impl;

import model.Cart;
import model.Product;
import service.CartService;

public class CartServiceImpl implements CartService {

    @Override
    public void addToCart(Cart cart, Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    @Override
    public void removeFromCart(Cart cart, Product product) {
        cart.removeProduct(product);
    }

    @Override
    public double calculateTotal(Cart cart) {
        return cart.calculateTotal();
    }
}
