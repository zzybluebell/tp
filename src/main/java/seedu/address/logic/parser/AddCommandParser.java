package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddMemberCommand object
 */
public abstract class AddCommandParser {
    public abstract AddCommand parse(String args) throws ParseException;
}
