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
}
