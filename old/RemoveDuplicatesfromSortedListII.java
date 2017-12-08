//http://blog.csdn.net/linhuanmars/article/details/24389429
//http://jixiangsanbao.wordpress.com/2014/05/10/remove-duplicates-from-sorted-list-2/

public class RemoveDuplicatesfromSortedListII {
	
	public static void main(String[] args) {
		RemoveDuplicatesfromSortedListII rd=new RemoveDuplicatesfromSortedListII();
		ListNode n=new ListNode(1);
		n.next=new ListNode(1);
		rd.deleteDuplicates(n);
	}
	//第一次是半年前的了，肯定写的不行，第二次写调试了几次，行了
	public ListNode deleteDuplicates(ListNode head) {
		if(head==null){
			return head;
		}
		ListNode dum=new ListNode(Integer.MIN_VALUE);
		dum.next=head;
		ListNode cur=dum;
		boolean flag=false;
		ListNode cur2=dum;
		//比如dum，1,1开始cur在dum那，cur2在1那，然后发现1和1一样，则cur.next=cur2.next即dum.next
		//是空了。再如dum，1,2,3,3,3,4,4，先cur在dum，然后用cur2比较1,2，不相等，再cur在1，比较
		//2,3不等，再cur在2，比较3,3相等，则cur2前进一步，再比较3,3还是相等，再前进，再比较3,4，
		//不相等，此时cur2在3，cur再2，所以把cur.next=cur2.next，即变成dum，1,2,4,4了，后面也一样
		while(cur.next!=null){
			cur2=cur.next;
			while(cur2.next!=null&&cur2.next.val==cur2.val){
				flag=true;//flag用来记录有没有发现相等的
				cur2=cur2.next;
			}
			if(flag){
				cur.next=cur2.next;
				flag=false;
			}else{
				flag=false;
				cur=cur.next;
				
			}
			
		}
		return dum.next;
		
	}

}
