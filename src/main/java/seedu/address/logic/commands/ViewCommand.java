package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.member.IdContainsKeywordsPredicate;
import seedu.address.model.member.Member;

/**
 * View specific member details in eZFoodie, accessed by member ID.
 */
public class ViewCommand extends Command {

    /**
     * Stands for view command.
     */
    public static final String COMMAND_WORD = "show";

    /**
     * Stands for the message of open view window successfully.
     */
    public static final String SHOWING_VIEW_MESSAGE = "Opened view window.";

    /**
     * Stands for the message of show and view command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View a specific member's details, "
            + "accessed by member ID.\n"
            + "Parameters:\n"
            + PREFIX_MEMBER + " [" + PREFIX_ID + " ID]\n"
            + "Example:\n"
            + COMMAND_WORD + " " + PREFIX_MEMBER + PREFIX_ID + " 10001\n";

    private final Predicate<Member> predicate;

    /**
     * Construct the view command based on member id predicate.
     *
     * @param predicate
     */
    public ViewCommand(IdContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Overrides and executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMemberListForView(predicate);
        return new CommandResult(SHOWING_VIEW_MESSAGE, false, false, true, false);
    }

    /**
     * Override the equals method
     *
     * @param other
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
