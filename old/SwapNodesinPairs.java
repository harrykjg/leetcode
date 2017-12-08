public class SwapNodesinPairs {
	
	public static void main(String[] args) {
		SwapNodesinPairs sn=new SwapNodesinPairs();
		ListNode l=new ListNode(1);
		l.next=new ListNode(2);
		l.next.next=new ListNode(3);
	    l.next.next.next=new ListNode(4);
	    ListNode rs=sn.swapPairs2(l);
	}

	public ListNode swapPairs(ListNode head) {
		if(head==null||head.next==null){
			return head;
		}
		ListNode dum=new ListNode(0);
		dum.next=head;
		ListNode cur=dum;
		while(cur!=null&&cur.next!=null&&cur.next.next!=null){//其实这里cur肯定不为空的，
			//因为如果换了的话，肯定是2cur后面接了2个node的，所以cur.next.next肯定不是空
			ListNode temp1=cur.next;
			cur.next=cur.next.next;
			ListNode temp2=cur.next.next;
			cur.next.next=temp1;
			temp1.next=temp2;
			
			cur=cur.next.next;
		}
		return dum.next;

	}
	//1/10
	public ListNode swapPairs2(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode dum=new ListNode(Integer.MAX_VALUE);
        dum.next=head;
        ListNode cur=dum;
        while(cur.next!=null&&cur.next.next!=null){
            ListNode temp=cur.next.next;
            cur.next.next=cur.next.next.next;
            temp.next=cur.next;
            cur.next=temp;
            cur=cur.next.next;
        }
        return dum.next;
    }

}
