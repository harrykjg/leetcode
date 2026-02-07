package linkedin;

import 灵神.链表二叉树回溯.树.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesofBinaryTree366 {
    //没想出来，一直卡在第一个例子那想的是依靠判断node有没有孩子来判断是否是叶子节点，但是判断是之后怎么知道应该放哪个结果集里，用depth的话也
    //不对啊，结果是应该用height就对了，就是buttom up，先dfs到叶子，可以得到height，用来确定去哪个结果集，那你咋知道应该建几个结果集呢？
    //应该就是边走buttom up的时候有了height再建吧
    //感觉比他们好几个答案都写的好
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        dfs(root,rs);
        return rs;
    }
    int dfs(TreeNode n,List<List<Integer>> rs){
        if(n==null){
            return 0;
        }
        int lh=dfs(n.left,rs);
        int rh=dfs(n.right,rs);
        int max=Math.max(lh,rh);
        int height=max+1;
        if(rs.size()<height){
            rs.add(new ArrayList<>());
        }
        rs.get(height-1).add(n.val);
        return height;
    }
}
