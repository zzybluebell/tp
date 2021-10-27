package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Transaction's billing in the Transaction.
 * Guarantees: immutable; is valid as declared in {@link #isValidBilling(String)}
 */
public class Billing {

    public static final String MESSAGE_CONSTRAINTS =
            "Billings should be numeric with 2 decimal places";
    public static final String VALIDATION_REGEX = "\\d*\\.\\d{2}$";
    public static final int LENGTH = 7; // Max amount is 9999.99

    public final String value;

    /**
     * Constructs a {@code Billing}.
     *
     * @param billing A valid billing amount.
     */
    public Billing(String billing) {
        requireNonNull(billing);
        checkArgument(isValidBilling(billing), MESSAGE_CONSTRAINTS);
        value = billing;
    }

    /**
     * Returns true if a given string is a valid billing amount.
     */
    public static boolean isValidBilling(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= LENGTH;
    }

    /**
     * Returns double value of billing amount.
     */
    public double getDoubleValue() {
        return Double.parseDouble(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Billing // instanceof handles nulls
                && value.equals(((Billing) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
