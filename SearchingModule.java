import java.util.Arrays;

public class SearchingModule {

    static int[] data = new int[0]; // ang array na hahanapan

    public static void run() {
        System.out.println("\nAbout: Searching finds the position of a value in an array.");
        int choice;
        do {
            System.out.println("\n--- SEARCHING ---");
            System.out.println("1. Enter / replace array");
            System.out.println("2. Linear Search");
            System.out.println("3. Binary Search");
            System.out.println("4. Interpolation Search");
            System.out.println("5. Display array");
            System.out.println("0. Back to Main Menu");
            choice = Input.readInt("Enter your choice: ", 0, 5);

            if (choice >= 2 && choice <= 4 && data.length == 0) {
                System.out.println("No array yet! Please choose option 1 first.");
                continue;
            }

            switch (choice) {
                case 1: enterArray(); break;
                case 2: linearSearch(); break;
                case 3: binarySearch(); break;
                case 4: interpolationSearch(); break;
                case 5: show("Array", data); break;
            }
        } while (choice != 0);
    }

    static void enterArray() {
        int n = Input.readInt("How many elements (1 to 20)? ", 1, 20);
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = Input.readInt("Enter element " + (i + 1) + ": ");
        }
        show("Array", data);
    }

    static void show(String label, int[] a) {
        if (a.length == 0) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.println(label + ": " + Arrays.toString(a));
    }

    // Para sa binary at interpolation: kailangan sorted.
    // Kung hindi sorted, gagawa ng sorted COPY (hindi ginagalaw ang original).
    static int[] getSortedArray() {
        int[] copy = Arrays.copyOf(data, data.length);
        Arrays.sort(copy);
        if (!Arrays.equals(copy, data)) {
            System.out.println("Note: The array is not sorted, so a sorted copy will be searched.");
        }
        show("Sorted array", copy);
        return copy;
    }

    static void linearSearch() {
        int target = Input.readInt("Enter value to search: ");
        show("Array", data);
        for (int i = 0; i < data.length; i++) {
            System.out.println("Step " + (i + 1) + ": checking index " + i + " (value " + data[i] + ")");
            if (data[i] == target) {
                System.out.println("FOUND! " + target + " is at index " + i + ".");
                return;
            }
        }
        System.out.println(target + " was NOT found.");
    }

    static void binarySearch() {
        int[] a = getSortedArray();
        int target = Input.readInt("Enter value to search: ");
        int low = 0;
        int high = a.length - 1;
        int step = 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println("Step " + step + ": low=" + low + ", high=" + high
                    + ", mid=" + mid + " (value " + a[mid] + ")");
            step++;
            if (a[mid] == target) {
                System.out.println("FOUND! " + target + " is at index " + mid + " of the sorted array.");
                return;
            } else if (a[mid] < target) {
                low = mid + 1;   // nasa kanan
            } else {
                high = mid - 1;  // nasa kaliwa
            }
        }
        System.out.println(target + " was NOT found.");
    }

    static void interpolationSearch() {
        int[] a = getSortedArray();
        int target = Input.readInt("Enter value to search: ");
        int low = 0;
        int high = a.length - 1;
        int step = 1;

        while (low <= high && target >= a[low] && target <= a[high]) {
            if (a[high] == a[low]) { // iisa ang value sa range, iwas divide by zero
                if (a[low] == target) {
                    System.out.println("FOUND! " + target + " is at index " + low + " of the sorted array.");
                } else {
                    System.out.println(target + " was NOT found.");
                }
                return;
            }
            // Formula: hulaan ang position base sa value
            int pos = low + (int) ((double) (target - a[low]) * (high - low) / (a[high] - a[low]));
            System.out.println("Step " + step + ": low=" + low + ", high=" + high
                    + ", estimated pos=" + pos + " (value " + a[pos] + ")");
            step++;
            if (a[pos] == target) {
                System.out.println("FOUND! " + target + " is at index " + pos + " of the sorted array.");
                return;
            } else if (a[pos] < target) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        System.out.println(target + " was NOT found.");
    }
}
