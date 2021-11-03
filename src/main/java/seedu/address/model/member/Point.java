package seedu.address.model.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Point in the ezFoodie which should be equivalent to credit,
 * and it can be redeem from a redemption process
 */
public class Point {
    /**
     * Stands for message constraints for point.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Points should only contain digits, and it should not be blank";

    /**
     * Stands for validation regex.
     */
    public static final String VALIDATION_REGEX = "[\\p{Digit}]*";

    /**
     * Stands for point max value.
     */
    public static final int MAX = 99999999;

    /**
     * Stands for point max length
     */
    public static final int LENGTH = 8; // Max point is 99999999

    /**
     * Stands for point value
     */
    public final String value;

    /**
     * Constructs a {@code Point}.
     *
     * @param point a valid point
     */
    public Point(String point) {
        requireNonNull(point);
        checkArgument(isValidPoint(point), MESSAGE_CONSTRAINTS);
        value = point;
    }

    /**
     * Returns true if a given string is a valid point.
     *
     * @param test
     * @return boolean
     */
    public static boolean isValidPoint(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= LENGTH;
    }

    /**
     * Returns int value of point.
     *
     * @return int
     */
    public int getIntValue() {
        return Integer.parseInt(value);
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
                || (other instanceof Point // instanceof handles nulls
                && value.equals(((Point) other).value)); // state check
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

    /**
     * Returns double value of transaction amount.
     *
     * @return double
     */
    public double getDoubleValue() {
        return Double.parseDouble(value);
    }
}
