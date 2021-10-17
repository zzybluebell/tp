package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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
 * Jackson-friendly version of {@link Member}.
 */
class JsonAdaptedMember {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Member's %s field is missing!";

    private final String id;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String registrationTimestamp;
    private final String credit;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedTransaction> transactions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedMember} with the given member details.
     */
    @JsonCreator
    public JsonAdaptedMember(@JsonProperty("id") String id, @JsonProperty("name") String name,
            @JsonProperty("phone") String phone, @JsonProperty("email") String email,
            @JsonProperty("address") String address,
            @JsonProperty("registrationTimestamp") String registrationTimestamp,
            @JsonProperty("credit") String credit,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("transactions") List<JsonAdaptedTransaction> transactions) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.registrationTimestamp = registrationTimestamp;
        this.credit = credit;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (transactions != null) {
            this.transactions.addAll(transactions);
        }
    }

    /**
     * Converts a given {@code Member} into this class for Jackson use.
     */
    public JsonAdaptedMember(Member source) {
        id = source.getId().value;
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        registrationTimestamp = source.getRegistrationTimestamp().value;
        credit = source.getCredit().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        transactions.addAll(source.getTransactions().stream()
                .map(JsonAdaptedTransaction::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted member object into the model's {@code Member} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted member.
     */
    public Member toModelType() throws IllegalValueException {
        final List<Tag> memberTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            memberTags.add(tag.toModelType());
        }

        final List<Transaction> memberTransactions = new ArrayList<>();
        for (JsonAdaptedTransaction transaction : transactions) {
            memberTransactions.add(transaction.toModelType());
        }

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(id)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Id modelId = new Id(id);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (registrationTimestamp == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, RegistrationTimestamp.class.getSimpleName()));
        }
        if (!RegistrationTimestamp.isValidRegistrationTimestamp(registrationTimestamp)) {
            throw new IllegalValueException(RegistrationTimestamp.MESSAGE_CONSTRAINTS);
        }
        final RegistrationTimestamp modelRegistrationTimestamp = new RegistrationTimestamp(registrationTimestamp);

        if (credit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Credit.class.getSimpleName()));
        }
        if (!Credit.isValidCredit(credit)) {
            throw new IllegalValueException(Credit.MESSAGE_CONSTRAINTS);
        }
        final Credit modelCredit = new Credit(credit);

        final Set<Tag> modelTags = new HashSet<>(memberTags);

        final Set<Transaction> modelTransactions = new HashSet<>(memberTransactions);

        return new Member(modelId, modelName, modelPhone, modelEmail, modelAddress, modelRegistrationTimestamp,
                modelCredit, modelTags, modelTransactions);
    }

}
