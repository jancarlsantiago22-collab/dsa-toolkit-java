public class QueueModule {

    //RRAY IMPLEMENTATION (circular)
    static class ArrayQueue {
        int[] items = new int[5]; // capacity = 5
        int front = 0;
        int rear = -1;
        int count = 0;

        boolean isEmpty() {
            return count == 0;
        }

        void enqueue(int value) {
            if (count == items.length) {
                System.out.println("Queue Overflow! Queue is full (capacity " + items.length + ").");
                return;
            }
            rear = (rear + 1) % items.length; // babalik sa 0 kapag dulo na
            items[rear] = value;
            count++;
            System.out.println(value + " enqueued.");
        }

        void dequeue() {
            if (isEmpty()) {
                System.out.println("Queue Underflow! Queue is empty.");
                return;
            }
            System.out.println(items[front] + " dequeued.");
            front = (front + 1) % items.length;
            count--;
        }

        void peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty.");
                return;
            }
            System.out.println("Front element: " + items[front]);
        }

        void display() {
            if (isEmpty()) {
                System.out.println("Queue is empty.");
                return;
            }
            System.out.print("Queue (front -> rear): ");
            for (int i = 0; i < count; i++) {
                System.out.print(items[(front + i) % items.length] + " ");
            }
            System.out.println();
        }
    }

    // LINKED IMPLEMENTATION
    static class LinkedQueue {
        Node front = null;
        Node rear = null;

        boolean isEmpty() {
            return front == null;
        }

        void enqueue(int value) {
            Node n = new Node(value);
            if (rear == null) {   // unang node
                front = n;
                rear = n;
            } else {
                rear.next = n;    // idikit sa dulo
                rear = n;
            }
            System.out.println(value + " enqueued.");
        }

        void dequeue() {
            if (isEmpty()) {
                System.out.println("Queue Underflow! Queue is empty.");
                return;
            }
            System.out.println(front.data + " dequeued.");
            front = front.next;
            if (front == null) {  // naubos na
                rear = null;
            }
        }

        void peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty.");
                return;
            }
            System.out.println("Front element: " + front.data);
        }

        void display() {
            if (isEmpty()) {
                System.out.println("Queue is empty.");
                return;
            }
            System.out.print("Queue (front -> rear): ");
            Node cur = front;
            while (cur != null) {
                System.out.print(cur.data + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    //MENU 
    public static void run() {
        System.out.println("\n--- QUEUES ---");
        System.out.println("About: A queue is FIFO (First In, First Out). The first item added is the first one removed.");
        System.out.println("1. Array Implementation");
        System.out.println("2. Linked Implementation");
        System.out.println("0. Back to Main Menu");
        int type = Input.readInt("Choose implementation: ", 0, 2);
        if (type == 0) {
            return;
        }

        ArrayQueue arrayQueue = new ArrayQueue();
        LinkedQueue linkedQueue = new LinkedQueue();
        String name = (type == 1) ? "ARRAY QUEUE" : "LINKED QUEUE";

        int choice;
        do {
            System.out.println("\n--- " + name + " ---");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek");
            System.out.println("4. Check if empty");
            System.out.println("5. Display");
            System.out.println("0. Back to Main Menu");
            choice = Input.readInt("Enter your choice: ", 0, 5);

            switch (choice) {
                case 1:
                    int value = Input.readInt("Enter value to enqueue: ");
                    if (type == 1) arrayQueue.enqueue(value); else linkedQueue.enqueue(value);
                    break;
                case 2:
                    if (type == 1) arrayQueue.dequeue(); else linkedQueue.dequeue();
                    break;
                case 3:
                    if (type == 1) arrayQueue.peek(); else linkedQueue.peek();
                    break;
                case 4:
                    boolean empty = (type == 1) ? arrayQueue.isEmpty() : linkedQueue.isEmpty();
                    System.out.println(empty ? "The queue is empty." : "The queue is NOT empty.");
                    break;
                case 5:
                    if (type == 1) arrayQueue.display(); else linkedQueue.display();
                    break;
            }
        } while (choice != 0);
    }
}
