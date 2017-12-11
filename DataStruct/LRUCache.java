package DataStruct;

import java.util.HashMap;

/**
 * Created by 502575560 on 7/11/17.
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache lr=new LRUCache(2);
        lr.put(1, 1);

        lr.put(2, 2);
        System.out.println(lr.get(1));
//        lr.put(4,4);
        System.out.println(lr.get(1));
        System.out.println("Sasas");

    }
    //这次没想到如何在O(1)时间找到是那个listnode,原来是hashmap应该存的就是listnode,而且listnode里要有key和value,我开始只写了个value
    //还是太tm烦了要debug很久,不搞了
//    doublelyLinkedList head;
//    doublelyLinkedList last;
//    HashMap<Integer,doublelyLinkedList> map;
//    int cap;
//    int size;
//    public LRUCache(int capacity) {
//        cap=capacity;
//        map=new HashMap<>();
//    }
//
//    public int get(int key) {
//        return 0;
//    }
//    public void put(int key, int value) {
//
//    }
//
//    void setHead(doublelyLinkedList n){
//
//    }


//九章第二轮12／9／2017,漏了2个地方，还算顺利
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
        if(!map.containsKey(key)){
            return -1;
        }else{
            setHead(map.get(key));
            return map.get(key).val;
        }
    }
    public void put(int key, int value) {
        if(map.containsKey(key)){
            map.get(key).val=value;
            setHead(map.get(key));
            return;
        }
        if(size+1>cap){
            removeLast();
        }
        doublelyLinkedList n=new doublelyLinkedList(key,value);
        map.put(key,n);
        setHead(n);
        size++;
    }
    void removeLast(){
        if(head==last){
            head=null;
            last=null;
            map.clear();
            return;
        }
        map.remove(last.key);
        last.pre.next=last.next;
        last.next.pre=last.pre;
        last=last.pre;
        size--;
    }
    void setHead(doublelyLinkedList n){
        if(head==null){
            head=n;
            last=n;
            head.next=last;
            head.pre=last;
            last.next=head;
            last.pre=head;
            return;
        }
        if (n==head){
            return;
        }
        if(n==last){
            last=last.pre;
            head=last.next;
            return;
        }
        //这里开始考虑的时候就把n当成是已经存在于链表重的，其实还是漏了一个情况，就是n是一个新建的node的话那么他的pre和next都是空，
        if(n.pre==null||n.next==null){
            n.next=head;
            n.pre=last;
            head.pre=n;
            last.next=n;
            head=n;
            return;
        }
        n.pre.next=n.next;
        n.next.pre=n.pre;
        n.next=head;
        n.pre=last;
        head.pre=n;
        head=n;
        last.next=head;

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
