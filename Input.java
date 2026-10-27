import java.util.Scanner;

// Helper class: lahat ng pagbabasa ng input ay dito dumadaan para may validation.
public class Input {

    static Scanner sc = new Scanner(System.in);

    // Magbasa ng whole number. Uulit hanggang valid ang input.
    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

    // Magbasa ng whole number na nasa pagitan ng min at max (inclusive).
    static int readInt(String prompt, int min, int max) {
        while (true) {
            int n = readInt(prompt);
            if (n >= min && n <= max) {
                return n;
            }
            System.out.println("Please enter a number from " + min + " to " + max + ".");
        }
    }

    // Magbasa ng text na hindi empty.
    static String readLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input must not be empty.");
        }
    }
}
