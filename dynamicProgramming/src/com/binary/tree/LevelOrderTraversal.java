package com.binary.tree;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal {

    public void levelOrderTraversal(Node node) {
        Queue<Node> queue = new LinkedList();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node n = queue.remove();
            System.out.println(n.data);
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }
    }

}
