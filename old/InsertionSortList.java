//��ʱ�����⣺dum.next�ĸı��Ӱ�쵽֮ǰ��node����Ϊ֮ǰ��node�Ļ�λ�õ���dum.next��

//�ڶ�����һ��accept�ˣ�������ʵ���븴���ˣ���ʵ���ð��Ѿ�sort�Ĳ��ֵ������µ�������¼�������յ�
//http://blog.csdn.net/linhuanmars/article/details/21144553

public class InsertionSortList {

	public static void main(String[] args) {
		ListNode n1=new ListNode(1);
		ListNode n2=new ListNode(1);
		ListNode n3=new ListNode(7);
		ListNode n4=new ListNode(30);
		ListNode n5=new ListNode(9);
//		ListNode n6=new ListNode(9);
		n1.next=n2;
//		n2.next=n3;
//		n3.next=n4;
//		n4.next=n5;
//		n5.next=n6;
	
		ListNode curr=n1;
		
	
//		���齻������ͷ2��node
//		ListNode temp=curr.next;
//		curr.next=curr.next.next;
//		temp.next=curr;
//		
//		while(temp!=null){
//			System.out.println(temp.val);
//			temp=temp.next;
//		}
		
		
//		���齻��35��40���м����Ԫ�صĽ�������ֻҪ��temp��¼��ȥ�����Ǹ�Ԫ�أ�����
		//curr.next=curr.next.next����curr.next��ȥ�����ˣ�����
//		ListNode temp1=n5;
//		
//		
//		n4.next=n5.next;
//		temp1.next=n3;
//		n2.next=temp1;
//		
//		
		while(curr!=null){
		System.out.println(curr.val);
		curr=curr.next;
	}
		System.out.println("-------------");
		ListNode xx=sort(n1);
		
		while(xx!=null){
			System.out.println(xx.val);
			xx=xx.next;
		}
		
		
		
		
	}
	
	public static ListNode sort(ListNode root){
		
		ListNode curr=root;
		ListNode head=root;
//		ListNode curr2=null;
		ListNode curr3=null;
		
		
		if(root==null||root.next==null){
			return root;
		}
		
		while(null!=curr.next){
			curr3=curr.next;
			
			ListNode dum=new ListNode(0);//ע������ÿ��Ҫnewһ��dum�����ܰ�dum������ǰ��
			dum.next=head;
			
			while(dum.next!=curr3){
//				ListNode curr2=dum.next;
				if(curr.next.val<dum.next.val){
					
					if(dum.next==head){
						ListNode temp=curr.next;
						curr.next=curr.next.next;
						temp.next=dum.next;
						head=temp;
						dum.next=curr.next;//Ϊʲô����dum.next���˲���Ӱ��text.next������
						//�����ڲ��Ǻ�ͷ��㻻������£�ִ��������dum=dum.next���
						break;
					}
					else{
					
						
						ListNode temp=curr.next;
						curr.next=curr.next.next;
						temp.next=dum.next;//��������dum.next��������һ��dum.next=head֮��
						//�����headҲ����ű䣬Ϊʲô��
						dum.next=temp;
//						dum.next=null;
						break;
						
					}
				
				}
				dum=dum.next;
			}
			
//			if(curr.next!=null){
//				curr=curr.next;
//			}else{
//				break;
//			}

		}	
		
		return head;
		
	}
	
	public static ListNode sort2(ListNode head){
		ListNode dum=new ListNode(Integer.MIN_VALUE);
		if(head==null){
			return null;
		}
		dum.next=head;
		ListNode dd=dum;
		ListNode cur=dum;
		int count=0;
		ListNode last=dd;
		while(cur.next!=null){
			if(cur.next.val>=last.val){
				cur=cur.next;
				count++;
				last=last.next;
			}
			else{//��ʱcur.nextָ�������ǰ��Ԫ��ֵ���Ǹ�Ԫ�أ���ʵӦ�ò���count1��2����¼
				//sorted���ֵĳ��ȵģ�ֻҪ��dum.next����ƶ��Ľڵ㲻����cur.next���жϾ�����
				int count2=0;
				while(count2<count){//���whileѭ��Ҫ�ҵ���cur.next��sorted���ֵ�λ��
					if(dd.next.val>cur.next.val){
						ListNode temp1=cur.next;
						cur.next=cur.next.next;
						ListNode temp2=dd.next;
						dd.next=temp1;
						temp1.next=temp2;
						
						dd=dum;
						count++;
						break;
					}else{
						dd=dd.next;
						count2++;
					}
					
				}
			}
		}
		return dum.next;
		
	}

	
}
