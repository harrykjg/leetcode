import java.util.Stack;

public class RangeSumofBST {
    //8/16/2021
    //想的和maximum depth of binary tree是一样的，不需要维护一个全局rs然后一个void方法去update他。基本一次过
    public int rangeSumBST(TreeNode root, int low, int high) {
        int rs=0;
        if (root==null){
            return 0;
        }
        int right=0;
        if (root.val<=high){
            right=rangeSumBST(root.right,low,high);
        }
        int left=0;
        if (root.val>=low){
            left=rangeSumBST(root.left,low,high);
        }

        if (root.val>=low&&root.val<=high){
            rs+=root.val;
        }
        return rs+left+right;

    }
    //8/25/2021想的又不一样了
    int rs=0;
    public int rangeSumBST2(TreeNode root, int low, int high) {
        helper(root,low,high);
        return rs;
    }
    void helper(TreeNode root,int low,int high){
        if(root==null){
            return;
        }
        if(root==null){
            return;
        }
        if(root.val<=high&&root.val>=low){
            rs+=root.val;
        }
        if(root.val>high){//这样应该好理解一些，如果大于high了，则至于左边有可能。同理如果小于low了只有右边有可能，否则两边都有可能
            helper(root.left,low,high);
        }else if(root.val<low){
            helper(root.right,low,high);
        }else{
            helper(root.left,low,high);
            helper(root.right,low,high);
        }
    }

    //写个iterative的  不太会.用的preorder
    public int rangeSumBST3(TreeNode root, int low, int high) {
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        int rs=0;
        while (!st.isEmpty()){
            TreeNode cur=st.pop();
            if(cur.val<=high&&cur.val>=low){
                rs+=cur.val;
            }
            if(cur.left!=null){
                if (cur.val>low){//想的是要是cur小于等于low就肯定不去，因此只要cur大于low就去
                    st.push(cur.left);
                }
            }
            if (cur.right!=null){
                if (cur.val<high){
                    st.push(cur.right);
                }
            }
        }
        return rs;
    }
}
