package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REDEEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Id;
import seedu.address.model.member.Point;
import seedu.address.model.transaction.Transaction;

public class RedeemPointCommand extends Command {
    public static final String COMMAND_WORD = "redeem";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redeems points from member id in the ezFoodie. "
            + "Parameters: "
            + "[ " + PREFIX_REDEEM + " Redeem points ]"
            + " " + PREFIX_ID + " ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_REDEEM + " 100"
            + PREFIX_ID + " 10001";

    // todo: need to work on this
    public static final String MESSAGE_SUCCESS = "Redemption is done";

    private final Set<Point> pointsToRedeem;
    private final Id idToRedeem;

    /**
     * Creates an AddTransactionCommand to add the specified {@code Member}
     */
    public RedeemPointCommand(Set<Point> redemptions, Id id) {
        requireNonNull(id);
        pointsToRedeem = redemptions;
        idToRedeem = id;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
//        model.redeemPoints(pointsToRedeem, idToRedeem);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

}