public class BinaryTreeLongestConsecutiveSequence {
    //7/18/2021 dfs改了一次对了
    int rs=0;
    public int longestConsecutive(TreeNode root) {
        if (root==null){
            return 0;
        }
        rs=1;
        dfs(root.left,root.val,1);
        dfs(root.right,root.val,1);
        return rs;
    }
    void dfs(TreeNode root,int num,int cur){
        if (root==null){
            return ;
        }

        if (root.val==num+1){
            rs=Math.max(rs,cur+1);
            dfs(root.left,root.val,cur+1);
            dfs(root.right,root.val,cur+1);
        }else{
            dfs(root.left,root.val,1);
            dfs(root.right,root.val,1);
        }

    }
}
