package GraphAndSearch;

import java.util.*;

public class Dijkstra {
    public static void main(String[] args){
        int[][] edges={{0,1,10},{0,3,30},{0,4,99},{1,2,50},{1,3,40},{2,4,10},{3,2,20},{3,4,60}};
        List<Pair> rs=new ArrayList<>();
        Dijkstra dj=new Dijkstra();
        dj.shortest(edges,0,5);
    }
    //9/7/2021 不会写
    //https://www.softwaretestinghelp.com/dijkstras-algorithm-in-java/
    public void  shortest(int[][] edges, int source,int num){
        HashMap<Integer,List<Pair>> map=new HashMap<>();
        for (int[] e:edges){
            if (!map.containsKey(e[0])){
                map.put(e[0],new ArrayList<>());
            }
            map.get(e[0]).add(new Pair(e[1],e[2]));
        }
        PriorityQueue<Pair> pq=new PriorityQueue<Pair>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.cost-o2.cost;
            }
        });
        int[] path=new int[num];//用来dfs找终点到原点的路径
        Set<Integer> set=new HashSet<>();
        int[] dist=new int[num];//代表source到i点的距离
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source]=0;
        List<Pair> rs=new ArrayList<>();
        pq.offer(new Pair(source,0));
        while (set.size()<num){//注意不是用pq。isempty来判断，
            Pair p=pq.poll();
            set.add(p.vertex);
            if (map.containsKey(p.vertex)){//不写这个的话，没邻居的节点会npe
                for (Pair nei:map.get(p.vertex)){
                    if (!set.contains(nei)){
                        int temp=dist[p.vertex]+nei.cost;//这里算法要搞清楚，每次while循环poll出来的点，其实就是已经确定是最短的了，而这里再取更新这个点
                        if (dist[nei.vertex]>temp){//的所有邻居，看是不是更短，放进pq里，更新路径。这里是pq poll出来才设置set。add，而不是这里就加进set
                            dist[nei.vertex]=temp;
                            path[nei.vertex]=p.vertex;
                        }
                        pq.offer(nei);
                    }
                }
            }
        }
        for (int i=0;i<dist.length;i++){//打印路径，这里写的有点丑
            System.out.print("Source: "+i +" :");
            StringBuilder sb=new StringBuilder();
            int j=i;
            while (path[j]!=source){
                sb.insert(0,path[j]+" ,");
                j=path[j];
            }
            sb.insert(0," "+source);
            sb.append(" ,"+i);
            System.out.println(sb.toString());
        }
        for (int i=0;i<dist.length;i++){
            System.out.println("Source: "+i +" :"+dist[i]);
        }


    }
    class Pair{
        int vertex;
        int cost;
        public Pair(int v,int c){
            vertex=v;
            cost=c;
        }
    }
}
