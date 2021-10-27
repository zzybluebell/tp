package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a member view page for related details
 */
public class MemberViewWindow extends UiPart<Stage> {
    public static final String OFFICIAL_URL =
            "https://ay2122s1-cs2103t-f12-4.github.io/tp/";
    public static final String HELP_MESSAGE = "Features:\n"
            + "Add member: add -m -n <NAME> -p <PHONE> -e <EMAIL>\n"
            + "Search by name: find -m -n <NAME>\n"
            + "Search by phone: find -m -p <PHONE>\n"
            + "Search by email: find -m -e <EMAIL>\n"
            + "Search by registration date: find -m -d <REGISTRATION_DATE>\n"
            + "Search by member ID: find -m -id <MEMBER_ID>\n"
            + "Adding transaction: add -t -id <MEMBER_ID> -b <BILLING>\n"
            + "View member: show -m -id <MEMBER_ID>\n"
            + "Delete member: del -m -id <MEMBER_ID>\n"
            + "Exit Application: exit\n"
            + "To view full user guide: " + OFFICIAL_URL;

    private static final Logger logger = LogsCenter.getLogger(MemberViewWindow.class);
    private static final String FXML = "MemberViewWindow.fxml";

    @FXML
    private Label memberDetails;

    /**
     * Creates a new MemberView window.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public MemberViewWindow(Stage root) {
        super(FXML, root);
        memberDetails.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new MemberView window.
     */
    public MemberViewWindow() {
        this(new Stage());
    }

    /**
     * Shows the MemberView window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing member view page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
