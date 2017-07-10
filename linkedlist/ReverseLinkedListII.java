package linkedlist;

/**
 * Created by 502575560 on 7/8/17.
 */
public class ReverseLinkedListII {
    //lintcode 写的,思路是先找到第m个点的前一个点,和第n个点本身,然后把第m和n之间的这一段翻转,再接上第m个点的前一个就行了
    public ListNode reverseBetween(ListNode head, int m , int n) {
        // write your code
        ListNode dum=new ListNode(1);
        dum.next=head;
        ListNode cur=dum;
        int i=1;
        while(i<m){
            cur=cur.next;
            i++;
        }
        ListNode cur1=cur;
        i=1;
        cur=dum;
        while(i<=n){
            cur=cur.next;
            i++;
        }
        ListNode cur2=cur;
        cur=cur1;
        i=0;
        while(i<n-m){
            ListNode temp=cur1.next.next;
            cur1.next.next=cur2.next;
            cur2.next=cur1.next;
            cur1.next=temp;

            i++;
        }
        return dum.next;

    }
}
