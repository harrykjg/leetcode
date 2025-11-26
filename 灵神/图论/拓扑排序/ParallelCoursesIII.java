package 灵神.图论.拓扑排序;

import com.sun.jdi.IntegerValue;

import java.util.*;

public class ParallelCoursesIII {
    public static void main(String[] args) {

    }
//这里开始想错了，不是每一层的max加起来就行了。比如[3,6],[4,6],[7,6],[4,1],[6,9],[7,9]，n=9，time=[9,5,9,5,8,7,7,8,4]，画图发现第二层需要访问1,6,8，其中1需要9小时，6需要7小时，
    //然后6还有下一个节点9，那么1的确需要9小时，但是6用了7小时之后就可以立马进入9节点开始。因此并不是要等9小时才能开始。
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
                dp[cur]=Math.max(dp[cur],time[cur-1]);
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

}
