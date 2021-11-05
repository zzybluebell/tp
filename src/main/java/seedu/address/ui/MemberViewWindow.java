package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.member.Member;

/**
 * Controller for a member view page for related details.
 */
public class MemberViewWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(MemberViewWindow.class);
    private static final String FXML = "MemberViewWindow.fxml";

    @FXML
    private ListView<Member> memberDetailsView;

    /**
     * Creates a new MemberView window.
     *
     * */
    public MemberViewWindow(Logic logic) {
        super(FXML, new Stage());
        memberDetailsView.setItems(logic.getUpdatedMemberListForView());
        memberDetailsView.setCellFactory(listView -> new MemberViewListCell());
    }

    /**
     * Represents a inner class for MemberViewListCell.
     */
    class MemberViewListCell extends ListCell<Member> {
        @Override
        protected void updateItem(Member member, boolean empty) {
            super.updateItem(member, empty);

            if (empty || member == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MemberDetailsCard(member).getRoot());
            }
        }
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
