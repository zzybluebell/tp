package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.member.Address;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.RegistrationTimestamp;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Member objects.
 */
public class MemberBuilder {

    public static final String DEFAULT_ID = "00101";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REGISTRATION_TIMESTAMP = "1610236800000";

    private Id id;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private RegistrationTimestamp registrationTimestamp;
    private Set<Tag> tags;
    private Set<Transaction> transactions;

    /**
     * Creates a {@code MemberBuilder} with the default details.
     */
    public MemberBuilder() {
        id = new Id(DEFAULT_ID);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        registrationTimestamp = new RegistrationTimestamp(DEFAULT_REGISTRATION_TIMESTAMP);
        tags = new HashSet<>();
        transactions = new HashSet<>();
    }

    /**
     * Initializes the MemberBuilder with the data of {@code memberToCopy}.
     */
    public MemberBuilder(Member memberToCopy) {
        id = memberToCopy.getId();
        name = memberToCopy.getName();
        phone = memberToCopy.getPhone();
        email = memberToCopy.getEmail();
        address = memberToCopy.getAddress();
        registrationTimestamp = memberToCopy.getRegistrationTimestamp();
        tags = new HashSet<>(memberToCopy.getTags());
        transactions = new HashSet<>(memberToCopy.getTransactions());
    }

    /**
     * Sets the {@code Id} of the {@code Member} that we are building.
     */
    public MemberBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Member} that we are building.
     */
    public MemberBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Member} that we are building.
     */
    public MemberBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Member} that we are building.
     */
    public MemberBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Member} that we are building.
     */
    public MemberBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Member} that we are building.
     */
    public MemberBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code RegistrationTimestamp} of the {@code Member} that we are building.
     */
    public MemberBuilder withRegistrationTimestamp(String registrationTimestamp) {
        this.registrationTimestamp = new RegistrationTimestamp(registrationTimestamp);
        return this;
    }

    public Member build() {
        return new Member(id, name, phone, email, address, registrationTimestamp, tags, transactions);
    }

}
