package DataStruct.tree;

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
    //04/20/2020,思路记得但是写错了，不是说如果下限或上限是null就不用去判断左边或者右边子树，还是要两边都判断
    public boolean isValidBST2(TreeNode root) {
        if(root==null){
            return true;
        }
        return helper2(null,root,root.left)&&helper2(root,null,root.right);
    }
    boolean helper2(TreeNode left, TreeNode right, TreeNode cur){
        if(cur==null){
            return true;
        }

        if((right!=null&&cur.val>=right.val)||(left!=null&&cur.val<=left.val)){
            return false;
        }
        return helper2(cur,right,cur.right)&&helper2(left,cur,cur.left);
    }

    //05/24/2020,改了一次对了
    public boolean isValidBST3(TreeNode root) {
        if(root==null){
            return true;
        }
        return helper3(null,root.val,root.left)&&helper3(root.val,null,root.right);
    }
    boolean helper3(Integer low, Integer high, TreeNode root){
        if(root==null){
            return true;
        }

        if((high!=null&&root.val>=high)||(low!=null&&root.val<=low)){
            return false;
        }
        return helper3(low,root.val,root.left)&&helper3(root.val,high,root.right);
    }

    //12/19/2020写的一般般
    public boolean isValidBST4(TreeNode root) {
        if(root==null){
            return true;
        }
        return helper4(null,root,root.left)&&helper4(root,null,root.right);
    }
    boolean helper4(TreeNode low, TreeNode high, TreeNode cur){
        if(cur==null){
            return true;
        }
        if(low!=null&&cur.val<=low.val){
            return false;
        }
        if(high!=null&&cur.val>=high.val){
            return false;
        }

        return helper4(low,cur,cur.left)&&helper4(cur,high,cur.right);
    }
//6/1/2021
    public boolean isValidBST5(TreeNode root) {
        if(root==null){
            return true;
        }
        return helper5(null,root.val,root.left)&&helper5(root.val,null,root.right);
    }
    boolean helper5(Integer low,Integer high, TreeNode node){
        if(node==null){
            return true;
        }
        if(low!=null&&node.val<=low){
            return false;
        }
        if(high!=null&&node.val>=high){
            return false;
        }
        return helper5(low, node.val, node.left)&&helper5(node.val,high,node.right);
    }
}
