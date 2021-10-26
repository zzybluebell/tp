package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Timestamp;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Edits the details of an existing member in the ezFoodie.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the member identified "
            + "by the index number used in the displayed member list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters:\n"
            + "Edit by index number: " + PREFIX_MEMBER + " [" + PREFIX_INDEX + " INDEX] "
            + "(INDEX must be a positive integer) "
            + "[" + PREFIX_NAME + " NAME] "
            + "[" + PREFIX_PHONE + " PHONE] "
            + "[" + PREFIX_EMAIL + " EMAIL] "
            + "[" + PREFIX_ADDRESS + " ADDRESS] "
            + "[" + PREFIX_TAG + " TAG]... "
            + "[" + PREFIX_TRANSACTION + " TRANSACTION]..."
            + "[" + PREFIX_RESERVATION + "RESERVATION]...\n"
            + "Edit by member ID: " + PREFIX_MEMBER + " [" + PREFIX_ID + " ID] "
            + "[" + PREFIX_NAME + " NAME] "
            + "[" + PREFIX_PHONE + " PHONE] "
            + "[" + PREFIX_EMAIL + " EMAIL] "
            + "[" + PREFIX_ADDRESS + " ADDRESS] "
            + "[" + PREFIX_TAG + " TAG]... "
            + "[" + PREFIX_TRANSACTION + " TRANSACTION]..."
            + "[" + PREFIX_RESERVATION + "RESERVATION]...\n"
            + "Example:\n"
            + "Edit by index number: " + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_INDEX + " 1 "
            + PREFIX_PHONE + " 91234567 "
            + PREFIX_EMAIL + " johndoe@example.com\n"
            + "Edit by member ID: " + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_ID + " 10001 "
            + PREFIX_PHONE + " 91234567 "
            + PREFIX_EMAIL + " johndoe@example.com";

    public static final String MESSAGE_EDIT_MEMBER_SUCCESS = "Edited Member: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEMBER = "This member already exists in the ezFoodie.";

    private final Index index;
    private final Id id;
    private final EditMemberDescriptor editMemberDescriptor;

    /**
     * @param index of the member in the updated member list to edit
     * @param editMemberDescriptor details to edit the member with
     */
    public EditCommand(Index index, EditMemberDescriptor editMemberDescriptor) {
        requireNonNull(index);
        requireNonNull(editMemberDescriptor);

        this.index = index;
        id = null;
        this.editMemberDescriptor = new EditMemberDescriptor(editMemberDescriptor);
    }

    /**
     * @param id of the member in the updated member list to edit
     * @param editMemberDescriptor details to edit the member with
     */
    public EditCommand(Id id, EditMemberDescriptor editMemberDescriptor) {
        requireNonNull(id);
        requireNonNull(editMemberDescriptor);

        this.id = id;
        index = null;
        this.editMemberDescriptor = new EditMemberDescriptor(editMemberDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();

        Member memberToEdit = null;
        if (index != null) {
            if (index.getZeroBased() < lastShownList.size()) {
                memberToEdit = lastShownList.get(index.getZeroBased());
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }
        }
        if (id != null) {
            memberToEdit = lastShownList.stream()
                    .filter(member -> id.equals(member.getId())).findAny().orElse(null);
        }
        if (memberToEdit != null) {
            Member editedMember = createEditedMember(memberToEdit, editMemberDescriptor);
            if (model.hasMember(editedMember, member -> member.getId() != editedMember.getId())) {
                throw new CommandException(MESSAGE_DUPLICATE_MEMBER);
            }
            model.setMember(memberToEdit, editedMember);
            model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
            return new CommandResult(String.format(MESSAGE_EDIT_MEMBER_SUCCESS, editedMember));
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit}
     * edited with {@code editMemberDescriptor}.
     */
    private static Member createEditedMember(Member memberToEdit, EditMemberDescriptor editMemberDescriptor) {
        assert memberToEdit != null;

        Id id = memberToEdit.getId();
        Name updatedName = editMemberDescriptor.getName().orElse(memberToEdit.getName());
        Phone updatedPhone = editMemberDescriptor.getPhone().orElse(memberToEdit.getPhone());
        Email updatedEmail = editMemberDescriptor.getEmail().orElse(memberToEdit.getEmail());
        Address updatedAddress = editMemberDescriptor.getAddress().orElse(memberToEdit.getAddress());
        Timestamp timestamp = memberToEdit.getRegistrationTimestamp();
        Set<Tag> updatedTags = editMemberDescriptor.getTags().orElse(memberToEdit.getTags());
        // TODO: This is not the proper way to add transactions and calculate the sum
        //  need to check if the sum will overflow
        Set<Transaction> updatedTransactions = editMemberDescriptor.getTransactions()
                .orElse((memberToEdit.getTransactions()));
        Credit credit = new Credit("" + Math.min(updatedTransactions.stream()
                .mapToInt(transaction -> (int) transaction.getDoubleValue()).sum(), Credit.MAX));
        Set<Reservation> updatedReservations = editMemberDescriptor.getReservations()
                .orElse(memberToEdit.getReservations());

        return new Member(id, updatedName, updatedPhone, updatedEmail, updatedAddress, timestamp, credit,
                updatedTags, updatedTransactions, updatedReservations);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommand // instanceof handles nulls
                && (index == null || index.equals(((EditCommand) other).index))
                && (id == null || id.equals(((EditCommand) other).id)))
                && editMemberDescriptor.equals(((EditCommand) other).editMemberDescriptor); // state check
    }

    /**
     * Stores the details to edit the member with. Each non-empty field value will replace the
     * corresponding field value of the member.
     */
    public static class EditMemberDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Set<Transaction> transactions;
        private Set<Reservation> reservations;

        public EditMemberDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMemberDescriptor(EditMemberDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setTransactions(toCopy.transactions);
            setReservations(toCopy.reservations);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, transactions);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code transactions} to this object's {@code transactions}.
         * A defensive copy of {@code transactions} is used internally.
         */
        public void setTransactions(Set<Transaction> transactions) {
            this.transactions = (transactions != null) ? new HashSet<>(transactions) : null;
        }

        /**
         * Returns an unmodifiable transaction set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code transactions} is null.
         */
        public Optional<Set<Transaction>> getTransactions() {
            return (transactions != null) ? Optional.of(Collections.unmodifiableSet(transactions)) : Optional.empty();
        }

        /**
         * Sets {@code reservations} to this object's {@code reservations}.
         * A defensive copy of {@code reservations} is used internally.
         */
        public void setReservations(Set<Reservation> reservations) {
            this.reservations = (reservations != null) ? new HashSet<>(reservations) : null;
        }

        /**
         * Returns an unmodifiable reservation set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code reservation} is null.
         */
        public Optional<Set<Reservation>> getReservations() {
            return (reservations != null) ? Optional.of(Collections.unmodifiableSet(reservations)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMemberDescriptor)) {
                return false;
            }

            // state check
            EditMemberDescriptor e = (EditMemberDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getTransactions().equals(e.getTransactions())
                    && getReservations().equals(e.getReservations());
        }
    }
}

