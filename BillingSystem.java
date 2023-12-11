package trial;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class BillingSystem {
	private static List<User> usersList = new ArrayList<>();
	private static Transaction transaction = new Transaction();
    private static List<Product> products;
    private static ShoppingCart cart;
    private static String currentUsername; // get current username
    
    private static final int MAX_USERS = 10; // Maximum number of users
    private static String[][] users = new String[MAX_USERS][2]; // 2D array to store user data (username, password)
    private static int userCount = 0;

    public static void main(String[] args) {	
    	
    	
        cart = new ShoppingCart();
        initializeProducts();
        displayLoginMenu();       
    }

    private static void initializeProducts() {
    	 products = new ArrayList<>();
         products.add(new Product(1, "B1T1 burger", 35, 15));
         products.add(new Product(2, "Burger", 25, 13));
         products.add(new Product(3, "B1T1 Cheese Burger", 45, 16));
         products.add(new Product(4, "Cheese Burger", 30, 11));
         products.add(new Product(5, "Ham and Cheese Sandwich", 30, 20));
         products.add(new Product(6, "Ham and Egg Sandwich", 40, 12));
         products.add(new Product(7, "Ham, Egg, and Cheese Sandwich", 45, 19));
         products.add(new Product(8, "Chimichanggas", 70, 23));
         products.add(new Product(9, "Spaghetti", 70, 14));
         products.add(new Product(10, "Fries", 60, 17));
         products.add(new Product(11, "Cheesy Meaty Fries", 60, 22));
         products.add(new Product(12, "Nachos", 60, 18));
         products.add(new Product(13, "Siomai", 20, 15));
         products.add(new Product(14, "Siopao", 25, 21));
         products.add(new Product(15, "Kikiam", 20, 16));
         products.add(new Product(16, "Bottled Water", 10, 24));
         products.add(new Product(17, "Milktea", 70, 12));
         products.add(new Product(18, "Fruit Tea", 60, 20));
         products.add(new Product(19, "Lemon Iced Tea", 25, 14));
         products.add(new Product(20, "House Blend", 25, 19));
         products.add(new Product(21, "Red Iced Tea", 25, 16));
         products.add(new Product(22, "Calamansi Juice", 25, 23));
         products.add(new Product(23, "Cucumber Lime", 25, 17));
         products.add(new Product(24, "Pink Lemonade", 25, 13));
         products.add(new Product(25, "Blue Lemonade", 25, 20));
         products.add(new Product(26, "Coffee", 15, 15));
         products.add(new Product(27, "Mountain Dew", 20, 22));
         products.add(new Product(28, "Sotanghon Guisado", 150, 18));
         products.add(new Product(29, "Bihon Guisado", 150, 21));
         products.add(new Product(30, "Bam E Pansit", 150, 16));
         products.add(new Product(31, "Batchoy", 50, 19));       
          

        users = new String[10][2]; // Initialize the 2D array to store up to 10 users (each has 2 columns: username, password)
        users[0] = new String[]{null, null}; // Initialize the first user
    }
    
    private static void displayLoginMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Welcome to JADA'S CAFE");
                System.out.println("---------------------");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        registerUser(scanner);
                        break;
                    case 2:
                        loginUser(scanner);
                        break;
                    case 3:
                        System.out.println("Thank you for shopping with us. Goodbye!");
                        System.exit(0);;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        try {
            System.out.println("\nUser Registration");
            System.out.println("------------------");

            if (userCount < MAX_USERS) {
                System.out.print("Enter a username: ");
                String username = scanner.next();

                if (isUsernameTaken(username)) {
                    throw new IllegalArgumentException("\nOops! It looks like someone already has the username you entered. Please try a different one.\n");
                }

                System.out.print("Enter a password: ");
                String password = scanner.next();

                User newUser = new User(username, password);
                usersList.add(newUser);
                	userCount++;

                System.out.println("\nCongratulations, your registration was successful!\n");
            } else {
                throw new IllegalArgumentException("\nSorry, we've reached our user capacity. New user registrations are temporarily unavailable.\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.\n");
            scanner.nextLine(); // Consume invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void loginUser(Scanner scanner) {
        try {
            System.out.println("\nUser Login");
            System.out.println("-----------");
            System.out.print("Enter username: ");
            String username = scanner.next();
            System.out.print("Enter password: ");
            String password = scanner.next();

            for (User user : usersList) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    currentUsername = username; // Set the current logged-in username
                    printProductMenu();
                    return;
                }
            }

            System.out.println("The username or password you provided is incorrect. Try again or register first, please.\n");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.\n");
            scanner.nextLine(); // Consume invalid input
        }
    }



    private static boolean isUsernameTaken(String username) {
        for (User user : usersList) {
            if (user.getUsername().equals(username)) {
                return true; // Username is taken
            }
        }
        return false; // Username is available
    }



 // Method to retrieve the current logged-in username.
    private static String getCurrentUsername() {
       return currentUsername;
    }

    private static void addToCart(Scanner scanner) {
        try {
            System.out.print("Enter product number to add to cart: ");
            int productNumber = scanner.nextInt();

            Product product = getProductByNumber(products, productNumber);
            if (product == null) {
                throw new IllegalArgumentException("Product not found. Please enter a valid product number.\n");
            }

            System.out.print("Enter quantity to add to cart: ");
            int quantityToAdd = scanner.nextInt();

            if (quantityToAdd <= 0 || quantityToAdd > product.getStocks()) {
                throw new IllegalArgumentException("\nInvalid quantity. Please enter a positive integer within available stocks.\n");
            }

            cart.addToCart(product, quantityToAdd);
            product.updateStocks(-quantityToAdd);
            System.out.println("\nAdded to cart successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid positive integer.\n");
            scanner.nextLine(); // Consume invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeFromCart(Scanner scanner) {
        try {
            if (cart.getCart().isEmpty()) {
                System.out.println("Your cart is empty. There is nothing to remove.");
                return;
            }

            System.out.print("Enter product number to remove from cart: ");
            int productNumber = scanner.nextInt();

            Product product = getProductByNumber(products, productNumber);
            if (product == null) {
                throw new IllegalArgumentException("Product not found. Please enter a valid product number.");
            }

            System.out.print("Enter quantity to remove and return to stock: ");
            int quantityToRemove = scanner.nextInt();

            if (quantityToRemove <= 0 || quantityToRemove > cart.getCart().getOrDefault(productNumber, 0)) {
                throw new IllegalArgumentException("\nInvalid quantity. Not enough items in the cart to remove.");
            }

            cart.removeFromCart(product, quantityToRemove);
            product.updateStocks(quantityToRemove);
            System.out.println("\nRemoved from cart and returned to stock successfully.");
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    


    private static void viewInvoice() {
        if (cart.getCart().isEmpty()) {
            System.out.println("We're sorry but your cart is empty. No invoice generated.");
            return;
        }

        int totalCost = 0;

        System.out.println("\nInvoice:");
        System.out.printf("%-20s %-10s %-15s %-20s\n", "Product", "Quantity", "Price per unit", "Total Cost");
        System.out.println("-----------------------------------------------------------");

        for (Map.Entry<Integer, Integer> entry : cart.getCart().entrySet()) {
            int productNumber = entry.getKey();
            int quantity = entry.getValue();
            Product product = getProductByNumber(products, productNumber);

            if (product != null) {
                int itemCost = quantity * product.getPrice();
                totalCost += itemCost;

                System.out.printf("%-20s %-10d %-15s %-20s\n",
                        product.getProductName(), quantity,
                        "P" + product.getPrice(), "P" + itemCost);
            }
        }

        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-47s %-20s\n", "Total Cost:", "P" + totalCost);
    }


    private static void purchase() {
        if (cart.getCart().isEmpty()) {
            System.out.println("Your cart is currently empty. Let's add some items before making a purchase!");
            return;
        }

        try {
            System.out.println("\nThank you for your purchase!");
            viewInvoice(); // Display final invoice

            int totalCost = calculateTotalCost();
            addTransactionToHistory(totalCost);

            // Clear the cart after recording the transaction
            cart.clearCart();
        } catch (Exception e) {
            System.out.println("An error occurred during the purchase process: " + e.getMessage());
        }
    }


    private static int calculateTotalCost() {
        int totalCost = 0;

        for (Map.Entry<Integer, Integer> entry : cart.getCart().entrySet()) {
            int productNumber = entry.getKey();
            int quantity = entry.getValue();
            Product product = getProductByNumber(products, productNumber);

            if (product != null) {
                totalCost += quantity * product.getPrice();
            }
        }

        return totalCost;
    }

    private static void addTransactionToHistory(int totalAmount) {
        String username = getCurrentUsername(); // method to get the current logged-in username
        transaction.setUserTransaction(username);
        transaction.addTransaction(username, totalAmount);
    }

    private static void viewTransactionHistory() {
        String username = getCurrentUsername(); // get the current logged-in username
        transaction.displayTransactions(username);
    }

    private static Product getProductByNumber(List<Product> products, int productNumber) {
        for (Product product : products) {
            if (product.getProductNumber() == productNumber) {
                return product;
            }
        }
        return null;
    }
    

    private static void printProductMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nProduct Menu:");
        System.out.printf("%-15s %-30s %-10s %-10s\n", "Product Number", "Name", "Price", "Stocks");
        System.out.println("----------------------------------------------------------------");

        for (Product product : products) {
            System.out.printf("%-15s %-30s %-10s %-10s\n",
                    product.getProductNumber(),
                    product.getProductName(),
                    "P" + product.getPrice(),
                    product.getStocks());
        }
        System.out.println("----------------------------------------------------------------");
        
        while (true) {
            System.out.println("\n1. Add to Cart");
            System.out.println("2. Remove from Cart");
            System.out.println("3. View Cart");
            System.out.println("4. View Invoice");
            System.out.println("5. Purchase");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    addToCart(scanner);
                    break;
                case 2:
                    removeFromCart(scanner);
                    break;
                case 3:
                    cart.viewCart(products);
                    break;
                case 4:
                    viewInvoice();
                    break;
                case 5:
                    purchase();
                    break;
                case 6:
                	viewTransactionHistory();
                    break;                
                case 7:
                	System.out.println("Returning back to login menu.\n");
                    cart.restoreCartQuantities(products);
                    displayLoginMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            printProductMenu(); // Display product menu before the next iteration
        }
    }
}