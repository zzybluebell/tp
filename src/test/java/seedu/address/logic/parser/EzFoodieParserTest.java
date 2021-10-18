package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGISTRATION_DATE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.address.testutil.TypicalMembers.AMY;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.commons.status.LoginStatus;
import seedu.address.commons.status.SortStatus;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditMemberDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.commands.LogoutCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.account.Password;
import seedu.address.model.member.CreditSortComparator;
import seedu.address.model.member.EmailContainsKeywordsPredicate;
import seedu.address.model.member.Id;
import seedu.address.model.member.IdContainsKeywordsPredicate;
import seedu.address.model.member.Member;
import seedu.address.model.member.NameContainsKeywordsPredicate;
import seedu.address.model.member.PhoneContainsKeywordsPredicate;
import seedu.address.model.member.RegistrationDateContainsKeywordsPredicate;
import seedu.address.testutil.EditMemberDescriptorBuilder;
import seedu.address.testutil.MemberBuilder;
import seedu.address.testutil.MemberUtil;

public class EzFoodieParserTest {

    private Model model = new ModelManager();
    private final EzFoodieParser parser = new EzFoodieParser(model, ExecutionStatus.TEST);

    @Test
    public void parseCommand_add() throws Exception {

        Member member = new MemberBuilder(AMY).withCredit("0").withTransactions().build();
        AddMemberCommand command = (AddMemberCommand) parser.parseCommand(MemberUtil.getAddCommand(member));
        assertEquals(new AddMemberCommand(member), command);

    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteByIndex() throws Exception {
        LoginStatus.setLoginStatus(LoginStatus.MANAGER);
        DeleteCommand command = (DeleteCommand) parser.parseCommand(DeleteCommand.COMMAND_WORD + " "
                + PREFIX_MEMBER + " " + PREFIX_INDEX + " " + INDEX_FIRST_MEMBER.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_MEMBER), command);
    }

    @Test
    public void parseCommand_deleteById() throws Exception {
        Id id = new Id("10001");
        LoginStatus.setLoginStatus(LoginStatus.MANAGER);
        DeleteCommand command = (DeleteCommand) parser.parseCommand(DeleteCommand.COMMAND_WORD + " "
                + PREFIX_MEMBER + " " + PREFIX_ID + " " + id.value);
        assertEquals(new DeleteCommand(id), command);
    }

    @Test
    public void parseCommand_sortAsc() throws Exception {
        LoginStatus.setLoginStatus(LoginStatus.MANAGER);
        SortCommand command = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + PREFIX_MEMBER + " " + PREFIX_CREDIT + " " + PREFIX_ASC);
        assertEquals(new SortCommand(new CreditSortComparator(SortStatus.ASC)), command);
    }

    @Test
    public void parseCommand_sortDesc() throws Exception {
        LoginStatus.setLoginStatus(LoginStatus.MANAGER);
        SortCommand command = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + PREFIX_MEMBER + " " + PREFIX_CREDIT + " " + PREFIX_DESC);
        assertEquals(new SortCommand(new CreditSortComparator(SortStatus.DESC)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Member member = new MemberBuilder().build();
        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(member).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + PREFIX_MEMBER + " " + PREFIX_INDEX + " " + INDEX_FIRST_MEMBER.getOneBased() + " "
                + MemberUtil.getEditMemberDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_MEMBER, descriptor), command);
    }

    @Test
    public void parseCommand_login() throws Exception {
        assertTrue(parser.parseCommand(LoginCommand.COMMAND_WORD + " "
                + Password.DEFAULT_PLAINTEXT_PASSWORD) instanceof LoginCommand);
    }

    @Test
    public void parseCommand_logout() throws Exception {
        assertTrue(parser.parseCommand(LogoutCommand.COMMAND_WORD) instanceof LogoutCommand);
        assertTrue(parser.parseCommand(LogoutCommand.COMMAND_WORD + " 3") instanceof LogoutCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findById() throws Exception {
        List<String> keywords = Arrays.asList("10001", "10002", "10003");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_ID + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new IdContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByName() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_NAME + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByPhone() throws Exception {
        List<String> keywords = Arrays.asList("87328807", "99272758", "62784325");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_PHONE + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new PhoneContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByEmail() throws Exception {
        List<String> keywords = Arrays.asList("ben@gmail.com", "sunny@gmail.com", "jerry@gmail.com");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_EMAIL + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new EmailContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByRegistrationDate() throws Exception {
        List<String> keywords = Arrays.asList("2021-01-01", "2021-01-02", "2021-01-10");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_REGISTRATION_DATE + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new RegistrationDateContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " " + PREFIX_MEMBER) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " " + PREFIX_MEMBER + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
