package tree;

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

    //12/17/2020,
    //总体记得，但是还是写的不对,看回上面的注解
    int max2=0;
    public int maxPathSum2(TreeNode root) {
        if(root==null){
            return 0;
        }
        helper2(root);
        return max2;
    }

    int helper2(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=Math.max(0,helper(root.left));//开始写成root.left==null?0:helper2(root.left)这样是不对的，因为root。left可能是负数
        int right=Math.max(0,helper(root.right));
        max2=Math.max(max2,root.val+left+right);
        return Math.max(root.val+left,Math.max(root.val+right,root.val));
    }
//6/1/2021,一次过
    int max3=Integer.MIN_VALUE;
    public int maxPathSum3(TreeNode root) {
        if(root==null){
            return max3;
        }
        helper3(root);
        return max3;
    }
    int helper3(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=Math.max(0,helper3(root.left));
        int right=Math.max(0,helper3(root.right));

        max3=Math.max(max3,root.val+left+right);
        return root.val+Math.max(left,right);

    }
}
