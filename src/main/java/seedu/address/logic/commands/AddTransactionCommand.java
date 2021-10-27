package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.member.Timestamp;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Adds a transaction to the ezFoodie.
 */
public class AddTransactionCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a transaction to each member in the ezFoodie. "
            + "Parameters: "
            + "[" + PREFIX_TRANSACTION + " TRANSACTION AMOUNT (STRICTLY 2 DECIMAL PLACES)]"
            + " " + PREFIX_ID + " ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TRANSACTION + " 23.00"
            + PREFIX_TRANSACTION + " 988.56"
            + PREFIX_ID + " 10001";

    // todo: need to work on this
    public static final String MESSAGE_SUCCESS = "New transaction added.";
    public static final String MESSAGE_DUPLICATE_MEMBER = "This member already exists in the ezFoodie.";

    private final Set<Transaction> transactionToAdd;
    private final Id idToAdd;

    /**
     * Creates an AddTransactionCommand to add the specified {@code Member}
     */
    public AddTransactionCommand(Set<Transaction> transaction, Id id) {
        requireNonNull(id);
        transactionToAdd = transaction;
        idToAdd = id;
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit}
     * editedCredits with {@code editMemberDescriptor}.
     */
    private static Member createUpdatedCredits(Member memberToEdit, Set<Transaction> transactionToAdd) {
        assert memberToEdit != null;

        Id id = memberToEdit.getId();
        Name updatedName = memberToEdit.getName();
        Phone updatedPhone = memberToEdit.getPhone();
        Email updatedEmail = memberToEdit.getEmail();
        Address updatedAddress = memberToEdit.getAddress();
        Timestamp timestamp = memberToEdit.getRegistrationTimestamp();
        Set<Tag> updatedTags = memberToEdit.getTags();
        Set<Transaction> updatedTransactions = new HashSet<>(memberToEdit.getTransactions());
        updatedTransactions.addAll(transactionToAdd);
        Credit credit = new Credit("" + Math.min(updatedTransactions.stream()
                .mapToInt(t -> (int) t.getDoubleValue()).sum(), Credit.MAX));
        Point point = new Point(String.valueOf(credit.getIntValue() - memberToEdit.getCredit().getIntValue()
                + memberToEdit.getPoint().getIntValue()));
        return new Member(id, updatedName, updatedPhone, updatedEmail, updatedAddress, timestamp, credit, point,
                updatedTags, updatedTransactions);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();
        Member memberToEdit = lastShownList.stream()
                .filter(member -> idToAdd.equals(member.getId())).findAny().orElse(null);;
        Member editedMember = createUpdatedCredits(memberToEdit, transactionToAdd);
        if (model.hasMember(editedMember, member -> member.getId() != editedMember.getId())) {
            throw new CommandException(MESSAGE_DUPLICATE_MEMBER);
        }
        model.setMember(memberToEdit, editedMember);
        model.addTransaction(transactionToAdd, idToAdd);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTransactionCommand // instanceof handles nulls
                && transactionToAdd.equals(((AddTransactionCommand) other).transactionToAdd))
                && idToAdd.equals(((AddTransactionCommand) other).idToAdd);
    }
}
