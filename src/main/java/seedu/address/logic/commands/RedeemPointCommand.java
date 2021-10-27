package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REDEEM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Timestamp;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

public class RedeemPointCommand extends Command {

    public static final String COMMAND_WORD = "redeem";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redeems points from member id in the ezFoodie. "
            + "Parameters: "
            + PREFIX_REDEEM + " [points]"
            + " " + PREFIX_ID + " [ID]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_REDEEM + " 100 "
            + PREFIX_ID + " 10001";

    // todo: need to work on this
    public static final String MESSAGE_SUCCESS_REDEMPTION = "Redemption is done";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEMBER = "This member already exists in the ezFoodie.";

    private final List<Point> pointsToRedeemList = new ArrayList<>();
    private final Id idToRedeem;
    private final Index indexToRedeem;

    /**
     * Creates an redeemPointsCommand to add the specified {@code Member}
     */
    public RedeemPointCommand(List<Point> pointsToRedeemList, Id id) {
        requireNonNull(id);
        this.pointsToRedeemList.addAll(pointsToRedeemList);
        this.idToRedeem = id;
        this.indexToRedeem = null;
    }

    /**
     * Creates an redeemPointsCommand to add the specified {@code Member}
     */
    public RedeemPointCommand(List<Point> pointsToRedeemList, Index index) {
        requireNonNull(index);
        this.pointsToRedeemList.addAll(pointsToRedeemList);
        this.indexToRedeem = index;
        this.idToRedeem = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();

        Member memberToEdit = null;
        if (indexToRedeem != null) {
            if (indexToRedeem.getZeroBased() < lastShownList.size()) {
                memberToEdit = lastShownList.get(indexToRedeem.getZeroBased());
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }
        }
        if (idToRedeem != null) {
            memberToEdit = lastShownList.stream()
                    .filter(member -> idToRedeem.equals(member.getId())).findAny().orElse(null);
        }
        if (memberToEdit != null) {
            Member toRedeemPointsMember = createToRedeemPointsMember(memberToEdit, pointsToRedeemList);
            if (model.hasMember(toRedeemPointsMember, member -> member.getId() != toRedeemPointsMember.getId())) {
                throw new CommandException(MESSAGE_DUPLICATE_MEMBER);
            }
            model.setMember(memberToEdit, toRedeemPointsMember);
            model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
            return new CommandResult(String.format(MESSAGE_SUCCESS_REDEMPTION, toRedeemPointsMember));
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit}
     * edited with {@code editMemberDescriptor}.
     */
    private static Member createToRedeemPointsMember(Member memberToRedeemPoints, List<Point> points) {
        assert memberToRedeemPoints != null;

        Id id = memberToRedeemPoints.getId();
        Name updatedName = memberToRedeemPoints.getName();
        Phone updatedPhone = memberToRedeemPoints.getPhone();
        Email updatedEmail = memberToRedeemPoints.getEmail();
        Address updatedAddress = memberToRedeemPoints.getAddress();
        Timestamp updateTimestamp = memberToRedeemPoints.getTimestamp();
        Set<Tag> updatedTags = memberToRedeemPoints.getTags();
        List<Transaction> updatedTransactions = memberToRedeemPoints.getTransactions();
        Set<Reservation> updateReservations = memberToRedeemPoints.getReservations();
        Credit updateCredit = memberToRedeemPoints.getCredit();
        Point toRedeemedPointSum = new Point("" + Math.min(points.stream()
                .mapToInt(pointToUpdate -> (int) pointToUpdate.getDoubleValue()).sum(), Point.MAX));
        Point updatePoint = new Point(String.valueOf(memberToRedeemPoints.getPoint().getIntValue()
                - toRedeemedPointSum.getIntValue()));
        return new Member(id, updatedName, updatedPhone, updatedEmail, updatedAddress, updateTimestamp, updateCredit,
                updatePoint, updatedTransactions, updateReservations, updatedTags);
    }
}
