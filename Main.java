public class Main {

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n==========================================");
            System.out.println("   CC104 DATA STRUCTURES AND ALGORITHMS");
            System.out.println("==========================================");
            System.out.println("1. Arrays");
            System.out.println("2. Stacks - Array and Linked Implementations");
            System.out.println("3. Queues - Array and Linked Implementations");
            System.out.println("4. Linked Lists - Singly, Doubly, and Circular");
            System.out.println("5. Searching - Linear, Binary, and Interpolation");
            System.out.println("6. Sorting - Bubble, Insertion, Selection, and Quick Sort");
            System.out.println("7. Trees - Binary Search Tree and Expression Tree");
            System.out.println("8. Developers");
            System.out.println("0. Exit");

            choice = Input.readInt("Enter your choice: ", 0, 8);

            switch (choice) {
                case 1: ArrayModule.run(); break;
                case 2: StackModule.run(); break;
                case 3: QueueModule.run(); break;
                case 4: LinkedListModule.run(); break;
                case 5: SearchingModule.run(); break;
                case 6: SortingModule.run(); break;
                case 7: TreeModule.run(); break;
                case 8: DevelopersModule.run(); break;
                case 0: System.out.println("Thank you for using the program. Goodbye!"); break;
            }
        } while (choice != 0);
    }
}
