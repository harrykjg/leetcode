package 灵神.图论.dijistra;

import java.util.Arrays;

public class CheapestFlightsWithinKStops {
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
}
