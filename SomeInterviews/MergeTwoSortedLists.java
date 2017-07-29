package SomeInterviews;

/**
 * Created by 502575560 on 7/22/17.
 */
public class MergeTwoSortedLists {
    //以前是把l2插进l1这样写的，比较麻烦，还不如比较l1 l2谁小就插谁再dum后面
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        ListNode dum=new ListNode(1);
        ListNode cur=dum;
        while (l1!=null&&l2!=null){
            if(l1.val<l2.val){
                cur.next=l1;
                l1=l1.next;
            }else {
                cur.next=l2;
                l2=l2.next;
            }
            cur=cur.next;
        }
        if(l1!=null){
            cur.next=l1;
        }
        if(l2!=null){
            cur.next=l2;
        }
        return dum.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
