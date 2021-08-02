import java.util.*;

public class BST {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node(int data) {
            this(data, null, null);
        }
    }

    public static int size(Node node) {
        if (node == null)
            return 0;
        return size(node.left) + size(node.right) + 1;
    }

    public static int height(Node node) {
        if (node == null)
            return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static int maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }

    public static int minimum(Node node) {
        while (node.left != null)
            node = node.left;
        return node.data;
    }

    public static boolean find(Node node, int data) {
        while (node != null) {
            if (node.data == data)
                return true;
            if (node.data < data)
                node = node.right;
            else
                node = node.left;
        }
        return false;
    }

    public static ArrayList<Node> nodetoRootPath(Node node, int data) {
        ArrayList<Node> list = new ArrayList<>();
        boolean flag = false;
        while (node != null) {
            if (node.data == data) {
                list.add(node);
                flag = true;
                break;
            }
            if (data < node.data) {
                list.add(node);
                node = node.left;

            } else {
                list.add(node);
                node = node.right;

            }
        }
        if (!flag)
            list.clear();
        Collections.reverse(list);
        return list;
    }

    public static int lca(Node node, int d1, int d2) {
        int lca = -1;
        while (node != null) {
            if (d1 < node.data && d2 < node.data)
                node = node.left;
            if (d1 > node.data && d2 > node.data)
                node = node.right;
            else {
                lca = node.data;
                break;
            }
        }
        return lca;
    }

    public static void printInRange(Node node, int lr, int rr) {
        if (node == null)
            return;
        while (node != null) {
            printInRange(node.left, lr, rr);
            if (node.data >= lr && node.data <= rr)
                System.out.println(node.data);
            printInRange(node.right, lr, rr);
        }
    }

    public static Node addData(Node node, int data) {
        if (node == null)
            return new Node(data);
        if (node.data > data)
            node.left = addData(node.left, data);
        if (node.data < data)
            node.right = addData(node.right, data);
        return node;
    }

    public static Node removeNode(Node node, int data){
        if(node==null)
           return null;
        if(node.data>data)
           node.left= removeNode(node.left,data);
        else if(node.data<data)
                node.right= removeNode(node.right,data);
        else{
            if(node.left==null || node.right==null)
               return node.left!=null ? node.left: node.right;
            int min= minimum(node.right);
            node.data=min;
            node.right= removeNode(node.right,min);
        }
        return node;
    }

    public static void modify_(Node node, int[]arr){
        if(node==null)
           return;
        modify_(node.right,arr);
        node.data+=arr[0];
        arr[0]=node.data;
        modify_(node.left,arr);
    }

    public Node modify(Node node){
       int[] arr= new int[1];
       modify_(node,arr);
       return node;
    }
}
