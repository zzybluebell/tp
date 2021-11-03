package seedu.address.commons.core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Config values used by the application
 */
public class Config {
    /**
     * Gets the default congig json file
     */
    public static final Path DEFAULT_CONFIG_FILE = Paths.get("config.json");

    // Config values customizable through config file
    private Level logLevel = Level.INFO;
    private Path userPrefsFilePath = Paths.get("preferences.json");

    /**
     * Gets Log Level
     * @return Level
     */
    public Level getLogLevel() {
        return logLevel;
    }

    /**
     * Sets Log Level
     * @param logLevel
     */
    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * Gets User Prefs File Path
     * @return Path
     */
    public Path getUserPrefsFilePath() {
        return userPrefsFilePath;
    }

    /**
     * Sets User Prefs File Path
     * @param userPrefsFilePath
     */
    public void setUserPrefsFilePath(Path userPrefsFilePath) {
        this.userPrefsFilePath = userPrefsFilePath;
    }

    /**
     * Overrides the equal method
     * @param other
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Config)) { //this handles null as well.
            return false;
        }

        Config o = (Config) other;

        return Objects.equals(logLevel, o.logLevel)
                && Objects.equals(userPrefsFilePath, o.userPrefsFilePath);
    }

    /**
     * Overrides hashcode method
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(logLevel, userPrefsFilePath);
    }

    /**
     * Overrides toString method
     * @return String the log level and Config file location
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current log level : " + logLevel);
        sb.append("\nPreference file Location : " + userPrefsFilePath);
        return sb.toString();
    }

}
