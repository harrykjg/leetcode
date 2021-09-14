package tree;


public class MaximumDifferenceBetweenNodeandAncestor {
    //8/31/2021 只能想到暴力法n方的， 看了答案说只要dfs从root到leaf记录这条path上的最大最小值就行。
    int rs=0;
    public int maxAncestorDiff(MaximumDepthofBinaryTree.TreeNode root) {
        dfs(root,root.val,root.val);
        return rs;
    }
    void dfs(MaximumDepthofBinaryTree.TreeNode root,int max,int min){
        if (root==null){
            return;
        }
        int max2=max;
        int min2=min;
        if (root.val>max2){
            max2=root.val;
        }
        if(root.val<min){
            min2=root.val;
        }
        rs=Math.max(Math.abs(max2-min2),rs);
        dfs(root.left,max2,min2);
        dfs(root.right,max2,min2);
    }

}
