package 灵神.图论.拓扑排序;

import java.util.*;

public class ParallelCoursesIII2050 {
    public static void main(String[] args) {

    }
//这里开始想错了，不是每一层的max加起来就行了。比如[3,6],[4,6],[7,6],[4,1],[6,9],[7,9]，n=9，time=[9,5,9,5,8,7,7,8,4]，画图发现
// 第二层需要访问1,6,8，其中1需要9小时，6需要7小时，然后6还有下一个节点9，那么1的确需要9小时，但是6用了7小时之后就可以立马进入9节点开始。因此
// 要访问9只需要9的这条路径完成就可以访问，而1不在9的路径上面所以不用考虑
    //
    //https://leetcode.com/problems/parallel-courses-iii/solutions/4180416/topological-sort-using-kahns-algorithm-e-v04y/
    //实际上是
    public int minimumTime(int n, int[][] relations, int[] time) {
        int rs=0;
        Map<Integer, Set<Integer>> map=new HashMap<>();
        Map<Integer,Integer> indegree=new HashMap<>();
        for(int i=0;i<relations.length;i++){
            map.putIfAbsent(relations[i][0],new HashSet<>());
            map.get(relations[i][0]).add(relations[i][1]);
            indegree.put(relations[i][1],indegree.getOrDefault(relations[i][1],0)+1);
        }
        Queue<Integer> q=new LinkedList<>();
        for (int i=1;i<=n;i++){
            if(!indegree.containsKey(i)){
                q.offer(i);
            }
        }
        int[] dp=new int[n+1];
//        for (int i=1;i<time.length;i++){
//            dp[i]=time[i-1];
//        }
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int cur=q.poll();
                dp[cur]=Math.max(dp[cur],time[cur-1]);//要取最大的那个
                Set<Integer> neighbour=map.get(cur);
                if (neighbour==null){
                    continue;
                }
                int neiMax=0;
                for(int nei:neighbour){
                    dp[nei]=Math.max(dp[nei],dp[cur]+time[nei-1]);//关键是这里，实际上这个nei可能会被别的pre客更新，有点反直觉。因此他其实是包含前面路径的值，所以结果要看他的最大值
                    rs=Math.max(rs,dp[nei]);
                    indegree.put(nei,indegree.get(nei)-1);
                    if(indegree.get(nei)==0){
                        q.offer(nei);
                    }
                }

            }

        }


        return rs;

    }

    //3/1/2026 我还以为实际就是有向图求多个出发点到各个点最短距离？那么还要判断谁是终点？应该不是
    //还是不会，懒得写
    public int minimumTime2(int n, int[][] relations, int[] time) {


    }
    //5/24/2026还是不会，难点在于当遇到一个点的时候，他可能是由不同路径走过来的，那怎么怎么找最大的那条呢，看回以前的就是用一个数组记录每个点的
    //当前的最短距离，每次遇到某个点时算一下（第一次算），并且把这个点的邻居都算一遍（到这个点的距离加到邻居点的距离）
    public int minimumTime3(int n, int[][] relations, int[] time) {
        int[] indegree=new int[n+1];
        Map<Integer,PriorityQueue<Integer>> map=new HashMap<>();
        for (int i=0;i<relations.length;i++){
            indegree[relations[i][1]]++;
            map.putIfAbsent(relations[i][0],new PriorityQueue<>());//这个写pq是没用的，用set就行
            map.get(relations[i][0]).add(relations[i][1]);
        }
        Queue<Integer> q=new LinkedList<>();
        for (int i=1;i<indegree.length;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }

        int[] dist=new int[n+1];
        int rs=0;

        while (!q.isEmpty()){
            int cur=q.poll();
            dist[cur]=Math.max(dist[cur],time[cur-1]);
            PriorityQueue<Integer> pq=map.get(cur);
            if(pq!=null){
                while (!pq.isEmpty()){
                    int nei=pq.poll();
                    dist[nei]=Math.max(dist[nei],dist[cur]+time[nei-1]);
                    indegree[nei]--;
                    if(indegree[nei]==0){
                        q.offer(nei);
                    }
                }
            }
        }
        for (int i=1;i<dist.length;i++){
            rs=Math.max(rs,dist[i]);
        }

        return rs;
    }

}
