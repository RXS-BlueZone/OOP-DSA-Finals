package trial;

import java.util.*;
import java.util.LinkedList;

class User {
    private String username;
    private String password;
    private List<Transaction> transactionHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.transactionHistory = new LinkedList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
}