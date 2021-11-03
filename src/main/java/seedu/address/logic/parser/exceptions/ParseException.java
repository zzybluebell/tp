package seedu.address.logic.parser.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {
    /**
     * Parses the exception with string message.
     *
     * @param message
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * Parses the exception with string message amd throwable cause.
     *
     * @param message
     * @param cause
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
