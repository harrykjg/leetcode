package contest;

import java.util.*;

public class NumberofGoodLeafNodesPairs {

    public static void main(String[] args){
        NumberofGoodLeafNodesPairs ng=new NumberofGoodLeafNodesPairs() ;
        TreeNode root=new TreeNode(78);
        root.left=new TreeNode(15);
        root.right=new TreeNode(81);
        root.left.left=new TreeNode(73);
        root.left.right=new TreeNode(98);
        root.right.left=new TreeNode(36);
        root.left.left.left=new TreeNode(30);
        root.left.right.left=new TreeNode(63);
        root.left.right.right=new TreeNode(32);
        System.out.println(ng.countPairs(root,6));
    }
    //自己写的暴力法，就是把树转换成图，再对那一堆leaf，分别以每个leaf作为起点做bfs。貌似有更好的方法。
    //对于有一堆bfs起点的题，类似的参考WallsandGates
    int rs=0;
    public int countPairs(TreeNode root, int distance) {
        if(root.left==null&&root.right==null){
            return 0;
        }
        Map<TreeNode, List<TreeNode>> map=new HashMap<>();
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        List<TreeNode> pool=new ArrayList<>();
        while (!q.isEmpty()){
            TreeNode cur=q.poll();
            if(cur.left!=null){
                q.offer(cur.left);
                map.putIfAbsent(cur,new ArrayList<>());
                map.get(cur).add(cur.left);
                map.putIfAbsent(cur.left,new ArrayList<>());
                map.get(cur.left).add(cur);
            }
            if(cur.right!=null){
                q.offer(cur.right);
                map.putIfAbsent(cur,new ArrayList<>());
                map.get(cur).add(cur.right);
                map.putIfAbsent(cur.right,new ArrayList<>());
                map.get(cur.right).add(cur);
            }
            if(cur.left==null&&cur.right==null){
                pool.add(cur);
            }
        }
        Set<TreeNode> memo=new HashSet<>();
        for(int i=0;i<pool.size();i++){
            TreeNode cur=pool.get(i);
            memo.add(cur);
            bfs(cur,map,memo,distance,pool);
        }
        return rs;
    }
    void bfs(TreeNode start,Map<TreeNode,List<TreeNode>> map,Set<TreeNode> memo,int distance,List<TreeNode> pool){
        int dist=0;
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(start);
        int count=1;
        int count2=0;
        Set<TreeNode> memo2=new HashSet<>();
        while (!q.isEmpty()){
            TreeNode cur=q.poll();
            memo2.add(cur);
            count--;
            for(TreeNode nei:map.get(cur)){
                if(pool.contains(nei)&&!memo.contains(nei)){
                    if(dist+1<=distance){
                        rs++;
                    }
                }
                if(!memo2.contains(nei)){
                    q.offer(nei);
                    count2++;
                }
            }
            if(count==0){
                count=count2;
                count2=0;
                dist++;
                if(dist>=distance){
                    return;
                }
            }
        }
    }
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
