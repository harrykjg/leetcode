//ˬ������һ��һ��ͨ����
//˼·�����ҵ���mλ�õ�ǰһ���ڵ�
//https://jixiangsanbao.wordpress.com/2014/05/13/reverse-linked-list-ii/
public class ReverseLinkedListII {

	public ListNode reverseBetween(ListNode head, int m, int n) {
		int count1=1;
		int count2=1;
		ListNode dum=new ListNode(0);
		dum.next=head;
		ListNode cur1=dum;
		ListNode cur2=dum;
		for(int i=1;i<=n;i++){ //˼·�����ҵ���m����ǰһ���ڵ㣨cur1�����͵�n���ڵ㣨cur2��
			if(i<m){
				cur1=cur1.next;
			}
			cur2=cur2.next;
		}
		reverse(cur1,cur2);//�����ת���ǲ��ϰ�cur1�����Ǹ��ӵ�ȥcur2�ĺ����ok��
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
//�ڶ�������һ�£���Ϊ��Ҫ�ҵ���m���ڵ��ǰһ���͵�n���ڵ��ǰһ����Ȼ��recursilyλ�öԻ�
	//���Ƿ��ֻ��ǵ�һ�ε�˼·����
}
