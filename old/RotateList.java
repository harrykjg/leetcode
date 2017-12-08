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
	//�ڶ���д����Щ��Ť�������˼���
	public ListNode rotateRight2(ListNode head, int n) {
		if(head==null||n==0){
			return head;
		}
		ListNode dum=new ListNode(0);//dum��ʵ�Ƕ���ĸ������ã������û����
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
		//����dum1,2,3,4,5��nΪ2�����ھ��ҵ���3��Ϊtemp1,4��Ϊtemp2��curָ��5��Ȼ���5��nextָ��
		cur.next=head; //1��dum.nextָ��4���ٰ�3��next��Ϊ�վ�����
		dum.next=temp2;
		temp1.next=null;
		return dum.next;
	}
	//1/10����run����k����Ȼ��wal��runһ���ߣ���run��nextΪ��λ�ã���ʱwalָ��ľ���Ӧ��Ҫ��
	//�����еĵ�һ���ڵ��ǰһ���ڵ㣬�ٰѰ���wal���ڵ�ǰ��reverse���ٰ�wal�����reverse����
	//��������reverse�����Ƿ��ֵڶ��εķ������á���

}

