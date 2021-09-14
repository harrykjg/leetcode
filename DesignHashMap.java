import java.util.ArrayList;
import java.util.List;

public class DesignHashMap {
    public static void main(String[] args){
        DesignHashMap map=new DesignHashMap();
        map.put(1,1);
        map.put(2,2);
        System.out.println(map.get(1));
        System.out.println(map.get(3));
        map.put(2,1);
        System.out.println(map.get(2));
        map.remove(2);
        System.out.println(map.get(2));
    }
//    Kv[] list=new Kv[1000];
//    int mod=1000;
//    public DesignHashMap() {
//
//    }
//
//    /** value will always be non-negative. */
//    public void put(int key, int value) {
//        if (list[key%mod]==null){//新的值
//            Kv kv=new Kv(key,value);
//
//            list[key%mod]=kv;
//            return;
//        }
//        //否则，这个位置上有了一个东西，这个东西的key hash出来值和现在这个key hash出来的东西一样。这个东西是个linkedlist，还得便利这个list，
//        //便利的时候又有2种情况，1，没找到相同的key，那就返回这个linkedlist最后的那一个点，把现在新的点加上去。2找到相同的key，把他的value直接update
//        Kv cur=list[key%mod];//必须找到前一个点才能设置后一个点，如果现在的点就是null了那你就没法设置
//        if (cur.key==key){
//            cur.value=value;
//            return;
//        }
//        while (cur!=null&&cur.next!=null&&cur.next.key!=key){
//            cur=cur.next;
//        }
//        if (cur.next!=null){//
//            cur.next=new Kv(key,value);
//        }else {
//            cur.next.value=value;
//        }
//    }
//
//    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
//    public int get(int key) {
//        if (list[key%mod]==null){
//            return -1;
//        }
//        Kv node=list[key%mod];
//        while (node!=null&&node.key!=key){
//            node=node.next;
//        }
//        if (node==null){
//            return -1;
//        }
//        return node.value;
//    }
//
//    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
//    public void remove(int key) {
//        if (list[key%mod]==null){
//            return;
//        }
//        Kv node=list[key%mod];
//        while (node!=null&&node.next!=null&&node.next.key!=key){
//            node=node.next;
//        }
//        if (node==null){
//            return ;
//        }
//        if (node.next.key==key){
//            Kv temp=node.next.next;
//            node.next=temp;
//        }
//
//    }
//    class Kv{
//        int key;
//        int value;
//        Kv next;
//        public Kv(int key,int value){
//            this.key=key;
//            this.value=value;
//        }
//    }

    Node[] a=new Node[1000];
    /** Initialize your data structure here. */
    public DesignHashMap() {

    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index=key%1000;
        if(a[index]==null){
            Node n=new Node(key,value);
            a[index]=n;
        }else{//找这个linkedlist有没有对应的key
            Node cur=a[index];
            if(cur.key==key){//第一个就是
                cur.value=value;
                return;
            }
            while(cur!=null&&cur.next!=null&&cur.next.key!=key){//否则要用前一个节点看后一个节点往后走
                cur=cur.next;
            }
            if(cur.next==null){//如果走到最后都没找到对应的key，不用next判断的话这里没法append到最后
                Node n=new Node(key,value);
                cur.next=n;
            }else{//找到了
                cur.next.value=value;
            }
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index=key%1000;
        Node cur=a[index];
        while(cur!=null){
            if(cur.key==key){
                return cur.value;
            }
            cur=cur.next;
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index=key%1000;
        Node cur=a[index];
        if(cur==null){
            return;
        }
        if(cur.key==key){//第一个node就是了
            a[index]=cur.next;
            return;
        }

        while(cur.next!=null){
            if(cur.next.key==key){//找到了，删掉
                cur.next=cur.next.next;
                return;
            }
            cur=cur.next;
        }
    }
    class Node{
        int key;
        int value;
        Node next;
        public Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }
}
