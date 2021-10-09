package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the ezFoodie data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EzFoodie ezFoodie;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given ezFoodie and userPrefs.
     */
    public ModelManager(ReadOnlyEzFoodie ezFoodie, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(ezFoodie, userPrefs);

        logger.fine("Initializing with ezFoodie: " + ezFoodie + " and user prefs " + userPrefs);

        this.ezFoodie = new EzFoodie(ezFoodie);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.ezFoodie.getPersonList());
    }

    public ModelManager() {
        this(new EzFoodie(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getEzFoodieFilePath() {
        return userPrefs.getEzFoodieFilePath();
    }

    @Override
    public void setEzFoodieFilePath(Path ezFoodieFilePath) {
        requireNonNull(ezFoodieFilePath);
        userPrefs.setEzFoodieFilePath(ezFoodieFilePath);
    }

    //=========== EzFoodie ===================================================================================

    @Override
    public void setEzFoodie(ReadOnlyEzFoodie ezFoodie) {
        this.ezFoodie.resetData(ezFoodie);
    }

    @Override
    public ReadOnlyEzFoodie getEzFoodie() {
        return ezFoodie;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return ezFoodie.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        ezFoodie.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        ezFoodie.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        ezFoodie.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedEzFoodie}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return ezFoodie.equals(other.ezFoodie)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
