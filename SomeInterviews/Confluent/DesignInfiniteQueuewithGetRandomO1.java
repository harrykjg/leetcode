package SomeInterviews.Confluent;

import java.util.*;

public class DesignInfiniteQueuewithGetRandomO1 {
    //这个和lc的insetdeletegetrandom不太一样，那里是随便delete，这里是只能delete queue头上的元素
//这里直接抄的答案，有点绕，需要一个list（里面装的是node），以便getrandom用size，还要一个map其中key是node，value是index
    class InfiniteQueue {
        private Dll dll; // Double linked list
        private Map<Node, Integer> map; // Key: Node; Value: index。这样设计因为queue是允许重复的，你没法用int作为key
        private List<Node> list;
        private Random rand;

        public InfiniteQueue() {
            this.dll = new Dll();
            this.map = new HashMap<>();
            this.list = new ArrayList<>();
            this.rand = new Random();
        }
/*
Create a new node and insert it at the head of the doubly linked list.
Append the node to the array list.
Store the node's index in the hash map.
All steps are O(1).
 */
        public void offer(int val) {
            Node node = new Node(val);
            int idx = list.size();
            dll.addFirst(node);
            map.put(node, idx);
            list.add(node);
        }
/*
If the list is empty, return -1.
Remove the tail node from the doubly linked list.
Retrieve its index from the hash map, and swap it with the last node in the array list.
Update indices in the hash map, remove the last node from the array list, and delete the old mapping.
Return the removed node's value.
Overall O(1).
 */
        public int poll() {
            if (list.isEmpty()) {
                return -1;
            }

            Node node = dll.removeLast();
            int idx = map.get(node);

            // Delete node in the list in O(1): Replace current node with the last node in the list
            int lastIdx = list.size() - 1;
            Node lastNode = list.get(lastIdx);
            list.set(idx, lastNode);
            map.put(lastNode, idx);

            // Remove current node from both map and list
            list.remove(lastIdx);
            map.remove(node);
            return node.val;
        }
/*
If empty, return -1.
Generate a random index within the size of the array list.
Return the value of the node at that index.
O(1) time.
 */
        public int getRandom() {
            if (list.isEmpty()) {
                return -1;
            }

            int n = rand.nextInt(list.size());
            return list.get(n).val;
        }
    }
    class Dll {
        Node head, tail;
        int size;

        Dll() {
            head = new Node(-1);//用的dummy head和tail
            tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public Node addFirst(Node node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
            size++;
            return node;
        }

        private Node remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
            return node;
        }

        public Node removeLast() {
            return this.remove(tail.prev);
        }
    }
    class Node {
        int val;
        Node next;
        Node prev;

        Node(int val) {
            this.val = val;
        }
    }
}
