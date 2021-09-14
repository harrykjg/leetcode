import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLeavesofBinaryTree {
    //7/17/2021开始想的是层次便利，发现是叶子则把自己设成是空，结果是不行的，把自己设成空没用，父节点还是会指向子节点。看别人的写法吧
    //https://leetcode.com/problems/find-leaves-of-binary-tree/discuss/83775/Java-backtracking-O(n)-time-O(n)-space-No-hashing!
    //想的时候会想怎么知道rs数组有几个list，并且何时创建，不太好想，其实他是自底向上的，每次发现当前rs的size=curLevel时就去创建，刚好是从0开始，很巧妙
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        if (root==null){
            return rs;
        }
        getHeight(root,rs);
        return rs;
    }
    int getHeight(TreeNode node,List<List<Integer>> rs ){
        if (node==null){
            return -1;
        }
        int left=getHeight(node.left,rs);
        int right=getHeight(node.right,rs);
        int curLevel=Math.max(left,right)+1;
        if (rs.size()==curLevel){//这里不好想
            rs.add(new ArrayList<Integer>());
        }
        rs.get(curLevel).add(node.val);
        node.left=null;
        node.right=null;
        return curLevel;
    }

    //8/11/2021不会了 这个level很巧妙，不是深度,是反过来的深度
    public List<List<Integer>> findLeaves2(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        dfs(root,rs);
        return rs;
    }
    int dfs(TreeNode node,List<List<Integer>> rs){
        if (node==null){
            return -1;
        }
        int left=dfs(node.left,rs);
        int right=dfs(node.right,rs);
        int curL=Math.max(left,right)+1;
        if (rs.size()==curL){
            rs.add(new ArrayList<>());
        }
        rs.get(curL).add(node.val);
        return curL;
    }
}
