package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;

import java.util.stream.Stream;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.AddReservationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.member.Id;
import seedu.address.model.reservation.DateTime;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;

/**
 * Parses input arguments and creates a new AddReservationCommand object
 */
public class AddReservationCommandParser extends AddCommandParser implements Parser<AddReservationCommand> {

    private final Model model;
    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code AddReservationCommandParser} with the given {@code Model} and {@code ExecutionStatus}.
     */
    public AddReservationCommandParser(Model model, ExecutionStatus executionStatus) {
        this.model = model;
        this.executionStatus = executionStatus;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddReservationCommand
     * and returns an AddReservationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddReservationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RESERVATION, PREFIX_DATE_TIME, PREFIX_REMARK, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_RESERVATION, PREFIX_DATE_TIME, PREFIX_REMARK, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddReservationCommand.MESSAGE_USAGE));
        }

        DateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATE_TIME).get());
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Reservation reservation = new Reservation(dateTime, remark);

        return new AddReservationCommand(reservation, id);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
