package seedu.address.model.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Member's id in the ezFoodie.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    /**
     * Stands for Id message constraints.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Ids should only contain 5 digits, and it should not be blank";

    /**
     * Stands for validation regex of Id.
     */
    public static final String VALIDATION_REGEX = "[\\p{Digit}]*";

    /**
     * Stands for pattern of Id.
     */
    public static final String PATTERN = "%05d";

    /**
     * Stands for length of Id is five.
     */
    public static final int LENGTH = 5;

    /**
     * Stands Id value.
     */
    public final String value;

    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid id.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if a given string is a valid id.
     *
     * @param test
     * @return boolean
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() == LENGTH;
    }

    /**
     * Overrides toString method.
     *
     * @return String
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Overrides equals method.
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && value.equals(((Id) other).value)); // state check
    }

    /**
     * Overrides hashCode method.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
