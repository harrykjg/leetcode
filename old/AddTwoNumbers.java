//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)Output: 7 -> 0 -> 8����342+465=807
//�ٱ��磨2-3-4-7��+��4-1-3������7432+314�����ӷ�����պ�������ĵ�һ������ӣ�Ȼ��ڶ�������
public class AddTwoNumbers {
	public static void main(String[] args) {
		
		ListNode n1=new ListNode(9);
//		n1.next=new ListNode(1);
		
		ListNode n2=new ListNode(9);
//		n2.next=new ListNode(2);
//		n2.next.next=new ListNode(1);
		
		AddTwoNumbers at=new AddTwoNumbers();
		ListNode rs=at.addTwoNumbers(n1, n2);
		while(rs!=null){
			System.out.print(rs.val);
			rs=rs.next;
		}
		
	}
	ListNode head= new ListNode(0);
	public ListNode addTwoNumbers(ListNode l1,ListNode l2){
		
		
		if(l1==null&&l2==null){
			return null;
		}else if(l1==null){
			return l2;
		}
		else if(l2==null){
			return l1;
		}
		ListNode cur1=l1;
		ListNode cur2=l2;
		//�ȰѶ̵Ĳ�ȫ
		while(cur1.next!=null||cur2.next!=null){
			if(cur1.next==null){
				cur1.next=new ListNode(0);
			}
			if(cur2.next==null){
				cur2.next=new ListNode(0);
			}
			cur1=cur1.next;
			cur2=cur2.next;
		}
		cur1=l1;
		cur2=l2;
		add(cur1,cur2,0,head);
		return head.next;
		
	}
	
	public void add(ListNode n1,ListNode n2,int i,ListNode parent){
		if(n1==null&&n2==null&&i==1){//������һλ�����
			parent.next=new ListNode(1);
			return;
		}
		if(n1==null){
			return;
		}
		
		int temp=n1.val+n2.val+i;
		ListNode n;
		if(temp==10){
		 n=new ListNode(0);
			i=1;
			parent.next=n;
		}
		else if(temp>10){
			n=new ListNode(temp-10);
			i=1;
			parent.next=n;
		}
		else{n=new ListNode(temp);
		i=0;
		parent.next=n;
		}
		add(n1.next,n2.next,i,n);
	}
//�ڶ���ûд���͵�һ�εĴ����д��ͦ�õ�
	
}
