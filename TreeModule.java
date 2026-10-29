public class TreeModule {

    // ================= BINARY SEARCH TREE =================
    static TreeNode root = null;

    static TreeNode insert(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.data) {
            node.left = insert(node.left, value);
        } else if (value > node.data) {
            node.right = insert(node.right, value);
        }
        return node;
    }

    static boolean search(TreeNode node, int value) {
        if (node == null) return false;
        if (value == node.data) return true;
        if (value < node.data) return search(node.left, value);
        return search(node.right, value);
    }

    static TreeNode delete(TreeNode node, int value) {
        if (node == null) return null;
        if (value < node.data) {
            node.left = delete(node.left, value);
        } else if (value > node.data) {
            node.right = delete(node.right, value);
        } else {
            // Case 1 at 2: walang anak o isang anak lang
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // Case 3: dalawang anak -> palitan ng pinakamaliit sa kanan (inorder successor)
            TreeNode s = node.right;
            while (s.left != null) {
                s = s.left;
            }
            node.data = s.data;
            node.right = delete(node.right, s.data);
        }
        return node;
    }

    static void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    static void preorder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    static void postorder(TreeNode node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    static int height(TreeNode node) {
        if (node == null) return 0;
        int l = height(node.left);
        int r = height(node.right);
        return 1 + Math.max(l, r);
    }

    static void bstMenu() {
        int choice;
        do {
            System.out.println("\n--- BINARY SEARCH TREE ---");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Inorder traversal");
            System.out.println("5. Preorder traversal");
            System.out.println("6. Postorder traversal");
            System.out.println("7. Height of the tree");
            System.out.println("0. Back");
            choice = Input.readInt("Enter your choice: ", 0, 7);

            int value;
            switch (choice) {
                case 1:
                    value = Input.readInt("Enter value to insert: ");
                    if (search(root, value)) {
                        System.out.println(value + " already exists. Duplicates are not allowed.");
                    } else {
                        root = insert(root, value);
                        System.out.println(value + " inserted.");
                    }
                    break;
                case 2:
                    value = Input.readInt("Enter value to search: ");
                    System.out.println(search(root, value) ? value + " FOUND in the tree." : value + " NOT found.");
                    break;
                case 3:
                    value = Input.readInt("Enter value to delete: ");
                    if (search(root, value)) {
                        root = delete(root, value);
                        System.out.println(value + " deleted.");
                    } else {
                        System.out.println(value + " NOT found.");
                    }
                    break;
                case 4:
                    if (root == null) { System.out.println("Tree is empty."); break; }
                    System.out.print("Inorder  : "); inorder(root); System.out.println();
                    break;
                case 5:
                    if (root == null) { System.out.println("Tree is empty."); break; }
                    System.out.print("Preorder : "); preorder(root); System.out.println();
                    break;
                case 6:
                    if (root == null) { System.out.println("Tree is empty."); break; }
                    System.out.print("Postorder: "); postorder(root); System.out.println();
                    break;
                case 7:
                    System.out.println("Height: " + height(root));
                    break;
            }
        } while (choice != 0);
    }

    //EXPRESSION TREE
    static class ExprNode {
        String value;
        ExprNode left;
        ExprNode right;

        ExprNode(String value) {
            this.value = value;
        }
    }

    static ExprNode exprRoot = null;
    static boolean divideByZero = false;

    static boolean isOperator(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }

    static boolean isNumber(String t) {
        try {
            Double.parseDouble(t);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Gumawa ng tree mula sa POSTFIX expression, hal: 3 4 + 2 *
    static void buildFromPostfix() {
        String line = Input.readLine("Enter POSTFIX expression (separate with spaces, e.g. 3 4 + 2 *): ");
        String[] tokens = line.split("\\s+");
        ExprNode[] stack = new ExprNode[tokens.length];
        int top = -1;

        for (int i = 0; i < tokens.length; i++) {
            String t = tokens[i];
            if (isNumber(t)) {
                top++;
                stack[top] = new ExprNode(t);
            } else if (isOperator(t)) {
                if (top < 1) {
                    System.out.println("Invalid expression: not enough operands for '" + t + "'.");
                    return;
                }
                ExprNode n = new ExprNode(t);
                n.right = stack[top]; top--;  // unang pop = kanan
                n.left = stack[top]; top--;   // pangalawang pop = kaliwa
                top++;
                stack[top] = n;
            } else {
                System.out.println("Invalid token: '" + t + "'. Use numbers and + - * / only.");
                return;
            }
        }
        if (top != 0) {
            System.out.println("Invalid expression: too many operands.");
            return;
        }
        exprRoot = stack[0];
        System.out.println("Expression tree built successfully!");
    }

    static String infix(ExprNode n) {
        if (n.left == null && n.right == null) return n.value;
        return "(" + infix(n.left) + " " + n.value + " " + infix(n.right) + ")";
    }

    static String prefix(ExprNode n) {
        if (n.left == null && n.right == null) return n.value;
        return n.value + " " + prefix(n.left) + " " + prefix(n.right);
    }

    static String postfix(ExprNode n) {
        if (n.left == null && n.right == null) return n.value;
        return postfix(n.left) + " " + postfix(n.right) + " " + n.value;
    }

    static double evaluate(ExprNode n) {
        if (n.left == null && n.right == null) {
            return Double.parseDouble(n.value);
        }
        double a = evaluate(n.left);
        double b = evaluate(n.right);
        if (n.value.equals("+")) return a + b;
        if (n.value.equals("-")) return a - b;
        if (n.value.equals("*")) return a * b;
        if (b == 0) {
            divideByZero = true;
            return 0;
        }
        return a / b;
    }

    static void exprMenu() {
        int choice;
        do {
            System.out.println("\n--- EXPRESSION TREE ---");
            System.out.println("1. Build tree from postfix expression");
            System.out.println("2. Display infix, prefix, and postfix");
            System.out.println("3. Evaluate the expression");
            System.out.println("0. Back");
            choice = Input.readInt("Enter your choice: ", 0, 3);

            if ((choice == 2 || choice == 3) && exprRoot == null) {
                System.out.println("No expression tree yet! Choose option 1 first.");
                continue;
            }

            switch (choice) {
                case 1:
                    buildFromPostfix();
                    break;
                case 2:
                    System.out.println("Infix  : " + infix(exprRoot));
                    System.out.println("Prefix : " + prefix(exprRoot));
                    System.out.println("Postfix: " + postfix(exprRoot));
                    break;
                case 3:
                    divideByZero = false;
                    double result = evaluate(exprRoot);
                    if (divideByZero) {
                        System.out.println("Error: division by zero!");
                    } else if (result == Math.floor(result)) {
                        System.out.println("Result: " + (long) result);
                    } else {
                        System.out.println("Result: " + result);
                    }
                    break;
            }
        } while (choice != 0);
    }

    // ================= MAIN MENU NG TREES =================
    public static void run() {
        System.out.println("\nAbout: A tree stores data in nodes arranged in levels, with a root at the top and branches below.");
        int choice;
        do {
            System.out.println("\n--- TREES ---");
            System.out.println("1. Binary Search Tree");
            System.out.println("2. Expression Tree");
            System.out.println("0. Back to Main Menu");
            choice = Input.readInt("Enter your choice: ", 0, 2);

            switch (choice) {
                case 1: bstMenu(); break;
                case 2: exprMenu(); break;
            }
        } while (choice != 0);
    }
}
