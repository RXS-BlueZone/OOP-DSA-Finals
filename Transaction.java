package trial;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Transaction {
    private Map<String, List<String>> transactions = new HashMap<>();


    public void setUserTransaction(String username) {
        transactions.putIfAbsent(username, new LinkedList<>());
    }

    public void addTransaction(String username, int totalAmount) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String transactionMessage = String.format( " " + username + "  | " +  dateFormat.format(new Date()) + "|  P" + totalAmount);

        
        transactions.get(username).add(transactionMessage);
    }

    public static void showTransactionLabel() {
    	System.out.print("\nTRANSACTION HISTORY:  " + "\n User Id |  Transaction Date  |  Amount"+"\n");
    }
    
    public void displayTransactions(String username) {
        List<String> transactionToDisplay = transactions.get(username);

        if (transactionToDisplay == null || transactionToDisplay.isEmpty()) {
            System.out.println("No transactions found for user: " + username);
        } else {
        	showTransactionLabel();
            for (String transaction : transactionToDisplay) {
                System.out.println(transaction);
            }
        }
    }
}