import java.util.ArrayList;

public class pathSum {

	
	
	public static void main(String[] args) {
		pathSum ps=new pathSum();
		TreeNode node=new TreeNode(-2);
//		node.left=new TreeNode(4);
        node.right=new TreeNode(-3);
//		node.left.left=new TreeNode(11);
//		node.left.left.left=new TreeNode(7);
//		node.left.left.right=new TreeNode(2);
//		node.right.left=new TreeNode(13);
//		node.right.right=new TreeNode(4);
//		node.right.right.right=new TreeNode(1);
		
		System.out.println(ps.hasPathSum2(node,-2));
	}

	
	static ArrayList<Integer> al=new ArrayList<Integer>();
	public static boolean hasPathSum(TreeNode root, int sum) {
		sum(root);
		for(int i=0;i<al.size();i++){
			if(sum==al.get(i)){
				return true;
			}
		}
		return false;

	}
	
	
	public static void sum(TreeNode root){
		
		if(root.left!=null){
			root.left.val=root.val+root.left.val;
			sum(root.left);
		}
		if(root.right!=null){
			root.right.val=root.val+root.right.val;
			 sum(root.right);
		}
		if(root.left==null&&root.right==null){
			al.add(root.val);
		}
	}
	//第二次写，一次过
	public  boolean hasPathSum2(TreeNode root, int sum){
		if(root==null){
			return false;
		}
		return go(root,root.val,sum);
	}
	private boolean go(TreeNode root,int cur,int sum){
		if(cur==sum&&root.left==null&&root.right==null){
			return true;
		}
		if(root.left!=null){
			if(go(root.left,cur+root.left.val,sum)){
				return true;
			}
		}
		if(root.right!=null){
			if(go(root.right,cur+root.right.val,sum)){
				return true;
			}
		}
		return false;
	}
	

}

 class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}