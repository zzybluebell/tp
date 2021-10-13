package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Config;
import seedu.address.commons.status.LoginStatus;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class LogoutCommandTest {

    @Test
    public void execute_inStaffLoginStatus_success() {
        Config.setLoginStatus(LoginStatus.STAFF);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new LogoutCommand(), model, LogoutCommand.MESSAGE_ALREADY_IN_STATUS, expectedModel);
    }

    @Test
    public void execute_inManagerLoginStatus_success() {
        Config.setLoginStatus(LoginStatus.MANAGER);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new LogoutCommand(), model, LogoutCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
