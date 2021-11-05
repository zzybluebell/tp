package seedu.address.ui;

import java.text.DecimalFormat;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.member.Member;
import seedu.address.model.transaction.Transaction;

/**
 * An UI component that displays information of a summary.
 */
public class SummaryCard extends UiPart<Region> {

    private static final String FXML = "SummaryBox.fxml";
    private static final String PATTERN = "#.##";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final ObservableList<Member> memberList;

    @FXML
    private HBox cardPane;
    @FXML
    private Label totalNumberOfMembers;
    @FXML
    private Label totalNumberOfTransactions;
    @FXML
    private Label totalAmountOfTransactions;
    @FXML
    private Label totalNumberOfTransactionsInPastMonth;
    @FXML
    private Label totalAmountOfTransactionsInPastMonth;
    @FXML
    private Label totalNumberOfTransactionsInPast3Months;
    @FXML
    private Label totalAmountOfTransactionsInPast3Months;
    @FXML
    private Label totalNumberOfTransactionsInPast6Months;
    @FXML
    private Label totalAmountOfTransactionsInPast6Months;

    /**
     * Creates a {@code MemberCode} with the given {@code Member} and index to display.
     */
    public SummaryCard(ObservableList<Member> memberList) {
        super(FXML);
        this.memberList = memberList;
        DecimalFormat df = new DecimalFormat(PATTERN);
        totalNumberOfMembers.setText(String.valueOf(memberList.size()));
        totalNumberOfTransactions.setText(String.valueOf(memberList.stream()
                .mapToInt(member -> member.getTransactions().size()).sum()));
        totalAmountOfTransactions.setText(df.format(memberList.stream()
                .mapToDouble(member -> member.getTransactions().stream()
                .mapToDouble(transaction -> transaction.getBilling().getDoubleValue()).sum()).sum()));
        totalNumberOfTransactionsInPastMonth.setText(String.valueOf(memberList.stream()
                .mapToLong(member -> member.getTransactions().stream()
                .filter(transaction -> Transaction
                .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 1)).count()).sum()));
        totalAmountOfTransactionsInPastMonth.setText(df.format(memberList.stream()
                .mapToDouble(member -> member.getTransactions().stream()
                .filter(transaction -> Transaction
                .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 1))
                .mapToDouble(transaction -> transaction.getBilling().getDoubleValue()).sum()).sum()));
        totalNumberOfTransactionsInPast3Months.setText(String.valueOf(memberList.stream()
                .mapToLong(member -> member.getTransactions().stream()
                .filter(transaction -> Transaction
                .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 3)).count()).sum()));
        totalAmountOfTransactionsInPast3Months.setText(df.format(memberList.stream()
                .mapToDouble(member -> member.getTransactions().stream()
                .filter(transaction -> Transaction
                .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 3))
                .mapToDouble(transaction -> transaction.getBilling().getDoubleValue()).sum()).sum()));
        totalNumberOfTransactionsInPast6Months.setText(String.valueOf(memberList.stream()
                .mapToLong(member -> member.getTransactions().stream()
                .filter(transaction -> Transaction
                .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 6)).count()).sum()));
        totalAmountOfTransactionsInPast6Months.setText(df.format(memberList.stream()
                .mapToDouble(member -> member.getTransactions().stream()
                .filter(transaction -> Transaction
                .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 6))
                .mapToDouble(transaction -> transaction.getBilling().getDoubleValue()).sum()).sum()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SummaryCard)) {
            return false;
        }

        // state check
        SummaryCard card = (SummaryCard) other;
        return memberList.equals(card.memberList);
    }
}
