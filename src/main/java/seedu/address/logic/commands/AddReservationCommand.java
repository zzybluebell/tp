package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION_DATE;

import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.member.Id;
import seedu.address.model.reservation.Reservation;

/**
 * Adds a reservation to the ezFoodie.
 */
public class AddReservationCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a reservation to the ezFoodie. "
            + "Parameters: "
            + "[" + PREFIX_RESERVATION + " " + "RESERVATION"
            + " " + PREFIX_ID + " " + "ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_RESERVATION + " " + "12-01-2021"
            + PREFIX_ID + " " + "00001";

    public static final String MESSAGE_SUCCESS = "New Reservation added.";

    private final Set<Reservation> reservationToAdd;
    private final Id idToAdd;

    /**
     * Creates addCommand to add the specific {@code Member}
     *
     * @param reservation The set of reservation to be added
     * @param id The member id to add the reservation to
     */
    public AddReservationCommand(Set<Reservation> reservation, Id id) {
        requireNonNull(id);
        reservationToAdd = reservation;
        idToAdd = id;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.addReservation(reservationToAdd, idToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddReservationCommand // instanceof handles nulls
                && reservationToAdd.equals(((AddReservationCommand) other).reservationToAdd))
                && idToAdd.equals(((AddReservationCommand) other).idToAdd);
    }
}
