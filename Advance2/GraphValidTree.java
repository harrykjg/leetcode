package Advance2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 502575560 on 7/18/17.
 */
public class GraphValidTree {
    //不会,
    //https://segmentfault.com/a/1190000003791051 思路就是并查集,所有点开始都有根(初始值就是它自己),然后一个个遍历每个pair,如果这个pair中的两个点的根节点相同的话
    //,就说明有环,具体举个例子[1, 2], [2, 3], [1, 3]按它的思路试试(把前一个数的根设为pair里后一个数),其实它是用了原理那个链接的"牵一发而动全身"的算法(lintcode上要
    // 19秒),我觉得第二个链接好理解一点
    //
    //http://www.cnblogs.com/grandyang/p/5257919.html  还讲了dfs,bfs法,就是从一个点开始出发,一个一个往下走,如果遍历到一个已经访问过的点,说明有环,
    //,如果最后有节点没遍历到则说明不相连,也不能成为tree
    //第二个链接并查集思路是开始每个点根都是-1,然后再逐个赋值,如果发现某个点已经有根节点了就说明有环了，
    public static void main(String[] args){
        int[][] e=new int[][]{{0,1},{5,6},{6,7},{9,0},{3,7},{4,8},{1,8},{5,2},{5,7}};
        System.out.println(validTree(10,e));
    }
    //这个写法不知道对不对，尽管lintcode是accept了
    public static boolean validTree(int n, int[][] edges) {
        // Write your code here
        int[] ids=new int[n];
        Arrays.fill(ids,-1);
        for(int i=0;i<edges.length;i++){
            int first=edges[i][0];
            int sec=edges[i][1];
            int idf=find(ids,first);//注意这里其实和numberofislandsII不一样,那里的这一位置其实已经有了id值,再去合并,而这里是去找id值,然后再合并
            int idsec=find(ids,sec);
            ids[first]=idf;
            ids[sec]=idsec;
            if(idf==idsec){
                return false;
            }
            ids[idsec]=idf;//注意这里是先找到了sec的根节点，再把sec的根节点的根改变了，才是union方法了,以前一直以为直接ids[sec]=idf，那样是错的
        }
        return edges.length==n-1;//题目假设了edges不会有重复,所以edges应该刚好是n-1个,多了或者少了都不行,而链接1的是用count去算的,就是脱离了题目的假设也可以

    }
    static int find(int[] ids, int p){
        if(ids[p]==-1){//还没初始化
            return p;
        }
        while (ids[p]!=p){
            ids[p]=ids[ids[p]];//压缩路径没太想明白,不要也对
            p=ids[p];
        }
        return p;
    }
//九章第二轮，知道是并查集之后,以为写起来还可以，结果ids[root2]=root1那里写成了ids[edges[i][1]],那样就是改了子节点的根节点，而不是改根节点的父节点，卡了很久
// 还有，不知道如果是两个分开的树没有连在一起的情况是如何判断，还是看回第一个链接的写法，用了count
    public static boolean validTree2(int n, int[][] edges) {
        if(edges.length==0){
            if(n>1){//n大于1则说明是分开的几个点，那么也不是树了
                return false;
            }
            return true;
        }
        int[] ids=new int[n];
        for(int i=0;i<ids.length;i++){
            ids[i]=i;
        }
        for(int i=0;i<edges.length;i++){
            int root1=find2(ids,edges[i][0]);
            int root2=find2(ids,edges[i][1]);
            if(root1==root2){
                return false;
            }
            ids[root2]=root1 ;
        }
        return edges.length==n-1;//这里有可能是两个分开的树没有连载一起，那么也不是树
    }
    static int find2(int[] ids,int i){
        if(ids[i]==i){
            return i;
        }
        return ids[i]=find2(ids,ids[i]);
    }
//7/12/2018,开始还是想的是拓扑排序，但是看他第二个例子，[[0,1], [1,2], [2,3], [1,3], [1,4]]貌似拓扑排序能找到一个一笔画的路径，和树没啥关系。而且也没说要是二叉树，
    //所以说就是检测有没有环,但是还不够，还有可能是两个分开的树，那样的话只要从任意节点出发，能访问所有节点才对
//  https://www.cnblogs.com/TenosDoIt/p/3644225.html 这个帖子说如何检测图有无环
    //dfs版本
    public boolean validTree3(int n, int[][] edges) {
        if(n==1&&edges.length==0){
            return true;
        }
        boolean[] memo=new boolean[n];
        HashMap<Integer,Set<Integer>> map=new HashMap<>();
        for(int[] edge:edges){
            int a=edge[0];
            int b=edge[1];
            if(!map.containsKey(a)){
                HashSet<Integer> set=new HashSet<>();
                set.add(b);
                map.put(a,set);
            }else{
                map.get(a).add(b);
            }
            if(!map.containsKey(b)){
                HashSet<Integer> set=new HashSet<>();
                set.add(a);
                map.put(b,set);
            }else{
                map.get(b).add(a);
            }
        }
        memo[0]=true;
        if(!dfs(0,map,memo)){
            return false;
        };
        for(int i=0;i<memo.length;i++){
            if(memo[i]==false){
                return false;
            }

        }
        return true;

    }
    boolean dfs(int i,HashMap<Integer,Set<Integer>> map,boolean[] memo){//dfs写的不顺
        if(!map.containsKey(i)){
            return false;
        }
        for(int nei:map.get(i)){
            if (memo[nei]==true){
                return false;
            }else{
                memo[nei]=true;
                map.get(nei).remove(i);//开始这里忘了写这个，比如从1到了2，现在2的邻居也有1，再走回去的话那就不对了，所以要把1从2的邻居里删掉
                if(!dfs(nei,map,memo)){
                    return false;
                };
            }
        }
        return true;
    }

    public boolean validTree4(int n, int[][] edges) {

    }
}
