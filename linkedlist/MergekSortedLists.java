package linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by 502575560 on 7/9/17.
 */
public class MergekSortedLists {
    //就是用的heap,写起来比mergesort简单
    public ListNode mergeKLists(ListNode[] list) {
        if(list.length==0){
            return null;
        }
        ListNode dum=new ListNode(1);
        PriorityQueue<ListNode> q=new PriorityQueue(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });
        for(int i=0;i<list.length;i++){
            if(list[i]!=null){
                q.offer(list[i]);
            }

        }
        ListNode cur=dum;
        while (q.size()!=0){
            ListNode small=q.poll();
            cur.next=small;
            cur=cur.next;
            if(small.next!=null){
                q.offer(small.next);
            }
        }
        return dum.next;


    }
}
