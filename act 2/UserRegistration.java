import java.util.Scanner;
import java.util.regex.Pattern;

public class UserRegistration {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";

    public static boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidPassword(String password) {
        return password != null && Pattern.matches(PASSWORD_REGEX, password);
    }

    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            System.out.print("Enter email address: ");
            String email = scn.nextLine().trim();

            if (!isValidEmail(email)) {
                System.out.println("Registration failed: Invalid email format.");
                return;
            }

            System.out.print("Enter password: ");
            String password = scn.nextLine();

            if (!isValidPassword(password)) {
                System.out.println("Registration failed!\nPassword must be a minimum of 8 characters,\ninclude at least 1 uppercase letter,\none number and 1 special character.");
                return;
            }

            System.out.println("Registration successful!");
        }
    }
}