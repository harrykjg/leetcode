//http://blog.csdn.net/linhuanmars/article/details/19712593

public class MergeTwoSortedLists {
	
	//�����Լ�������İɣ���ʵ����list�������һ����Ϊ����ģ���һ������ר��ȥ��ģ���˭��һ��
	
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
			if(cur1.next.val<dum2.next.val){//�����һ��list��nodeС�ڵڶ���list��node�������
				//�ѵ�һ��list����һ��node��ֱ���ҵ���һ���ȵڶ���list�������ȵ�ֵ��ʱ��
				//�ѵڶ���list���Ǹ�node�г������鵽��һ��list�Ȼ������ѵ�һ��list
				//�ڶ���list�Ͳ��ö��ˣ����ǵ��ű��е�
				cur1=cur1.next;
				continue;
			}
			else{
				ListNode temp1=dum2.next;
				dum2.next=dum2.next.next;
				ListNode temp2=cur1.next;
				cur1.next=temp1;
				temp1.next=temp2;
//				temp1.next=cur1.next; //�Ҷ���ϲ��������temp����¼�ģ���ʵ���Բ��õڶ���temp
//				cur1.next=temp1;//������������Ҳ��
				cur1=cur1.next;
			}
		}
		if(dum2.next!=null){//�����һ��list���ˣ�����ڶ���list���滹ʣһ������
			cur1.next=dum2.next;
		}
		return dum1.next;
	}
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {//�ڶ�������������д��
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
