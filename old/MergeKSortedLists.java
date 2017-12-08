//http://blog.csdn.net/linhuanmars/article/details/19899259
//http://jixiangsanbao.wordpress.com/2014/03/27/merge-k-sorted-lists/ 吉祥的heap的方法易懂
//http://www.cnblogs.com/skysand/p/4300711.html
import java.util.ArrayList;

public class MergeKSortedLists {

	public static void main(String[] args) {

	}
//就是每次遍历lists里的所有list的第一个数，取最小的赋给结果list。每从k中取最小的，复杂度是k
	//要进行最长的list里面的元素个数次，所以复杂度是kn。2015年12月想，应该不是kn，每次遍历k中取最小是k，
//但是要取kn次，所以复杂度应该是k平方n？

	//他们的想法是用一个大小为k的minheap，建成之后，把堆顶放入结果集，再把该堆顶元素所在的list的
	//下一个元素放进来，再调整堆，再扔出堆顶元素，这样的话就是每次选出最k个元素中的最小元素复杂度
	//只需要logk，要进行k*n次，所以复杂度是knlogk
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
            //取arraylist里的第一个list的头结点
			ListNode min = lists.get(0);
			if (min == null) {
				lists.remove(min);
				continue;
			}
			//去arraylist的所有list的头结点
			for (ListNode ln : lists) {
				if (ln.val < min.val) {
					min = ln;
				}
			}
			head.next = min;
			head = head.next;
			if (min.next != null) {
				lists.add(min.next);//把这个最小的list的下一个结点加进arraylist里

			}
			lists.remove(min);//再把这个最小的list删掉
		}

		return temp.next;
	}
	//第二次就没写了，感觉第一次的就不错啊，运行时间和他们的两种方法几乎一样。
//他们用了head的就是用priorityqueue来实现的，priorityqueue自带排序功能，提供logn的排序，吉祥就是
	//把每个list的每个节点全都加进priorityqueue来，他就全都排序了，然后再挨个取出来就行了
	

}
