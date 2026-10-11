import java.util.Scanner;

/**
 * InputHelper makes keyboard input robust.
 * Instead of crashing on bad values, it prints a message and asks again.
 */
public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    /** Read a required non-blank string. */
    public String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isBlank()) {
                return value;
            }
            System.out.println("Input cannot be blank. Try again.");
        }
    }

    /** Read a whole number that is not below the supplied minimum. */
    public int readInt(String prompt, int min) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number < min) {
                    System.out.println("Enter a number of at least " + min + ".");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("That is not a whole number. Try again.");
            }
        }
    }

    /** Accept Y/Yes or N/No only. */
    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim().toLowerCase();
            if (value.equals("y") || value.equals("yes")) return true;
            if (value.equals("n") || value.equals("no")) return false;
            System.out.println("Please enter Y or N.");
        }
    }

    /** Read a main-menu option within the required range. */
    public int readMenuOption(int min, int max) {
        while (true) {
            int choice = readInt("Choose: ", min);
            if (choice <= max) {
                return choice;
            }
            System.out.println("Enter a number between " + min + " and " + max + ".");
        }
    }
}
