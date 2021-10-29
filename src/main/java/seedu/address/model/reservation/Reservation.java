package seedu.address.model.reservation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.util.DateTimeUtil;

/**
 * Represents a Reservation in the member.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reservation {

    private final Id id;
    private final DateTime dateTime;
    private final Remark remark;

    /**
     * Every field must be present and not null.
     */
    public Reservation(Id id, DateTime dateTime, Remark remark) {
        requireAllNonNull(id, dateTime, remark);
        this.id = id;
        this.dateTime = dateTime;
        this.remark = remark;
    }

    public Id getId() {
        return id;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns true if both reservations have the same date.
     * This defines a stronger notion of equality between two transactions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Reservation)) {
            return false;
        }

        Reservation otherReservation = (Reservation) other;
        LocalDate otherDate = DateTimeUtil.parseDateTime(otherReservation.getDateTime().value).toLocalDate();
        LocalDate date = DateTimeUtil.parseDateTime(getDateTime().value).toLocalDate();
        return otherDate.isEqual(date);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(dateTime, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Id: ")
                .append(getId())
                .append("DateTime: ")
                .append(getDateTime())
                .append("; Remark: ")
                .append(getRemark());

        return builder.toString();
    }

}
