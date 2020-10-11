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

    //04/19/2020,思路和上次一样，写的不好，找m的前一个写错了，while那里也改了
    public ListNode reverseBetween2(ListNode head, int m , int n) {
        if(head==null){
            return null;
        }
        ListNode dum=new ListNode(0);
        dum.next=head;//不设dum的话，如果m是1则没法找到前一个节点
        ListNode cur=dum;
        ListNode left=null;
        ListNode right=null;
        int index=1;
        while (index<=n){
            if(index==m){
                left=cur;
            }
            if(index==n){
                right=cur.next;
            }
            cur=cur.next;
            index++;
        }
        while (left.next!=right){//把left。next仍到m后面
            ListNode temp=left.next;
            left.next=left.next.next;
            temp.next=right.next;
            right.next=temp;
        }
        return dum.next;
    }
//05/24/2020，写的凑活，找m，n那有点麻烦
    public ListNode reverseBetween3(ListNode head, int m , int n) {
        if(head==null){
            return head;
        }
        ListNode dum=new ListNode(0);
        dum.next=head;
        ListNode cur=dum;
        ListNode b=dum;
        ListNode e=dum;
        int index=0;
        while (index<n){//这里index和n改了一次
            if(index==m-1){
                b=cur;
            }
            cur=cur.next;
            index++;
        }
        e=cur;
        while (b.next!=e){
            ListNode temp=b.next.next;
            b.next.next=e.next;
            e.next=b.next;
            b.next=temp;
        }
        return dum.next;
    }
}
