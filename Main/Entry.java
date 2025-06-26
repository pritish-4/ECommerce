package Main;

import dao.UserDAO;
import dao.daoImpl.UserDAOImpl;
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
        UserDAO userDAO = new UserDAOImpl();

        System.out.println("Welcome to E-Commerce App");
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        User user = userDAO.findByEmail(email);

        if (user != null) {
            System.out.println("Welcome back, " + user.getName());
        } else {
            System.out.print("New user! Enter your name: ");
            String name = scanner.nextLine();
            String userId = IdGenerator.generateUserId();
            user = new User(userId, name, email);
            userDAO.save(user);
            System.out.println("Registration successful!");
        }

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
                    System.out.println("Products:");
                    for (Product p : products) {
                        System.out.printf("ID: %s | %s | %.2f | Stock: %d\n",
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
                    System.out.println("Cart:");
                    for (Product p : cart.getItems().keySet()) {
                        int qty = cart.getItems().get(p);
                        System.out.printf("%s (x%d) - %.2f\n", p.getName(), qty, p.getPrice() * qty);
                    }
                    System.out.println("Total: " + cartService.calculateTotal(cart));
                    break;

                case 4:
                    if (user.getCart().getItems().isEmpty()) {
                        System.out.println("Nothing in cart to ship.");
                        break;
                    }

                    System.out.println("Choose Payment Method:");
                    System.out.println("1. UPI");
                    System.out.println("2. Card");
                    System.out.println("3. Cash on Delivery");
                    System.out.print("Enter option: ");
                    int payOpt = scanner.nextInt();
                    scanner.nextLine();

                    PaymentService paymentService;
                    switch (payOpt) {
                        case 1 -> paymentService = new DummyUpiPayment();
                        case 2 -> paymentService = new DummyCardPayment();
                        case 3 -> paymentService = new DummyCashOnDelivery();
                        default -> {
                            System.out.println("Invalid option. Defaulting to UPI.");
                            paymentService = new DummyUpiPayment();
                        }
                    }

                    Order order = orderService.placeOrder(user, paymentService);
                    if (order != null) {
                        System.out.println("Order Status: " + order.getStatus());
                        System.out.println("Order ID: " + order.getOrderId());
                        if (order.getShipment() != null) {
                            System.out.println("Shipment ID: " + order.getShipment().getShipmentId());
                            System.out.println("Shipment Status: " + order.getShipment().getStatus());
                        }
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
