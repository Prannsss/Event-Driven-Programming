import java.util.Arrays;
import java.util.regex.Pattern;

public class UserRegistration {

    // Precompile once
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^(?=.{1,64}@)[a-zA-Z0-9]+([._%+-][a-zA-Z0-9]+)*" +
            "@[a-zA-Z0-9]+([.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,6}$"
    );

    // Min 8 chars, at least 1 uppercase, 1 digit, 1 special char
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'|\\\\,.<>/?]).{8,}$"
    );

    private static final int MAX_EMAIL_LENGTH = 254;

    public static boolean isValidEmail(String email) {
        return email != null
                && email.length() <= MAX_EMAIL_LENGTH
                && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(char[] password) {
        if (password == null || password.length == 0) return false;
        return PASSWORD_PATTERN.matcher(CharBuffer_wrap(password)).matches();
    }

    // Wraps a char as a CharSequence without allocating a string
    private static CharSequence CharBuffer_wrap(char[] chars) {
        return java.nio.CharBuffer.wrap(chars);
    }

    public static void main(String[] args) {
        java.io.Console console = System.console();

        if (console == null) {
            // No console available failsafe
            System.err.println("No console available. Run this from a terminal " +
                    "so the password can be entered securely.");
            System.exit(1);
        }

        String email = console.readLine("Enter email address: ").trim();

        if (!isValidEmail(email)) {
            System.out.println("Registration failed: Invalid email format.");
            return;
        }

        char[] password = console.readPassword("Enter password: ");

        try {
            if (!isValidPassword(password)) {
                System.out.println(
                        "Registration failed!\n" +
                        "Password must be a minimum of 8 characters,\n" +
                        "include at least 1 uppercase letter,\n" +
                        "one number and 1 special character."
                );
                return;
            }

            System.out.println("Registration successful!");
        } finally {
            // Wipe the password from memory
            Arrays.fill(password, '\0');
        }
    }
}