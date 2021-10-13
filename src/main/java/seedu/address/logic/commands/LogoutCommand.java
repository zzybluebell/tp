package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import seedu.address.commons.core.Config;
import seedu.address.commons.status.LoginStatus;
import seedu.address.model.Model;

/**
 * Logs out as a manager.
 */
public class LogoutCommand extends Command {

    public static final String COMMAND_WORD = "logout";
    public static final String MESSAGE_SUCCESS = "Logout successfully!";
    public static final String MESSAGE_ALREADY_IN_STATUS = "You are already in STAFF login status!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (Config.getLoginStatus() == LoginStatus.STAFF) {
            return new CommandResult(MESSAGE_ALREADY_IN_STATUS);
        }
        Config.setLoginStatus(LoginStatus.STAFF);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
