package com.binary.tree;

import java.util.HashMap;
import java.util.Map;


//https://leetcode.com/problems/copy-list-with-random-pointer/description/
public class CopyListWithRandomPointer {

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    public Node copyRandomList(Node head) {
        Map<Node, Node> originalToCopy = new HashMap<>();
        Node current = head;
        while (current != null) {
            Node copy = new Node(current.val);
            originalToCopy.put(current, copy);
            current = current.next;
        }
        current = head;
        while (current != null) {
            Node copy = originalToCopy.get(current);
            copy.next = originalToCopy.get(current.next);
            copy.random = originalToCopy.get(current.random);
            current = current.next;
        }
        return originalToCopy.get(head);
    }
}
