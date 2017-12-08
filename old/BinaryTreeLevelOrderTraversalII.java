import java.util.ArrayList;
import java.util.LinkedList;

//这题就是纯属无聊，就是之前的level order下来，再用一个数组，反着添加上去就行了
public class BinaryTreeLevelOrderTraversalII {
	ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
	 public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
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
			ArrayList<ArrayList<Integer>> rs=new ArrayList<ArrayList<Integer>>();
			for(int i=al.size()-1;i>=0;i--){
				rs.add(al.get(i));
			}
			return rs;
	    }

}
