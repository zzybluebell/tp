package seedu.address.model.util;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.EncryptUtil;
import seedu.address.model.Account;
import seedu.address.model.EzFoodie;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.account.Password;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.RegistrationTimestamp;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Contains utility methods for populating {@code EzFoodie} with sample data.
 */
public class SampleDataUtil {
    public static Member[] getSampleMembers() {
        return new Member[] {
            new Member(new Id("10001"), new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                new RegistrationTimestamp("1609545600000"), new Credit("90"), getTagSet("friends"),
                getTransactionSet("90.00")),
            new Member(new Id("10002"), new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new RegistrationTimestamp("1609549200000"), new Credit("525"), getTagSet("colleagues", "friends"),
                getTransactionSet("200.12", "325.30")),
            new Member(new Id("10003"), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new RegistrationTimestamp("1609552800000"), new Credit("150"), getTagSet("neighbours"),
                getTransactionSet("150.00")),
            new Member(new Id("10004"), new Name("David Li"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new RegistrationTimestamp("1609556400000"), new Credit("401"), getTagSet("family"),
                getTransactionSet("100.50", "200.50", "100.00")),
            new Member(new Id("10005"), new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                new RegistrationTimestamp("1609560000000"), new Credit("1010"), getTagSet("classmates"),
                getTransactionSet("1000.00", "10.00")),
            new Member(new Id("10006"), new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                new RegistrationTimestamp("1609563600000"), new Credit("110"), getTagSet("colleagues"),
                getTransactionSet("10.00", "100.10"))
        };
    }

    public static ReadOnlyAccount getDefaultPassword() {
        Password password;
        try {
            password = new Password(EncryptUtil.hash(Password.DEFAULT_PLAINTEXT_PASSWORD));
        } catch (NoSuchAlgorithmException e) {
            password = new Password("");
        }
        return new Account(password);
    }

    public static ReadOnlyEzFoodie getSampleEzFoodie() {
        EzFoodie sampleEf = new EzFoodie();
        for (Member sampleMember : getSampleMembers()) {
            sampleEf.addMember(sampleMember);
        }
        return sampleEf;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a transaction set containing the list of strings given.
     */
    public static Set<Transaction> getTransactionSet(String... strings) {
        return Arrays.stream(strings)
                .map(Transaction::new)
                .collect(Collectors.toSet());
    }

}
