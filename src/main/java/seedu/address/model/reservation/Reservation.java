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
     * Constructs a {@code Reservation},
     * and every field must be present and not null.
     *
     * @param id member Id
     * @param dateTime Reservation date time
     * @param remark reservation remark
     */
    public Reservation(Id id, DateTime dateTime, Remark remark) {
        requireAllNonNull(id, dateTime, remark);
        this.id = id;
        this.dateTime = dateTime;
        this.remark = remark;
    }

    /**
     * Gets reservation id.
     *
     * @return id the reservation member id
     */
    public Id getId() {
        return id;
    }

    /**
     * Gets reservation's date time.
     *
     * @return DateTime of reservation.
     */
    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Gets reservation's remark.
     *
     * @return Remark of reservation.
     */
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

    /**
     * Overrides hashCode for custom fields hashing instead of implementing your own.
     */
    @Override
    public int hashCode() {
        return Objects.hash(dateTime, remark);
    }

    /**
     * Overrides toString method.
     *
     * @return String of reservation's information including Id, DateTime and remark.
     */
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
