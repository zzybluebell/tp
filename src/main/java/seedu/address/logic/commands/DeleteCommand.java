package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;

/**
 * Deletes a member identified using it's displayed index from the ezFoodie.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the member identified by the index number used in the displayed member list or member ID.\n"
            + "Parameters:\n"
            + "Delete by index number: " + PREFIX_MEMBER + " [" + PREFIX_INDEX + " INDEX]"
            + " (INDEX must be a positive integer)\n"
            + "Delete by member ID: " + PREFIX_MEMBER + " [" + PREFIX_ID + " ID]\n"
            + "Example:\n"
            + "Delete by index number: " + COMMAND_WORD + " " + PREFIX_MEMBER + " [" + PREFIX_INDEX + " 1]\n"
            + "Delete by member ID: " + COMMAND_WORD + " " + PREFIX_MEMBER + " [" + PREFIX_ID + " 10001]";

    public static final String MESSAGE_DELETE_MEMBER_SUCCESS = "Deleted Member: %1$s";

    private final Index targetIndex;
    private final Id memberId;

    /**
     * Creates an DeleteCommand to delete the specified {@code Member} by index number
     */
    public DeleteCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        memberId = null;
    }

    /**
     * Creates an DeleteCommand to delete the specified {@code Member} by member ID
     */
    public DeleteCommand(Id memberId) {
        requireNonNull(memberId);
        this.memberId = memberId;
        targetIndex = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getFilteredMemberList();

        Member memberToDelete = null;
        if (targetIndex != null) {
            if (targetIndex.getZeroBased() < lastShownList.size()) {
                memberToDelete = lastShownList.get(targetIndex.getZeroBased());
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }
        }
        if (memberId != null) {
            memberToDelete = lastShownList.stream()
                    .filter(member -> memberId.equals(member.getId())).findAny().orElse(null);
        }
        if (memberToDelete != null) {
            model.deleteMember(memberToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_MEMBER_SUCCESS, memberToDelete));
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && (targetIndex == null || targetIndex.equals(((DeleteCommand) other).targetIndex))
                && (memberId == null || memberId.equals(((DeleteCommand) other).memberId))); // state check
    }
}
