import java.util.*;
import java.util.regex.*;

class User {
    String password;
    String phone;

    User(String password, String phone) {
        this.password = password;
        this.phone = phone;
    }
}

public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    static {
        // Add test users
        users.put("user1", new User("Password123!", "+27821234567"));
        users.put("user2", new User("TestPassword456@", "+27829876543"));
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n📋 Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("👋 Goodbye!");
                    return;
                default:
                    System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    static void register() {
        System.out.println("\n🔐 Registration");

        String username;
        while (true) {
            System.out.print("Enter a username: ");
            username = scanner.nextLine();

            if (!username.contains("_") || username.length() > 5) {
                System.out.println("❌ Username must contain an underscore and be no more than 5 characters.");
                continue;
            }
            if (users.containsKey(username)) {
                System.out.println("❌ Username already exists.");
                continue;
            }
            break;
        }

        String password;
        while (true) {
            System.out.print("Enter a password: ");
            password = scanner.nextLine();

            if (!isValidPassword(password)) {
                System.out.println("❌ Password must be at least 8 characters long, contain a capital letter, a number, and a special character.");
                continue;
            }
            break;
        }

        String phone;
        while (true) {
            System.out.print("Enter your phone number (e.g., +27821234567): ");
            phone = scanner.nextLine();

            if (!validatePhoneNumber(phone)) {
                System.out.println("❌ Invalid phone number format.");
                continue;
            }
            break;
        }

        users.put(username, new User(password, phone));
        System.out.println("✅ Registration successful!");
    }

    static void login() {
        System.out.println("\n🔑 Login");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            System.out.println("✅ Login successful! Welcome, " + username + ".");
        } else {
            System.out.println("❌ Invalid username or password. Please try again.");
        }
    }

    static boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        Pattern upperCase = Pattern.compile("[A-Z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern specialChar = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]");

        return upperCase.matcher(password).find() &&
               digit.matcher(password).find() &&
               specialChar.matcher(password).find();
    }

    static boolean validatePhoneNumber(String phone) {
        String pattern = "^\\+[1-9]{1}[0-9]{1,3}[1-9][0-9]{6,9}$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(phone);
        return matcher.matches();
    }
}
