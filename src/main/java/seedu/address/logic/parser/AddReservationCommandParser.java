package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.AddReservationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.member.Id;
import seedu.address.model.reservation.Reservation;

/**
 * Parses input arguments and creates a new AddReservationCommand object
 */
public class AddReservationCommandParser extends AddCommandParser implements Parser<AddReservationCommand> {
    private static final String RESERVATION_TIMESTAMP_STUB = "1609459200000";

    private final Model model;
    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code AddReservationCommandParser} with the given {@code ExecutionStatus}.
     */
    public AddReservationCommandParser(Model model, ExecutionStatus executionStatus) {
        this.model = model;
        this.executionStatus = executionStatus;
    }

    private String generateReservationTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String generateReservationTimestampStub() {
        return RESERVATION_TIMESTAMP_STUB;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddReservationCommand
     * and returns an AddReservationCommand object for execution.
     */
    @Override
    public AddReservationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RESERVATION, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap,
                PREFIX_RESERVATION, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddReservationCommand.MESSAGE_USAGE));
        }
        Set<Reservation> reservationList = ParserUtil.parseReservations(argMultimap.getAllValues(PREFIX_RESERVATION));
        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());

        return new AddReservationCommand(reservationList, id);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
