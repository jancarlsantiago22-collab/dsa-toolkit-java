public class ArrayModule {

    static int[] data = new int[10]; // fixed capacity = 10
    static int size = 0;             // ilan na ang laman

    public static void run() {
        int choice;
        do {
            System.out.println("\n--- ARRAYS ---");
            System.out.println("1. Insert at the end");
            System.out.println("2. Insert at a position");
            System.out.println("3. Delete at a position");
            System.out.println("4. Search a value");
            System.out.println("5. Update a value");
            System.out.println("6. Display array");
            System.out.println("0. Back to Main Menu");
            choice = Input.readInt("Enter your choice: ", 0, 6);

            switch (choice) {
                case 1: insertEnd(); break;
                case 2: insertAt(); break;
                case 3: deleteAt(); break;
                case 4: search(); break;
                case 5: update(); break;
                case 6: display(); break;
            }
        } while (choice != 0);
    }

    static void insertEnd() {
        if (size == data.length) {
            System.out.println("Array is full! Cannot insert.");
            return;
        }
        int value = Input.readInt("Enter value to insert: ");
        data[size] = value;
        size++;
        System.out.println(value + " inserted at index " + (size - 1) + ".");
        display();
    }

    static void insertAt() {
        if (size == data.length) {
            System.out.println("Array is full! Cannot insert.");
            return;
        }
        int pos = Input.readInt("Enter index (0 to " + size + "): ", 0, size);
        int value = Input.readInt("Enter value to insert: ");

        // Shift to the right para magkaroon ng bakante sa pos
        for (int i = size; i > pos; i--) {
            data[i] = data[i - 1];
        }
        data[pos] = value;
        size++;
        System.out.println(value + " inserted at index " + pos + ".");
        display();
    }

    static void deleteAt() {
        if (size == 0) {
            System.out.println("Array is empty! Nothing to delete.");
            return;
        }
        int pos = Input.readInt("Enter index to delete (0 to " + (size - 1) + "): ", 0, size - 1);
        int removed = data[pos];

        // Shift to the left para mapunan ang butas
        for (int i = pos; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        System.out.println(removed + " deleted from index " + pos + ".");
        display();
    }

    static void search() {
        if (size == 0) {
            System.out.println("Array is empty!");
            return;
        }
        int target = Input.readInt("Enter value to search: ");
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (data[i] == target) {
                System.out.println(target + " found at index " + i + ".");
                found = true;
            }
        }
        if (!found) {
            System.out.println(target + " was not found.");
        }
    }

    static void update() {
        if (size == 0) {
            System.out.println("Array is empty!");
            return;
        }
        int pos = Input.readInt("Enter index to update (0 to " + (size - 1) + "): ", 0, size - 1);
        int value = Input.readInt("Enter new value: ");
        System.out.println("Index " + pos + " changed from " + data[pos] + " to " + value + ".");
        data[pos] = value;
        display();
    }

    static void display() {
        if (size == 0) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.print("Array (" + size + "/" + data.length + "): [");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
