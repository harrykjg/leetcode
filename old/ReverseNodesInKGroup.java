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

	public ListNode reverseKGroup3(ListNode head, int k) {//�ҵ��뷨�����ϵ���ʵһ��
		//��������reverse��ʱ�����in place�ģ�����Ҫ����һ��arraylist����

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
		for (int l = i; l > aa; l--) {// �õ�ʣ�µ�cur2
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

	public static ListNode reverseKGroup4(ListNode head, int k) {//���ϵ�
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
	private ListNode reverse(ListNode pre, ListNode end)  //code ganker��reverse����
	{  
	    if(pre==null || pre.next==null)  
	        return pre;  
	    ListNode head = pre.next;  
	    ListNode cur = pre.next.next;  
	    while(cur!=end)  
	    {  
	        ListNode next = cur.next; //������һ��pre�����ڵ�һ��node֮ǰ�̶��ţ�Ȼ��һ����һ����
	                              //����pre1234���ȱ��pre2134����pre3214����pre4321
	        cur.next = pre.next;  
	        pre.next = cur;  
	        cur = next;  
	    }  
	    head.next = end;  
	    return head;  
	}  
	//�ڶ���д�������˼��β�accept����Ҫ��count�Ĵ��ڵ��ڷ�Χ��л�˸��Ⱥ�
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
			while(cur2.next!=null&&count1<k){//����K����ʹcurָ���һ���û��Ķϵ��ǰһ����cur2
				cur2=cur2.next; //ָ�����һ���Ļ��ĵ㡣Ȼ�����count1����K�Ļ��ͻ���
				count1++;//����dum12345678��cur��dum��Ȼ��ʹcur2ָ��3��Ȼ����֮����
			}        //dum32145678��ע���ʱcur����dum��Ҫʹcur�ߵ�1��λ�ã��źý�����һ�ֵ���
			if(count1==k){//���������˸�tempָ��1���Ȼ���֮���ٰ�ָ��temp������
				ListNode temp=cur.next;
				rev(cur,cur2);
				cur=temp;
				count1=0;
			}else{
				while(count1>0){//��������ʣ�µ�Ԫ�ز���k����ôcur����ǰ���⼸����ʹ��ﵽĩβ
					cur=cur.next;//����ʹ�����Ǹ�forѭ������
					count1--;	
				}
			}
		}
		return dum.next;
	}
	private void rev(ListNode pre, ListNode end){
		while(pre.next!=end){//����pre123���ȱ��pre231���ٱ��pre321
			ListNode temp1=pre.next;
			pre.next=pre.next.next;
			temp1.next=end.next;
			end.next=temp1;
			
		}
	}

}
