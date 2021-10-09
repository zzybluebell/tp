package seedu.address.model.person;

//TODO: complete this similarly to other Person attributes
public class Transaction {
    private int amount;

    public Transaction(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int newAmount) {
        amount = newAmount;
    }
}
