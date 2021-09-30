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
//9/28/2021 和binary tree vertical order traversal差不多，就是map里的arraylist装node，用row和value排序就行了,我觉得比上面的dfs好理解，还容易一起记
    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        TreeMap<Integer,ArrayList<Node>> map=new TreeMap<>();
        Queue<Node> q=new LinkedList<>();
        Node n=new Node(root,0,0);
        q.offer(n);
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(!map.containsKey(cur.col)){
                map.put(cur.col,new ArrayList<Node>());
            }
            map.get(cur.col).add(cur);
            if(cur.node.left!=null){
                Node left=new Node(cur.node.left,cur.row-1,cur.col-1);
                q.offer(left);
            }
            if(cur.node.right!=null){
                Node right=new Node(cur.node.right,cur.row-1,cur.col+ 1);
                q.offer(right);
            }
        }
        for(ArrayList<Node> list:map.values()){
            Collections.sort(list,new Comparator<Node>(){
                public int compare(Node n1,Node n2){
                    if(n1.row==n2.row){
                        return n1.node.val-n2.node.val;
                    }
                    return n2.row-n1.row;
                }
            });
            ArrayList<Integer> al=new ArrayList<>();
            for(Node nn:list){
                al.add(nn.node.val);
            }
            rs.add(al);
        }
        return rs;
    }
    class Node{
        TreeNode node;
        int row;
        int col;
        public Node(TreeNode n,int row,int col){
            this.node=n;
            this.row=row;
            this.col=col;
        }
    }
}
