//http://blog.csdn.net/linhuanmars/article/details/19712593

public class MergeTwoSortedLists {
	
	//算是自己想出来的吧，其实两个list，随便找一个作为被插的，另一个就是专门去插的，找谁都一样
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1==null){
			return l2;
		}
		if(l2==null){
			return l1;
		}
		ListNode dum1=new ListNode(0);
		ListNode dum2=new ListNode(0);
		dum1.next=l1;
		dum2.next=l2;
		ListNode cur1=dum1;
		while(cur1.next!=null&&dum2.next!=null){
			if(cur1.next.val<dum2.next.val){//如果第一个list的node小于第二个list的node，则继续
				//搜第一个list的下一个node，直到找到下一个比第二个list大或者相等的值的时候，
				//把第二个list的那个node切出来，查到第一个list里，然后继续搜第一个list
				//第二个list就不用动了，都是等着被切的
				cur1=cur1.next;
				continue;
			}
			else{
				ListNode temp1=dum2.next;
				dum2.next=dum2.next.next;
				ListNode temp2=cur1.next;
				cur1.next=temp1;
				temp1.next=temp2;
//				temp1.next=cur1.next; //我都是喜欢用两个temp来记录的，其实可以不用第二个temp
//				cur1.next=temp1;//换成这样两步也行
				cur1=cur1.next;
			}
		}
		if(dum2.next!=null){//如果第一个list完了，如果第二个list后面还剩一溜，则街上
			cur1.next=dum2.next;
		}
		return dum1.next;
	}
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {//第二次想我是这样写的
		if(l1==null){
			return l2;
		}
		if(l2==null){
			return l1;
		}
		ListNode dum1=new ListNode(0);
		
		dum1.next=l1;
		
		ListNode cur1=dum1;
		while(cur1.next!=null&&l2!=null){
			if(cur1.next.val<l2.val){
				cur1=cur1.next;
				continue;
			}
			else{
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
		return dum1.next;
	}

}
