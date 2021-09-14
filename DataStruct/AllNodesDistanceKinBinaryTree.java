package DataStruct;

import apple.laf.JRSUIUtils;

import java.util.*;

/**
 * Created by yufengzhu on 9/4/18.
 */
public class AllNodesDistanceKinBinaryTree {
    //想到可以先建图再去找，其他的方法不会
    //https://www.youtube.com/watch?v=o1siL8eKCos
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        HashMap<TreeNode,Set<TreeNode>> map=new HashMap<TreeNode, Set<TreeNode>>();

        List<Integer> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        if(K==0){
            rs.add(target.val);
            return rs;
        }
        dfs(map,root);
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(target);
        int dist=0;
        int count=1;
        int count2=0;
        HashSet<TreeNode> set=new HashSet<>();
        set.add(target);
        while (!q.isEmpty()){
            TreeNode n=q.poll();
            count--;

            for(TreeNode nei:map.get(n)){
                if(!set.contains(nei)){
                    q.offer(nei);
                    count2++;
                    set.add(nei);//我觉得set。add（nei）要放在这里才对的，因为可能当前层有2个node都共同有一个邻居，那么的话这个邻居就被加入q两次了，但是试了下放在外面count--之后用set。add（n）也accept了，不知道为啥
                    if(dist+1==K){
                        rs.add(nei.val);
                    }
                }
            }
            if(count==0){
                count=count2;
                count2=0;
                dist++;
                if(dist>K){
                    break;
                }
            }
        }

        return rs;
    }
    void dfs(HashMap<TreeNode,Set<TreeNode>> map,TreeNode root){
        if(root==null){
            return;
        }
        TreeNode left=root.left;
        TreeNode right=root.right;
        if(!map.containsKey(root)){
            HashSet<TreeNode> set=new HashSet<>();
            if(left!=null){
                set.add(left);
                if(!map.containsKey(left)){
                    HashSet<TreeNode> set2=new HashSet<>();
                    set2.add(root);
                    map.put(left,set2);
                }else{
                    map.get(left).add(root);
                }
            }
            if(right!=null){
                set.add(right);
                if(!map.containsKey(right)){
                    HashSet<TreeNode> set2=new HashSet<>();
                    set2.add(root);
                    map.put(right,set2);
                }else{
                    map.get(right).add(root);
                }
            }
            map.put(root,set);
        }else{
            if(left!=null){
                map.get(root).add(left);
                if(!map.containsKey(left)){
                    HashSet<TreeNode> set2=new HashSet<>();
                    set2.add(root);
                    map.put(left,set2);
                }else{
                    map.get(left).add(root);
                }
            }
            if(right!=null){
                map.get(root).add(right);
                if(!map.containsKey(right)){
                    HashSet<TreeNode> set2=new HashSet<>();
                    set2.add(root);
                    map.put(right,set2);
                }else{
                    map.get(right).add(root);
                }
            }
        }
        dfs(map,root.left);
        dfs(map,root.right);

    }

    //8/23/2021只能想到建图再bfs。就这样写吧。
    //https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143752/JAVA-Graph-%2B-BFS
    HashMap<TreeNode,Set<TreeNode>> map=new HashMap<>();
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        List<Integer> rs=new ArrayList<>();
        if (root==null||target==null){
            return rs;
        }
        build(root);
        Queue<TreeNode> q=new LinkedList<>();
        Set<TreeNode> set=new HashSet<>();
        set.add(target);
        q.add(target);
        int dist=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                TreeNode cur=q.poll();
                if (dist==K){
                    rs.add(cur.val);
                }
                for (TreeNode nei:map.get(cur)){
                    if (!set.contains(nei)){
                        q.offer(nei);
                        set.add(nei);
                    }
                }
            }

            dist++;
            if (dist>K){
                break;
            }

        }
        return rs;
    }
    void build(TreeNode root){
        if (root==null){
            return;
        }
        if (!map.containsKey(root)){
            map.put(root,new HashSet<>());
        }
        if (root.left!=null){
            map.get(root).add(root.left);
            if (!map.containsKey(root.left)){
                map.put(root.left,new HashSet<>());
                map.get(root.left).add(root);
            }
            build(root.left);
        }
        if (root.right!=null){
            map.get(root).add(root.right);
            if (!map.containsKey(root.right)){
                map.put(root.right,new HashSet<>());
                map.get(root.right).add(root);
            }
            build(root.right);
        }
    }
}
