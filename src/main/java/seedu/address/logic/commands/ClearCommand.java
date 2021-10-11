package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.EzFoodie;
import seedu.address.model.Model;

/**
 * Clears the ezFoodie.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ezFoodie has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setEzFoodie(new EzFoodie());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
