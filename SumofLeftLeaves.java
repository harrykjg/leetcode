/**
 * Created by 502575560 on 6/13/17.
 */
public class SumofLeftLeaves {
    //这题只有一个根节点的话则它不算left node,一次过
    //https://discuss.leetcode.com/topic/60403/java-iterative-and-recursive-solutions/2  iterative的可以看看
    int rs=0;
    public int sumOfLeftLeaves(DataStruct.tree.TreeNode root) {
        if(root==null){
            return 0;
        }
        DataStruct.tree.TreeNode left=root.left;
        if(left!=null&&left.left==null&&left.right==null){
            rs+=left.val;
        }
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
        return rs;

    }
}
