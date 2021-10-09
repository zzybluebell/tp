package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalEzFoodie;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class EzFoodieTest {

    private final EzFoodie ezFoodie = new EzFoodie();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), ezFoodie.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ezFoodie.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEzFoodie_replacesData() {
        EzFoodie newData = getTypicalEzFoodie();
        ezFoodie.resetData(newData);
        assertEquals(newData, ezFoodie);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        EzFoodieStub newData = new EzFoodieStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> ezFoodie.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ezFoodie.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInEzFoodie_returnsFalse() {
        assertFalse(ezFoodie.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInEzFoodie_returnsTrue() {
        ezFoodie.addPerson(ALICE);
        assertTrue(ezFoodie.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInEzFoodie_returnsTrue() {
        ezFoodie.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(ezFoodie.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> ezFoodie.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyEzFoodie whose persons list can violate interface constraints.
     */
    private static class EzFoodieStub implements ReadOnlyEzFoodie {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        EzFoodieStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
