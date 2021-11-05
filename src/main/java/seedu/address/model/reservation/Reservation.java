package seedu.address.model.reservation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.util.DateTimeUtil;

/**
 * Represents a Reservation in the member.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reservation {

    public static final String MESSAGE_CONSTRAINTS =
            "The given reservation data time should be after current date time.";

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
     * Returns true if both reservations have the same id.
     * This defines a weaker notion of equality between two reservations.
     */
    public boolean isSameId(Reservation otherReservation) {
        if (otherReservation == this) {
            return true;
        }

        return otherReservation != null
                && otherReservation.getId().equals(getId());
    }

    /**
     * Returns true if both reservations have the same date.
     * This defines a weaker notion of equality between two reservations.
     */
    public boolean isSameDate(Reservation otherReservation) {
        if (otherReservation == this) {
            return true;
        }

        LocalDate otherDate = DateTimeUtil.parseDateTime(otherReservation.getDateTime().value).toLocalDate();
        LocalDate date = DateTimeUtil.parseDateTime(getDateTime().value).toLocalDate();
        return otherDate.isEqual(date);
    }

    /**
     * Returns true if the reservation have the same date with the other date time.
     */
    public boolean isSameDate(LocalDateTime otherDateTime) {

        LocalDate otherDate = otherDateTime.toLocalDate();
        LocalDate date = DateTimeUtil.parseDateTime(getDateTime().value).toLocalDate();
        return otherDate.isEqual(date);
    }

    /**
     * Returns true if a given data time is after current date time.
     */
    public static boolean isValidDateTime(DateTime dateTime) {
        LocalDateTime otherDateTime = DateTimeUtil.parseDateTime(dateTime.value);
        LocalDateTime nowDateTime = LocalDateTime.now();
        return otherDateTime.isAfter(nowDateTime);
    }

    /**
     * Returns true if both reservations have the same date time and remark.
     * This defines a stronger notion of equality between two reservations.
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
        return otherReservation.getId().equals(getId())
                && otherReservation.getDateTime().equals(getDateTime())
                && otherReservation.getRemark().equals(getRemark());
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
                .append("; DateTime: ")
                .append(getDateTime())
                .append("; Remark: ")
                .append(getRemark());

        return builder.toString();
    }

}
