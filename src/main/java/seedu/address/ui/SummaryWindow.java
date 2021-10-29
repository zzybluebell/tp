package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for a summary page.
 */
public class SummaryWindow extends UiPart<Stage> {
    private static final String FXML = "SummaryWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(SummaryWindow.class);

    private String summaryText = "";

    @FXML
    private Label summaryMessage;

    /**
     * Creates a new SummaryWindow.
     *
     * @param root Stage to use as the root of the SummaryWindow.
     */
    public SummaryWindow(Stage root, Logic logic) {
        super(FXML, root);
        initSummaryMessage(logic);
        summaryMessage.setText(summaryText);
    }

    /**
     * Creates a new SummaryWindow.
     */
    public SummaryWindow(Logic logic) {
        this(new Stage(), logic);
    }

    private void initSummaryMessage(Logic logic) {
        summaryText += "Total number of Members: " + logic.getNumberOfMembers()
                + "\n\n"
                + "Total number of transactions of all time: "
                + logic.getNumberOfTransactions()
                + "\n\n"
                + "Total amount of transactions of all time: "
                + logic.getTotalAmountOfTransactions()
                + "\n\n"
                + "Total number of transactions of past month: "
                + logic.getNumberOfTransactionsPastMonth()
                + "\n\n"
                + "Total amount of transactions of past month: "
                + logic.getTotalAmountOfTransactionsPastMonth()
                + "\n\n"
                + "Total number of transactions of past 3 months: "
                + logic.getNumberOfTransactionsPastThreeMonth()
                + "\n\n"
                + "Total amount of transactions of past 3 months: "
                + logic.getTotalAmountOfTransactionsPastThreeMonth()
                + "\n\n"
                + "Total number of transactions of past 6 months: "
                + logic.getNumberOfTransactionsPastSixMonths()
                + "\n\n"
                + "Total amount of transactions of past 6 months: "
                + logic.getTotalAmountOfTransactionsPastSixMonth();
    }

    /**
     * Shows the summary window.
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
        logger.fine("Showing summary page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the summary window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the summary window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the summary window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the summary message to the clipboard.
     */
    @FXML
    private void copySummary() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent summary = new ClipboardContent();
        summary.putString(summaryText);
        clipboard.setContent(summary);
    }
}
