package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalMembers.AMY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.member.Address;
import seedu.address.model.member.Email;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.MemberBuilder;

public class AddMemberCommandParserTest {

    private Model model = new ModelManager();
    private AddMemberCommandParser parser = new AddMemberCommandParser(model, ExecutionStatus.TEST);

    @Test
    public void parse_allFieldsPresent_success() {
        Member expectedMember = new MemberBuilder(AMY).withCredit("0").withTags(VALID_TAG_FRIEND).withTransactions()
                .build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MEMBER_DESC + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND, new AddMemberCommand(expectedMember));

        // multiple names - last name accepted
        assertParseSuccess(parser, MEMBER_DESC + NAME_DESC_BOB + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND, new AddMemberCommand(expectedMember));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, MEMBER_DESC + NAME_DESC_AMY + PHONE_DESC_BOB + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND, new AddMemberCommand(expectedMember));

        // multiple emails - last email accepted
        assertParseSuccess(parser, MEMBER_DESC + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_BOB
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND, new AddMemberCommand(expectedMember));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, MEMBER_DESC + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_BOB + ADDRESS_DESC_AMY + TAG_DESC_FRIEND, new AddMemberCommand(expectedMember));

        // multiple tags - all accepted
        Member expectedMemberMultipleTags = new MemberBuilder(AMY).withCredit("0")
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).withTransactions().build();
        assertParseSuccess(parser, MEMBER_DESC + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddMemberCommand(expectedMemberMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Member expectedMember = new MemberBuilder(AMY).withCredit("0").withTags().withTransactions().build();
        assertParseSuccess(parser, MEMBER_DESC + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY, new AddMemberCommand(expectedMember));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMemberCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, MEMBER_DESC + VALID_NAME_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, MEMBER_DESC + NAME_DESC_AMY + VALID_PHONE_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, MEMBER_DESC + NAME_DESC_AMY + PHONE_DESC_AMY + VALID_EMAIL_AMY + ADDRESS_DESC_AMY,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, MEMBER_DESC + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + VALID_ADDRESS_AMY,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, MEMBER_DESC + VALID_NAME_AMY + VALID_PHONE_AMY + VALID_EMAIL_AMY
                + VALID_ADDRESS_AMY, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, MEMBER_DESC + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, MEMBER_DESC + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, MEMBER_DESC + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, MEMBER_DESC + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, MEMBER_DESC + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, MEMBER_DESC + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMemberCommand.MESSAGE_USAGE));
    }
}