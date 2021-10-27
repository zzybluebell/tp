package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Distinguishes between add member and add transaction commands and
 * returns an AddMemberCommandParser or AddTransactionCommandParser
 * depends on the very first prefix appears after command word.
 */
public class AddCommandPrefixParser {
    private final Model model;
    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code AddCommandPrefixParser} with the given {@code Model} and {@code ExecutionStatus}.
     *
     * @param model of ezFoodie.
     * @param executionStatus normal or test.
     */
    public AddCommandPrefixParser(Model model, ExecutionStatus executionStatus) {
        this.model = model;
        this.executionStatus = executionStatus;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommandParser
     * and returns an AddCommandParser object for execution.
     *
     * @param args to be parsed.
     * @return AddMemberCommandParser or AddTransactionCommandParser.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommandParser parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String prefix;
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        int index = trimmedArgs.indexOf(' ');
        if (index > -1) { // Check if there is more than one word.
            prefix = trimmedArgs.substring(0, index).trim(); // Extract first word.
        } else {
            prefix = trimmedArgs; // Extracted word is the first word itself.
        }

        if (prefix.equals(PREFIX_MEMBER.getPrefix())) {
            return new AddMemberCommandParser(model, executionStatus);
        } else if (prefix.equals(PREFIX_TRANSACTION.getPrefix())) {
            return new AddTransactionCommandParser(model, executionStatus);
        } else if (prefix.equals(PREFIX_RESERVATION.getPrefix())) {
            return new AddReservationCommandParser(model, executionStatus);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }
}
