package DataStruct;

import java.util.HashMap;

/**
 * Created by 502575560 on 7/11/17.
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache lr=new LRUCache(2);
        lr.put(2, 1);

        lr.put(1, 1);
        System.out.println(lr.get(2));
        lr.put(4,4);
        System.out.println(lr.get(1));
        System.out.println();

    }
    //这次没想到如何在O(1)时间找到是那个listnode,原来是hashmap应该存的就是listnode,而且listnode里要有key和value,我开始只写了个value
    //还是太tm烦了要debug很久,不搞了
    doublelyLinkedList head;
    doublelyLinkedList last;
    HashMap<Integer,doublelyLinkedList> map;
    int cap;
    int size;
    public LRUCache(int capacity) {
        cap=capacity;
        map=new HashMap<>();
    }

    public int get(int key) {
        return 0;
    }
    public void put(int key, int value) {

    }

    void setHead(doublelyLinkedList n){

    }
}
class doublelyLinkedList{
    doublelyLinkedList pre;
    doublelyLinkedList next;
    int val;
    int key;
    public doublelyLinkedList(int x,int y){
        key=x;
        val=y;
    }
}
