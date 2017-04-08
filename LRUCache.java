import java.util.HashMap;
import java.util.List;

/**
 * Created by 502575560 on 7/12/16.
 */
public class LRUCache {
    int cap;
    int cur;
    HashMap<Integer,doublyLinkedList> map=new HashMap<>();
    doublyLinkedList head;
    doublyLinkedList last;

    public static void main(String[] args){
        LRUCache l=new LRUCache(2);
        l.set(2,1);
        l.set(2,2);
        System.out.println(l.get(2));
        l.set(1,1);
        l.set(4,1);
        System.out.println(l.get(2));
//        System.out.println(l.get(3));
    }

    public LRUCache(int capacity) {
        cap = capacity;

    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        putHead(map.get(key));

        return map.get(key).val;

    }

    public void set(int key, int value) {
        if(map.containsKey(key)){
            map.get(key).setVal(value);
            putHead(map.get(key));


        }else{
            doublyLinkedList node=new doublyLinkedList(key,value);
            putHead(node);
            map.put(key,node);
            cur++;
            if(cur>cap){
                deleteLast();
            }
        }

    }

    public void putHead(doublyLinkedList node){
        if(head==null){
            head=node;
            last=node;
            head.pre=head;
            head.next=head;
            cur++;
            if(cur>cap){
                deleteLast();
            }
            return;
        }
        if(node==head){
            return;
        }
        else if(node==last){
            head=node;
            last=last.pre;
        }else{
            if(node.pre==null||node.next==null){
                node.pre=last;
                node.next=head;
                head.pre=node;
                last.next=node;

                head=node;
                return;
            }
            node.pre.next=node.next;
            node.next.pre=node.pre;

            head.pre=node;
            node.pre=last;
            node.next=head;

            head=node;

        }

    }
    public void deleteLast(){
        if(head==last){
            head=null;
            cur--;
            map.remove(last.key);
            return;
        }
        last.pre.next=last.next;
        last.next.pre=last.pre;
        last=last.pre;
        cur--;
        map.remove(last.key);
    }
}

class doublyLinkedList{
    doublyLinkedList pre;
    doublyLinkedList next;
    int val;
    int key;
    public doublyLinkedList(int key,int val){
        this.val=val;
        this.key=key;
    }
    public void setVal(int a){
        val=a;
    }
}