package DataStruct;

import java.util.*;

/**
 * Created by yufengzhu on 8/2/18.
 */
public class BinaryTreeVerticalOrderTraversal {
    //不会
    //关键是要在层次遍历的时候，要记录这个node的col的index，于是他们就用了一个queue去记录，因为加进原来queue的顺序就是从左到右的，所以可以。然后再用一个map记录对应column的index和一个arraylist
    //代表这个column有的node，因为层次遍历是从上到下的所以也对
    //https://leetcode.com/problems/binary-tree-vertical-order-traversal/discuss/76401/5ms-Java-Clean-Solution
    //https://leetcode.com/problems/binary-tree-vertical-order-traversal/discuss/76508/3ms-java-solution-beats-100
    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        HashMap<Integer,ArrayList> map=new HashMap<>();
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
        for(int i=left;i<=right;i++){//开始想的是rs。set（index，al）这样的，其实list这里的index没有值的话会outboundexception的
            rs.add(map.get(i));
        }
        return rs;
    }
    //9/26/2018，还是不会，自己想了个先找左右最远的节点的index，然后就可以确定结果集的长度，然后就可以从root开始dfs遇到节点就放到结果集的对应index的那，但是貌似不能保证每个结果集的顺序是从上倒下，从左到右的
}
