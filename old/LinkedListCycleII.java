//http://blog.csdn.net/sysucph/article/details/15378043 ������͵ĺ�
//�����Լ���Ļ��϶��벻������̫�������ˣ����������extra space�Ļ�����hashtable��ڵ�
//http://blog.csdn.net/linhuanmars/article/details/21260943 code ganker��
//http://blog.csdn.net/lidalong0408/article/details/14104553

public class LinkedListCycleII {
	public static void main(String[] args) {
		LinkedListCycleII ll=new LinkedListCycleII();
		ListNode h=new ListNode(1);
		h.next=new ListNode(2);
		h.next.next=h;
		ll.detectCycle(h);
		
		
	}
	
	
	public ListNode detectCycle(ListNode head) {
		if(head==null||head.next==null){
			return null;
		}
		ListNode f=head.next.next;
		ListNode s=head.next;
		//���ǰ����ǵĽ��ۣ��л���������һ�����ʣ���fast��slow��һ������ʱ����slow�Ƶ�ͷ����fast
		//��slow����ÿ����һ���ߣ�������ʱ���õ���ǻ������
		while(true){//���ҵ���һ�������ĵ�.����д��ʱ��ע�⣬��fast��slow���������˵ķ���
			//��������ͷ���Ļ����޷���whileѭ�����ж����������ĵ㣬��Ϊһ��ʼf�͵���s�����
			//�ѽ���whileѭ����break�ˡ����ǿ���code ganker��д����
			if(f==null||f.next==null||s==null||s.next==null){
				return null;
			}
			if(f==s){
				break;
			}else{
				f=f.next.next;
				s=s.next;
			}
			
		}
		s=head;//�ٰ�����һ���Ƶ�ͷ������һ��һ���ߣ��������ǻ������
		while(s!=f){
			s=s.next;
			f=f.next;
		}
		return s;
	}
	//������,�����е���һ�������ָ����÷�����û��ã�Ȼ����������2��ָ�붼һ������Ҳû��ס
	public ListNode detectCycle2(ListNode head) {
		if(head==null||head.next==null){
			return null;
		}
		ListNode wal=head.next;//ע������wal��run�������߳�ȥ���ˣ�����ָ��head������
		//while�߳�ȥ�Ļ�����û���ж�1�Լ����Լ��������,���������������1�Լ���1�����ӣ���ô�Ͳ����������߳�ȥ��
		ListNode run=head.next.next;//����run��=null�������������ûд�Ļ���1-2-null������Ӿͻ�
		while(wal!=run&&run!=null&&run.next!=null&&run.next.next!=null){//���쳣��
			wal=wal.next;
			run=run.next.next;
		}
		if(run==null||run.next==null||run.next.next==null){
			return null;
		}
		wal=head;
		while(wal!=run){
			wal=wal.next;
			run=run.next;
		}
		return wal;
		
	}

}
