import java.util.ArrayList;
//�������ռ����ҪO��n)�ˣ��˼���ʹ��2��ָ�루һ������һ�����ߣ�ʵ�ֵ�ֻ��O��1��
//http://blog.csdn.net/linhuanmars/article/details/19778441
public class RemoveNthNodeFromEndofList {

	public ListNode removeNthFromEnd(ListNode head, int n) {

		ArrayList<ListNode> al = new ArrayList<ListNode>();
		ListNode pre = null;
		ListNode post = null;
		int len = 0;
		ListNode cur = head;
		while (cur != null) {
			len++;
			al.add(cur);
			cur = cur.next;
		}

		if (len != 1) {
			if (n != 1&&n!=len) {//���Ȳ�Ϊ1���ҷ��صĲ��ǵ�һ��
				pre = al.get(len - n - 1);
				post = al.get(len - n + 1);
				pre.next = post;
				return head;
			}
			else if(n!=1){//���Ȳ�Ϊ1���ҷ��ص��ǵ�һ��
				post = al.get(len - n + 1);
				return post;
			}
			else{
				pre = al.get(len - n - 1);
				pre.next=null;
				return head;
			}
		} else {//n˵�ǿ϶���Ч�ģ��������len��1�Ļ���n�϶�Ҳ��1�����Է��ؿ�
			return null;
		}
	}
	public ListNode removeNthFromEnd2(ListNode head, int n) {//������ָ��

		ListNode run=head;
		ListNode slow=head;
		int count=0;
		
		while(count!=n){
			run=run.next;
			count++;
			if(run==null){//��Ҫɾ����һ��
				return head.next;
			}
		}
		while(run.next!=null){
			slow=slow.next;
			run=run.next;
		}
		slow.next=slow.next.next;
		return head;

	}
	
	//1/10 ��Ҫ������һ�Σ�����ɾ���ĵ��ǵ�һ���ڵ���жϣ���ʱfront�Ѿ���null�ˣ�����head.next����
	 public ListNode removeNthFromEnd3(ListNode head, int n) {
	        if(head==null){
	            return head;
	        }
	        ListNode front=head;
	        
	        for(int i=0;i<n;i++){
	            if(front!=null){
	                front=front.next;
	            }else{
	                return head;
	            }
	        }
	        ListNode cur=head;
	        if(front==null){
	            return head.next;
	        }
	        while(front.next!=null){
	            front=front.next;
	            cur=cur.next;
	        }
	        cur.next=cur.next.next;
	        return head;
	        
	        
	    }

}
