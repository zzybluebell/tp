package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.EzFoodie;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TransactionHistory;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EzFoodie} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Id("1234"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new TransactionHistory()),
            new Person(new Name("Bernice Yu"), new Id("1235"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new TransactionHistory()),
            new Person(new Name("Charlotte Oliveiro"), new Id("1236"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new TransactionHistory()),
            new Person(new Name("David Li"), new Id("1236"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new TransactionHistory()),
            new Person(new Name("Irfan Ibrahim"), new Id("1237"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new TransactionHistory()),
            new Person(new Name("Roy Balakrishnan"), new Id("1238"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new TransactionHistory())
        };
    }

    public static ReadOnlyEzFoodie getSampleEzFoodie() {
        EzFoodie sampleEf = new EzFoodie();
        for (Person samplePerson : getSamplePersons()) {
            sampleEf.addPerson(samplePerson);
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

}
