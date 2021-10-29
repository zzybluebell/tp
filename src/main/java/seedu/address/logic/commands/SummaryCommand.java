package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;


/**
 * Format the summary of members and transactions related information
 * for display in text form.
 */
public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows summary for members and transactions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_SUMMARY_MESSAGE = "Opened summary window.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(SHOWING_SUMMARY_MESSAGE,
                false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                // instanceof handles nulls
                || (other instanceof SummaryCommand); // short circuit if same object
    }
}
