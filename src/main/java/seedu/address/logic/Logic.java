package seedu.address.logic;

import java.nio.file.Path;
import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.PermissionException;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.member.Member;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException    If an error occurs during command execution.
     * @throws ParseException      If an error occurs during parsing.
     * @throws PermissionException If an error occurs during insufficient permission.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException, PermissionException;

    /**
     * Returns the EzFoodie.
     *
     * @see seedu.address.model.Model#getEzFoodie()
     */
    ReadOnlyEzFoodie getEzFoodie();

    /**
     * Returns an unmodifiable view of the sorted or filtered list of members
     */
    ObservableList<Member> getUpdatedMemberList();

    /**
     * Returns an unmodifiable view of the sorted or filtered list of members
     * for viewCommand to use only
     */
    ObservableList<Member> getUpdatedMemberListForView();

    /**
     * Returns the total number of members in ezFoodie.
     */
    int getNumberOfMembers();

    /**
     * Returns the numbers of members by different tiers in ezFoodie.
     */
    HashMap<String, Integer> getNumberOfMembersByTiers();

    /**
     * Returns the total number of transactions in ezFoodie.
     */
    int getNumberOfTransactions();

    /**
     * Returns the total number of transactions in the past month in ezFoodie.
     */
    int getNumberOfTransactionsPastMonth();

    /**
     * Returns the total number of transactions in the past 3 months in ezFoodie.
     */
    int getNumberOfTransactionsPastThreeMonth();

    /**
     * Returns the total number of transactions in the past 6 months in ezFoodie.
     */
    int getNumberOfTransactionsPastSixMonths();

    /**
     * Returns the total amount of transactions in ezFoodie.
     */
    double getTotalAmountOfTransactions();

    /**
     * Returns the total amount of transactions in the past month in ezFoodie.
     */
    double getTotalAmountOfTransactionsPastMonth();

    /**
     * Returns the total amount of transactions in the past 3 months in ezFoodie.
     */
    double getTotalAmountOfTransactionsPastThreeMonth();

    /**
     * Returns the total amount of transactions in the past 6 months in ezFoodie.
     */
    double getTotalAmountOfTransactionsPastSixMonth();

    /**
     * Returns the user prefs' ezFoodie file path.
     */
    Path getEzFoodieFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
