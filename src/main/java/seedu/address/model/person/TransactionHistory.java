package seedu.address.model.person;

import java.util.ArrayList;

//TODO: complete this similarly to other Person attributes
public class TransactionHistory {
    private ArrayList<Transaction> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
