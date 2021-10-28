package seedu.address.commons.util;

import java.util.ArrayList;
import java.util.List;

public class CommandUtil {

    private static final int MAX_LENGTH = 30;
    private static final List<String> commands = new ArrayList<>();

    private static int POINTER = 0;

    public static void addCommand(String command) {
        if (!commands.isEmpty() && commands.size() >= MAX_LENGTH) {
            commands.remove(0);
        }
        commands.add(command);
        POINTER = commands.size();
    }

    public static String getPreCommand() {
        if (POINTER > 0) {
            POINTER--;
            return commands.get(POINTER);
        } else {
            POINTER = -1;
            return "";
        }
    }

    public static String getNextCommand() {
        if (POINTER < commands.size() - 1) {
            POINTER++;
            return commands.get(POINTER);
        } else {
            POINTER = commands.size();
            return "";
        }
    }
}
