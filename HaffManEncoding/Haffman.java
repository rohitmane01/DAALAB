
package HaffManEncoding;

import java.util.*;
//Goal: To encode the character string that we get form the user as an input by using the Haffman Encoding Algo






// Node class represents each character and its frequency in the Huffman tree
class Node {
    char ch; // The character
    int freq; // Frequency of the character
    Node left, right; // Left and right children in the tree

    // Constructor for leaf node (actual character)
    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    // Constructor for internal node (no character, just frequency)
    Node(int freq, Node left, Node right) {
        this.ch = '\0'; // Special value for internal nodes
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}


public class Haffman{

    public static void main(String[] args) {

        // Take input from user
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string that you want to encode");
        String s = sc.nextLine();


        // Start Huffman encoding process
        map(s);
    }


    // This method counts frequency of each character and starts tree building

    private static void map(String s){

        Map<Character,Integer> mp = new HashMap<>(); // Stores character frequencies

        // Count frequency for each character in the string and store it into hashMAp

        for(char c : s.toCharArray()){
            mp.put(c, mp.getOrDefault(c, 0)+1);
        }



        // Create a priority queue (min-heap) to build the Huffman tree
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.freq));
        //here the sorting is done on the basis of the frequency of the each node..
       

        // Add each character as a leaf node to the queue
        for(Map.Entry<Character,Integer> entry : mp.entrySet()){
            pq.add(new Node(entry.getKey(), entry.getValue())); // priority queue will consist leaf nodes i.e. (char,freq)
        }

        // Build the Huffman tree from the queue
        Node root = buildTree(pq);

        // Generate Huffman codes for each character
        Map<Character, String> codes = new HashMap<>();
        generateCodes(root, "", codes);

        // Print the Huffman codes for each character
        System.out.println("Huffman Codes:");
        for(Map.Entry<Character, String> entry : codes.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Encode the input string using the Huffman codes
        System.out.println("\nEncoded String:");
        StringBuilder encoded = new StringBuilder();
        for(char c : s.toCharArray()) {
            encoded.append(codes.get(c)); // Replace each character with its code
        }
        System.out.println(encoded.toString());
    }


    // This method builds the Huffman tree using the priority queue
    private static Node buildTree(PriorityQueue<Node> pq) {

        // Keep combining two lowest frequency nodes until one node remains
        while(pq.size() > 1) {
            Node left = pq.poll(); // Node with lowest frequency
            Node right = pq.poll(); // Node with second lowest frequency

            // Create a new internal node with combined frequency
            Node merged = new Node(left.freq + right.freq, left, right);
            pq.add(merged); // Add back to the queue ------
            
            // leaf nodes removed and internal nodes addded in the priority queue
        }

        // The last node is the root of the Huffman tree
        return pq.poll();
    }


    // This method generates Huffman codes by traversing the tree
    private static void generateCodes(Node node, String code, Map<Character, String> codes) {
        if(node == null) return; // Base case: empty node

        if(node.ch != '\0') {
            // If it's a leaf node, store the code for the character
            codes.put(node.ch, code);
        }

        // Traverse left subtree, add '0' to code
        generateCodes(node.left, code + "0", codes);

        // Traverse right subtree, add '1' to code
        generateCodes(node.right, code + "1", codes);
    }

}
