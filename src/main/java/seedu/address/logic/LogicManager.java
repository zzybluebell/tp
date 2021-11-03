package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.PermissionException;
import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EzFoodieParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.member.Member;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final EzFoodieParser ezFoodieParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        ezFoodieParser = new EzFoodieParser(model);
    }

    /**
     * Constructs a {@code LogicManager} with the given {@code Model}, {@code Storage} and {@code ExecutionStatus}.
     */
    public LogicManager(Model model, Storage storage, ExecutionStatus executionStatus) {
        this.model = model;
        this.storage = storage;
        ezFoodieParser = new EzFoodieParser(model, executionStatus);
    }

    /**
     * Executes with given string of command text.
     *
     * @param commandText The command as entered by the user.
     * @return {@code CommandResult}
     * @throws CommandException
     * @throws ParseException
     * @throws PermissionException
     */
    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException, PermissionException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = ezFoodieParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveEzFoodie(model.getEzFoodie());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    /**
     * Gets EzFoodie.
     *
     * @return ReadOnlyEzFoodie
     */
    @Override
    public ReadOnlyEzFoodie getEzFoodie() {
        return model.getEzFoodie();
    }

    /**
     * Gets updated member list.
     *
     * @return ObservableList
     */
    @Override
    public ObservableList<Member> getUpdatedMemberList() {
        return model.getUpdatedMemberList();
    }

    /**
     * Gets updated member list for view.
     *
     * @return ObservableList
     */
    @Override
    public ObservableList<Member> getUpdatedMemberListForView () {
        return model.getUpdatedMemberListForView();
    }

    /**
     * Gets number of members.
     *
     * @return int
     */
    @Override
    public int getNumberOfMembers() {
        return model.getNumberOfMembers();
    }

    /**
     * Gets number of members by tier.
     *
     * @return HashMap
     */
    @Override
    public HashMap<String, Integer> getNumberOfMembersByTiers() {
        return model.getNumberOfMembersByTiers();
    }

    /**
     * Gets number of transactions.
     *
     * @return int
     */
    @Override
    public int getNumberOfTransactions() {
        return model.getNumberOfTransactions();
    }

    /**
     * Gets number of transactions in the past month.
     *
     * @return int
     */
    @Override
    public int getNumberOfTransactionsPastMonth() {
        return model.getNumberOfTransactionsPastMonth();
    }

    /**
     * Gets number of transaction in past three months.
     *
     * @return int
     */
    @Override
    public int getNumberOfTransactionsPastThreeMonth() {
        return model.getNumberOfTransactionsPastThreeMonth();
    }

    /**
     * Gets number of transactions in past six months.
     *
     * @return int
     */
    @Override
    public int getNumberOfTransactionsPastSixMonths() {
        return model.getNumberOfTransactionsPastSixMonth();
    }

    /**
     * Gets total amount of transactions.
     *
     * @return double
     */
    @Override
    public double getTotalAmountOfTransactions() {
        return model.getTotalAmountOfTransactions();
    }

    /**
     * Gets total amount of transactions in the past month.
     *
     * @return double
     */
    @Override
    public double getTotalAmountOfTransactionsPastMonth() {
        return model.getTotalAmountOfTransactionsPastMonth();
    }

    /**
     * Gets total amount of transactions in the past three months.
     *
     * @return double
     */
    @Override
    public double getTotalAmountOfTransactionsPastThreeMonth() {
        return model.getTotalAmountOfTransactionsPastThreeMonth();
    }

    /**
     * Gets total amount of transactions in the past six months.
     *
     * @return double
     */
    @Override
    public double getTotalAmountOfTransactionsPastSixMonth() {
        return model.getTotalAmountOfTransactionsPastSixMonth();
    }

    /**
     * Gets EzFoodie Path.
     *
     * @return Path
     */
    @Override
    public Path getEzFoodieFilePath() {
        return model.getEzFoodieFilePath();
    }

    /**
     * Gets Gui Settings.
     *
     * @return
     */
    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    /**
     * Sets Gui settings.
     *
     * @param guiSettings
     */
    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
