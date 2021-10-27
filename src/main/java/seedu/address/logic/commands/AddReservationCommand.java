package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.DateTimeUtil;
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
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Adds a reservation to the ezFoodie.
 */
public class AddReservationCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds reservation to member "
            + "by member ID in the ezFoodie. "
            + "Parameters: "
            + PREFIX_RESERVATION + " "
            + PREFIX_DATE_TIME + " " + "DATE_TIME (" + DateTimeUtil.DATE_TIME_PATTERN + ") "
            + PREFIX_REMARK + " " + "REMARK "
            + PREFIX_ID + " " + "ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_RESERVATION + " "
            + PREFIX_DATE_TIME + " " + "2021-12-01 13:00 "
            + PREFIX_REMARK + " " + "2 people "
            + PREFIX_ID + " " + "10001";

    public static final String MESSAGE_SUCCESS = "New reservation added: %1$s";

    private final Reservation reservationToAdd;
    private final Id idToAdd;

    /**
     * Creates an AddReservationCommand to add the specified {@code Member}
     */
    public AddReservationCommand(Reservation reservation, Id id) {
        requireNonNull(id);
        reservationToAdd = reservation;
        idToAdd = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();
        Member memberToEdit = lastShownList.stream()
                .filter(member -> idToAdd.equals(member.getId())).findAny().orElse(null);
        if (memberToEdit != null) {
            Member editedMember = createUpdatedReservations(memberToEdit, reservationToAdd);
            model.setMember(memberToEdit, editedMember);
            model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, editedMember));
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit}
     */
    private static Member createUpdatedReservations(Member memberToEdit, Reservation reservation) {
        assert memberToEdit != null;

        Id id = memberToEdit.getId();
        Name name = memberToEdit.getName();
        Phone phone = memberToEdit.getPhone();
        Email email = memberToEdit.getEmail();
        Address address = memberToEdit.getAddress();
        Timestamp timestamp = memberToEdit.getTimestamp();
        Credit credit = memberToEdit.getCredit();
        List<Transaction> transactions = memberToEdit.getTransactions();
        Set<Reservation> reservations = memberToEdit.getReservations();
        Set<Tag> tags = memberToEdit.getTags();

        Set<Reservation> updatedReservations = new HashSet<>(reservations);
        updatedReservations.add(reservation);

        return new Member(id, name, phone, email, address, timestamp, credit, transactions, updatedReservations, tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddReservationCommand // instanceof handles nulls
                && reservationToAdd.equals(((AddReservationCommand) other).reservationToAdd))
                && idToAdd.equals(((AddReservationCommand) other).idToAdd);
    }
}
