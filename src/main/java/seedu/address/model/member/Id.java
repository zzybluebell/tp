package seedu.address.model.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Member's id in the ezFoodie.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    public static final int MAX = 99999;
    public static final String MESSAGE_CONSTRAINTS =
            "Member IDs should only contain 5 digits and it should not be blank, and max ID is " + MAX;
    public static final String TRIM_LEADING_ZERO_REGEX = "^0+(?!$)";
    public static final String VALIDATION_REGEX = "[\\p{Digit}]*";
    public static final String PATTERN = "%05d";
    public static final int LENGTH = 5; // Max ID is 99999

    public final String value;

    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid id.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = String.format(PATTERN, Long.parseLong(id));
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidId(String test) {
        test = test.replaceFirst(TRIM_LEADING_ZERO_REGEX, "");
        try {
            return test.length() <= LENGTH && Long.parseLong(test) <= MAX && test.matches(VALIDATION_REGEX);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns long value of id.
     */
    public Long getLongValue() {
        return Long.parseLong(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && value.equals(((Id) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
