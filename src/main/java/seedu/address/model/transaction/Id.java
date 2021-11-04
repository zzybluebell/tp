package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Transaction's id in the ezFoodie.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    /**
     * Stands for message constraints of transaction Id.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Transactions should only contain 6 digits, and it should not be blank";

    /**
     * Stands for validation regex of transaction Id.
     */
    public static final String VALIDATION_REGEX = "[\\p{Digit}]*";

    /**
     * Stands for transaction Id max length
     */
    public static final int LENGTH = 6;

    /**
     * Stands for transaction Id value.
     */
    public final String value;

    /**
     * Constructs a {@code Id}.
     *
     * @param id a valid id.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns whether is valid id.
     *
     * @param test input string test
     * @return boolean true if a given string is a valid id.
     */

    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() == LENGTH;
    }

    /**
     * Overrides the toString method.
     *
     * @return transaction billing value.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Overrides equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && value.equals(((Id) other).value)); // state check
    }

    /**
     * Overrides hashCode method.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
