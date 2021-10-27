package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_MEMBER = new Prefix("-m");
    public static final Prefix PREFIX_ID = new Prefix("-id");
    public static final Prefix PREFIX_INDEX = new Prefix("-row");
    public static final Prefix PREFIX_NAME = new Prefix("-n");
    public static final Prefix PREFIX_PHONE = new Prefix("-p");
    public static final Prefix PREFIX_EMAIL = new Prefix("-e");
    public static final Prefix PREFIX_ADDRESS = new Prefix("-a");
    public static final Prefix PREFIX_DATE = new Prefix("-date");
    public static final Prefix PREFIX_CREDIT = new Prefix("-c");
    public static final Prefix PREFIX_TRANSACTION = new Prefix("-txn");
    public static final Prefix PREFIX_BILLING = new Prefix("-b");
    public static final Prefix PREFIX_RESERVATION = new Prefix("-rsvn");
    public static final Prefix PREFIX_DATE_TIME = new Prefix("-time");
    public static final Prefix PREFIX_REMARK = new Prefix("-rm");
    public static final Prefix PREFIX_TAG = new Prefix("-tag");

    /* Only used in sort command */
    public static final Prefix PREFIX_ASC = new Prefix("-a");
    public static final Prefix PREFIX_DESC = new Prefix("-d");
}
