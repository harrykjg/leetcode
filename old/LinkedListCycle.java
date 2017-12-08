import java.util.ArrayList;

public class LinkedListCycle {

	public static void main(String[] args) {

	}
//两个指针，一个走两步一个走一步
	public boolean hasCycle(ListNode head) {

		if (head == null||head.next==null) {
			return false;
		}
		if (head.next == head) {
			return true;
		}
		ListNode fast = head.next.next;//第二次做的时候还卡住了，注意是要先fast和slow都走出去，
		ListNode slow = head.next;  //然后再判断，我写成fast=head,slow=head然后进入while循环
		while (true) {             //那样的话fast肯定等于slow了
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
