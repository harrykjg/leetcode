import apple.laf.JRSUIUtils;

import java.util.*;

/**
 * Created by yufengzhu on 7/24/18.
 */
public class SmallestSubtreewithalltheDeepestNodes {
    public static void main(String[] a){
        TreeNode n=new TreeNode(1);
        n.left=new TreeNode(2);
        n.right=new TreeNode(3);
        n.left.left=new TreeNode(4);
        n.left.right=new TreeNode(5);
        n.right.left=new TreeNode(6);
        n.right.right=new TreeNode(7);
        n.left.right.left=new TreeNode(8);
        n.left.right.right= new TreeNode(9);
        SmallestSubtreewithalltheDeepestNodes ss=new SmallestSubtreewithalltheDeepestNodes();
        ss.subtreeWithAllDeepest(n);


    }
    //自己想出一半，联想到lowerst common ancestor那题
    //https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/solution/
    ArrayList<TreeNode> al=new ArrayList<>();
    int depth=0;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root==null){
            return null;
        }
        //开始想用层次遍历，貌似要用O（n）空间去记录每一层，判断是否是最后一层，还是算了，用dfs,不过貌似dfs也要一个O(n）的map去存
        HashMap<Integer,HashSet<TreeNode>> map=new HashMap<>();
        dfs(0,root,map);
        HashSet<TreeNode> set=map.get(depth);
        return helper(root,set);


    }
    void dfs(int cur,TreeNode root,HashMap<Integer,HashSet<TreeNode>> map){
        if(root==null){
            return;
        }
        depth=Math.max(cur,depth);
        if(!map.containsKey(cur)){
            HashSet<TreeNode> al=new HashSet<>();
            al.add(root);
            map.put(cur,al);
        }else{
            map.get(cur).add(root);
        }
        dfs(cur+1,root.left,map);
        dfs(cur+1,root.right,map);
    }
    TreeNode helper(TreeNode n,HashSet<TreeNode> set){
        if(n==null||set.contains(n)){
            return n;
        }
        TreeNode left=helper(n.left,set);
        TreeNode right=helper(n.right,set);
        if(left!=null&&right!=null){
            return n;
        }
        return left==null?right:left;
    }
}
