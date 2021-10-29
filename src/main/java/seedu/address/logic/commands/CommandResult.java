package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * Member view information should be shown to the user.
     */
    private final boolean showMemberView;

    /**
     * Summary information should be shown to the user.
     */
    private final boolean showSummary;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showMemberView,
                         boolean showSummary) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showMemberView = showMemberView;
        this.exit = exit;
        this.showSummary = showSummary;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Determines whether the app should show help window.
     */
    public boolean isShowHelp() {
        return showHelp;
    }

    /**
     * Determines whether the app should show member window.
     */
    public boolean isShowMemberView() {
        return showMemberView;
    }

    /**
     * Determines whether the app should show summary window.
     */
    public boolean isShowSummary() {
        return showSummary;
    }

    /**
     * Determines whether the app should exit.
     */
    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && showMemberView == otherCommandResult.showMemberView
                && exit == otherCommandResult.exit
                && showSummary == otherCommandResult.showSummary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, showMemberView, exit, showSummary);
    }

}
