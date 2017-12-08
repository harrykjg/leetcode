import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/19957455
public class ReverseNodesInKGroup {

	public static void main(String[] args) {

		ListNode n = new ListNode(1);
		n.next = new ListNode(2);
		n.next.next = new ListNode(3);
//		n.next.next.next = new ListNode(4);
//		n.next.next.next.next = new ListNode(5);
//		 n.next.next.next.next.next=new ListNode(6);
//		 n.next.next.next.next.next.next=new ListNode(7);
		// n.next.next.next.next.next.next.next=new ListNode(2);

		ReverseNodesInKGroup rn = new ReverseNodesInKGroup();

		ListNode rs = rn.reverseKGroup5(n, 2);
		while (rs != null) {
			System.out.println(rs.val);
			rs = rs.next;
		}

	}

	ListNode hh;
	ArrayList<ListNode> al = new ArrayList<ListNode>();

	
	public ListNode reverseKGroup2(ListNode head, int k) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode lastGroupTail = dummy;
		ListNode p = head;
		boolean reverse = true;
		while (reverse) {
			for (int i = 0; i < k; ++i) {
				if (p == null) {
					reverse = false;
					break;
				}
				p = p.next;
			}
			if (reverse) {
				ListNode nextGroupHead = p;
				p = lastGroupTail.next;
				ListNode tmp = p;
				lastGroupTail.next = null;
				for (int i = 0; i < k; ++i) {
					ListNode next = p.next;
					p.next = lastGroupTail.next;
					lastGroupTail.next = p;
					p = next;
				}
				reverse = true;
				lastGroupTail = tmp;
				lastGroupTail.next = nextGroupHead;
				p = nextGroupHead;
			}
		}
		return dummy.next;
	}

	public ListNode reverseKGroup3(ListNode head, int k) {//我的想法和网上的其实一样
		//但是他们reverse的时候就是in place的，而我要加了一个arraylist做的

		if (head == null || k == 1) {
			return head;
		}

		ListNode cur = head;
		ListNode cur2 = head;
		int i = 0;
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		int a = i / k;
		if (i < k) {
			return head;
		}
		int aa = i % k;
		ListNode n1 = head;
		for (int l = i; l > aa; l--) {// 得到剩下的cur2
			cur2 = cur2.next;
		}
		for (int j = 0; j < a; j++) {
			n1 = swap3(n1, k);
		}

		for (int j = 0; j < al.size() - 1; j++) {
			ListNode n = al.get(j);
			for (int m = 0; m < k - 1; m++) {
				n = n.next;
			}
			n.next = al.get(j + 1);
			n = n.next;
			if (j == al.size() - 2 && cur2 != head) {
				for (int o = 0; o < k - 1; o++) {
					n = n.next;
				}
				n.next = cur2;
			}
		}
		return al.get(0);
	}

	public ListNode swap3(ListNode n1, int k) {
		ArrayList<ListNode> a = new ArrayList<ListNode>();
		ListNode cur = n1;
		for (int i = 0; i < k; i++) {
			a.add(cur);
			cur = cur.next;
		}
		ListNode next = cur;
		ListNode n = a.get(k - 1);
		al.add(n);
		for (int j = k - 1; j > 0; j--) {
			n.next = a.get(j - 1);
			n = n.next;
		}
		n.next = cur;
		return next;
	}

	public static ListNode reverseKGroup4(ListNode head, int k) {//网上的
		int count = 0;
		ListNode p = head;
		while (p != null) {
			count++;
			p = p.next;
		}
		if (count < k || k == 1) {
			return head;
		}
		int loopCount = count / k;
		ListNode safeNode = new ListNode(Integer.MAX_VALUE);
		safeNode.next = head;
		ListNode pre = safeNode;
		ListNode cur = head;
		for (int i = 0; i < loopCount; i++) {
			ListNode tmp = reverseK(cur, k);
			pre.next = tmp;
			ListNode tmpPre = null;
			ListNode tmpCur = tmp;
			for (int j = 0; j < k; j++) {
				tmpPre = tmpCur;
				tmpCur = tmpCur.next;
			}
			pre = tmpPre;
			cur = tmpCur;
		}
		return safeNode.next;
	}

	public static ListNode reverseK(ListNode head, int k) {
		ListNode pre = null;
		ListNode cur = head;
		ListNode post = null;
		for (; k > 0 && cur != null; k--) {
			post = cur.next;
			cur.next = pre;
			pre = cur;
			cur = post;
		}
		head.next = cur;
		return pre;
	}
	private ListNode reverse(ListNode pre, ListNode end)  //code ganker的reverse部分
	{  
	    if(pre==null || pre.next==null)  
	        return pre;  
	    ListNode head = pre.next;  
	    ListNode cur = pre.next.next;  
	    while(cur!=end)  
	    {  
	        ListNode next = cur.next; //就是设一个pre变量在第一个node之前固定着，然后一个接一个换
	                              //比如pre1234，先变成pre2134，再pre3214，再pre4321
	        cur.next = pre.next;  
	        pre.next = cur;  
	        cur = next;  
	    }  
	    head.next = end;  
	    return head;  
	}  
	//第二次写，调试了几次才accept，主要是count的大于等于范围多谢了个等号
	public ListNode reverseKGroup5(ListNode head, int k) {
		if (head == null || k == 1) {
			return head;
		}
		int count1=0;
		ListNode dum=new ListNode(Integer.MAX_VALUE);
		dum.next=head;
		ListNode cur=dum;
		ListNode cur2=null;
		while(cur.next!=null){
			cur2=cur;
			while(cur2.next!=null&&count1<k){//先走K步，使cur指向第一个该换的断电的前一个，cur2
				cur2=cur2.next; //指向最后一个改换的点。然后如果count1等于K的话就换。
				count1++;//比如dum12345678，cur在dum，然后使cur2指向3，然后换完之后变成
			}        //dum32145678，注意此时cur还是dum，要使cur走到1的位置，才好进行下一轮调换
			if(count1==k){//所以我设了个temp指向1，等换完之后再把指向temp就行了
				ListNode temp=cur.next;
				rev(cur,cur2);
				cur=temp;
				count1=0;
			}else{
				while(count1>0){//如果最后面剩下的元素不到k，那么cur就再前进这几步，使其达到末尾
					cur=cur.next;//才能使外面那个for循环结束
					count1--;	
				}
			}
		}
		return dum.next;
	}
	private void rev(ListNode pre, ListNode end){
		while(pre.next!=end){//比如pre123，先变成pre231，再变成pre321
			ListNode temp1=pre.next;
			pre.next=pre.next.next;
			temp1.next=end.next;
			end.next=temp1;
			
		}
	}

}
