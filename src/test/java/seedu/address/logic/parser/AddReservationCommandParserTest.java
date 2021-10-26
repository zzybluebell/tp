package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RESERVATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESERVATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalMembers.AMY;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.AddReservationCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.member.Id;
import seedu.address.model.reservation.Reservation;
import seedu.address.testutil.MemberBuilder;

class AddReservationCommandParserTest {

    private Model model = new ModelManager();
    private AddReservationCommandParser parser = new AddReservationCommandParser(model, ExecutionStatus.TEST);

    @Test
    public void parse_allFieldsPresent_success() {
        Set<Reservation> expectedReservations = new MemberBuilder(AMY)
                .withReservations(VALID_RESERVATION).build().getReservations();
        Id expectedId = new Id(VALID_ID_AMY);

        // multiple reservations - all accepted
        assertParseSuccess(parser, RESERVATION_DESC + ID_DESC_AMY,
                new AddReservationCommand(expectedReservations, expectedId));
    }

}
