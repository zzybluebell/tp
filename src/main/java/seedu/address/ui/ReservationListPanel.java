package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.reservation.Reservation;

/**
 * Represents for Panel containing the list of reservations.
 */
public class ReservationListPanel extends UiPart<Region> {
    private static final String FXML = "ReservationListPanel.fxml";

    @FXML
    private ListView<Reservation> reservationListView;

    /**
     * Creates a {@code ReservationListPanel} with the given {@code ObservableList}.
     */
    public ReservationListPanel(ObservableList<Reservation> reservationList) {
        super(FXML);
        reservationListView.setItems(reservationList);
        reservationListView.setCellFactory(listView -> new ReservationListViewCell());
    }

    /**
     * Customs {@code ListCell} that displays the graphics of a {@code Reservation} using a {@code ReservationCard}.
     */
    class ReservationListViewCell extends ListCell<Reservation> {
        @Override
        protected void updateItem(Reservation reservation, boolean empty) {
            super.updateItem(reservation, empty);

            if (empty || reservation == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReservationCard(reservation).getRoot());
            }
        }
    }

}
