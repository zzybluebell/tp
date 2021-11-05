package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Calendar;
import java.util.Objects;

import seedu.address.model.Timestamp;

/**
 * Represents a Transaction in the member.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Transaction {

    private final Id id;
    private final Timestamp timestamp;
    private final Billing billing;

    /**
     * Every field must be present and not null.
     */
    public Transaction(Id id, Timestamp timestamp, Billing billing) {
        requireAllNonNull(id, timestamp, billing);
        this.id = id;
        this.timestamp = timestamp;
        this.billing = billing;
    }

    public Id getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Billing getBilling() {
        return billing;
    }

    /**
     * Returns true if both transactions have the same id.
     * This defines a weaker notion of equality between two transactions.
     */
    public boolean isSameId(Transaction otherTransaction) {
        if (otherTransaction == this) {
            return true;
        }

        return otherTransaction != null
                && otherTransaction.getId().equals(getId());
    }

    /**
     * Returns true if a given timestamp is within {@code numOfPastMonths}.
     */
    public static boolean isWithinPastMonths(long timestamp, int numOfPastMonths) {
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(System.currentTimeMillis());
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int currentMonth = currentCalendar.get(Calendar.MONTH);

        Calendar calendarToCheck = Calendar.getInstance();
        calendarToCheck.setTimeInMillis(timestamp);
        int yearToCheck = calendarToCheck.get(Calendar.YEAR);
        int monthToCheck = calendarToCheck.get(Calendar.MONTH);

        int diffYears = currentYear - yearToCheck;
        int diffMonths = diffYears * 12 + currentMonth - monthToCheck;

        return diffMonths <= numOfPastMonths;
    }

    /**
     * Returns true if both transactions have the same timestamp and billing.
     * This defines a stronger notion of equality between two transactions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Transaction)) {
            return false;
        }

        Transaction otherTransaction = (Transaction) other;
        return otherTransaction.getId().equals(getId())
                && otherTransaction.getTimestamp().equals(getTimestamp())
                && otherTransaction.getBilling().equals(getBilling());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(timestamp, billing);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Id: ")
                .append(getId())
                .append("; Timestamp: ")
                .append(getTimestamp())
                .append("; Billing: ")
                .append(getBilling());

        return builder.toString();
    }

}
