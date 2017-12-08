//http://blog.csdn.net/linhuanmars/article/details/21903027
//http://jixiangsanbao.wordpress.com/2014/04/12/rotate-list/

public class RotateList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode n=new ListNode(1);
//		n.next=new ListNode(2);
//		n.next.next=new ListNode(3);
//		n.next.next.next=new ListNode(4);
//		n.next.next.next.next=new ListNode(5);
		
		RotateList rl=new RotateList();
		
		ListNode l=rl.rotateRight2(n, 1);
		
		while(l!=null){
			System.out.println(l.val);
			l=l.next;
		}
		
	}

	public ListNode rotateRight(ListNode head, int n) {

		if (head == null) {
			return null;
		}

		int len = 1;
		ListNode last = head;
		while (last.next != null) {
			len++;
			last = last.next;
		}
		int nn = n % len;
		if (nn == 0) {
			return head;
		}
		ListNode cur = head;
		ListNode re = null;
		int i = 0;
		while (i < len - nn - 1) {
			cur = cur.next;
			i++;
		}
		re = cur.next;
		last.next = head;
		cur.next = null;

		return re;

	}
	//第二次写，有些别扭，调试了几次
	public ListNode rotateRight2(ListNode head, int n) {
		if(head==null||n==0){
			return head;
		}
		ListNode dum=new ListNode(0);//dum其实是多余的根本不用，这里就没改了
		dum.next=head;
		ListNode cur=dum;
		int len=0;
		while(cur.next!=null){
			len++;
			cur=cur.next;
		}
		int nn=n%len;
		if(nn==0){
			return head;
		}
		cur=head;
		for(int i=0;i<len-nn-1;i++){
			cur=cur.next;
		}
		ListNode temp1=cur;
		ListNode temp2=cur.next;
		while(cur.next!=null){
			cur=cur.next;
		}
		//比如dum1,2,3,4,5，n为2，现在就找到了3作为temp1,4作为temp2，cur指向5，然后把5的next指向
		cur.next=head; //1，dum.next指向4，再把3的next设为空就行了
		dum.next=temp2;
		temp1.next=null;
		return dum.next;
	}
	//1/10可以run先走k步，然后，wal和run一起走，到run。next为空位置，此时wal指向的就是应该要换
	//的序列的第一个节点的前一个节点，再把包括wal在内的前面reverse，再把wal后面的reverse，再
	//整个数组reverse。但是发现第二次的方法更好。。

}

