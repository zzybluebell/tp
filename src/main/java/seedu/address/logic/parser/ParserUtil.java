package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.EncryptUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.account.Password;
import seedu.address.model.member.Address;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.RegistrationTimestamp;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String id} into a {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static Id parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!Id.isValidId(trimmedId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
        return new Id(trimmedId);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String registrationTimestamp} into an {@code RegistrationTimestamp}.
     */
    public static RegistrationTimestamp parseRegistrationTimestamp(String registrationTimestamp) throws ParseException {
        requireNonNull(registrationTimestamp);
        String trimmedRegistrationTimestamp = registrationTimestamp.trim();
        if (!RegistrationTimestamp.isValidRegistrationTimestamp(trimmedRegistrationTimestamp)) {
            throw new ParseException(RegistrationTimestamp.MESSAGE_CONSTRAINTS);
        }
        return new RegistrationTimestamp(trimmedRegistrationTimestamp);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String transaction} into a {@code Transaction}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code transaction} is invalid.
     */
    public static Transaction parseTransaction(String transaction) throws ParseException {
        requireNonNull(transaction);
        String trimmedTransaction = transaction.trim();
        if (!Transaction.isValidTransactionAmount(trimmedTransaction)) {
            throw new ParseException(Transaction.MESSAGE_CONSTRAINTS);
        }
        return new Transaction(trimmedTransaction);
    }

    /**
     * Parses {@code Collection<String> transactions} into a {@code Set<Transaction>}.
     */
    public static Set<Transaction> parseTransactions(Collection<String> transactions) throws ParseException {
        requireNonNull(transactions);
        final Set<Transaction> transactionSet = new HashSet<>();
        for (String transactionAmount : transactions) {
            transactionSet.add(parseTransaction(transactionAmount));
        }
        return transactionSet;
    }

    /**
     * Parses {@code password} into a {@code Password} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code password} is invalid.
     */
    public static Password parsePassword(String password) throws ParseException {
        String trimmedPassword = password.trim();
        String hashedTrimmedPassword;
        try {
            hashedTrimmedPassword = EncryptUtil.hash(trimmedPassword);
        } catch (NoSuchAlgorithmException e) {
            hashedTrimmedPassword = "";
        }
        if (!Password.isValidPassword(hashedTrimmedPassword)) {
            throw new ParseException(Password.MESSAGE_CONSTRAINTS);
        }
        return new Password(hashedTrimmedPassword);
    }
}
