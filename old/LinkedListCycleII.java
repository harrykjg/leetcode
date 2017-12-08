//http://blog.csdn.net/sysucph/article/details/15378043 这个解释的好
//这题自己想的话肯定想不出来，太他妈难了，如果可以用extra space的话就用hashtable存节点
//http://blog.csdn.net/linhuanmars/article/details/21260943 code ganker的
//http://blog.csdn.net/lidalong0408/article/details/14104553

public class LinkedListCycleII {
	public static void main(String[] args) {
		LinkedListCycleII ll=new LinkedListCycleII();
		ListNode h=new ListNode(1);
		h.next=new ListNode(2);
		h.next.next=h;
		ll.detectCycle(h);
		
		
	}
	
	
	public ListNode detectCycle(ListNode head) {
		if(head==null||head.next==null){
			return null;
		}
		ListNode f=head.next.next;
		ListNode s=head.next;
		//就是按他们的结论，有环的链表有一个性质，当fast和slow第一次相遇时，把slow移到头，再fast
		//和slow都按每次走一步走，再相遇时，该点就是环的起点
		while(true){//先找到第一次相遇的点.这里写的时候注意，先fast和slow都往外走了的否则
			//如果起点是头结点的话，无法再while循环里判断他们相遇的点，因为一开始f就等于s，因此
			//已进入while循环就break了。这是看了code ganker的写法的
			if(f==null||f.next==null||s==null||s.next==null){
				return null;
			}
			if(f==s){
				break;
			}else{
				f=f.next.next;
				s=s.next;
			}
			
		}
		s=head;//再把其中一个移到头部，再一步一步走，相遇便是环的起点
		while(s!=f){
			s=s.next;
			f=f.next;
		}
		return s;
	}
	//第三轮,把找中点和找换的两个指针的用法区别没想好，然后相遇后再2个指针都一步步走也没记住
	public ListNode detectCycle2(ListNode head) {
		if(head==null||head.next==null){
			return null;
		}
		ListNode wal=head.next;//注意这里wal和run都是先走出去的了，否则都指向head再利用
		//while走出去的话，会没法判断1自己连自己这个例子,但是如果单独处理1自己连1的例子，那么就不用这样先走出去了
		ListNode run=head.next.next;//这里run！=null容易忘掉，如果没写的话，1-2-null这个例子就会
		while(wal!=run&&run!=null&&run.next!=null&&run.next.next!=null){//抛异常了
			wal=wal.next;
			run=run.next.next;
		}
		if(run==null||run.next==null||run.next.next==null){
			return null;
		}
		wal=head;
		while(wal!=run){
			wal=wal.next;
			run=run.next;
		}
		return wal;
		
	}

}
