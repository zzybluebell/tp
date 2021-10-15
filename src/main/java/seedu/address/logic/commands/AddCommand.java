package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a add command to add member or transaction to ezFoodie.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public abstract CommandResult execute(Model model) throws CommandException;

}
