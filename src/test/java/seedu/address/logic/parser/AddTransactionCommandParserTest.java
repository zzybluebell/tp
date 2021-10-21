package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRANSACTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TRANSACTION_DESC_200;
import static seedu.address.logic.commands.CommandTestUtil.TRANSACTION_DESC_300;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSACTION_200;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSACTION_300;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalMembers.AMY;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.AddTransactionCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.member.Id;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.MemberBuilder;

class AddTransactionCommandParserTest {

    private Model model = new ModelManager();
    private AddTransactionCommandParser parser = new AddTransactionCommandParser(model, ExecutionStatus.TEST);

    @Test
    public void parse_allFieldsPresent_success() {
        Set<Transaction> expectedTransactions = new MemberBuilder(AMY)
                .withTransactions(VALID_TRANSACTION_200, VALID_TRANSACTION_300).build().getTransactions();
        Id expectedId = new Id(VALID_ID_AMY);

        // multiple transactions - all accepted
        assertParseSuccess(parser, TRANSACTION_DESC_200 + TRANSACTION_DESC_300 + ID_DESC_AMY,
                new AddTransactionCommand(expectedTransactions, expectedId));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid transaction
        assertParseFailure(parser, INVALID_TRANSACTION_DESC + ID_DESC_AMY, Transaction.MESSAGE_CONSTRAINTS);
    }
}
