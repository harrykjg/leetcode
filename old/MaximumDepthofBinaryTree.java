//ºÜ¼òµ¥

//http://www.cnblogs.com/bakari/p/4126693.html
public class MaximumDepthofBinaryTree {
	 int max=0;
	    public int maxDepth(TreeNode root) {
	        if(root==null){
	            return 0;
	        }
	        check(root,0);
	        return max;
	        
	    }
	    public void check(TreeNode root,int cur){
	        if(root==null){
	            if(cur>max){
	                max=cur;
	            }
	            return;
	        }
	        check(root.left,cur+1);
	        check(root.right,cur+1);
	    }

}
