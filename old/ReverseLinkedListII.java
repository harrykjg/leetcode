//爽啊，第一次一次通过！
//思路就是找到第m位置的前一个节点
//https://jixiangsanbao.wordpress.com/2014/05/13/reverse-linked-list-ii/
public class ReverseLinkedListII {

	public ListNode reverseBetween(ListNode head, int m, int n) {
		int count1=1;
		int count2=1;
		ListNode dum=new ListNode(0);
		dum.next=head;
		ListNode cur1=dum;
		ListNode cur2=dum;
		for(int i=1;i<=n;i++){ //思路就是找到第m个的前一个节点（cur1），和第n个节点（cur2）
			if(i<m){
				cur1=cur1.next;
			}
			cur2=cur2.next;
		}
		reverse(cur1,cur2);//这个反转就是不断把cur1后面那个扔到去cur2的后面就ok了
		return dum.next;

	}
	public void reverse (ListNode n1,ListNode n2){
		while(n1.next!=n2){
			ListNode temp1=n1.next;
			n1.next=n1.next.next;
			ListNode temp2=n2.next;
			n2.next=temp1;
			temp1.next=temp2;
		}
	
	}
//第二次想了一下，以为是要找到第m个节点的前一个和第n个节点的前一个，然后recursily位置对换
	//但是发现还是第一次的思路更好
}
