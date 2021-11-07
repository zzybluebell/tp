package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Represents for Controlling a help page.
 */
public class HelpWindow extends UiPart<Stage> {
    /**
     * Stands for help window URL to tP web page.
     */
    public static final String OFFICIAL_URL =
            "https://ay2122s1-cs2103t-f12-4.github.io/tp/";

    /**
     * Stands for help command message.
     */
    public static final String HELP_MESSAGE = "Features:\n"
            + "Login: login ******"
            + "logout"
            + "List members: list -mem/"
            + "Add member: add -mem/ -n/<NAME> -p/<PHONE> -e/<EMAIL> -a/<ADDRESS>\n"
            + "Delete member: del -mem/ -id/<MEMBER_ID>\n"
            + "Search: find -mem/ [-n/<NAME>][-p/<PHONE>][-e/<EMAIL>][-d/<REGISTRATION_DATE>][-id/<MEMBER_ID>]\n"
            + "View member: show -mem/ -id/<MEMBER_ID>\n"
            + "Edit member: edit -mem/ -id/<MEMBER_ID> [-n/<NAME>][-p/<PHONE_NUMBER>][-e/<EMAIL_ID>][-a/<ADDRESS>]"
            + "Add transaction: add -txn -b/<BILLING> -id/<MEMBER_ID>\n"
            + "Delete transaction: del -txn/ -id/<MEMBER_ID + TRANSACTION_ID>\n"
            + "Edit transaction: edit -txn/ -id/<MEMBER_ID + TRANSACTION_ID> -b/<BILL_AMMOUNT>\n"
            + "Add Reservation: add -rs/ -dt/<DATE_TIME yyyy-MM-dd HH:mm> -rm/ <REMARK> -id/<MEMBER_ID>\n"
            + "Delete Reservation: del -rs/ -id/<MEMBER_ID + RESERVATION_ID>\n"
            + "Edit reservation: edit -rs/ -id/<MEMBER_ID + RESERVATION_ID> [-dt/<DATE_TIME>][-rm/<REMARK>]\n"
            + "Sort members by ascending credit: sort -mem/ -c/ -a/\n"
            + "Sort members by descending credit: sort -mem/ -c/ -d/\n"
            + "Redeem points: redeem -rd/<POINTS> -id/<MEMBER_ID>\n"
            + "View summary: summary"
            + "Exit Application: exit\n"
            + "To view full user guide: " + OFFICIAL_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Constructs a new {@code HelpWindow}.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            System.out.println(event.getCode());
            if (event.getCode() == KeyCode.ESCAPE) {
                Stage stage = (Stage) getRoot().getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     * Constructs a new {@code HelpWindow}.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException if the user input does not conform the expected format.
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
        logger.fine("Showing help page about the application.");
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

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(OFFICIAL_URL);
        clipboard.setContent(url);
    }
}
