import java.util.*;

public class BinaryTreeRightSideView {
    //7/18/2021 也是想的是层次便利取该层最后一个加入结果 一次过
    //https://leetcode.com/problems/binary-tree-right-side-view/discuss/56012/My-simple-accepted-solution(JAVA) 这个方法叼，空间就是stack的数量
    //就是树的高度
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rs=new ArrayList<>();
        if (root==null){
            return rs;
        }
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        int count=1;
        int count2=0;
        while (!q.isEmpty()){
            TreeNode cur=q.poll();
            count--;
            if (cur.left!=null){
                q.offer(cur.left);
                count2++;
            }
            if (cur.right!=null){
                q.offer(cur.right);
                count2++;
            }
            if (count==0){
                rs.add(cur.val);
                count=count2;
                count2=0;
            }
        }
        return rs;
    }
//8/28/2021 抄别人的
    //https://leetcode.com/problems/binary-tree-right-side-view/discuss/56012/My-simple-accepted-solution(JAVA)
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> rs=new ArrayList<>();
        helper(root,rs,0);
        return rs;
    }
    void helper(TreeNode root,List<Integer> rs,int depth){
        if (root==null){
            return;
        }
        if (rs.size()==depth){
            rs.add(root.val);
        }
        helper(root.right,rs,depth+1);
        helper(root.left,rs,depth+1);
    }
}
