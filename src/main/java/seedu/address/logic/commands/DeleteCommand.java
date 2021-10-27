package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a member identified using it's displayed index from the ezFoodie.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "del";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a member or a transaction from the ezFoodie.\n"
            + "With "
            + PREFIX_MEMBER + " (member details) or "
            + PREFIX_TRANSACTION + " (transaction details)";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

}
