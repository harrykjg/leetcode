import java.util.ArrayList;
import java.util.LinkedList;
//http://blog.csdn.net/linhuanmars/article/details/23404111
//����������˼·����queue����2��count����¼�����ж��ٸ�Ԫ�ء�code ganker����һ������2��count��
public class BinaryTreeLevelOrderTraversal {
	ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		if(root==null){
			return al;
		}
		LinkedList<TreeNode> q=new LinkedList<TreeNode>();
		q.add(root);
		int count1=1;
		int count2=0;
		while(!q.isEmpty()){
			ArrayList<Integer> a=new ArrayList<Integer>();
			while(count1>0){
				TreeNode temp=q.poll();
				count1--;
				
				a.add(temp.val);
				if(count1==0){
					al.add(a);
				}
				if(temp.left!=null){
					q.add(temp.left);
					count2++;
				}
				if(temp.right!=null){
					q.add(temp.right);
					count2++;
				}
			}
			count1=count2;
			count2=0;
		}
		return al;
	}

}
