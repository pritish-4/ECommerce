package Main;

import model.*;
import service.*;
import service.impl.*;
import util.IdGenerator;

import java.util.List;
import java.util.Scanner;

public class Entry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductServiceImpl();
        CartService cartService = new CartServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        PaymentService paymentService = new DummyPaymentService();

        System.out.println("Welcome to the E-Commerce App!");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        String userId = IdGenerator.generateUserId();
        User user = new User(userId, name, email);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Place Order");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Product> products = productService.getAllProducts();
                    System.out.println("Available Products:");
                    for (Product p : products) {
                        System.out.printf("ID: %s | %s | ₹%.2f | Stock: %d\n",
                                p.getId(), p.getName(), p.getPrice(), p.getStock());
                    }
                    break;

                case 2:
                    System.out.print("Enter product ID: ");
                    String prodId = scanner.nextLine();
                    Product selected = productService.getProductById(prodId);
                    if (selected != null) {
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        scanner.nextLine();
                        cartService.addToCart(user.getCart(), selected, qty);
                        System.out.println("Added to cart!");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    Cart cart = user.getCart();
                    System.out.println("Your Cart:");
                    for (Product p : cart.getItems().keySet()) {
                        System.out.printf("%s (x%d) - %.2f\n",
                                p.getName(), cart.getItems().get(p),
                                p.getPrice() * cart.getItems().get(p));
                    }
                    System.out.println("Total: " + cartService.calculateTotal(cart));
                    break;

                case 4:
                    Order order = orderService.placeOrder(user, paymentService);
                    System.out.println("Order Status: " + order.getStatus());
                    System.out.println("Order ID: " + order.getOrderId());
                    if (order.getShipment() != null) {
                        System.out.println("Shipment ID: " + order.getShipment().getShipmentId());
                        System.out.println("Shipment Status: " + order.getShipment().getStatus());
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Thank you for shopping!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }

        scanner.close();
    }
}
