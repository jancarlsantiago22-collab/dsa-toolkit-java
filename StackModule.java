public class StackModule {

    // ---------- ARRAY IMPLEMENTATION ----------
    static class ArrayStack {
        int[] items = new int[5]; // capacity = 5
        int top = -1;             // -1 means empty

        boolean isEmpty() {
            return top == -1;
        }

        void push(int value) {
            if (top == items.length - 1) {
                System.out.println("Stack Overflow! Stack is full (capacity " + items.length + ").");
                return;
            }
            top++;
            items[top] = value;
            System.out.println(value + " pushed onto the stack.");
        }

        void pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow! Stack is empty.");
                return;
            }
            System.out.println(items[top] + " popped from the stack.");
            top--;
        }

        void peek() {
            if (isEmpty()) {
                System.out.println("Stack is empty.");
                return;
            }
            System.out.println("Top element: " + items[top]);
        }

        void display() {
            if (isEmpty()) {
                System.out.println("Stack is empty.");
                return;
            }
            System.out.println("Stack (top to bottom):");
            for (int i = top; i >= 0; i--) {
                System.out.println("  | " + items[i] + " |");
            }
        }
    }

    // ---------- LINKED IMPLEMENTATION ----------
    static class LinkedStack {
        Node top = null;

        boolean isEmpty() {
            return top == null;
        }

        void push(int value) {
            Node n = new Node(value);
            n.next = top; // ang bagong node ay tumuturo sa dating top
            top = n;      // siya na ang bagong top
            System.out.println(value + " pushed onto the stack.");
        }

        void pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow! Stack is empty.");
                return;
            }
            System.out.println(top.data + " popped from the stack.");
            top = top.next;
        }

        void peek() {
            if (isEmpty()) {
                System.out.println("Stack is empty.");
                return;
            }
            System.out.println("Top element: " + top.data);
        }

        void display() {
            if (isEmpty()) {
                System.out.println("Stack is empty.");
                return;
            }
            System.out.println("Stack (top to bottom):");
            Node cur = top;
            while (cur != null) {
                System.out.println("  | " + cur.data + " |");
                cur = cur.next;
            }
        }
    }

    // ---------- MENU ----------
    public static void run() {
        System.out.println("\n--- STACKS ---");
        System.out.println("About: A stack is LIFO (Last In, First Out). The last item added is the first one removed.");
        System.out.println("1. Array Implementation");
        System.out.println("2. Linked Implementation");
        System.out.println("0. Back to Main Menu");
        int type = Input.readInt("Choose implementation: ", 0, 2);
        if (type == 0) {
            return;
        }

        ArrayStack arrayStack = new ArrayStack();
        LinkedStack linkedStack = new LinkedStack();
        String name = (type == 1) ? "ARRAY STACK" : "LINKED STACK";

        int choice;
        do {
            System.out.println("\n--- " + name + " ---");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Check if empty");
            System.out.println("5. Display");
            System.out.println("0. Back to Main Menu");
            choice = Input.readInt("Enter your choice: ", 0, 5);

            switch (choice) {
                case 1:
                    int value = Input.readInt("Enter value to push: ");
                    if (type == 1) arrayStack.push(value); else linkedStack.push(value);
                    break;
                case 2:
                    if (type == 1) arrayStack.pop(); else linkedStack.pop();
                    break;
                case 3:
                    if (type == 1) arrayStack.peek(); else linkedStack.peek();
                    break;
                case 4:
                    boolean empty = (type == 1) ? arrayStack.isEmpty() : linkedStack.isEmpty();
                    System.out.println(empty ? "The stack is empty." : "The stack is NOT empty.");
                    break;
                case 5:
                    if (type == 1) arrayStack.display(); else linkedStack.display();
                    break;
            }
        } while (choice != 0);
    }
}
