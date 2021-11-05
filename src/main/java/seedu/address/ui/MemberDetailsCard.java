package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.member.Member;
import seedu.address.model.member.Tier;

/**
 * An UI component that displays information of a {@code Member}.
 */
public class MemberDetailsCard extends UiPart<Region> {

    private static final String FXML = "MemberDetailsCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Member member;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label registrationTime;
    @FXML
    private Label credit;
    @FXML
    private Label point;
    @FXML
    private Label tier;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane transactions;
    @FXML
    private FlowPane reservations;

    /**
     * Creates a {@code MemberCode} with the given {@code Member} and index to display.
     */
    public MemberDetailsCard(Member member) {
        super(FXML);
        this.member = member;
        id.setText(member.getId().value);
        name.setText(member.getName().fullName);
        phone.setText(member.getPhone().value);
        email.setText(member.getEmail().value);
        address.setText(member.getAddress().value);
        registrationTime.setText(DateTimeUtil.timestampToDate(Long.parseLong(member.getTimestamp().value)).toString());
        credit.setText(member.getCredit().value);
        point.setText(member.getPoint().value);
        tier.setText(Tier.getTierByCredit(Integer.parseInt(member.getCredit().value)));
        tier.getStyleClass().add(Tier.getTierByCredit(Integer.parseInt(member.getCredit().value)).toLowerCase());
        member.getTransactions().stream()
                .sorted(Comparator.comparing(transaction -> transaction.getId().value))
                .forEach(transaction -> transactions.getChildren().add(new Label("["
                        + transaction.getId().value + " "
                        + DateTimeUtil.timestampToDate(Long.parseLong(transaction.getTimestamp().value))
                        + " " + transaction.getBilling().value + "] ")));
        member.getReservations().stream()
                .sorted(Comparator.comparing(reservation -> DateTimeUtil
                        .parseDateTime(reservation.getDateTime().value)))
                .forEach(reservation -> reservations.getChildren().add(new Label("["
                        + reservation.getId().value + " "
                        + reservation.getDateTime().value + " " + reservation.getRemark().value + "] ")));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MemberDetailsCard)) {
            return false;
        }

        // state check
        MemberDetailsCard card = (MemberDetailsCard) other;
        return member.equals(card.member);
    }
}
