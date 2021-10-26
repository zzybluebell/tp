package seedu.address.logic.commands;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.member.Address;
import seedu.address.model.member.Email;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Stores the details to edit the member with. Each non-empty field value will replace the
 * corresponding field value of the member.
 */
public class EditMemberDescriptor {
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
