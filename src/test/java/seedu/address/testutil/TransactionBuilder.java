package seedu.address.testutil;

import seedu.address.model.Timestamp;
import seedu.address.model.transaction.Billing;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class to help with building Transaction objects.
 */
public class TransactionBuilder {

    public static final String DEFAULT_TIMESTAMP = "1611136800000";
    public static final String DEFAULT_BILLING = "345.67";

    private Timestamp timestamp;
    private Billing billing;

    /**
     * Creates a {@code TransactionBuilder} with the default details.
     */
    public TransactionBuilder() {
        timestamp = new Timestamp(DEFAULT_TIMESTAMP);
        billing = new Billing(DEFAULT_BILLING);
    }

    /**
     * Initializes the TransactionBuilder with the data of {@code transactionToCopy}.
     */
    public TransactionBuilder(Transaction transactionToCopy) {
        timestamp = transactionToCopy.getTimestamp();
        billing = transactionToCopy.getBilling();
    }

    /**
     * Sets the {@code Timestamp} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withTimestamp(String timestamp) {
        this.timestamp = new Timestamp(timestamp);
        return this;
    }

    /**
     * Sets the {@code Billing} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withBilling(String billing) {
        this.billing = new Billing(billing);
        return this;
    }

    public Transaction build() {
        return new Transaction(timestamp, billing);
    }

}
