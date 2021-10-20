package seedu.address.logic.parser;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

/**
 * Distinguishes between add member and add transaction commands and
 * returns an AddMemberCommandParser or AddTransactionCommandParser
 * depends on the very first prefix appears after command word.
 */
public class AddCommandPrefixParser {
	private final Model model;
	private final ExecutionStatus executionStatus;

	/**
	 * Constructor for AddCommandPrefixParser.
	 * @param model
	 * @param executionStatus
	 */
	public AddCommandPrefixParser(Model model, ExecutionStatus executionStatus) {
		this.model = model;
		this.executionStatus = executionStatus;
	}

	/**
	 * Parses the arguments depends on the first prefix.
	 * @param args
	 * @return AddMemberCommandParser or AddTransactionCommandParser.
	 * @throws ParseException
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
		} else {
			throw new ParseException(
					String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
		}
	}
}
