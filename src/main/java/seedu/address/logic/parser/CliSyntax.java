package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_MEMBER = new Prefix("-m");
    public static final Prefix PREFIX_ID = new Prefix("-id");
    public static final Prefix PREFIX_INDEX = new Prefix("-r");
    public static final Prefix PREFIX_NAME = new Prefix("-n");
    public static final Prefix PREFIX_PHONE = new Prefix("-p");
    public static final Prefix PREFIX_EMAIL = new Prefix("-e");
    public static final Prefix PREFIX_ADDRESS = new Prefix("-a");
    public static final Prefix PREFIX_REGISTRATION_DATE = new Prefix("-d");
    public static final Prefix PREFIX_CREDIT = new Prefix("-c");
    public static final Prefix PREFIX_TAG = new Prefix("-g");
    public static final Prefix PREFIX_TRANSACTION = new Prefix("-t");
    public static final Prefix PREFIX_REDEEM = new Prefix("-r");

    /* Only used in sort command */
    public static final Prefix PREFIX_ASC = new Prefix("-a");
    public static final Prefix PREFIX_DESC = new Prefix("-d");
}
