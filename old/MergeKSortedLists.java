//http://blog.csdn.net/linhuanmars/article/details/19899259
//http://jixiangsanbao.wordpress.com/2014/03/27/merge-k-sorted-lists/ �����heap�ķ����׶�
//http://www.cnblogs.com/skysand/p/4300711.html
import java.util.ArrayList;

public class MergeKSortedLists {

	public static void main(String[] args) {

	}
//����ÿ�α���lists�������list�ĵ�һ������ȡ��С�ĸ������list��ÿ��k��ȡ��С�ģ����Ӷ���k
	//Ҫ�������list�����Ԫ�ظ����Σ����Ը��Ӷ���kn��2015��12���룬Ӧ�ò���kn��ÿ�α���k��ȡ��С��k��
//����Ҫȡkn�Σ����Ը��Ӷ�Ӧ����kƽ��n��

	//���ǵ��뷨����һ����СΪk��minheap������֮�󣬰ѶѶ������������ٰѸöѶ�Ԫ�����ڵ�list��
	//��һ��Ԫ�طŽ������ٵ����ѣ����ӳ��Ѷ�Ԫ�أ������Ļ�����ÿ��ѡ����k��Ԫ���е���СԪ�ظ��Ӷ�
	//ֻ��Ҫlogk��Ҫ����k*n�Σ����Ը��Ӷ���knlogk
	public ListNode mergeKLists(ArrayList<ListNode> lists) {

		if (lists.size() == 0) {
			return null;
		}
		if (lists.size() == 1) {
			return lists.get(0);
		}
		ListNode head = new ListNode(0);
		ListNode temp = head;
		while (lists.size() != 0) {
            //ȡarraylist��ĵ�һ��list��ͷ���
			ListNode min = lists.get(0);
			if (min == null) {
				lists.remove(min);
				continue;
			}
			//ȥarraylist������list��ͷ���
			for (ListNode ln : lists) {
				if (ln.val < min.val) {
					min = ln;
				}
			}
			head.next = min;
			head = head.next;
			if (min.next != null) {
				lists.add(min.next);//�������С��list����һ�����ӽ�arraylist��

			}
			lists.remove(min);//�ٰ������С��listɾ��
		}

		return temp.next;
	}
	//�ڶ��ξ�ûд�ˣ��о���һ�εľͲ���������ʱ������ǵ����ַ�������һ����
//��������head�ľ�����priorityqueue��ʵ�ֵģ�priorityqueue�Դ������ܣ��ṩlogn�����򣬼������
	//��ÿ��list��ÿ���ڵ�ȫ���ӽ�priorityqueue��������ȫ�������ˣ�Ȼ���ٰ���ȡ����������
	

}
