import java.util.Stack;

public class ClosestBinarySearchTreeValue {
    //7/16/2021,inorder再找算是暴力法了
    //看到可以是o（h）复杂度然后自己想的。但是用了stack，别人连stack都不用，想一想确实，根本不用stack，顺着往下走就完了
    // https://leetcode.com/problems/closest-binary-search-tree-value/discuss/70331/Clean-and-concise-java-solution
    public int closestValue(TreeNode root, double target) {
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        int rs=0;
        double diff=Integer.MAX_VALUE;
        while (!st.isEmpty()){
            TreeNode cur=st.pop();
            if (Math.abs(cur.val-target)<diff){
                rs=cur.val;
                diff=Math.abs(cur.val-target);
            }
            if (cur.val>target){
                if (cur.left!=null){
                    st.push(cur.left);
                    cur.left=null;
                }
            }else {
                if (cur.right!=null){
                    st.push(cur.right);
                    cur.right=null;
                }
            }
        }
        return rs;
    }

    //8/9/2021 写的不好，要练
    public int closestValue2(TreeNode root, double target) {
        if (root==null){
            return -1;
        }
        double diff=Integer.MAX_VALUE;
        int rs=0;
        while (root!=null){
            double d=Math.abs(root.val-target);
            if (d==0){
                return root.val;
            }
            if (d<diff){
                diff=d;
                rs=root.val;
            }
            if (root.val<target){
                root=root.left;
            }else {
                root=root.right;
            }
        }
        return rs;
    }
}
