package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTransactionCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteMemberCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteMemberCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteMemberTransactionParserTest {

    private DeleteTransactionCommandParser parser = new DeleteTransactionCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        seedu.address.model.member.Id memberId = new seedu.address.model.member.Id("10001");
        seedu.address.model.transaction.Id transactionId = new seedu.address.model.transaction.Id("100001");
        assertParseSuccess(parser, " " + PREFIX_TRANSACTION + " " + PREFIX_ID + " "
                + memberId.value + transactionId.value, new DeleteTransactionCommand(memberId, transactionId));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTransactionCommand.MESSAGE_USAGE));
    }
}
