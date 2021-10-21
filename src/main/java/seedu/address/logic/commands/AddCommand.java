package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

/**
 * Represents a add command to add member or transaction to ezFoodie.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": adds a member or a transaction to the ezFoodie.\n"
            + "With "
            + PREFIX_MEMBER + " or "
            + PREFIX_TRANSACTION;

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

}
