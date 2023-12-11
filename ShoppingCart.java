package trial;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;

class ShoppingCart {
    private Map<Integer, Integer> cart;

    public ShoppingCart() {
        this.cart = new HashMap<>();
    }

    public Map<Integer, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, Integer> cart) {
        this.cart = cart;
    }

    public void addToCart(Product product, int quantity) {
        cart.put(product.getProductNumber(), cart.getOrDefault(product.getProductNumber(), 0) + quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        if (cart.containsKey(product.getProductNumber())) {
            int currentQuantity = cart.get(product.getProductNumber());
            if (currentQuantity <= quantity) {
                cart.remove(product.getProductNumber());
            } else {
                cart.put(product.getProductNumber(), currentQuantity - quantity);
            }
        }
    }

    public void viewCart(List<Product> products) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add something to the cart first.");
            return;
        }

        Map<Integer, Integer> sortedByKey = sortByKeyUsingBubbleSort(cart);

        System.out.println("\nCart Contents:");
        System.out.printf("%-15s %-25s %-10s\n", "Product Number", "Product Name", "Quantity");
        System.out.println("--------------------------------------------------");

        for (Map.Entry<Integer, Integer> entry : sortedByKey.entrySet()) {
            int productNumber = entry.getKey();
            int quantity = entry.getValue();

            Product product = getProductByNumber(products, productNumber);
            if (product != null) {
                System.out.printf("%-15d %-25s %-10d\n", product.getProductNumber(), product.getProductName(), quantity);
            }
        }
    }

    
    private Product getProductByNumber(List<Product> products, int productNumber) {
        for (Product product : products) {
            if (product.getProductNumber() == productNumber) {
                return product;
            }
        }
        return null;
    }

    public static Map<Integer, Integer> sortByKeyUsingBubbleSort(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getKey() > list.get(j + 1).getKey()) {
                    // Swap list[j] and list[j+1]
                    Map.Entry<Integer, Integer> temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }

        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }


	public void clearCart() {
		cart.clear();
		
	}
	
    public void restoreCartQuantities(List<Product> products) {
        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            int productNumber = entry.getKey();
            int quantityInCart = entry.getValue();

            Product product = getProductByNumber(products, productNumber);
            if (product != null) {
                product.updateStocks(quantityInCart); // Restore quantity to product stocks
            }
        }
        clearCart(); // Clear the cart after updating product stocks
    }
}