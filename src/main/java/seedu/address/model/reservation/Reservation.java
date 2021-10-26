package seedu.address.model.reservation;

/**
 * Represents a Transaction in the ezFoodie.
 * TODO: Change String to DateTime format
 */
public class Reservation {

    private final String dateTime;

    public Reservation(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getReservationDate() {
        return dateTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reservation // instanceof handles nulls
                && dateTime.equals(((Reservation) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + dateTime + ']';
    }
}
