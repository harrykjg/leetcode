//http://blog.csdn.net/linhuanmars/article/details/24389429
//http://jixiangsanbao.wordpress.com/2014/05/10/remove-duplicates-from-sorted-list-2/

public class RemoveDuplicatesfromSortedListII {
	
	public static void main(String[] args) {
		RemoveDuplicatesfromSortedListII rd=new RemoveDuplicatesfromSortedListII();
		ListNode n=new ListNode(1);
		n.next=new ListNode(1);
		rd.deleteDuplicates(n);
	}
	//��һ���ǰ���ǰ���ˣ��϶�д�Ĳ��У��ڶ���д�����˼��Σ�����
	public ListNode deleteDuplicates(ListNode head) {
		if(head==null){
			return head;
		}
		ListNode dum=new ListNode(Integer.MIN_VALUE);
		dum.next=head;
		ListNode cur=dum;
		boolean flag=false;
		ListNode cur2=dum;
		//����dum��1,1��ʼcur��dum�ǣ�cur2��1�ǣ�Ȼ����1��1һ������cur.next=cur2.next��dum.next
		//�ǿ��ˡ�����dum��1,2,3,3,3,4,4����cur��dum��Ȼ����cur2�Ƚ�1,2������ȣ���cur��1���Ƚ�
		//2,3���ȣ���cur��2���Ƚ�3,3��ȣ���cur2ǰ��һ�����ٱȽ�3,3������ȣ���ǰ�����ٱȽ�3,4��
		//����ȣ���ʱcur2��3��cur��2�����԰�cur.next=cur2.next�������dum��1,2,4,4�ˣ�����Ҳһ��
		while(cur.next!=null){
			cur2=cur.next;
			while(cur2.next!=null&&cur2.next.val==cur2.val){
				flag=true;//flag������¼��û�з�����ȵ�
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
