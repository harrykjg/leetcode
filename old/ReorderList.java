public class ReorderList {
//http://blog.csdn.net/linhuanmars/article/details/21503215
	public static void main(String[] args) {

		
		ListNode node=new ListNode(1);
		node.next=new ListNode(2);
		node.next.next=new ListNode(3);
		node.next.next.next=new ListNode(4);;
		node.next.next.next.next=new ListNode(5);
		ListNode curr=node;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
		reorderList(node);
		curr=node;
		System.out.println("-------");
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
		
		
	}
//这样是对的，但是会超时
	public static void reorderList(ListNode head) {
		if(head==null||head.next==null){
			return ;
		}
		ListNode wal=head;
		ListNode run=head;
		while(run.next!=null&&run.next.next!=null){//思路就是用runner和walker，找到链表中点
			wal=wal.next;              //然后把它分成2个部分比如12345678，分成了1234和5678，
			run=run.next.next;        //然后1和8连，变成18234，同时5678变成567，然后2和7连，
		}                          //变成182734，同时567变成56，and so on，这样就是每次要遍历
		ListNode head2=wal.next;    //链表的一半找到最后的那点，应该比直接遍历整个链表找到最后那点
		wal.next=null;             //好一点了吧 
		ListNode dum=new ListNode(0);
		ListNode cur1=head;
		dum.next=head2;
		while(cur1!=null&&dum.next!=null){
			ListNode cur2=dum;
			while(cur2.next.next!=null){
				cur2=cur2.next;
			}
			ListNode temp=cur1.next;
			cur1.next=cur2.next;
			cur2.next=null;
			cur1.next.next=temp;
			cur1=cur1.next.next;

		}

	}

	public static void reorderList2(ListNode head) {
		if(head==null||head.next==null){
			return ;
		}
		ListNode wal=head;
		ListNode run=head;
		while(run.next!=null&&run.next.next!=null){
			wal=wal.next;              
			run=run.next.next;       
		}                         
		ListNode head2=wal.next;    
		wal.next=null;         
		ListNode dum=new ListNode(0);//比如说12345678，先分成1234和5678，然后把5678reverse了
		dum.next=head2;      //成8765，然后把1234和8765拼起来就行了。
		while(head2.next!=null){//如dum5678，先变成dum6578，再变成dum7658，再变成dum8765
			ListNode temp=head2.next;
			head2.next=head2.next.next;
			temp.next=dum.next;
			dum.next=temp;
		}
		while(dum.next!=null){//如1234和dum8765，先变成18234和dum765，再变成182734和dum65，在变
			ListNode temp=head.next;//成1827364和dum5，and so on
			head.next=dum.next;
			dum.next=dum.next.next;
			head.next.next=temp;
			head=head.next.next;
		}
		return;

	}
	//第二次的想法,一次成功，但是超时，还是要用他们的思路才行
	public  void reorderList3(ListNode head) {
		if(head==null||head.next==null){
			return ;
		}
		ListNode cur1=head;
		ListNode cur2=cur1;
		while(true){
			if(cur1.next==null||cur1.next.next==null){
				break;
			}
			while(cur2.next!=null&&cur2.next.next!=null){
				cur2=cur2.next;
			}
			ListNode temp=cur2.next;
			cur2.next=null;
			temp.next=cur1.next;
			cur1.next=temp;
			cur1=cur1.next.next;
			cur2=cur1;
		}
	}
	//第三轮，想到了是要reverse再接起来的，但是我想的是reverse整个再去接，好像会有个问题就是
	//奇数和偶数链表判断终止的条件不同，可能搞起来比较麻烦，还是用他们的思路吧。如何reverse都
	//差点忘了
	public  void reorderList4(ListNode head) {
		if(head==null||head.next==null){
			return ;
		}
		ListNode wal=head;
		ListNode run=head;
		while(run.next!=null&&run.next.next!=null){
			wal=wal.next;
			run=run.next.next;
		}
		ListNode head2=wal.next;
		wal.next=null;
		ListNode dum=new ListNode(0);
		dum.next=head2;
		while(head2.next!=null){
			ListNode temp=head2.next;
			head2.next=head2.next.next;
			temp.next=dum.next;
			dum.next=temp;
		}
		ListNode cur=head;
		while(dum.next!=null){
			ListNode temp=dum.next;
			dum.next=dum.next.next;
			temp.next=cur.next;
			cur.next=temp;
			cur=cur.next.next;
		}
		return;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}