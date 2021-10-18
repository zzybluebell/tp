package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import java.util.Set;

import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.EditCommand.EditMemberDescriptor;
import seedu.address.model.member.Member;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class for Member.
 */
public class MemberUtil {

    /**
     * Returns an add command string for adding the {@code member}.
     */
    public static String getAddCommand(Member member) {
        return AddMemberCommand.COMMAND_WORD + " " + getMemberDetails(member);
    }

    /**
     * Returns the part of command string for the given {@code member}'s details.
     */
    public static String getMemberDetails(Member member) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MEMBER + " ");
        sb.append(PREFIX_NAME + member.getName().fullName + " ");
        sb.append(PREFIX_PHONE + member.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + member.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + member.getAddress().value + " ");
        member.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        member.getTransactions().stream().forEach(
            s -> sb.append(PREFIX_TRANSACTION + s.transactionAmount + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditMemberDescriptor}'s details.
     */
    public static String getEditMemberDescriptorDetails(EditMemberDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getTransactions().isPresent()) {
            Set<Transaction> transactions = descriptor.getTransactions().get();
            if (transactions.isEmpty()) {
                sb.append(PREFIX_TRANSACTION);
            } else {
                transactions.forEach(s -> sb.append(PREFIX_TRANSACTION).append(s.transactionAmount).append(" "));
            }
        }
        return sb.toString();
    }
}
