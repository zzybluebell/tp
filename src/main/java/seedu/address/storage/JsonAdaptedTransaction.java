package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.transaction.Transaction;

/**
 * Jackson-friendly version of {@link Transaction}.
 */
class JsonAdaptedTransaction {

    private final String transactionAmount;

    /**
     * Constructs a {@code JsonAdaptedTransaction} with the given {@code transactionAmount}.
     */
    @JsonCreator
    public JsonAdaptedTransaction(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * Converts a given {@code Transaction} into this class for Jackson use.
     */
    public JsonAdaptedTransaction(Transaction source) {
        transactionAmount = source.transactionAmount;
    }

    @JsonValue
    public String getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Converts this Jackson-friendly adapted transaction object into the model's {@code Transaction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted transaction.
     */
    public Transaction toModelType() throws IllegalValueException {
        if (!Transaction.isValidTransactionAmount(transactionAmount)) {
            throw new IllegalValueException(Transaction.MESSAGE_CONSTRAINTS);
        }
        return new Transaction(transactionAmount);
    }

}
