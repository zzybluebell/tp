package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Timestamp;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.reservation.DateTime;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Edits the details of an existing reservation in the ezFoodie.
 */
public class EditReservationCommand extends EditCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the reservation identified "
            + "by the member ID and reservation ID. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters:\n"
            + "Edit by member ID and reservation ID: "
            + PREFIX_RESERVATION + " [" + PREFIX_ID + " member ID + reservation ID] "
            + "[" + PREFIX_DATE_TIME + " DATE_TIME]"
            + "[" + PREFIX_REMARK + " REMARK]\n"
            + "Example:\n"
            + "Edit by member ID and reservation ID: "
            + COMMAND_WORD + " " + PREFIX_RESERVATION + " " + PREFIX_ID + " 10001100001 "
            + PREFIX_DATE_TIME + " 2021-12-01 13:00"
            + PREFIX_REMARK + " 3 people";

    public static final String MESSAGE_SUCCESS = "Edited Member: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final seedu.address.model.member.Id memberId;
    private final seedu.address.model.reservation.Id reservationId;
    private final EditReservationDescriptor editReservationDescriptor;

    /**
     * @param memberId of the member in the updated member list to edit
     * @param reservationId of the reservation in the reservation list to edit
     * @param editReservationDescriptor details to edit the reservation with
     */
    public EditReservationCommand(
            seedu.address.model.member.Id memberId, seedu.address.model.reservation.Id reservationId,
            EditReservationDescriptor editReservationDescriptor) {
        requireAllNonNull(memberId, reservationId, editReservationDescriptor);

        this.memberId = memberId;
        this.reservationId = reservationId;
        this.editReservationDescriptor = new EditReservationDescriptor(editReservationDescriptor);
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit}
     */
    private static Member createUpdatedCredits(
            Member memberToEdit, Reservation reservationToEdit, EditReservationDescriptor editReservationDescriptor) {
        assert memberToEdit != null;
        assert reservationToEdit != null;

        // Member
        seedu.address.model.member.Id id = memberToEdit.getId();
        Name updatedName = memberToEdit.getName();
        Phone updatedPhone = memberToEdit.getPhone();
        Email updatedEmail = memberToEdit.getEmail();
        Address updatedAddress = memberToEdit.getAddress();
        Timestamp timestamp = memberToEdit.getTimestamp();
        Credit credit = memberToEdit.getCredit();
        Point point = memberToEdit.getPoint();
        List<Transaction> transactions = memberToEdit.getTransactions();
        List<Reservation> reservations = memberToEdit.getReservations();
        Set<Tag> updatedTags = memberToEdit.getTags();

        // Reservation
        DateTime updatedDateTime = editReservationDescriptor.getDateTime().orElse(reservationToEdit.getDateTime());
        Remark updatedRemark = editReservationDescriptor.getRemark().orElse(reservationToEdit.getRemark());

        List<Reservation> updatedReservations = new ArrayList<>(reservations);
        Reservation updatedReservation = new Reservation(reservationToEdit.getId(), updatedDateTime, updatedRemark);
        updatedReservations.add(updatedReservation);

        return new Member(id, updatedName, updatedPhone, updatedEmail, updatedAddress, timestamp, credit, point,
                transactions, updatedReservations, updatedTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();
        Member memberToEdit = lastShownList.stream()
                .filter(member -> memberId.equals(member.getId())).findAny().orElse(null);
        if (memberToEdit != null) {
            Reservation reservationToEdit = memberToEdit.getReservations().stream()
                    .filter(reservation -> reservationId.equals(reservation.getId())).findAny().orElse(null);
            if (reservationToEdit != null) {
                Member editedMember = createUpdatedCredits(memberToEdit, reservationToEdit, editReservationDescriptor);
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
                || (other instanceof EditReservationCommand // instanceof handles nulls
                // && memberId.equals(((EditReservationCommand) other).memberId)
                && reservationId.equals(((EditReservationCommand) other).reservationId)
                && editReservationDescriptor
                .equals(((EditReservationCommand) other).editReservationDescriptor)); // state check
    }

    /**
     * Stores the details to edit the reservation with. Each non-empty field value will replace the
     * corresponding field value of the reservation.
     */
    public static class EditReservationDescriptor {
        private DateTime dateTime;
        private Remark remark;

        public EditReservationDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditReservationDescriptor(EditReservationDescriptor toCopy) {
            setDateTime(toCopy.dateTime);
            setRemark(toCopy.remark);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(dateTime, remark);
        }

        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<DateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditReservationDescriptor)) {
                return false;
            }

            // state check
            EditReservationDescriptor e = (EditReservationDescriptor) other;

            return getDateTime().equals(e.getDateTime())
                    && getRemark().equals(e.getRemark());
        }
    }
}