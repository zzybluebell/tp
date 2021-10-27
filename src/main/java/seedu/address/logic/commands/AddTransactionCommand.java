package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILLING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;
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
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Adds a transaction to the ezFoodie.
 */
public class AddTransactionCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a transaction to each member in the ezFoodie. "
            + "Parameters: "
            + PREFIX_TRANSACTION + " "
            + PREFIX_BILLING + " " + "BILLING (STRICTLY 2 DECIMAL PLACES) "
            + PREFIX_ID + " " + "ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TRANSACTION + " "
            + PREFIX_BILLING + " " + "23.00 "
            + PREFIX_ID + " " + "10001";

    public static final String MESSAGE_SUCCESS = "New transaction added: %1$s";

    private final Transaction transactionToAdd;
    private final Id idToAdd;

    /**
     * Creates an AddTransactionCommand to add the specified {@code Member}
     */
    public AddTransactionCommand(Transaction transaction, Id id) {
        requireAllNonNull(transaction, id);
        transactionToAdd = transaction;
        idToAdd = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();
        Member memberToEdit = lastShownList.stream()
                .filter(member -> idToAdd.equals(member.getId())).findAny().orElse(null);
        if (memberToEdit != null) {
            Member editedMember = createUpdatedCredits(memberToEdit, transactionToAdd);
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
    private static Member createUpdatedCredits(Member memberToEdit, Transaction transaction) {
        assert memberToEdit != null;

        Id id = memberToEdit.getId();
        Name updatedName = memberToEdit.getName();
        Phone updatedPhone = memberToEdit.getPhone();
        Email updatedEmail = memberToEdit.getEmail();
        Address updatedAddress = memberToEdit.getAddress();
        Timestamp updateTimestamp = memberToEdit.getTimestamp();
        List<Transaction> transactions = memberToEdit.getTransactions();
        Set<Reservation> reservations = memberToEdit.getReservations();
        Set<Tag> updatedTags = memberToEdit.getTags();
        List<Transaction> updatedTransactions = new ArrayList<>(transactions);
        updatedTransactions.add(transaction);
        Credit updatedCredit = new Credit("" + Math.min(updatedTransactions.stream()
                .mapToInt(t -> (int) t.getBilling().getDoubleValue()).sum(), Credit.MAX));
        Point updatePoint = new Point(String.valueOf(updatedCredit.getIntValue() - memberToEdit.getCredit().getIntValue()
                + memberToEdit.getPoint().getIntValue()));
        return new Member(id, updatedName, updatedPhone, updatedEmail, updatedAddress, updateTimestamp, updatedCredit,
                updatePoint, updatedTransactions, reservations, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTransactionCommand // instanceof handles nulls
                && transactionToAdd.equals(((AddTransactionCommand) other).transactionToAdd))
                && idToAdd.equals(((AddTransactionCommand) other).idToAdd);
    }
}
