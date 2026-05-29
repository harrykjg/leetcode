package 灵神.图论.dijistra;

import java.util.Arrays;
import java.util.Map;

public class FindtheCityWiththSmallestNumberofNeighborsataThresholdDistance1334 {
    public static void main(String[] args) {

    }

    //开始想的是对于每一个node做bfs，问过gpt是不行的，因为bfs不能处理有权重的，因为你把的邻居入队算他的距离，但这个距离可能不是最短的。
    //应该用floyd，就是算多源起点的最短路径的。而bellman ford是算一个起点的
    //写法主要是用二维数组记录i到j点的距离。然后三重for loop，其中外层位k，就是不断那i到k的距离加上k到j的距离试能不能找到更短的i到j的距离
    //// Floyd–Warshall: allow intermediate nodes 0..k
    //        for (int k = 0; k < n; k++) {
    //            for (int i = 0; i < n; i++) {
    //                if (dist[i][k] == INF) continue;    // small pruning
    //                for (int j = 0; j < n; j++) {
    //                    if (dist[k][j] == INF) continue;
    //                    int alt = dist[i][k] + dist[k][j];
    //                    if (alt < dist[i][j]) dist[i][j] = alt;
    //                }
    //            }
    //        }
    //2/27/2026,看gpt和答案
    //https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/solutions/5532364/find-the-city-with-the-smallest-number-o-1sk2/
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dist=new int[n][n];
        for (int[] a:dist){
            Arrays.fill(a,Integer.MAX_VALUE);
        }
        for (int i=0;i<edges.length;i++){
           dist[edges[i][0]][edges[i][1]]= Math.min(dist[edges[i][0]][edges[i][1]],edges[i][2]);
            dist[edges[i][1]][edges[i][0]]= Math.min(dist[edges[i][1]][edges[i][0]],edges[i][2]);//两个方向
        }

        for (int k=0;k<n;k++){//记住这是floyd的固定写法，先固定k作为中转点，遍历所有的dist[i][j]把k作为中转点。而不是外面两层是i，j，里面是中砖点k
            for(int i=0;i<n;i++){
                if(dist[i][k]==Integer.MAX_VALUE){
                    continue;
                }
                for(int j=0;j<n;j++){
                    if(dist[k][j]==Integer.MAX_VALUE){
                        continue;
                    }
                    dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);
                }
            }
        }

        int min=Integer.MAX_VALUE;
        int rs=0;
        for (int i=0;i<n;i++){
            int count=0;
            for (int j=0;j<n;j++){
                if(j==i){
                    continue;
                }
                if(dist[i][j]<=distanceThreshold){
                    count++;
                }
            }
            if(count<=min){
                min=count;
                rs=i;
            }
        }
        return rs;
    }

}
