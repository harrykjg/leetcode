package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturnForest {
//7/21/2021 这题还做不出来，不好想
    public static void main(String[] args){
        DeleteNodesAndReturnForest dn=new DeleteNodesAndReturnForest();
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);
        dn.delNodes(root,new int[]{5,3});
    }
    //https://leetcode.com/problems/delete-nodes-and-return-forest/discuss/328853/JavaC%2B%2BPython-Recursion-Solution

    //这个是自己想的，主要是分成2中情况和利用2个helper方法。先检查root，如果root在set里则不把root加进结果集，然后去检查root的left和right。如果root不在
    //set里，则把root加进结果集里，然后这个root的孩子们可能是要被删的，但是他们不需要再加进结果集里了，所以有另一个方法trim去删他们。
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set=new HashSet<>();
        for (int d:to_delete){
            set.add(d);
        }
        List<TreeNode> rs=new ArrayList<>();

        helper(root,set,rs);
        return rs;
    }
    void helper(TreeNode root,Set<Integer> set,List<TreeNode> rs){//helper方法的责任就是检查root是否要被删，是的话就不加入结果集，然后递归检查他左右孩子
        if (root==null){   //也是否要被删。root不被删的话就假如结果集，并且调用trim方法在root身上。
            return;
        }
        if (set.contains(root.val)){
            helper(root.left,set,rs);
            helper(root.right,set,rs);
        }else {
            rs.add(root);
            trim(root,set,rs);
        }
    }
    void trim(TreeNode root,Set<Integer> set,List<TreeNode> rs){//trim方法的责任就是检查这个root以及他的孩子，看是否要被删的，只负责改变树的结构，
                        // 而如果发现他的孩子有要被删的则说明这个孩子要通过父节点设成空，并且这个节点的左右子树要单独加进结果集了，那就是helper方法的责任
        if (root==null){
            return;
        }

        if (root.left!=null&&set.contains(root.left.val)){//如果这个root的左孩子要被删，则先把他左孩子的左右利用helper加进结果集，然后把做孩子设成空
            helper(root.left.left,set,rs);
            helper(root.left.right,set,rs);
            root.left=null;
        }else {//否则继续看左孩子他以及下面有没有要删的
            trim(root.left,set,rs);
        }
        if (root.right!=null&&set.contains(root.right.val)){
            helper(root.right.left,set,rs);
            helper(root.right.right,set,rs);
            root.right=null;
        }else {
            trim(root.right,set,rs);
        }

    }
    //https://www.cnblogs.com/cnoodle/p/13685245.html 他的层次便利的
    //https://zhuanlan.zhihu.com/p/84370532 层次便利
    //https://my.oschina.net/u/4364580/blog/3337952  divide and conqur 也不好想，还不如看下面的youtube那个链接

    //https://www.youtube.com/watch?v=46GgJCUjXZ8 按她的思路写的
    public List<TreeNode> delNodes2(TreeNode root, int[] to_delete) {
        Set<Integer> set=new HashSet<>();
        for (int d:to_delete){
            set.add(d);
        }
        List<TreeNode> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        boolean d1=false;
        boolean d2=false;
        if(set.contains(root.val)){
            d1=dfs2(root.left,true,set,rs);
            d2=dfs2(root.right,true,set,rs);
        }else{
            d1=dfs2(root.left,false,set,rs);
            d2=dfs2(root.right,false,set,rs);
            rs.add(root);
        }
        if(d1){
            root.left=null;
        }
        if(d2){
            root.right=null;
        }
        return rs;
    }
    boolean dfs2(TreeNode root,boolean newTree,Set<Integer> set,List<TreeNode> rs){
        if(root==null){
            return true;
        }
        boolean needDelete=false;
        if(set.contains(root.val)){
            needDelete=true;
        }
        boolean d1=dfs2(root.left,needDelete,set,rs);
        boolean d2=dfs2(root.right,needDelete,set,rs);
        if(d1){
            root.left=null;
        }
        if(d2){
            root.right=null;
        }
        if(newTree&&!needDelete){
            rs.add(root);
        }
        return needDelete;
    }

    static class TreeNode {
        protected int val;
        protected TreeNode left;
        protected TreeNode right;
        TreeNode() {}
        protected TreeNode(int val) { this.val = val; }
        protected TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
