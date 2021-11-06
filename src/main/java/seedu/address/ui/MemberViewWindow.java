package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for a member view page for related details.
 */
public class MemberViewWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(MemberViewWindow.class);
    private static final String FXML = "MemberViewWindow.fxml";

    @FXML
    private StackPane memberDetailsListPlaceholder;

    /**
     * Constructs a new {@code MemberViewWindow} .
     *
     * @param root Stage to use as the root of the MemberViewWindow.
     * @param logic summary of logic.
     */
    public MemberViewWindow(Stage root, Logic logic) {
        super(FXML, root);
        MemberDetailsListPanel memberDetailsListPanel = new MemberDetailsListPanel(logic.getUpdatedMemberListForView());
        memberDetailsListPlaceholder.getChildren().add(memberDetailsListPanel.getRoot());
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            System.out.println(event.getCode());
            if (event.getCode() == KeyCode.ESCAPE) {
                Stage stage = (Stage) getRoot().getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     * Constructs a new {@code MemberViewWindow} with input {@code logic}.
     */
    public MemberViewWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the MemberView window.
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
