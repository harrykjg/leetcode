package SomeInterviews.karat;

import java.util.*;

public class TreeAncestor {
    public static void main(String[] args){
        TreeAncestor ta=new TreeAncestor();
        int[][] m={{1,3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5},{4, 8}, {4, 9}, {9, 11}, {14, 4}, {13, 12}, {12, 9}};
        /*
                         14     13
                          |     /
                1    2    4    12
                 \  /   / | \  /
                  3    5  8  9
                    \ /  \    \
                     6    7    11




         */
//        ta.findSingleParent(m);
        System.out.println(ta.commonParent(m,5,6));//注意1，3是没有共同祖先的，而5，6是有的，比如4
        System.out.println(ta.farthestParent(m,6));
    }
    //https://leetcode.com/discuss/interview-question/1061028/Indeed-or-Phone-Screen-(Karat)-or-Parent-Child-Graph-and-Calculator
    //他这个链接的数据和图对不上的
    private List<List<Integer>> findSingleParent(int[][] relations){
        HashMap<Integer,Set<Integer>> map=new HashMap<>();//value代表key的所有parent
        HashSet<Integer> set=new HashSet<>();//所有出现的parent都在set里，那么出现在set里但是没出现在map里的就是没parent的
        for (int[] r:relations){
            if (!map.containsKey(r[1])){
                map.put(r[1],new HashSet<>());
            }
            map.get(r[1]).add(r[0]);
            set.add(r[0]);
        }
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        for (int key:map.keySet()){
            if (map.get(key).size()==1){
                al.add(key);
            }
        }
        rs.add(al);
        List<Integer> al2=new ArrayList<>();
        for (int key:set){
            if (!map.containsKey(key)){
                al2.add(key);
            }
        }
        rs.add(al2);
        for (List<Integer> ls:rs){
            for (int i:ls){
                System.out.println(i);
            }
        }
        return rs;
    }
//找祖先
    private boolean commonParent(int[][] relations,int a, int b){
        HashMap<Integer,Set<Integer>> map=new HashMap<>();//value代表key的所有parent
        for (int[] r:relations){
            if (!map.containsKey(r[1])){
                map.put(r[1],new HashSet<>());
            }
            map.get(r[1]).add(r[0]);
        }

        Set<Integer> aset=new HashSet<>();
        Set<Integer> bset=new HashSet<>();
        dfs(map,aset,a);//把a的所有祖先放在aset里，同样b的放进bset里，看有没交集
        dfs(map,bset,b);
        for (int i:aset){
            if (bset.contains(i)){
                return true;
            }
        }
        return false;
    }
    void dfs(HashMap<Integer,Set<Integer>> map,Set<Integer> set,int a){//应该也可以用stack找所有parent
        if (map.containsKey(a)){
            for(Integer p:map.get(a)){
                set.add(p);
                dfs(map,set,p);
            }
        }
    }
    //第三问，是问某个点的最远祖先，就是dfs记录距离最远的任意一个
    //https://www.1point3acres.com/bbs/thread-558238-1-1.html
    int father=-1;
    int max=0;
    int farthestParent(int[][] relations,int node){
        HashMap<Integer,Set<Integer>> map=new HashMap<>();//value代表key的所有parent
        for (int[] r:relations){
            if (!map.containsKey(r[1])){
                map.put(r[1],new HashSet<>());
            }
            map.get(r[1]).add(r[0]);
        }
        if (!map.containsKey(node)) {
            return -1;
        }
        fathest(map,node,1);
        return father;
    }
    void fathest(HashMap<Integer,Set<Integer>> map,int node,int dist){
        if (map.containsKey(node)){
            for (int p:map.get(node)){
                if (dist>max){
                    dist=max;
                    father=p;
                }
                fathest(map,p,dist+1);
            }
        }
    }
}
