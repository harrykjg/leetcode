package DataStruct;

/**
 * Created by 502575560 on 8/20/17.
 */
public class BinaryTreeMaximumPathSum {
    public static void main(String[] args){
        BinaryTreeMaximumPathSum bt=new BinaryTreeMaximumPathSum();
        TreeNode n=new TreeNode(2);
        n.left=new TreeNode(1);
        n.right=new TreeNode(3);
        System.out.println(bt.maxPathSum(n));

    }
    //还是有点难想,大概写出来之后发现就是要分成两个方法的,否则没法返回结果,而且如果node只有一个并且是负数时会不对
    int rs=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return rs;
    }
    int helper(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=Math.max(0,helper(root.left));
        int right=Math.max(0,helper(root.right));
        rs=Math.max(rs,left+root.val+right);//这里开始写成Math.max(rs,Math.max(left,Math.max(right,left+root.val+right)))了,
        //这样会导致node只有一个并且是负数时会返回0是答案,应该不能把left和right作为rs的candidate,因为上一层要取得当前层的话,必须要经过当前root.val而
        //不可能跳过root而去取它的儿子
        return Math.max(root.val,Math.max(root.val+left,root.val+right));
    }
}
