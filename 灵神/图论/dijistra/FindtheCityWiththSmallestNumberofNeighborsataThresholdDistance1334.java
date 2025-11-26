package 灵神.图论.dijistra;

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
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

    }

}
