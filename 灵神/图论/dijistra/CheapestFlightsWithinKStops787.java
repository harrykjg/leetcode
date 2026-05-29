package 灵神.图论.dijistra;

import java.util.*;

public class CheapestFlightsWithinKStops787 {
    public static void main(String[] args) {
        int[][] f={{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        System.out.println(findCheapestPrice(4,f,0,3,1));
    }
    //不会
    //用bellman ford
    //https://leetcode.cn/problems/cheapest-flights-within-k-stops/solutions/955290/gong-shui-san-xie-xiang-jie-bellman-ford-dc94/ 最后那个
    //也参考了gpt的解释
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        for(int i=0;i<k+1;i++){//k+1代表最多用k+1条边，就是中间最多有k个stop
            int[] curdist=dist.clone();//每一次的snapshot
            for (int j=0;j<flights.length;j++){
                int from=flights[j][0];
                int to=flights[j][1];
                int d=flights[j][2];
                if(curdist[from]==Integer.MAX_VALUE){//不加这个的话后面curdist[from]+d就越界变成负数了
                    continue;
                }
                dist[to]=Math.min(dist[to],curdist[from]+d);//注意这里可以更新 curdist【to】的值再在内层for loop结束之后
                // 把curdist assign to dist，或者直接更新dist【to】的值，但是都是用dist【to】的值作为判断
            }
        }
        if(dist[dst]==Integer.MAX_VALUE){
            return -1;
        }
        return dist[dst];
    }

    //2/18/2026以为是dijistra，其实是不行的因为限制了k
    public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
       int[] dist=new int[n];
       Arrays.fill(dist,Integer.MAX_VALUE);
       dist[src]=0;
       for (int i=0;i<k+1;i++){
           int[] curdist=Arrays.copyOf(dist,dist.length);//开始写错了写成里面了
           for (int j=0;j<flights.length;j++){
               int from=flights[j][0];
               int to=flights[j][1];
               int d=flights[j][2];
               if(curdist[from]==Integer.MAX_VALUE){
                   continue;
               }
               if(dist[to]>curdist[from]+d){//注意是那原来的dist来赋值
                   dist[to]=curdist[from]+d;
               }
           }
       }
       return dist[dst]==Integer.MAX_VALUE?-1:dist[dst];
    }

    //3/8/2026 还是不会bellman ford，怎么他for loop就能找到最短距离不好想
    public static int findCheapestPrice3(int n, int[][] flights, int src, int dst, int k) {
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        for (int i=0;i<k+1;i++){
            int[] cur=Arrays.copyOf(dist,dist.length);
            for (int j=0;j<flights.length;j++){
                int from=flights[j][0];
                int to=flights[j][1];
                int d=flights[j][2];
                if(cur[from]==Integer.MAX_VALUE){
                    continue;
                }
                if(dist[to]>cur[from]+d){
                    dist[to]=cur[from]+d;
                }
            }
        }
        return dist[dst]==Integer.MAX_VALUE?-1:dist[dst];
    }

    //5/20/2026还是不会，真是神奇
    /*
    用一个具体小例子直观感受
        src=0
        航班：0->1 (100), 1->2 (100), 0->2 (500)
        k=1（允许 1 个中转）⇒ 最多 2 条边
        初始化：dist = [0, INF, INF]
        第 1 轮（允许 1 条边）
        curDist=[0,INF,INF]
        松弛 0->1：dist[1]=100
        松弛 1->2：curDist[1] 是 INF（因为这一轮不允许用刚得到的 100）⇒ 不更新
        松弛 0->2：dist[2]=500
        结束：dist 表示最多 1 条边的最短价：到 2 是 500（直飞）
        第 2 轮（允许 2 条边）
        curDist=[0,100,500]
        松弛 1->2：dist[2] = min(500, 100+100)=200
        结束：dist 表示最多 2 条边的最短价：到 2 是 200（0->1->2）
     */
    public static int findCheapestPrice4(int n, int[][] flights, int src, int dst, int k) {
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        for (int i=0;i<k+1;i++){//想如果k=1的话，就是可以有一个中砖，即src--》中间--》dst，就是两条边，如果k=0那么也得for循环一次吧
            int[] curDist=Arrays.copyOf(dist,dist.length);//他说这个dist[]其实意义是用i个中转站情况下到每个点的距离，因此当前层中
            for (int j=0;j<flights.length;j++){//这个curDist[from]会是上一层用最多用i-1个中转点的情况下到from的距离，看下面update是
                int from=flights[j][0]; //update到dist[]数组里而不是update到当前curDist里
                int to=flights[j][1];
                int d=flights[j][2];
                if(curDist[from]==Integer.MAX_VALUE){
                    continue;
                }
                if(dist[to]>curDist[from]+d){//巧妙的是from如果就是本来这一点的话也对，那么内层forloop无非就是遍历现有的flight
                    dist[to]=curDist[from]+d; //然后填上直达的航班而已。为啥加上外层k+1次就行？应该把这个现有的flight想成是一个中转的
                }//的岔路，dist[to]=src到from+from到to这个d的距离
            }
        }
        return dist[dst]==Integer.MAX_VALUE?-1:dist[dst];
    }
}
