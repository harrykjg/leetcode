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

//8/15/2018,还算比较顺
    public int get2(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        doublyLinkedList dl=map.get(key);
        putFirst(dl);
        return dl.val;
    }

    public void put2(int key, int value) {
        if(map.containsKey(key)){
            map.get(key).val=value;
            putFirst(map.get(key));
            return;
        }else{
            if(cur+1>cap){
                removeLast();
            }
            doublyLinkedList node=new doublyLinkedList(key,value);
            map.put(key,node);
            if(head==null){
                head=node;
                last=node;
                head.pre=last;
                last.next=head;
            }else{
                putFirst(node);
            }
            cur++;
        }

    }
    void putFirst(doublyLinkedList node){
        if(node==head){
            return;
        }
        if(node==last){
            last=node.pre;
            head=node;
            return;
        }
        //这个putFirst方法在insert的时候也用到，所以也要考虑node前后都是null的情况
        if(node.pre==null||node.next==null){
            //就是把node放到head和last中间,画个图，就算head=last也是可以的
            last.next=node;
            node.pre=last;
            head.pre=node;
            node.next=head;
            head=node;
            return;
        }
        //先把node切出来
        node.pre.next=node.next;
        node.next.pre=node.pre;
        //再放到last和head中间
        last.next=node;
        node.pre=last;
        head.pre=node;
        node.next=head;
        //把head指向node
        head=node;
    }
    void removeLast(){
        if(last==null){
            return;
        }
        if(head==last){
            map.remove(last.key);
            head=null;
            last=null;
            cur--;
            return;
        }
        //先切出来最后一个，那么head。pre就是last了
        map.remove(last.key);
        last.pre.next=last.next;
        last.next.pre=last.pre;
        last=head.pre;

        cur--;
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
class MethodPath{
    String method;
    String path;
    public MethodPath(String method,String path){
        this.method=method;
        this.path=path;
    }

    @Override
    public boolean equals(Object o) {


    }

    @Override
    public int hashCode() {
        int result = method.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }
}