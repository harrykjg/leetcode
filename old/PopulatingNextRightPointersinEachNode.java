package old;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersinEachNode {
	public void connect(TreeLinkNode root) {
		
		if(root==null){
			return;
		}
		con(root);

		return;
	}
	public void con(TreeLinkNode root){
		if(root.left!=null){
			root.left.next=root.right;
			if(root.next!=null){
				root.right.next=root.next.left;
			}else{
				root.right.next=null;
				
			}
			connect(root.left);
			connect(root.right);
					
		}
		return;
	}

	//11/10/1028,想的是层次遍历，还不如以前的方法好
	public void connect2(TreeLinkNode root) {
		if(root==null){
			return;
		}
		Queue<TreeLinkNode> q=new LinkedList<>();
		int count1=1;
		int count2=0;
		q.offer(root);
		while (!q.isEmpty()){
			TreeLinkNode cur=q.poll();
			count1--;
			if(cur.left!=null){
				q.offer(cur.left);
				count2++;
			}
			if(cur.right!=null){
				q.offer(cur.right);
				count2++;
			}
			if(count1==0){
				ArrayList<TreeLinkNode> temp=new ArrayList<>(q);
				for(int i=0;i<temp.size()-1;i++){
					temp.get(i).next=temp.get(i+1);
				}
				count1=count2;
				count2=0;
			}
		}

	}

}

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}