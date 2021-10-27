package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;

import java.util.Arrays;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.member.IdContainsKeywordsPredicate;

public class ViewCommandParser implements Parser<Command> {

    private static final int PREFIX_SIZE = 3;

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMBER, PREFIX_ID);

        if (argMultimap.getSize() != PREFIX_SIZE || argMultimap.getValue(PREFIX_MEMBER).isEmpty()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_ID).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }
            String[] idKeywords = trimmedArgs.split("\\s+");
            return new ViewCommand(new IdContainsKeywordsPredicate(Arrays.asList(idKeywords)));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
}
