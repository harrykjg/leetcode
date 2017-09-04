/**
 * Created by 502575560 on 7/22/17.
 */
public class
BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        int d1=depth(0,root.left);
        int d2=depth(0,root.right);
        if(Math.abs(d1-d2)>1){
            return false;
        }

        return isBalanced(root.left)&&isBalanced(root.right);//开始这里直接返回true了就错了,应该要左右再检查,因为左右虽然深度一样,
    }                                              //但是由于深度是算最深的那个,所以说其实左右子树可能各自不平衡
    int depth(int cur,TreeNode n){
        if(n==null){
            return cur;
        }
        return Math.max(depth(cur+1,n.left),depth(cur+1,n.right));
    }
}
