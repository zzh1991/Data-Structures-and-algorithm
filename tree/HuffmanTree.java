package tree;

import java.util.*;

/**
 * Created by dell on 2016/7/3.
 */
class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    public Tree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

class Node implements Comparable<Node> {
    private String chars = "";
    private int freq = 0;
    private Node parent;
    private Node leftChild;
    private Node rightChild;

    public Node(String chars, int freq) {
        this.chars = chars;
        this.freq = freq;
    }

    @Override
    public int compareTo(Node o) {
        return freq - o.freq;
    }

    public boolean isParent(Node node) {
        return node == null;
    }

    public boolean isLeaf() {
        return chars.length() == 1;
    }

    public boolean isLeftChild() {
        return parent != null && this == parent.leftChild;
    }

    public String getChars() {
        return chars;
    }

    public void setChars(String chars) {
        this.chars = chars;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
public class HuffmanTree {
    public static void main(String[] args) {
        String string = new String("aaabbcddddeeeffg");
        Map<Character, Integer> map = charStat(string);
        List<Node> list = new ArrayList<>();
        Tree tree = buildHuffmanTree(map, list);
        inOrderPrint(tree.getRoot());
    }

    private static Map<Character, Integer> charStat (String string) {
        Map<Character, Integer> map = new HashMap<>();
        if (string == null || string.length() == 0) return map;

        for (char c : string.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    private static Tree buildHuffmanTree (Map<Character, Integer> map, List<Node> list) {
        if (map.isEmpty()) return new Tree();

        PriorityQueue<Node> queue = new PriorityQueue<>();
        Set<Character> set = map.keySet();
        for (Character c: set) {
            Node node = new Node(c.toString(), map.get(c));
            queue.add(node);
            list.add(node);
        }

        int size = queue.size();
        for (int i = 0; i < size - 1; i++) {
            Node node1 = queue.poll();
            Node node2 = queue.poll();
            Node root = new Node(node1.getChars() + node2.getChars(),
                    node1.getFreq() + node2.getFreq());
            node1.setParent(root);
            node2.setParent(root);
            root.setLeftChild(node1);
            root.setRightChild(node2);

            queue.add(root);
        }

        Tree tree = new Tree(queue.poll());
        return tree;
    }

    private static Map<Character, String> encode(List<Node> list) {
        Map<Character, String> encodeMap = new HashMap<>();
        if (list.isEmpty()) return encodeMap;

        for (Node node: list) {
            StringBuffer sb = new StringBuffer();
            Node cur = node;
            do {
                if (cur.isLeftChild()) {
                    sb.insert(0, 0);
                }
                else {
                    sb.insert(0, 1);
                }
                cur = cur.getParent();
            } while(cur.getParent() != null);
            encodeMap.put(node.getChars().charAt(0), sb.toString());
        }
        return encodeMap;
    }

    private static String decode (String binaryStr, Tree tree) {
        if (binaryStr == null || binaryStr.length() == 0) return "";

        LinkedList<Character> list = new LinkedList<>();
        for (char c: binaryStr.toCharArray()) {
            list.addLast(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!list.isEmpty()) {
            Node node = tree.getRoot();
            do {
                Character c = list.removeFirst();
                if (c.equals('0')) {
                    node = node.getLeftChild();
                }
                else {
                    node = node.getRightChild();
                }
            } while (!node.isLeaf());
            sb.append(node.getChars());
        }
        return sb.toString();
    }

    private static void inOrderPrint(Node node) {
        if (node != null) {
            inOrderPrint(node.getLeftChild());
            if (node.isLeaf())
                System.out.println(node.getChars());
            inOrderPrint(node.getRightChild());

        }
    }
}
