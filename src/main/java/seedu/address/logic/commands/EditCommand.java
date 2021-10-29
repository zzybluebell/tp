package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a edit command to add member or transaction to ezFoodie.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a member or a transaction in the ezFoodie.\n"
            + "With "
            + PREFIX_MEMBER + " (member details) or "
            + PREFIX_TRANSACTION + " (transaction details)";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

}
