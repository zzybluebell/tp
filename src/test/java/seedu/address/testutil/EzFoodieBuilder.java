package seedu.address.testutil;

import seedu.address.model.EzFoodie;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building EzFoodie objects.
 * Example usage: <br>
 *     {@code EzFoodie ef = new EzFoodieBuilder().withPerson("John", "Doe").build();}
 */
public class EzFoodieBuilder {

    private EzFoodie ezFoodie;

    public EzFoodieBuilder() {
        ezFoodie = new EzFoodie();
    }

    public EzFoodieBuilder(EzFoodie ezFoodie) {
        this.ezFoodie = ezFoodie;
    }

    /**
     * Adds a new {@code Person} to the {@code EzFoodie} that we are building.
     */
    public EzFoodieBuilder withPerson(Person person) {
        ezFoodie.addPerson(person);
        return this;
    }

    public EzFoodie build() {
        return ezFoodie;
    }
}
