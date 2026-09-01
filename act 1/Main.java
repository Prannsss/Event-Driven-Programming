import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    private static final int USER_COUNT = 3;

    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {

            // store the unames & pw using map loop n times
            Map<String, String> credentials = new HashMap<>();
            Map<String, String> displayNames = new HashMap<>(); 

            for (int i = 0; i < USER_COUNT; i++) {
                // ask for username
                System.out.println("----User " + (i + 1) + " account---- \nUsername: ");
                String unameInput = scn.nextLine();
                String reversedUname = reverseString(unameInput);

                // ask for password
                System.out.println("\nEnter User " + (i + 1) + " Password: ");
                String pwInput = scn.nextLine();
                String reversedPw = reverseString(pwInput);

                credentials.put(reversedUname, reversedPw);
                displayNames.put(reversedUname, unameInput);
            }

            // ask to login
            System.out.println("\n---Login to your Account---");
            System.out.print("Username: ");
            String loginUsername = scn.nextLine();

            System.out.print("Password: ");
            String loginPassword = scn.nextLine();
            
            String reversedLoginUser = reverseString(loginUsername);
            String reversedLoginPass = reverseString(loginPassword);

            // display reveresd login credentials if needed for debugging
            // System.out.println("stored username: " + reversedLoginUser);
            // System.out.println("stored password: " + reversedLoginPass);

            // check if existing ang user then validate password niya
            boolean isSuccess = credentials.containsKey(reversedLoginUser)
                    && credentials.get(reversedLoginUser).equals(reversedLoginPass);

            // result
            if (isSuccess) {
                String originalUname = displayNames.get(reversedLoginUser);
                System.out.println("Login Successful! Welcome, " + originalUname + "!");
            } else {
                System.out.println("Access Denied! Invalid username or password.");
            }
        }
    }

    // reverse inputted strings
    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}