package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REDEEM;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.RedeemPointCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.member.Id;
import seedu.address.model.member.Point;

/**
 * Parses input arguments and creates a new RedeemPoint object
 */
public class RedeemPointCommandParser implements Parser<RedeemPointCommand> {

    private final Model model;
    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code AddTransactionCommandParser} with the given {@code ExecutionStatus}.
     */
    public RedeemPointCommandParser(Model model, ExecutionStatus executionStatus) {
        this.model = model;
        this.executionStatus = executionStatus;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the RedeemPointCommand
     * and returns an RedeemPointCommand object for execution.
     */
    @Override
    public RedeemPointCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_REDEEM, PREFIX_ID, PREFIX_INDEX);
        if ((argMultimap.getValue(PREFIX_ID).isEmpty() && argMultimap.getValue(PREFIX_INDEX).isEmpty())
                || (argMultimap.getValue(PREFIX_ID).isPresent() && argMultimap.getValue(PREFIX_INDEX).isPresent())
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RedeemPointCommand.MESSAGE_USAGE));
        }


        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            List<Point> pointToRedeemList = ParserUtil.parsePoints(argMultimap.getAllValues(PREFIX_REDEEM));
            Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
            return new RedeemPointCommand(pointToRedeemList, id);
        } else if (argMultimap.getValue(PREFIX_INDEX).isPresent()) {
            List<Point> pointToRedeemList = ParserUtil.parsePoints(argMultimap.getAllValues(PREFIX_REDEEM));
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            return new RedeemPointCommand(pointToRedeemList, index);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RedeemPointCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
