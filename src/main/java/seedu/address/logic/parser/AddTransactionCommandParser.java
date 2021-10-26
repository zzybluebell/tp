package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.AddTransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.member.Id;
import seedu.address.model.transaction.Transaction;
/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddTransactionCommandParser extends AddCommandParser implements Parser<AddTransactionCommand> {
    // todo : add in timestamp soon
    private static final String TRANSACTION_TIMESTAMP_STUB = "1609459200000";

    private final Model model;
    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code AddTransactionCommandParser} with the given {@code ExecutionStatus}.
     */
    public AddTransactionCommandParser(Model model, ExecutionStatus executionStatus) {
        this.model = model;
        this.executionStatus = executionStatus;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddTransactionCommand
     * and returns an AddTransactionCommand object for execution.
     */
    @Override
    public AddTransactionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TRANSACTION, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap,
                PREFIX_TRANSACTION, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTransactionCommand.MESSAGE_USAGE));
        }

        Set<Transaction> transactionList = ParserUtil.parseTransactions(argMultimap.getAllValues(PREFIX_TRANSACTION));
        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());

        return new AddTransactionCommand(transactionList, id);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

