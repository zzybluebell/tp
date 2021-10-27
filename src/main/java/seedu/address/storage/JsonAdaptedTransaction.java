package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Timestamp;
import seedu.address.model.transaction.Billing;
import seedu.address.model.transaction.Transaction;

/**
 * Jackson-friendly version of {@link Transaction}.
 */
class JsonAdaptedTransaction {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Transaction's %s field is missing!";

    private final String timestamp;
    private final String billing;

    /**
     * Constructs a {@code JsonAdaptedTransaction} with the given transaction details.
     */
    @JsonCreator
    public JsonAdaptedTransaction(@JsonProperty("timestamp") String timestamp,
            @JsonProperty("billing") String billing) {

        this.timestamp = timestamp;
        this.billing = billing;
    }

    /**
     * Converts a given {@code Transaction} into this class for Jackson use.
     */
    public JsonAdaptedTransaction(Transaction source) {
        timestamp = source.getTimestamp().value;
        billing = source.getBilling().value;
    }

    /**
     * Converts this Jackson-friendly adapted transaction object into the model's {@code Transaction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted transaction.
     */
    public Transaction toModelType() throws IllegalValueException {
        if (timestamp == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Timestamp.class.getSimpleName()));
        }
        final Timestamp modelTimestamp = new Timestamp(timestamp);

        if (billing == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Billing.class.getSimpleName()));
        }
        final Billing modelBilling = new Billing(billing);

        return new Transaction(modelTimestamp, modelBilling);
    }

}
