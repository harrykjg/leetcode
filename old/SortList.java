//http://jixiangsanbao.wordpress.com/2014/07/15/sort-list/
//http://blog.csdn.net/linhuanmars/article/details/21133949
public class SortList {
	
	public static void main(String[] args) {
		SortList sl=new SortList();
		ListNode l=new ListNode(4);
		l.next=new ListNode(2);
		l.next.next=new ListNode(3);
		l.next.next.next=new ListNode(1);
		l.next.next.next.next=new ListNode(6);
		l.next.next.next.next.next=new ListNode(5);
		ListNode rs=sl.sortList(l);
		while(rs!=null){
			System.out.println(rs.val);
			rs=rs.next;
		}
	}
	
	
	//主要是看了他们的思想后，才知道要怎么找中点，分割数组，merge的方法是自己想的，他们的看不懂
	public ListNode sortList(ListNode head) {
		if(head==null||head.next==null){
			return head;//开始这里写的是return null，错的，但是居然accept了，发现了个bug
		}
		ListNode wal=head;
		ListNode run=head;
		while(run.next!=null&&run.next.next!=null){
			wal=wal.next;
			run=run.next.next;
		}
		ListNode head2=wal.next;
		wal.next=null;//把wal断后，分成了2个部分
		head=sortList(head);
		head2=sortList(head2);
		return merge(head,head2);

	}
	private ListNode merge(ListNode l1,ListNode l2){
		//和merge sorted list一样，我找了l1是被插的
		ListNode dum=new ListNode(0);
		dum.next=l1;
		ListNode cur1=dum;
		while(cur1.next!=null&&l2!=null){
			if(cur1.next.val<=l2.val){
				cur1=cur1.next;
				continue;
			}else{
				ListNode temp1=cur1.next;
				cur1.next=l2;
				l2=l2.next;
				cur1.next.next=temp1;
				cur1=cur1.next;
			}
		}
		if(l2!=null){
			cur1.next=l2;
		}
		return dum.next;
	}
	//第二次没写了，还是看了第一次的思路才知道
}
