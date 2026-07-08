import java.util.*;
public class MergeBSTs {
    public static void main(String[] args) {
        Node root = new Node(0);

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);


        n2.left = n1;
        n3.left = n2;
        n3.right = n4;
        n5.left = n3;

        n8.left = n7;
        n7.left = n6;
        n8.right = n9;
        Node root1 = n5, root2 = n8;

        BST bst = new BST();
        
        // bst.visitDLL(bst.treeToDLL(root1));
        // bst.visitDLL(bst.treeToDLL(root2));
        // bst.visitDLL(root2);
        // System.out.print(root2);

        System.out.println(bst.merge(root1, root2));
    }
}
class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

class BST {
    Node head = null;
    Node prev = null;
    Node dll = null;

    public ArrayList<Integer> merge(Node root1, Node root2) {
        List<Node> list = new ArrayList<>();
        Node ptr1 = treeToDLL(root1), ptr2 = treeToDLL(root2), tail = new Node(-1), dummy = tail;

        while (ptr1 != null && ptr2 != null) {
            if (ptr1.data < ptr2.data) {
                tail.right = ptr1;
                ptr1.left = tail;
                list.add(ptr1);


                ptr1 = ptr1.right;
            } else {
                tail.right = ptr2;
                ptr2.left = tail;
                list.add(ptr2);

                
                ptr2 = ptr2.right;
            }

            tail = tail.right;
        }

        while (ptr1 != null) {
            list.add(ptr1);

            tail.right = ptr1;
            ptr1.left = tail;
            tail = tail.right;

            ptr1 = ptr1.right;
        }

        while (ptr2 != null) {
            list.add(ptr2);
            
            tail.right = ptr2;
            ptr2.left = tail;
            tail = tail.right;

            ptr2 = ptr2.right;
        }

        head = dummy.right;

        int n = getCount(head);
        Node root = build(n);

        return inorder(root);

    }

    int getCount(Node node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.right;
        }

        return count;
    }

    public Node treeToDLL(Node root) {
        prev = null;
        convert(root);
        return dll;
    }

    public void visitDLL(Node node) {
        while (node != null) {
            System.out.println(node.data);
            node = node.right;
        }
    }

    void convert(Node node) {
        if (node == null)
            return;

        convert(node.left);

        if (prev == null) {
            dll = node;
        } else {
            prev.right = node;
            node.left = prev;
        }

        prev = node;
        convert(node.right);
    }

    ArrayList<Integer> inorder(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Node> s = new ArrayDeque<>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                s.push(root);
                root = root.left;
            }

            Node node = s.pop();
            ans.add(node.data);
            root = node.right;
        }

        return ans;
    }

    Node build(int n) {
        if (n <= 0)
            return null;

        Node left = build(n / 2);

        Node node = head;
        node.left = left;
        head = head.right;

        node.right = build(n - n / 2 - 1);
        return node;
    }
}