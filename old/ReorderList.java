public class ReorderList {
//http://blog.csdn.net/linhuanmars/article/details/21503215
	public static void main(String[] args) {

		
		ListNode node=new ListNode(1);
		node.next=new ListNode(2);
		node.next.next=new ListNode(3);
		node.next.next.next=new ListNode(4);;
		node.next.next.next.next=new ListNode(5);
		ListNode curr=node;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
		reorderList(node);
		curr=node;
		System.out.println("-------");
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
		
		
	}
//�����ǶԵģ����ǻᳬʱ
	public static void reorderList(ListNode head) {
		if(head==null||head.next==null){
			return ;
		}
		ListNode wal=head;
		ListNode run=head;
		while(run.next!=null&&run.next.next!=null){//˼·������runner��walker���ҵ������е�
			wal=wal.next;              //Ȼ������ֳ�2�����ֱ���12345678���ֳ���1234��5678��
			run=run.next.next;        //Ȼ��1��8�������18234��ͬʱ5678���567��Ȼ��2��7����
		}                          //���182734��ͬʱ567���56��and so on����������ÿ��Ҫ����
		ListNode head2=wal.next;    //�����һ���ҵ������ǵ㣬Ӧ�ñ�ֱ�ӱ������������ҵ�����ǵ�
		wal.next=null;             //��һ���˰� 
		ListNode dum=new ListNode(0);
		ListNode cur1=head;
		dum.next=head2;
		while(cur1!=null&&dum.next!=null){
			ListNode cur2=dum;
			while(cur2.next.next!=null){
				cur2=cur2.next;
			}
			ListNode temp=cur1.next;
			cur1.next=cur2.next;
			cur2.next=null;
			cur1.next.next=temp;
			cur1=cur1.next.next;

		}

	}

	public static void reorderList2(ListNode head) {
		if(head==null||head.next==null){
			return ;
		}
		ListNode wal=head;
		ListNode run=head;
		while(run.next!=null&&run.next.next!=null){
			wal=wal.next;              
			run=run.next.next;       
		}                         
		ListNode head2=wal.next;    
		wal.next=null;         
		ListNode dum=new ListNode(0);//����˵12345678���ȷֳ�1234��5678��Ȼ���5678reverse��
		dum.next=head2;      //��8765��Ȼ���1234��8765ƴ���������ˡ�
		while(head2.next!=null){//��dum5678���ȱ��dum6578���ٱ��dum7658���ٱ��dum8765
			ListNode temp=head2.next;
			head2.next=head2.next.next;
			temp.next=dum.next;
			dum.next=temp;
		}
		while(dum.next!=null){//��1234��dum8765���ȱ��18234��dum765���ٱ��182734��dum65���ڱ�
			ListNode temp=head.next;//��1827364��dum5��and so on
			head.next=dum.next;
			dum.next=dum.next.next;
			head.next.next=temp;
			head=head.next.next;
		}
		return;

	}
	//�ڶ��ε��뷨,һ�γɹ������ǳ�ʱ������Ҫ�����ǵ�˼·����
	public  void reorderList3(ListNode head) {
		if(head==null||head.next==null){
			return ;
		}
		ListNode cur1=head;
		ListNode cur2=cur1;
		while(true){
			if(cur1.next==null||cur1.next.next==null){
				break;
			}
			while(cur2.next!=null&&cur2.next.next!=null){
				cur2=cur2.next;
			}
			ListNode temp=cur2.next;
			cur2.next=null;
			temp.next=cur1.next;
			cur1.next=temp;
			cur1=cur1.next.next;
			cur2=cur1;
		}
	}
	//�����֣��뵽����Ҫreverse�ٽ������ģ������������reverse������ȥ�ӣ�������и��������
	//������ż�������ж���ֹ��������ͬ�����ܸ������Ƚ��鷳�����������ǵ�˼·�ɡ����reverse��
	//�������
	public  void reorderList4(ListNode head) {
		if(head==null||head.next==null){
			return ;
		}
		ListNode wal=head;
		ListNode run=head;
		while(run.next!=null&&run.next.next!=null){
			wal=wal.next;
			run=run.next.next;
		}
		ListNode head2=wal.next;
		wal.next=null;
		ListNode dum=new ListNode(0);
		dum.next=head2;
		while(head2.next!=null){
			ListNode temp=head2.next;
			head2.next=head2.next.next;
			temp.next=dum.next;
			dum.next=temp;
		}
		ListNode cur=head;
		while(dum.next!=null){
			ListNode temp=dum.next;
			dum.next=dum.next.next;
			temp.next=cur.next;
			cur.next=temp;
			cur=cur.next.next;
		}
		return;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}