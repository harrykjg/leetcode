import java.util.ArrayList;
import java.util.LinkedList;

//������Ǵ������ģ�����֮ǰ��level order����������һ�����飬���������ȥ������
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
