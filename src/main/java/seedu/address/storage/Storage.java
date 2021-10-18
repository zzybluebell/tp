package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends EzFoodieStorage, AccountStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Optional<ReadOnlyAccount> readAccount() throws DataConversionException, IOException;

    @Override
    void saveAccount(ReadOnlyAccount account) throws IOException;

    @Override
    Path getEzFoodieFilePath();

    @Override
    Optional<ReadOnlyEzFoodie> readEzFoodie() throws DataConversionException, IOException;

    @Override
    void saveEzFoodie(ReadOnlyEzFoodie ezFoodie) throws IOException;

}
