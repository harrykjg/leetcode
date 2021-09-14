import java.util.*;

/**
 * Created by yufengzhu on 7/24/18.
 */
public class SmallestSubtreewithalltheDeepestNodes {
    public static void main(String[] a){
        tree.TreeNode n=new tree.TreeNode(1);
        n.left=new tree.TreeNode(2);
        n.right=new tree.TreeNode(3);
        n.left.left=new tree.TreeNode(4);
        n.left.right=new tree.TreeNode(5);
        n.right.left=new tree.TreeNode(6);
        n.right.right=new tree.TreeNode(7);
        n.left.right.left=new tree.TreeNode(8);
        n.left.right.right= new tree.TreeNode(9);
        SmallestSubtreewithalltheDeepestNodes ss=new SmallestSubtreewithalltheDeepestNodes();
        ss.subtreeWithAllDeepest(n);


    }
    //自己想出一半，联想到lowerst common ancestor那题
    //https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/solution/
    ArrayList<tree.TreeNode> al=new ArrayList<>();
    int depth=0;
    public tree.TreeNode subtreeWithAllDeepest(tree.TreeNode root) {
        if(root==null){
            return null;
        }
        //开始想用层次遍历，貌似要用O（n）空间去记录每一层，判断是否是最后一层，还是算了，用dfs,不过貌似dfs也要一个O(n）的map去存
        HashMap<Integer,HashSet<tree.TreeNode>> map=new HashMap<>();
        dfs(0,root,map);
        HashSet<tree.TreeNode> set=map.get(depth);
        return helper(root,set);


    }
    void dfs(int cur, tree.TreeNode root, HashMap<Integer,HashSet<tree.TreeNode>> map){
        if(root==null){
            return;
        }
        depth=Math.max(cur,depth);
        if(!map.containsKey(cur)){
            HashSet<tree.TreeNode> al=new HashSet<>();
            al.add(root);
            map.put(cur,al);
        }else{
            map.get(cur).add(root);
        }
        dfs(cur+1,root.left,map);
        dfs(cur+1,root.right,map);
    }
    tree.TreeNode helper(tree.TreeNode n, HashSet<tree.TreeNode> set){
        if(n==null||set.contains(n)){
            return n;
        }
        tree.TreeNode left=helper(n.left,set);
        tree.TreeNode right=helper(n.right,set);
        if(left!=null&&right!=null){
            return n;
        }
        return left==null?right:left;
    }
}
