package linkedlist;

/**
 * Created by 502575560 on 7/8/17.
 */
public class RemoveDuplicatesfromSortedII {
    public static ListNode deleteDuplicates(ListNode head) {
        // write your code here
        ListNode dum=new ListNode(1);
        dum.next=head;
        ListNode cur=dum;
        while(cur!=null&&cur.next!=null){
            ListNode n=cur.next;
            boolean flag=false;
            while(n!=null&&n.next!=null&&n.val==n.next.val){
                n=n.next;
                flag=true;
            }
            if(!flag){
                cur=cur.next;
            }else{
                cur.next=n.next;
                //这里开始写了cur。next＝n。next就错了
            }

        }
        return dum.next;
    }
    //05/24/2020,改了一次对了，比以前的多设了个pre节点，之前的cur初始化=dum，我这里初始化cur为head
    public static ListNode deleteDuplicates2(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode dum=new ListNode(1);
        dum.next=head;
        ListNode cur=head;
        ListNode pre=dum;
        while (cur!=null){
            ListNode next=cur.next;
            boolean found=false;
            while (next!=null&&next.val==cur.val){
                next=next.next;
                found=true;
            }
            if(found){
                pre.next=next;
                cur=next;
                continue;
            }
            cur=cur.next;
            pre=pre.next;
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