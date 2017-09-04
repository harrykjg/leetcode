package DataStruct;

/**
 * Created by 502575560 on 8/21/17.
 */
public class ValidateBinarySearchTree {

    //还是做错了,以为就是递归判断某个node的左右子树的值和root值的大小关系就行了,其实不是,要所有的左子树都小于根节点才行
    //然后还要巧妙的运用null来避免某个node的值是Integer.MaxValue这样的情况无法判断的情况
    public boolean isValidBST(TreeNode root) {
        if(root==null){
            return true;
        }
        return helper(root.left,null,root.val)&&helper(root.right,root.val,null);
    }
    boolean helper(TreeNode n, Integer low, Integer high){
        if(n==null){
            return true;
        }
        if((low!=null&&n.val<=low)||(high!=null&&n.val>=high)){
            return false;
        }
        return helper(n.left,low,n.val)&&helper(n.right,n.val,high);
    }
}
