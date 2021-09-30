package tree;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryTreeVerticalOrderTraversal {

    //以前的代码
    //https://leetcode.com/problems/binary-tree-vertical-order-traversal/discuss/76401/5ms-Java-Clean-Solution
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        HashMap<Integer,ArrayList> map=new HashMap<>();
        if(root==null){
            return rs;
        }
        Queue<TreeNode> q=new LinkedList<>();
        Queue<Integer> q2=new LinkedList<>();
        int left=Integer.MAX_VALUE;
        int right=Integer.MIN_VALUE;
        q2.offer(0);
        q.offer(root);
        while (!q.isEmpty()){
            TreeNode n=q.poll();
            int index=q2.poll();
            if(!map.containsKey(index)){
                ArrayList<Integer> al=new ArrayList<>();
                al.add(n.val);
                map.put(index,al);
            }else{
                map.get(index).add(n.val);
            }
            if(n.left!=null){
                q.offer(n.left);
                q2.offer(index-1);
            }
            if(n.right!=null){
                q.offer(n.right);
                q2.offer(index+1);
            }
            left=Math.min(left,index);
            right=Math.max(right,index);

        }
        for(int i=left;i<=right;i++){
            rs.add(map.get(i));

        }
        return rs;
    }
    //8/24/2021
    //这是照着以前的改的，用的treemap来排序col，就省去了上面用min max来排序map。然后用一个pair class来记录这个node的col，就省去了上面用另一个q来记录col，
    //他这里能work的巧妙地方就在于level order的时候肯定是从上到下（row就不需要排序了），并且level order还是从左到右的（题目要求col相同的时候按左到右），因此
    //只要col按treemap排序，里面装个list就能满足需要
    public List<List<Integer>> verticalOrder3(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        TreeMap<Integer,ArrayList> map=new TreeMap<>();
        if(root==null){
            return rs;
        }
        Queue<Pair> q=new LinkedList<>();
        Pair p=new Pair(root,0);
        q.offer(p);
        while (!q.isEmpty()){
            Pair n=q.poll();
            int index=n.col;
            if(!map.containsKey(index)){
                ArrayList<Integer> al=new ArrayList<>();
                al.add(n.node.val);
                map.put(index,al);
            }else{
                map.get(index).add(n.node.val);
            }
            if(n.node.left!=null){
                Pair pl=new Pair(n.node.left,index-1);
                q.offer(pl);
            }
            if(n.node.right!=null){
                Pair pr=new Pair(n.node.right,index+1);
                q.offer(pr);
            }

        }
        for(List<Integer> al:map.values()){
            rs.add(al);
        }
        return rs;
    }
    class Pair{
        TreeNode node;
        int col;
        public Pair(TreeNode node,int col){
            this.node=node;
            this.col=col;
        }
    }
    //8/24/2021 还以为和Vertical Order Traversal of a Binary Tree是一样的，其实有点不一样。那里同一col的排序是：row大的先，row相同的按val的大小排，因此
    //可以很明确的用这两条规律sort，而这里的同一col的排序是row大的先，然后就是按先后顺序，那貌似就没法用linkedhashset活着treemap来搞了,但是可以再搞一个treemap
    //装的是key为row，value为list。这样挺麻烦但是可以
    //https://leetcode.com/problems/binary-tree-vertical-order-traversal/discuss/76528/Java-TreeMap-Solution
    TreeMap<Integer, TreeMap<Integer,List<Integer>>> map=new TreeMap<>();//这个解法不好写
    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        dfs(root,0,0);
        for (Map.Entry<Integer,TreeMap<Integer,List<Integer>>> entry:map.entrySet()){

            TreeMap<Integer,List<Integer>> map2=entry.getValue();
            ArrayList<Integer> al=new ArrayList<>();
            for (List<Integer> l:map2.values()){
                al.addAll(l);
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
            TreeMap<Integer,List<Integer>> map2=new TreeMap<>();
            List<Integer> ls=new ArrayList<>();
            ls.add(root.val);
            map2.put(row,ls);
            map.put(col,map2);
        }else {
            if (!map.get(col).containsKey(row)){
                List<Integer> ls=new ArrayList<>();
                ls.add(root.val);
                map.get(col).put(row,ls);
            }else {
                map.get(col).get(row).add(root.val);
            }
        }
        dfs(root.left,row+1,col-1);//注意由于内层treemap是升序，而我们要上层是节点先出，所以定于下层的row是+1，而不是-1
        dfs(root.right,row+1,col+1);
    }

    //9/28/2021就是用level order，treemap的key是col，value就是个arraylist就可以按顺序装了，这里其实我间的node class不需要row，只要col就行了
    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        TreeMap<Integer,ArrayList<Integer>> map=new TreeMap<>();
        Queue<Node> q=new LinkedList<>();
        Node n=new Node(root,0,0);
        q.offer(n);
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(!map.containsKey(cur.col)){
                map.put(cur.col,new ArrayList<Integer>());
            }
            map.get(cur.col).add(cur.node.val);
            if(cur.node.left!=null){
                Node left=new Node(cur.node.left,cur.row-1,cur.col-1);
                q.offer(left);
            }
            if(cur.node.right!=null){
                Node right=new Node(cur.node.right,cur.row-1,cur.col+ 1);
                q.offer(right);
            }
        }
        for(Map.Entry<Integer,ArrayList<Integer>> entry:map.entrySet()){
            rs.add(entry.getValue());
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
