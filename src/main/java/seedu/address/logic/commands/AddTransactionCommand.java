package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Transaction;

/**
 * Adds a Transaction to a member's records.
 */
public class AddTransactionCommand extends Command {
    public static final String COMMAND_WORD = "add -t";

    // TODO: complete the string
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a transaction to a member with the member ID. "
            + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "New transaction added: %1$s";

    private final Person person;
    private final Transaction toAdd;

    /**
     * @param person the person to add the transaction to
     * @param transaction the transaction to add
     */
    public AddTransactionCommand(Person person, Transaction transaction) {
        requireAllNonNull(person, transaction);
        this.person = person;
        toAdd = transaction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        person.getTransactionHistory().addTransaction(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTransactionCommand // instanceof handles nulls
                && toAdd.equals(((AddTransactionCommand) other).toAdd));
    }
}
