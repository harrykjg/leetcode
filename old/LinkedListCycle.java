import java.util.ArrayList;

public class LinkedListCycle {

	public static void main(String[] args) {

	}
//����ָ�룬һ��������һ����һ��
	public boolean hasCycle(ListNode head) {

		if (head == null||head.next==null) {
			return false;
		}
		if (head.next == head) {
			return true;
		}
		ListNode fast = head.next.next;//�ڶ�������ʱ�򻹿�ס�ˣ�ע����Ҫ��fast��slow���߳�ȥ��
		ListNode slow = head.next;  //Ȼ�����жϣ���д��fast=head,slow=headȻ�����whileѭ��
		while (true) {             //�����Ļ�fast�϶�����slow��
		if (fast != slow) {
				if (slow==null||slow.next==null||fast == null||fast.next==null) {
				return false;
			} else{
				slow = slow.next;
				
					fast = fast.next.next;
			}
				
			} else {
				return true;
			}

		}

}
	

	public boolean hasCycle2(ListNode head) {
		ListNode slow = head, fast = head;
		while (true) {
			if (fast == null || fast.next == null || slow == null) {
				return false;
			}
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				return true;
			}
		}
	}

}
