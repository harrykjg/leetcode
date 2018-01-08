package DataStruct;

import java.util.PriorityQueue;

/**
 * Created by yufengzhu on 1/7/18.
 */
//九章第二轮
public class MergekSortedLists {
    //总体思路还是记得的
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        PriorityQueue<ListNode> pq=new PriorityQueue<>((o1, o2) -> o1.val-o2.val);
        int size=lists.length;
        for(int i=0;i<size;i++){
            if(lists[i]!=null){
                pq.offer(lists[i]);
            }
        }
        ListNode dum=new ListNode(0);
        ListNode cur=dum;
        while (!pq.isEmpty()){
            ListNode min=pq.poll();
            if(min.next!=null){
                pq.offer(min.next);
            }
            cur.next=min;
            cur=cur.next;
        }
        return dum.next;

    }
}
