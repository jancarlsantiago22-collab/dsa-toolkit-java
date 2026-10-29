public class ArrayModule {

    static int[] data = new int[10]; // fixed capacity = 10
    static int size = 0;             // ilan na ang laman

    // ================= MAIN ENTRY NG ARRAYS MODULE =================
    public static void run() {
        System.out.println("\n--- ARRAYS ---");
        System.out.println("About: An array stores items in order in fixed-size slots. Each item is found by its index (position number).");
        System.out.println("1. One-dimensional (1D) array");
        System.out.println("2. Two-dimensional (2D) array");
        System.out.println("0. Back to Main Menu");
        int type = Input.readInt("Is the array 1D or 2D? Enter choice: ", 0, 2);

        if (type == 1) {
            oneDimensional();
        } else if (type == 2) {
            twoDimensional();
        }
        // kapag 0, babalik na agad sa main menu
    }

    // ================= ONE-DIMENSIONAL ARRAY =================
    static void oneDimensional() {
        int choice;
        do {
            System.out.println("\n--- ARRAYS (1D) ---");
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

    // ================= TWO-DIMENSIONAL ARRAY =================
    static int[][] table = null; // null = wala pang nagagawang 2D array

    static void twoDimensional() {
        int choice;
        do {
            System.out.println("\n--- ARRAYS (2D) ---");
            System.out.println("1. Create 2D array (rows, columns, values)");
            System.out.println("2. Display");
            System.out.println("3. Search a value");
            System.out.println("4. Update a value");
            System.out.println("5. Sum of each row");
            System.out.println("6. Sum of each column");
            System.out.println("7. Transpose (swap rows and columns)");
            System.out.println("0. Back to Main Menu");
            choice = Input.readInt("Enter your choice: ", 0, 7);

            if (choice >= 2 && table == null) {
                System.out.println("No 2D array yet! Please choose option 1 first.");
                continue;
            }

            switch (choice) {
                case 1: create2D(); break;
                case 2: display2D(table); break;
                case 3: search2D(); break;
                case 4: update2D(); break;
                case 5: rowSums(); break;
                case 6: columnSums(); break;
                case 7: transpose(); break;
            }
        } while (choice != 0);
    }

    static void create2D() {
        int rows = Input.readInt("Number of rows (1 to 5): ", 1, 5);
        int cols = Input.readInt("Number of columns (1 to 5): ", 1, 5);
        table = new int[rows][cols];

        // Nested loop: outer = row, inner = column
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                table[r][c] = Input.readInt("Enter value for [" + r + "][" + c + "]: ");
            }
        }
        System.out.println("2D array created!");
        display2D(table);
    }

    static void display2D(int[][] a) {
        System.out.println("2D array (" + a.length + " rows x " + a[0].length + " columns):");
        System.out.print("        ");
        for (int c = 0; c < a[0].length; c++) {
            System.out.print("col" + c + "\t");
        }
        System.out.println();
        for (int r = 0; r < a.length; r++) {
            System.out.print("row " + r + " | ");
            for (int c = 0; c < a[r].length; c++) {
                System.out.print(a[r][c] + "\t");
            }
            System.out.println();
        }
    }

    static void search2D() {
        int target = Input.readInt("Enter value to search: ");
        boolean found = false;
        for (int r = 0; r < table.length; r++) {
            for (int c = 0; c < table[r].length; c++) {
                if (table[r][c] == target) {
                    System.out.println(target + " found at [" + r + "][" + c + "].");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println(target + " was not found.");
        }
    }

    static void update2D() {
        int r = Input.readInt("Enter row (0 to " + (table.length - 1) + "): ", 0, table.length - 1);
        int c = Input.readInt("Enter column (0 to " + (table[0].length - 1) + "): ", 0, table[0].length - 1);
        int value = Input.readInt("Enter new value: ");
        System.out.println("[" + r + "][" + c + "] changed from " + table[r][c] + " to " + value + ".");
        table[r][c] = value;
        display2D(table);
    }

    static void rowSums() {
        for (int r = 0; r < table.length; r++) {
            int sum = 0;
            for (int c = 0; c < table[r].length; c++) {
                sum = sum + table[r][c];
            }
            System.out.println("Sum of row " + r + " = " + sum);
        }
    }

    static void columnSums() {
        for (int c = 0; c < table[0].length; c++) {
            int sum = 0;
            for (int r = 0; r < table.length; r++) {
                sum = sum + table[r][c];
            }
            System.out.println("Sum of column " + c + " = " + sum);
        }
    }

    static void transpose() {
        // Baligtad ang sukat: kung 2x3 ang original, magiging 3x2
        int[][] result = new int[table[0].length][table.length];
        for (int r = 0; r < table.length; r++) {
            for (int c = 0; c < table[r].length; c++) {
                result[c][r] = table[r][c];
            }
        }
        System.out.println("Transposed array (original is not changed):");
        display2D(result);
    }
}
