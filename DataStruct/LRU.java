package DataStruct;

import java.util.HashMap;

/**
 * Created by yufengzhu on 9/30/18.
 */
public class LRU {
    //就是漏掉了tail就是cur这个地方导致会错。还必须要单独处理
    public static void main(String[] args){
        LRU l=new LRU(2);
        l.put(1,1);
        l.put(2,2);
        System.out.println(l.get(1));
        l.put(3,3);
//        l.put(4,1);
        System.out.println(l.get(2));
    }
    int cap;
    int size;
    HashMap<Integer,DNode> map;
    DNode head;
    DNode tail;
    public LRU(int capacity) {
        this.cap=capacity;
        map=new HashMap<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        int val=map.get(key).val;
        putHead(key);
        return val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            map.get(key).val=value;
            putHead(key);
            return;
        }
        if(size==cap){
            removeLast();
        }
        DNode dn=new DNode(key,value);
        map.put(key,dn);
        if(head==null){
            head=dn;
            tail=head;
            head.next=tail;
            head.pre=tail;
        }else{
            putHead(key);
        }
        size++;
    }
    void putHead(int key){
        DNode cur=map.get(key);
        if (cur==head){
            return;
        }
        if(cur.next==null){
            cur.next=head;
            cur.pre=head.pre;
            head.pre=cur;
            tail.next=cur;
            head=cur;
        }else if(tail==cur){//就是漏掉了tail就是cur这个地方导致会错。还必须要单独处理
            head=tail;
            tail= head.pre;
        }
        else {
            cur.pre.next=cur.next;
            cur.next.pre=cur.pre;
            cur.next=head;
            cur.pre=tail;
            head.pre=cur;
            tail.next=cur;
            head=cur;

        }
    }
    void removeLast(){
        int key=tail.key;
        if(head==tail){
            head=null;
        }else{
            tail.pre.next=tail.next;
            tail.next.pre=tail.pre;
            tail=head.pre;
        }
        size--;
        map.remove(key);
    }
    class DNode{
        DNode next;
        DNode pre;
        int key;
        int val;
        public DNode(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
}
