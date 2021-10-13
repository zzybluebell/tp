package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Member's registration timestamp in the ezFoodie.
 * Guarantees: immutable; is valid as declared in {@link #isValidRegistrationTimestamp(String)}
 */
public class RegistrationTimestamp {

    public static final String MESSAGE_CONSTRAINTS =
            "RegistrationTimestamps should only contain digits and can be parsed to long, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Digit}]*";

    public final String value;

    /**
     * Constructs a {@code RegistrationTimestamp}.
     */
    public RegistrationTimestamp(String registrationTimestamp) {
        requireNonNull(registrationTimestamp);
        checkArgument(isValidRegistrationTimestamp(registrationTimestamp), MESSAGE_CONSTRAINTS);
        value = registrationTimestamp;
    }

    /**
     * Returns true if a given string is a valid timestamp.
     */
    public static boolean isValidRegistrationTimestamp(String test) {
        try {
            Long.parseLong(test);
            return test.matches(VALIDATION_REGEX);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RegistrationTimestamp // instanceof handles nulls
                && value.equals(((RegistrationTimestamp) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
