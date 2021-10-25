package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Transaction in the ezFoodie.
 * Guarantees: immutable; amount is valid as declared in {@link #isValidTransactionAmount(String)}
 */
public class Transaction {

    public static final String MESSAGE_CONSTRAINTS =
            "Transactions amounts should be numeric with 2 decimal places";
    public static final String VALIDATION_REGEX = "\\d*\\.\\d{2}$";
    public static final int LENGTH = 7; // Max amount is 9999.99

    private final String transactionAmount;
    //private Timestamp timestamp;
    //todo: Add in transaction_id soon.

    /**
     * Constructs a {@code Transaction}.
     *
     * @param transactionAmount A valid transaction amount.
     */
    public Transaction(String transactionAmount) {
        requireNonNull(transactionAmount);
        checkArgument(isValidTransactionAmount(transactionAmount), MESSAGE_CONSTRAINTS);
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Returns true if a given string is a valid transaction amount.
     */
    public static boolean isValidTransactionAmount(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= LENGTH;
    }

    /**
     * Returns double value of transaction amount.
     */
    public double getDoubleValue() {
        return Double.parseDouble(transactionAmount);
    }

    //todo: can use the id to generate hashcode
    @Override
    public int hashCode() {
        return transactionAmount.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Transaction // instanceof handles nulls
                && transactionAmount.equals(((Transaction) other).transactionAmount)); // state check
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + transactionAmount + ']';
    }

}
