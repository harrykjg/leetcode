package 灵神.图论.dijistra;

import java.util.*;

public class PathwithMaximumProbability1514 {
    public static void main(String[] args) {

    }
    //就是他是doble的priorityqueue 的comparator有点难写
    //https://leetcode.cn/problems/path-with-maximum-probability/solutions/2819954/dai-ni-tui-dao-chu-dijkstra-suan-fa-zai-i67wr/
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {

        Map<Integer, List<double[]>> map=new HashMap<>();
        for(int i=0;i<edges.length;i++){
            map.putIfAbsent(edges[i][0],new ArrayList<>());
            map.get(edges[i][0]).add(new double[]{edges[i][1],succProb[i]});
            map.putIfAbsent(edges[i][1],new ArrayList<>());
            map.get(edges[i][1]).add(new double[]{edges[i][0],succProb[i]});
        }
        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble((double[] p) -> -p[1]));
        pq.offer(new double[]{start_node,1});
        double[] dist=new double[n];
        Arrays.fill(dist,0);
        while (!pq.isEmpty()){
            double[] cur=pq.poll();
            int curNode=(int) cur[0];
            double curProb=cur[1];
            if(dist[curNode]>curProb){
                continue;
            }
            List<double[]> neighbour=map.get(curNode);
            if(neighbour!=null){
                for(double[] nei:neighbour){
                    double nextProb=nei[1];
                    int nextNode=(int)nei[0];
                    if(nextProb*curProb>dist[nextNode]){
                        dist[nextNode]=nextProb*curProb;
                        pq.offer(new double[]{nextNode,dist[nextNode]});
                    }
                }
            }
        }
        return dist[end_node];
    }


}

