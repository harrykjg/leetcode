package linkedin;

import java.util.*;

public class MaxStack716 {
    static void main() {

    }
    //为啥要用doubly linkedlist是因为可以快速在popmax的情况下快速在链表中间删除元素，单linkedlist就找不到pre节点所以不好删。这个list
    //还应该维持stack的顺序，即新进来的将变成head。
    //然后想的用map的key为integer来定位node，但是如果push进来的是重复的key那咋办？那只能map里装的是list of dnode，有重复的就append到list后
    //但是这样的话如果pop的情况咋办？是没问题的，pop的话永远都是把head pop掉，然后map里把对应的list of node删掉最后一个就行了，
    //然后就是怎么获得topmax，那就得用treemap的lastkey，就是最大的数。popmax怎么处理？用map定位删除map里的list的最后一个，那么就能拿到
    //这最后一个node，由于他就是doublylinkedlist因此可删掉并维持doublylinkedlist的顺序
    class MaxStack {
        Dnode head;
        TreeMap<Integer, List<Dnode>> map=new TreeMap<>();
        public MaxStack() {

        }

        public void push(int x) {
            Dnode n=new Dnode(x);
            if(head==null){
                head=n;
                head.next=head;
                head.pre=head;

            }else {
                n.next=head;
                n.pre=head.pre;
                head.pre.next=n;
                head.pre=n;
                head=n;
            }
            if(!map.containsKey(x)){
                List<Dnode> ls=new ArrayList<>();
                ls.addLast(n);
                map.put(x,ls);
            }else{
                map.get(x).addLast(n);
            }

        }

        public int pop() {
            int rs=head.value;
            if(head.next==head){
                head=null;
            }else{
                head.next.pre=head.pre;
                head.pre.next=head.next;
                head=head.next;
            }
            map.get(rs).removeLast();
            if (map.get(rs).size()==0){
                map.remove(rs);
            }
            return rs;
        }

        public int top() {
            return head.value;
        }

        public int peekMax() {
            return map.lastKey();
        }

        public int popMax() {
            int rs=map.lastKey();
            Dnode node=map.get(rs).removeLast();
            if(map.get(rs).size()==0){
                map.remove(rs);
            }
            if(head==node){
                node.next.pre=node.pre;
                node.pre.next=node.next;
                head=node.next;
            }else{
                node.next.pre=node.pre;
                node.pre.next=node.next;
                node=null;
            }
            return rs;
        }
    }
    class Dnode{
        int value;
        Dnode next;
        Dnode pre;
        public Dnode(int value){
            this.value=value;
        }
    }
}
