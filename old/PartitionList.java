//�����½�����list��һ����С��x�ģ�һ�������x�ģ������������ɣ��ܼ򵥣����ǵ���O��n���ռ�
//Ӧ����inplace�Ľ�������
//http://blog.csdn.net/linhuanmars/article/details/24446871 ���������֣�����̫��
//http://alexjixiang.com/2014/05/12/partition-list/
public class PartitionList {
	
	public static void main(String[] args) {
		PartitionList pl =new PartitionList();
		ListNode head=new ListNode(4);
		head.next=new ListNode(3);
		head.next.next=new ListNode(2);
		head.next.next.next=new ListNode(5);
		head.next.next.next.next=new ListNode(2);
//		head.next.next.next.next.next=new ListNode(2);
		ListNode rs=pl.partition(head, 3);
		while(rs!=null){
			System.out.println(rs.val);
			rs=rs.next;
		}
	}
	

	public ListNode partition(ListNode head, int x) {
		//����뷨���������ǵĲ�ͬ����������code ganker������
		//���ҳ�����С��x�Ľڵ��е����һ����Ȼ����ʼ����ɨ�������д��ڵ���x�Ķ��ӵ�����ڵ�֮��
		if (head == null) {
			return null;
		}
		ListNode dum=new ListNode(0);
		dum.next=head;
		ListNode cur=head;
		ListNode cur2=head;
		
		while(cur!=null){//���ҳ�С��x�����һ���ڵ�
			if(cur.val<x){
				cur2=cur;
			}
			cur=cur.next;
		}
		ListNode cur3=cur2;//ע�⣡����cur3ֻ�Ǹ����ã���ָ�����cur2�Ķ������cur2������ˣ���
		//cur3����ָ����ԭ����cur2�Ķ��󣡶�cur2������ʵ��cur2ָ���������Ķ���cur2ԭ���Ķ�������
		cur=dum;
		    
		while(cur.next!=null&&cur.next!=cur3){//��ǰ�ڵ�������������һ��С��x�Ľڵ�Ļ����ͽ���
			//Ȼ���ǰ����ɨ�������ǰ�ڵ���ڵ���x�����x
			//���뵽С��x�����һ���ڵ�ĺ��棬Ȼ������С��x�����һ���ڵ�ָ������һ���ڵ㣬����
			//���ܱ�֤��һ������x�Ľڵ�����ʱ�ǶԵ�λ�ã���֤��ԭlist�����λ�ò���
			if(cur.next.val<x){
				cur=cur.next;//����ڵ㲻�û��Ļ���cur����ǰһ���������Ҫ���Ļ�������ڵ�
				//�ͻᱻ�ӵ�����ȥ����cur.next�����û�����һ��Ҫ���Ľڵ㣬��tm����
				
			}else{                    //�� 1 4 3 2 5
				ListNode temp1=cur.next; //��4��1��3���г���
				cur.next=cur.next.next;//��1��3������
				ListNode temp2=cur2.next;//��5��������
				cur2.next=temp1;//��4�ӵ�2����
				cur2.next.next=temp2;//��5�ӵ�4���棬��ԭ������1 3 2 4 5
				cur2=cur2.next;//��cur2���4������һ�����ӹ��������Ͳ���4����
			}
			
		}
		return dum.next;

	}
//�ڶ���д��˼·�ǰ����ҵ���һ�����ڵ���x�ĵ㣬Ȼ���������ɨ��������x��ľͲ��ܼ�����������xС��
	//�Ͱ������������һ������x�Ľڵ��ǰ�棬����143251�����ҵ�4�����õ�����ǰ����1)��Ȼ������2��
	//���124351��Ȼ������1�����121435
	public ListNode partition2(ListNode head, int x) {
		if (head == null) {
			return null;
		}
		ListNode curbig=null;
		ListNode dum=new ListNode(0);
		dum.next=head;
		ListNode cur=dum;
		while(cur.next!=null){
			if(cur.next.val>=x){
				curbig=cur;
				break;
			}
			cur=cur.next;
		}
		if(curbig==null){//��������©�����Ǹ���û�д���x�����
			return head;
		}
		cur=curbig.next;
		
		while(cur.next!=null){
			if(cur.next.val>=x){
				cur=cur.next;
			}else{
				ListNode temp1=cur.next;
				cur.next=cur.next.next;
				temp1.next=curbig.next;
				curbig.next=temp1;
				curbig=curbig.next;
			}
		}
		return dum.next;
	}
	
}
