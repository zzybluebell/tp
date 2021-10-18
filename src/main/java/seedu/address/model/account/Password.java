package seedu.address.model.account;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a password for manager to login.
 * Guarantees: immutable; is valid as declared in {@link #isValidPassword(String)}
 */
public class Password {

    public static final String DEFAULT_PLAINTEXT_PASSWORD = "123456";
    public static final String MESSAGE_CONSTRAINTS =
            "Passwords should only contain 32 alphanumeric characters or empty, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}]*";
    public static final int LENGTH = 32;

    public final String value;

    /**
     * Constructs a {@code Password}.
     *
     * @param password A valid password.
     */
    public Password(String password) {
        requireNonNull(password);
        checkArgument(isValidPassword(password), MESSAGE_CONSTRAINTS);
        value = password;
    }

    /**
     * Returns true if a given string is a valid password.
     */
    public static boolean isValidPassword(String test) {
        return test.isEmpty() || (test.matches(VALIDATION_REGEX) && test.length() == LENGTH);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Password // instanceof handles nulls
                && value.equals(((Password) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}