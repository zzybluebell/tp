package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Timestamp;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.ReservationId;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Deletes a reservation identified by it's member ID and reservation ID from the ezFoodie.
 */
public class DeleteReservationCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "del";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the reservation identified by the member ID and reservation ID.\n"
            + "Parameters:\n"
            + "Delete by member ID and reservation ID: "
            + PREFIX_RESERVATION + " [" + PREFIX_ID + " member ID + reservation ID]\n"
            + "Example:\n"
            + "Delete by member ID and reservation ID: "
            + COMMAND_WORD + " " + PREFIX_RESERVATION + " " + PREFIX_ID + " 10001100001";

    public static final String MESSAGE_SUCCESS = "Deleted reservation: %1$s";

    private final Id memberId;
    private final ReservationId reservationId;

    /**
     * Creates an DeleteCommand to delete the specified {@code Member} by member ID and transaction ID
     */
    public DeleteReservationCommand(Id memberId, ReservationId reservationId) {
        requireAllNonNull(memberId, reservationId);
        this.memberId = memberId;
        this.reservationId = reservationId;
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit}
     */
    private static Member createUpdatedReservation(Member memberToEdit, Reservation reservation) {
        assert memberToEdit != null;

        Id id = memberToEdit.getId();
        Name updatedName = memberToEdit.getName();
        Phone updatedPhone = memberToEdit.getPhone();
        Email updatedEmail = memberToEdit.getEmail();
        Address updatedAddress = memberToEdit.getAddress();
        Timestamp timestamp = memberToEdit.getTimestamp();
        Point point = memberToEdit.getPoint();
        Credit credit = memberToEdit.getCredit();
        List<Transaction> transactions = memberToEdit.getTransactions();
        List<Reservation> reservations = memberToEdit.getReservations();
        Set<Tag> updatedTags = memberToEdit.getTags();

        List<Reservation> updatedReservations = new ArrayList<>(reservations);
        updatedReservations.remove(reservation);

        return new Member(id, updatedName, updatedPhone, updatedEmail, updatedAddress, timestamp, credit, point,
                transactions, reservations, updatedTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();
        Member memberToEdit = lastShownList.stream()
                .filter(member -> memberId.equals(member.getId())).findAny().orElse(null);
        if (memberToEdit != null) {
            Reservation reservationToDelete = memberToEdit.getReservations().stream()
                    .filter(reservation -> reservationId.equals(reservation.getId())).findAny().orElse(null);
            if (reservationToDelete != null) {
                Member editedMember = createUpdatedReservation(memberToEdit, reservationToDelete);
                model.setMember(memberToEdit, editedMember);
                model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
                return new CommandResult(String.format(MESSAGE_SUCCESS, editedMember));
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_RESERVATION_DISPLAYED_ID);
            }
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteReservationCommand // instanceof handles nulls
                && memberId.equals(((DeleteReservationCommand) other).memberId)
                && reservationId.equals(((DeleteReservationCommand) other).reservationId)); // state check
    }
}
