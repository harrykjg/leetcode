package contest;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

//这题有点恶心，没说是，edge给出来不一定是前者是parent，后者是child，貌似也没啥好方法，就是dfs,有的建了树有的不用建树，
//这个dfs和以前不太一样，他是有返回值的，一般的都没有
//https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/discuss/743133/JavaPython-3-DFS-w-brief-explanation-and-analysis.
//https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/discuss/743327/Java-Easy-DFS
public class NumberofNodesintheSubTreeWiththeSameLabel {
    public static void main(String[] args){

    }
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        Map<Integer, Set<Integer>> map=new HashMap<>();
        for(int[] edge:edges){
            map.putIfAbsent(edge[0],new HashSet<>());
            map.get(edge[0]).add(edge[1]);
            map.putIfAbsent(edge[1],new HashSet<>());
            map.get(edge[1]).add(edge[0]);
        }
        int[] rs=new int[n];
        char[] ch=labels.toCharArray();
        Set<Integer> memo=new HashSet<>();
        dfs(0,map,rs,ch,memo);
        return rs;
    }
    //dfs返回的是子树的所有节点的所有char的次数
    int[] dfs(int cur,Map<Integer,Set<Integer>> map,int[] rs, char[] ch,Set<Integer> memo){
        int[] a=new int[26];//a的意义是当前层的以及其所有子树的char的统计
        if(memo.contains(cur)){
            return a;
        }
        memo.add(cur);
        a[ch[cur]-'a']++;//先把当前node的label加上1，即自己
        Set<Integer> children=map.get(cur);
        for(Integer child:children){
            int[] childrencount=dfs(child,map,rs,ch,memo);
            for(int i=0;i<26;i++){//得到子树的所有节点的label的次数之后，加到当前层的统计
                a[i]+=childrencount[i];
            }

        }
        rs[cur]=a[ch[cur]-'a'];//rs【cur】就是a里面对应统计完了的数
        return a;
    }


}

