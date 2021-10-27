package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.Timestamp;

/**
 * Represents a Transaction in the member.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Transaction {

    private final Timestamp timestamp;
    private final Billing billing;

    /**
     * Every field must be present and not null.
     */
    public Transaction(Timestamp timestamp, Billing billing) {
        requireAllNonNull(timestamp, billing);
        this.timestamp = timestamp;
        this.billing = billing;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Billing getBilling() {
        return billing;
    }

    /**
     * Returns true if both transactions have the same timestamp and billing.
     * This defines a stronger notion of equality between two transactions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Transaction)) {
            return false;
        }

        Transaction otherTransaction = (Transaction) other;
        return otherTransaction.getTimestamp().equals(getTimestamp())
                && otherTransaction.getBilling().equals(getBilling());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(timestamp, billing);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Timestamp: ")
                .append(getTimestamp())
                .append("; Billing: ")
                .append(getBilling());

        return builder.toString();
    }

}
