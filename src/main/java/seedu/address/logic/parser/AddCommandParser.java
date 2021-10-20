package seedu.address.logic.parser;

import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddMemberCommand object
 */
public abstract class AddCommandParser implements Parser<AddMemberCommand> {
    private static final String ID_STUB = "00001";
    private static final String REGISTRATION_TIMESTAMP_STUB = "1609459200000";

    public abstract AddMemberCommand parse(String args) throws ParseException;
}
