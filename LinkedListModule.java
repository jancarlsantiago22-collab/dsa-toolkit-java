public class LinkedListModule {

    //SINGLY LINKED LIST
    static class SinglyList {
        Node head = null;

        void insertBeginning(int value) {
            Node n = new Node(value);
            n.next = head;
            head = n;
            System.out.println(value + " inserted at the beginning.");
        }

        void insertEnd(int value) {
            Node n = new Node(value);
            if (head == null) {
                head = n;
            } else {
                Node cur = head;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = n;
            }
            System.out.println(value + " inserted at the end.");
        }

        void delete(int value) {
            if (head == null) {
                System.out.println("List is empty!");
                return;
            }
            if (head.data == value) {
                head = head.next;
                System.out.println(value + " deleted.");
                return;
            }
            Node cur = head;
            while (cur.next != null && cur.next.data != value) {
                cur = cur.next;
            }
            if (cur.next == null) {
                System.out.println(value + " was not found.");
            } else {
                cur.next = cur.next.next; // laktawan ang node na buburahin
                System.out.println(value + " deleted.");
            }
        }

        void search(int value) {
            Node cur = head;
            int pos = 0;
            while (cur != null) {
                if (cur.data == value) {
                    System.out.println(value + " found at position " + pos + ".");
                    return;
                }
                cur = cur.next;
                pos++;
            }
            System.out.println(value + " was not found.");
        }

        void display() {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            Node cur = head;
            while (cur != null) {
                System.out.print(cur.data + " -> ");
                cur = cur.next;
            }
            System.out.println("null");
        }
    }

    //DOUBLY LINKED LIST
        DNode head = null;
        DNode tail = null;

        void insertBeginning(int value) {
            DNode n = new DNode(value);
            n.next = head;
            if (head != null) {
                head.prev = n;
            } else {
                tail = n; // unang node, siya rin ang tail
            }
            head = n;
            System.out.println(value + " inserted at the beginning.");
        }

        void insertEnd(int value) {
            DNode n = new DNode(value);
            n.prev = tail;
            if (tail != null) {
                tail.next = n;
            } else {
                head = n; // unang node, siya rin ang head
            }
            tail = n;
            System.out.println(value + " inserted at the end.");
        }

        void delete(int value) {
            DNode cur = head;
            while (cur != null && cur.data != value) {
                cur = cur.next;
            }
            if (cur == null) {
                System.out.println(value + " was not found.");
                return;
            }
            if (cur.prev != null) {
                cur.prev.next = cur.next;
            } else {
                head = cur.next; // head ang binura
            }
            if (cur.next != null) {
                cur.next.prev = cur.prev;
            } else {
                tail = cur.prev; // tail ang binura
            }
            System.out.println(value + " deleted.");
        }

        void search(int value) {
            DNode cur = head;
            int pos = 0;
            while (cur != null) {
                if (cur.data == value) {
                    System.out.println(value + " found at position " + pos + ".");
                    return;
                }
                cur = cur.next;
                pos++;
            }
            System.out.println(value + " was not found.");
        }

        void display() {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            System.out.print("Forward : null <- ");
            DNode cur = head;
            while (cur != null) {
                System.out.print(cur.data + (cur.next != null ? " <-> " : " -> null"));
                cur = cur.next;
            }
            System.out.println();
            System.out.print("Backward: ");
            cur = tail;
            while (cur != null) {
                System.out.print(cur.data + (cur.prev != null ? " <-> " : " -> null"));
                cur = cur.prev;
            }
            System.out.println();
        }
    }

    //CIRCULAR LINKED LIST (singly, tail pointer)
    static class CircularList {
        Node tail = null; // tail.next ay ang head

        void insertBeginning(int value) {
            Node n = new Node(value);
            if (tail == null) {
                tail = n;
                n.next = n;
            } else {
                n.next = tail.next; // tumuturo sa lumang head
                tail.next = n;      // siya na ang bagong head
            }
            System.out.println(value + " inserted at the beginning.");
        }

        void insertEnd(int value) {
            Node n = new Node(value);
            if (tail == null) {
                tail = n;
                n.next = n;
            } else {
                n.next = tail.next;
                tail.next = n;
                tail = n;           // siya na ang bagong tail
            }
            System.out.println(value + " inserted at the end.");
        }

        void delete(int value) {
            if (tail == null) {
                System.out.println("List is empty!");
                return;
            }
            Node prev = tail;
            Node cur = tail.next; // head
            do {
                if (cur.data == value) {
                    if (cur == prev) {
                        tail = null;          // isa lang ang node
                    } else {
                        prev.next = cur.next;
                        if (cur == tail) {
                            tail = prev;      // tail ang binura
                        }
                    }
                    System.out.println(value + " deleted.");
                    return;
                }
                prev = cur;
                cur = cur.next;
            } while (prev != tail);
            System.out.println(value + " was not found.");
        }

        void search(int value) {
            if (tail == null) {
                System.out.println("List is empty!");
                return;
            }
            Node cur = tail.next;
            int pos = 0;
            do {
                if (cur.data == value) {
                    System.out.println(value + " found at position " + pos + ".");
                    return;
                }
                cur = cur.next;
                pos++;
            } while (cur != tail.next);
            System.out.println(value + " was not found.");
        }

        void display() {
            if (tail == null) {
                System.out.println("List is empty.");
                return;
            }
            Node cur = tail.next;
            do {
                System.out.print(cur.data + " -> ");
                cur = cur.next;
            } while (cur != tail.next);
            System.out.println("(back to head " + tail.next.data + ")");
        }
    }

    //MENU 
    public static void run() {
        System.out.println("\n--- LINKED LISTS ---");
        System.out.println("About: A linked list is a chain of nodes. Each node points to the next one.");
        System.out.println("1. Singly Linked List");
        System.out.println("2. Doubly Linked List");
        System.out.println("3. Circular Linked List");
        System.out.println("0. Back to Main Menu");
        int type = Input.readInt("Choose list type: ", 0, 3);
        if (type == 0) {
            return;
        }

        SinglyList singly = new SinglyList();
        DoublyList doubly = new DoublyList();
        CircularList circular = new CircularList();
        String name = (type == 1) ? "SINGLY" : (type == 2) ? "DOUBLY" : "CIRCULAR";

        int choice;
        do {
            System.out.println("\n--- " + name + " LINKED LIST ---");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at end");
            System.out.println("3. Delete a value");
            System.out.println("4. Search a value");
            System.out.println("5. Display");
            System.out.println("0. Back to Main Menu");
            choice = Input.readInt("Enter your choice: ", 0, 5);

            int value;
            switch (choice) {
                case 1:
                    value = Input.readInt("Enter value: ");
                    if (type == 1) singly.insertBeginning(value);
                    else if (type == 2) doubly.insertBeginning(value);
                    else circular.insertBeginning(value);
                    break;
                case 2:
                    value = Input.readInt("Enter value: ");
                    if (type == 1) singly.insertEnd(value);
                    else if (type == 2) doubly.insertEnd(value);
                    else circular.insertEnd(value);
                    break;
                case 3:
                    value = Input.readInt("Enter value to delete: ");
                    if (type == 1) singly.delete(value);
                    else if (type == 2) doubly.delete(value);
                    else circular.delete(value);
                    break;
                case 4:
                    value = Input.readInt("Enter value to search: ");
                    if (type == 1) singly.search(value);
                    else if (type == 2) doubly.search(value);
                    else circular.search(value);
                    break;
                case 5:
                    if (type == 1) singly.display();
                    else if (type == 2) doubly.display();
                    else circular.display();
                    break;
            }
        } while (choice != 0);
    }
}
