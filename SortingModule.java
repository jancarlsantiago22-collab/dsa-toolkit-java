import java.util.Arrays;

public class SortingModule {

    static int[] data = new int[0];

    public static void run() {
        System.out.println("\nAbout: Sorting arranges the items of an array in ascending order (smallest to largest).");
        int choice;
        do {
            System.out.println("\n--- SORTING ---");
            System.out.println("1. Enter / replace array");
            System.out.println("2. Bubble Sort");
            System.out.println("3. Insertion Sort");
            System.out.println("4. Selection Sort");
            System.out.println("5. Quick Sort");
            System.out.println("6. Display array");
            System.out.println("0. Back to Main Menu");
            choice = Input.readInt("Enter your choice: ", 0, 6);

            if (choice >= 2 && choice <= 5 && data.length == 0) {
                System.out.println("No array yet! Please choose option 1 first.");
                continue;
            }

            switch (choice) {
                case 1: enterArray(); break;
                case 2: bubbleSort(); break;
                case 3: insertionSort(); break;
                case 4: selectionSort(); break;
                case 5: quickSortDemo(); break;
                case 6: System.out.println("Array: " + Arrays.toString(data)); break;
            }
        } while (choice != 0);
    }

    static void enterArray() {
        int n = Input.readInt("How many elements (1 to 20)? ", 1, 20);
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = Input.readInt("Enter element " + (i + 1) + ": ");
        }
        System.out.println("Array: " + Arrays.toString(data));
    }

    // Lahat ng sort ay gumagana sa COPY para pwedeng ulitin sa ibang algorithm.
    static void bubbleSort() {
        int[] a = Arrays.copyOf(data, data.length);
        System.out.println("Before: " + Arrays.toString(a));

        for (int pass = 1; pass < a.length; pass++) {
            boolean swapped = false;
            for (int j = 0; j < a.length - pass; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true;
                }
            }
            System.out.println("Pass " + pass + ": " + Arrays.toString(a));
            if (!swapped) {
                break; // wala nang swap, sorted na
            }
        }
        System.out.println("After : " + Arrays.toString(a));
    }

    static void insertionSort() {
        int[] a = Arrays.copyOf(data, data.length);
        System.out.println("Before: " + Arrays.toString(a));

        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j]; // usog pakanan
                j--;
            }
            a[j + 1] = key;
            System.out.println("Pass " + i + ": " + Arrays.toString(a));
        }
        System.out.println("After : " + Arrays.toString(a));
    }

    static void selectionSort() {
        int[] a = Arrays.copyOf(data, data.length);
        System.out.println("Before: " + Arrays.toString(a));

        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
            System.out.println("Pass " + (i + 1) + ": " + Arrays.toString(a));
        }
        System.out.println("After : " + Arrays.toString(a));
    }

    static void quickSortDemo() {
        int[] a = Arrays.copyOf(data, data.length);
        System.out.println("Before: " + Arrays.toString(a));
        quickSort(a, 0, a.length - 1);
        System.out.println("After : " + Arrays.toString(a));
    }

    static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int p = partition(a, low, high);
            System.out.println("Pivot " + a[p] + " placed at index " + p + ": " + Arrays.toString(a));
            quickSort(a, low, p - 1);   // kaliwang bahagi
            quickSort(a, p + 1, high);  // kanang bahagi
        }
    }

    // Lomuto partition: ang huling element ang pivot
    static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;
        return i + 1;
    }
}
