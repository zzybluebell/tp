package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for a summary page.
 */
public class SummaryWindow extends UiPart<Stage> {

    private static final String FXML = "SummaryWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(SummaryWindow.class);

    @FXML
    private StackPane summaryCardPlaceholder;

    /**
     * Creates a new SummaryWindow.
     *
     * @param root Stage to use as the root of the SummaryWindow.
     */
    public SummaryWindow(Stage root, Logic logic) {
        super(FXML, root);
        SummaryCard summaryCard = new SummaryCard(logic.getUpdatedMemberList());
        summaryCardPlaceholder.getChildren().add(summaryCard.getRoot());
    }

    /**
     * Creates a new SummaryWindow.
     */
    public SummaryWindow(Logic logic) {
        this(new Stage(), logic);
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
}
