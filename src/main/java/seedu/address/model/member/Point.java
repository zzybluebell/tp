package seedu.address.model.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Point in the ezFoodie which should be equivalent to credit,
 * and it can be redeem from a redemption process
 */
public class Point {

    public static final int MAX = 99999999;
    public static final String MESSAGE_CONSTRAINTS =
            "Points should only contain no more than 8 digits and it should not be blank, and max point is " + MAX;
    public static final String TRIM_LEADING_ZERO_REGEX = "^0+(?!$)";
    public static final String VALIDATION_REGEX = "[\\p{Digit}]*";
    public static final int LENGTH = 8; // Max point is 99999999

    public final String value;

    /**
     * Constructs a {@code Point}.
     *
     * @param point A valid point.
     */
    public Point(String point) {
        requireNonNull(point);
        checkArgument(isValidPoint(point), MESSAGE_CONSTRAINTS);
        value = point;
    }

    /**
     * Returns true if a given string is a valid point.
     */
    public static boolean isValidPoint(String test) {
        test = test.replaceFirst(TRIM_LEADING_ZERO_REGEX, "");
        try {
            return test.length() <= LENGTH && Integer.parseInt(test) <= MAX && test.matches(VALIDATION_REGEX);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns int value of point.
     */
    public int getIntValue() {
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Point // instanceof handles nulls
                && value.equals(((Point) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns double value of transaction amount.
     */
    public double getDoubleValue() {
        return Double.parseDouble(value);
    }
}
