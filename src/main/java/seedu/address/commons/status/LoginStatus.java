package seedu.address.commons.status;

import javafx.beans.property.SimpleStringProperty;

public enum LoginStatus {

    STAFF("STAFF"),
    MANAGER("MANAGER");

    public static final SimpleStringProperty CURRENT_STATUS = new SimpleStringProperty(STAFF.value);

    private final String value;

    LoginStatus(String value) {
        this.value = value;
    }

    /**
     * Returns {@code LoginStatus}.
     */
    public static LoginStatus getLoginStatus() {
        if (CURRENT_STATUS.getValue().equals(STAFF.value)) {
            return STAFF;
        } else {
            return MANAGER;
        }
    }

    /**
     * Resets {@code CURRENT_STATUS} by {@code LoginStatus}.
     */
    public static void setLoginStatus(LoginStatus loginStatus) {
        CURRENT_STATUS.setValue(loginStatus.value);
    }
}