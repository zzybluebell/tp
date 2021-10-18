package seedu.address.commons.exceptions;

/**
 * Represents an error during insufficient permission.
 */
public class PermissionException extends Exception {
    public PermissionException(String message) {
        super(message);
    }

}
