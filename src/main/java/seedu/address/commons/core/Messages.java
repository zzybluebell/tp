package seedu.address.commons.core;

import seedu.address.commons.status.LoginStatus;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX = "The member index provided is invalid";
    public static final String MESSAGE_INVALID_MEMBER_DISPLAYED_ID = "The member ID provided is invalid";
    public static final String MESSAGE_INVALID_TRANSACTION_DISPLAYED_ID = "The transaction ID provided is invalid";
    public static final String MESSAGE_INVALID_RESERVATION_DISPLAYED_ID = "The reservation ID provided is invalid";
    public static final String MESSAGE_MEMBERS_LISTED_OVERVIEW = "%1$d members listed!";
    public static final String MESSAGE_PERMISSION_DENIED = "Permission denied! Please login as " + LoginStatus.MANAGER;

}
