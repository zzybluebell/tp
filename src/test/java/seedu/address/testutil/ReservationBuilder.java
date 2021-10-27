package seedu.address.testutil;

import seedu.address.model.reservation.DateTime;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;

/**
 * A utility class to help with building Reservation objects.
 */
public class ReservationBuilder {

    public static final String DEFAULT_DATE_TIME = "2021-01-20 10:00";
    public static final String DEFAULT_REMARK = "3 people";

    private DateTime dateTime;
    private Remark remark;

    /**
     * Creates a {@code ReservationBuilder} with the default details.
     */
    public ReservationBuilder() {
        dateTime = new DateTime(DEFAULT_DATE_TIME);
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the ReservationBuilder with the data of {@code reservationToCopy}.
     */
    public ReservationBuilder(Reservation reservationToCopy) {
        dateTime = reservationToCopy.getDateTime();
        remark = reservationToCopy.getRemark();
    }

    /**
     * Sets the {@code DateTime} of the {@code Reservation} that we are building.
     */
    public ReservationBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Reservation} that we are building.
     */
    public ReservationBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Reservation build() {
        return new Reservation(dateTime, remark);
    }

}
