package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's member ID in the ezFoodie.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS =
            "IDs should only contain numbers, and it should be 4 digits.";
    public static final String VALIDATION_REGEX = "\\d{4}";

    public final String memberId;

    /**
     * Constructs a {@code Id}.
     *
     * @param memberId A valid phone number.
     */
    public Id(String memberId) {
        requireNonNull(memberId);
        checkArgument(isValidId(memberId), MESSAGE_CONSTRAINTS);
        this.memberId = memberId;

    }

    /**
     * Returns true if a given string is a valid member ID.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && memberId.equals(((Id) other).memberId)); // state check
    }

    @Override
    public int hashCode() {
        return memberId.hashCode();
    }
}
