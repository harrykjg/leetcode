import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by 502575560 on 7/10/16.
 */
public class MergekSortedLists {
    //july2017,卧槽,除了priorityqueue不知道怎么样写comparator之外一次过,而且比以前的简介,因为用了dum
    public ListNode mergeKLists(ListNode[] list) {
        if(list.length==0){
            return null;
        }
        ListNode Dum=new ListNode(1);
        ListNode cur=Dum;
        PriorityQueue<ListNode> q=new PriorityQueue<>(10,new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }

        });
        for(int i=0;i<list.length;i++){
            if(list[i]!=null){
                q.add(list[i]);
            }
        }
        while(!q.isEmpty()){
            ListNode temp=q.poll();
            cur.next=temp;
            cur=cur.next;
            if(temp.next!=null){
                q.add(temp.next);
            }
        }
        return Dum.next;

    }

    //用mergesort,这个忘了怎么做,看以前的再自己写的

    public ListNode mergeKLists2(ListNode[] list) {
        if (list.length == 0) {
            return null;
        }
        int b=0;
        int e=list.length-1;
        int mid=(b+e)/2;

        return merge(0,list.length-1,list);

    }

    public ListNode merge(int b, int e,ListNode[] list){
        if(b>=e){
            return list[b];
        }

        int m=(b+e)/2;
        ListNode l1=merge(b,m,list);
        ListNode l2=merge(m+1,e,list);
        ListNode rs=mergesort(l1,l2);
        return rs;

    }
    public ListNode mergesort(ListNode l1,ListNode l2){
        if(l1==null&&l2==null){
            return null;
        }
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }

        ListNode dum=new ListNode(0);
        ListNode head=dum;
        ListNode cur1=l1;
        ListNode cur2=l2;
        while(cur1!=null&&l2!=null){
            if(cur1.val<cur2.val){
                head.next=cur1;
                cur1=cur1.next;
            }else{
                head.next=cur2;
                cur2=cur2.next;
            }
            head=head.next;
        }
        if(cur1==null&&cur2==null){
            return dum.next;
        }
        if(cur1==null){
            head.next=cur2;
        }else{
            head.next=cur1;
        }
        return dum.next;

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}