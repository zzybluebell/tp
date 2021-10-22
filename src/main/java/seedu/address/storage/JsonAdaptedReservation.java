package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.reservation.Reservation;

/**
 * Jackson-friendly version of {@link Reservation}.
 */
class JsonAdaptedReservation {

    private final String reservationDate;

    /**
     * Constructs a {@code JsonAdaptedTransaction} with the given {@code transactionAmount}.
     */
    @JsonCreator
    public JsonAdaptedReservation(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    /**
     * Converts a given {@code Transaction} into this class for Jackson use.
     */
    public JsonAdaptedReservation(Reservation source) {
        reservationDate = source.getReservationDate();
    }

    @JsonValue
    public String getReservationDate() {
        return reservationDate;
    }

    /**
     * Converts this Jackson-friendly adapted transaction object into the model's {@code Transaction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted transaction.
     */
    public Reservation toModelType() throws IllegalValueException {
//        if (!Reservation.isValidReservationDate(reservationDate)) {
//            throw new IllegalValueException(Reservation.MESSAGE_CONSTRAINTS);
//        }
        return new Reservation(reservationDate);
    }

}
