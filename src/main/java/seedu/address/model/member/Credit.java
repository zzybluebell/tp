package seedu.address.model.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Member's id in the ezFoodie.
 * Guarantees: immutable; is valid as declared in {@link #isValidCredit(String)}
 */
public class Credit {

    /**
     * Stands for credits message constraints.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Credits should only contain digits, and it should not be blank";

    /**
     * Stands for validation regex.
     */
    public static final String VALIDATION_REGEX = "[\\p{Digit}]*";

    /**
     * Stands for the max credit number.
     */
    public static final int MAX = 99999999;

    /**
     * Stands for the length of credit.
     */
    public static final int LENGTH = 8; // Max credit is 99999999

    /**
     * Stands for the credit value.
     */
    public final String value;

    /**
     * Constructs a {@code Credit}.
     *
     * @param credit a valid credit.
     */
    public Credit(String credit) {
        requireNonNull(credit);
        checkArgument(isValidCredit(credit), MESSAGE_CONSTRAINTS);
        value = credit;
    }

    /**
     * Returns whether is a valid credit.
     *
     * @param test input String test
     * @return boolean true if a given string is a valid credit.
     */
    public static boolean isValidCredit(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= LENGTH;
    }

    /**
     * Gets int value of credit.
     */
    public int getIntValue() {
        return Integer.parseInt(value);
    }

    /**
     * Gets String value of credit for Point use.
     *
     * @return String of credit value.
     */
    public String getStringValue() {
        return value;
    }

    /**
     * Overrides toString method.
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
                || (other instanceof Credit // instanceof handles nulls
                && value.equals(((Credit) other).value)); // state check
    }

    /**
     * Overrides hashCode method.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
