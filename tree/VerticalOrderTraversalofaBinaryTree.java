package tree;

import java.util.*;

public class VerticalOrderTraversalofaBinaryTree {
    //8/17/2021 开始以为row没用，结果是有用的，col相等的话rol小的靠前.思路就是从root开始dfs就知道row和col，然后设个treemap，col作为key，里面装一个
    //list，list里面装的是row和value的pair，然后按treemap的key来取，取出来的list再按row和value 排序加入结果集
    //https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/discuss/231148/Java-TreeMap-Solution 他这个我和接近，他就是用了pq，
    //复杂度应该和我一样
    TreeMap<Integer, List<Pair>>map=new TreeMap<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        dfs(root,0,0);
        Iterator<Map.Entry<Integer, List<Pair>>> it=map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Integer,List<Pair>> entry=it.next();
            ArrayList<Integer> al=new ArrayList<>();
            List<Pair> pairs=entry.getValue();
            Collections.sort(pairs, new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    if (o1.row==o2.row){
                        return o1.row-o2.row;
                    }
                    return o1.val-o2.val;
                }
            });
            for (Pair p:pairs){
                al.add(p.val);
            }
            rs.add(al);
        }
        return rs;
    }
    void dfs(TreeNode root,int row,int col){
        if (root==null){
            return;
        }
        if (!map.containsKey(col)){
            map.put(col,new ArrayList<>());
        }
        map.get(col).add(new Pair(row,root.val));
        dfs(root.left,row+1,col-1);
        dfs(root.right,row+1,col+1);
    }
    class Pair{
        int row;
        int val;
        public Pair(int row,int val){
            this.row=row;
            this.val=val;
        }
    }
}
