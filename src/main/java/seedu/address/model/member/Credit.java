package seedu.address.model.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Member's id in the ezFoodie.
 * Guarantees: immutable; is valid as declared in {@link #isValidCredit(String)}
 */
public class Credit {

    public static final String MESSAGE_CONSTRAINTS =
            "Credits should only contain digits, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Digit}]*";
    public static final int MAX = 99999999;
    public static final int LENGTH = 8; // Max credit is 99999999

    public final String value;

    /**
     * Constructs a {@code Credit}.
     *
     * @param credit A valid credit.
     */
    public Credit(String credit) {
        requireNonNull(credit);
        checkArgument(isValidCredit(credit), MESSAGE_CONSTRAINTS);
        value = credit;
    }

    /**
     * Returns true if a given string is a valid credit.
     */
    public static boolean isValidCredit(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= LENGTH;
    }

    /**
     * Returns int value of credit.
     */
    public int getIntValue() {
        return Integer.parseInt(value);
    }

    /**
     * Returns String value of credit for Point use.
     */
    public String getStringValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Credit // instanceof handles nulls
                && value.equals(((Credit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
