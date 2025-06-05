package moreoptions;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;

public class Password {
    public static void main(String[] args) throws IOException {
        Console console = System.console();
        if(console == null) {
            System.err.println("No console");
            System.exit(1);
        }

        String login = console.readLine("Enter your login: ");
        char[] oldPassword = console.readPassword("Enter your old password: ");

        if(verify(login, oldPassword)) {
            boolean noMatch;
            do {
                char[] newPassword1 = console.readPassword("Enter your new password: ");
                char[] newPassword2 = console.readPassword("Enter new password again: ");
                noMatch = !Arrays.equals(newPassword1, newPassword2);
                if(noMatch) {
                    console.format("Passwords don't match. Try again.%n");
                } else {
                    change(login, newPassword1);
                    console.format("Password for %s changed.%n", login);
                }
                Arrays.fill(newPassword1, ' ');
                Arrays.fill(newPassword2, ' ');
            } while (noMatch);
        }

        Arrays.fill(oldPassword, ' ');
    }

    // dummy change method
    private static void change(String login, char[] newPassword1) {
    }

    // Dummy change method
    private static boolean verify(String login, char[] password) {
        // this method always returns true in this example. Modify this method to verify password according to your rules
        return true;
    }
}
