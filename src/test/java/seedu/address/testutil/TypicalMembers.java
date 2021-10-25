package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDIT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDIT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGISTRATION_TIMESTAMP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGISTRATION_TIMESTAMP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSACTION_200;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSACTION_300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.EzFoodie;
import seedu.address.model.member.Member;

/**
 * A utility class containing a list of {@code Member} objects to be used in tests.
 */
public class TypicalMembers {

    public static final Member ALICE = new MemberBuilder().withId("00001").withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRegistrationTimestamp("1609459200000").withCredit("610").withPoint("610")
            .withTags("friends").withTransactions("567.54", "43.34").build();
    public static final Member BENSON = new MemberBuilder().withId("00002").withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withPhone("98765432").withRegistrationTimestamp("1609462800000").withCredit("645")
            .withTags("owesMoney", "friends").withTransactions("645.39").build();
    public static final Member CARL = new MemberBuilder().withId("00003").withName("Carl Kurz")
            .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street")
            .withRegistrationTimestamp("1609466400000").withCredit("272").withTransactions("272.57").build();
    public static final Member DANIEL = new MemberBuilder().withId("00004").withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street")
            .withRegistrationTimestamp("1609470000000").withCredit("0").withTags("friends").build();
    public static final Member ELLE = new MemberBuilder().withId("00005").withName("Elle Meyer")
            .withPhone("9482224").withEmail("werner@example.com").withAddress("michegan ave")
            .withRegistrationTimestamp("1609473600000").withCredit("0").build();
    public static final Member FIONA = new MemberBuilder().withId("00006").withName("Fiona Kunz")
            .withPhone("9482427").withEmail("lydia@example.com").withAddress("little tokyo")
            .withRegistrationTimestamp("1609477200000").withCredit("0").build();
    public static final Member GEORGE = new MemberBuilder().withId("00007").withName("George Best")
            .withPhone("9482442").withEmail("anna@example.com").withAddress("4th street")
            .withRegistrationTimestamp("1609480800000").withCredit("0").build();

    // Manually added
    public static final Member HOON = new MemberBuilder().withId("00008").withName("Hoon Meier")
            .withPhone("8482424").withEmail("stefan@example.com").withAddress("little india")
            .withRegistrationTimestamp("1609484400000").withCredit("0").build();
    public static final Member IDA = new MemberBuilder().withId("00009").withName("Ida Mueller")
            .withPhone("8482131").withEmail("hans@example.com").withAddress("chicago ave")
            .withRegistrationTimestamp("1609488000000").withCredit("0").build();

    // Manually added - Member's details found in {@code CommandTestUtil}
    public static final Member AMY = new MemberBuilder().withId(VALID_ID_AMY).withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withRegistrationTimestamp(VALID_REGISTRATION_TIMESTAMP_AMY).withCredit(VALID_CREDIT_AMY)
            .withTags(VALID_TAG_FRIEND).withTransactions(VALID_TRANSACTION_200).build();
    public static final Member BOB = new MemberBuilder().withId(VALID_ID_BOB).withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withRegistrationTimestamp(VALID_REGISTRATION_TIMESTAMP_BOB).withCredit(VALID_CREDIT_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withTransactions(VALID_TRANSACTION_200, VALID_TRANSACTION_300).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalMembers() {} // prevents instantiation

    /**
     * Returns an {@code EzFoodie} with all the typical members.
     */
    public static EzFoodie getTypicalEzFoodie() {
        EzFoodie ef = new EzFoodie();
        for (Member member : getTypicalMembers()) {
            ef.addMember(member);
        }
        return ef;
    }

    public static List<Member> getTypicalMembers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
