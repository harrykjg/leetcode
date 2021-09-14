package DataStruct;

public class PalindromeLinkedList {
    //8/27/2021 思路没问题，改了一两下对了
    public boolean isPalindrome(ListNode head) {
        ListNode dum=new ListNode(0);
        dum.next=head;
        ListNode slow=dum;
        ListNode fast=dum;
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode second=slow.next;
        slow.next=null;
        //把第二段反过来 如 dum 1 2 3，不断把1后面的那个插到dum后面，第一步dum 2 1 3，第二部 dum 3 2 1，1。next为空即停止
        ListNode dum2=new ListNode(0);
        dum2.next=second;
        ListNode cur=second;
        while (cur.next!=null){
            ListNode temp=cur.next;
            cur.next=cur.next.next;
            temp.next=dum2.next;
            dum2.next=temp;

        }
        cur=dum2.next;
        ListNode first=dum.next;
        while (cur!=null&&first!=null){
            if (cur.val!=first.val){
                return false;
            }
            cur=cur.next;
            first=first.next;
        }
        return true;
    }

    // 以前的代码。思路是对的，开始想的是找到重点，看list是奇数还是偶数，然后把后半段翻转，与前半段比较
    public static boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return true;
        }
        if (head.next == null) {
            return true;
        }
        ListNode dum = new ListNode(0);
        dum.next = head;
        ListNode wal = dum;
        ListNode run = dum;
        while (run!=null&&run.next != null) {
            wal = wal.next;
            run = run.next.next;
        }
        ListNode head2=wal.next;
        wal.next=null;//把list分成前后两段

        //把后半段reverse,如1234，先变成2134，再3214，再4321
        ListNode cur=head2;
        while(cur.next!=null){//这里开始写错了，要想清楚
            ListNode temp=cur.next;
            cur.next=cur.next.next;
            temp.next=head2;
            head2=temp;

        }
        ListNode cur1=head;
        ListNode cur2=head2;
        while(cur1!=null&&cur2!=null){//反正已经把list分成2段了，就算list本来是奇数如5，分段后后面那段只有2个node，
            if(cur1.val!=cur2.val){//所以谁先完就整个循环结束
                return false;
            }
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return true;
    }
}
