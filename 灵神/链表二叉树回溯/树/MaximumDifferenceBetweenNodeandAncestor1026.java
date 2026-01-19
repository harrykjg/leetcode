package 灵神.链表二叉树回溯.树;

public class MaximumDifferenceBetweenNodeandAncestor1026 {
    static void main() {

    }
    //12/24/2025 自己想的就是标准答案
    int rs=0;
    public int maxAncestorDiff(TreeNode root) {
        if(root==null){
            return rs;
        }
        dfs(root,root.val,root.val);
        return rs;
    }
    void dfs(TreeNode root, int max,int min){
        if(root==null){
            return;
        }
        int cur= root.val;
        int temp=Math.max(Math.abs(cur-min),Math.abs(cur-max));
        rs=Math.max(rs,temp);
        max=Math.max(cur,max);
        min=Math.min(cur,min);
        dfs(root.left,max,min);
        dfs(root.right,max,min);
    }
}
