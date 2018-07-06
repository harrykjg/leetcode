/**
 * Created by yufengzhu on 7/1/18.
 */
public class LongestUnivaluePath {
    public static void main(String[] a){
        LongestUnivaluePath lu=new LongestUnivaluePath();
        TreeNode r=new TreeNode(1);
        r.left=new TreeNode(2);
        r.right=new TreeNode(2);
        r.left.left=new TreeNode(2);
        r.left.right=new TreeNode(2);
        r.right.left=new TreeNode(2);
        r.right.right=new TreeNode(2);
        r.left.left.left=new TreeNode(2);
        lu.longestUnivaluePath(r);
    }
    //和Binary Tree Maximum Path Sum很像，path可以是左中右这样连起来的,大概是知道怎么写，但是还是很难写对！对比答案，他的arrowLeft那部分有差别！
    int rs=0;
    public int longestUnivaluePath(TreeNode root) {
        if(root==null){
            return 0;
        }
        helper(root);
        return rs;

    }
    int helper(TreeNode r){
        if(r==null){
            return 0;
        }
        int left=0;
        int right=0;
        if(r.left!=null){
            left=helper(r.left)+(r.left.val==r.val?1:0);

        }
        if(r.right!=null){
            right=helper(r.right)+(r.right.val==r.val?1:0);

        }
        if(r.right!=null&&r.right.val==r.val){//这里很容易漏！比如一个root，其左子树与右子树相等，但是不和root相等，那么左边或右边也只能算成0，作为当层返回的结果

        }else{
            right=0;
        }
        if(r.left!=null&&r.left.val==r.val){

        }else{
            left=0;
        }

        rs=Math.max(left+right,rs);
        return Math.max(left,right);

    }
}
