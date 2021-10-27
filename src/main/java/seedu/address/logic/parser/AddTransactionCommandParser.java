package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILLING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import java.util.stream.Stream;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.AddTransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.Timestamp;
import seedu.address.model.member.Id;
import seedu.address.model.transaction.Billing;
import seedu.address.model.transaction.Transaction;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddTransactionCommandParser extends AddCommandParser implements Parser<AddTransactionCommand> {

    private final Model model;
    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code AddTransactionCommandParser} with the given {@code Model} and {@code ExecutionStatus}.
     */
    public AddTransactionCommandParser(Model model, ExecutionStatus executionStatus) {
        this.model = model;
        this.executionStatus = executionStatus;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddTransactionCommand
     * and returns an AddTransactionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddTransactionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TRANSACTION, PREFIX_BILLING, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_TRANSACTION, PREFIX_BILLING, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTransactionCommand.MESSAGE_USAGE));
        }

        Billing billing = ParserUtil.parseBilling(argMultimap.getValue(PREFIX_BILLING).get());
        Timestamp timestamp = executionStatus == ExecutionStatus.NORMAL
                ? ParserUtil.parseTimestamp(DateTimeUtil.generateTimestamp())
                : ParserUtil.parseTimestamp(DateTimeUtil.generateTimestampStub());
        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Transaction transaction = new Transaction(timestamp, billing);

        return new AddTransactionCommand(transaction, id);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
