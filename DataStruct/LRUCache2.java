package DataStruct;

import java.util.HashMap;
import java.util.Map;

public class LRUCache2{
    //6/7/2021。没啥大问题，改了几次
    public static void main(String[] args){
        LRUCache2 l=new LRUCache2(2);
        l.put(2,1);
        l.put(2,2);
        System.out.println(l.get(2));
        l.put(1,1);
        l.put(4,1);
        System.out.println(l.get(4));
    }
    int cap;
    DoublyNode head;
    DoublyNode end;
    Map<Integer,DoublyNode> map=new HashMap<>();
    int curCap;
    public LRUCache2(int capacity) {
        cap=capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            putToHead(map.get(key));
            return map.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(!map.containsKey(key)){
            DoublyNode node=new DoublyNode(key,value);

            if(curCap==0){
                node.pre=node;
                node.next=node;
                head=node;
                end=node;
                map.put(key,node);
                curCap++;
                return;
            }
            if(curCap<cap){
                putToHead(node);
                map.put(key,node);
                curCap++;
                return;
            }
            if(curCap==cap){
                putToHead(node);
                map.put(key,node);
                curCap++;
                deleteLast();
            }
        }else {
            DoublyNode node=map.get(key);
            node.val=value;
            putToHead(node);
        }

    }
    void putToHead(DoublyNode node){
        if(node==head){
            return;
        }
        if(node==end){
            head=node;
            end=node.pre;
            return;
        }
        if(map.containsKey(node.key)){//当这个node就是在link中间时
            node.pre.next =node.next;
            node.next.pre=node.pre;
            node.next=head;
            node.pre=end;
            end.next=node;
            head.pre=node;
            head=node;
            end=node.pre;
        }else {//当这个node是一个新的node时，插入到head前面
            node.next=head;
            node.pre=head.pre;
            head.pre=node;
            node.pre.next=node;//node.pre就是end了
            head=node;
        }
    }
    void deleteLast(){
        DoublyNode pre=end.pre;
        pre.next=end.next;
        end.next.pre=pre;
        map.remove(end.key);
        end=pre;
        curCap--;
    }
}
class DoublyNode{
    int key;
    int val;
    DoublyNode pre;
    DoublyNode next;
    public DoublyNode(int key,int val){
        this.key=key;
        this.val=val;
    }
}
