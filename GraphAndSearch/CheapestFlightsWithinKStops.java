package GraphAndSearch;

import java.util.*;

/**
 * Created by yufengzhu on 7/21/18.
 */
public class CheapestFlightsWithinKStops {
    public static void main(String[] a){
        CheapestFlightsWithinKStops cf=new CheapestFlightsWithinKStops();
//        int[][] f={{0, 1,1}, {0,2,5},{1,2,1},{2,3,1}};
        int[][] f={{0,1,100},{1,2,100},{0,2,500}};
        cf.findCheapestPrice2(3,f,0,2,1);
    }
    //开始想法是dfs，记录步数,结果超时，dfs写的不太熟，还有Dijkstra's，但是忘了怎么写
    int rs=Integer.MAX_VALUE;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        HashMap<Integer,HashSet<Pair>> map=new HashMap<>();
        for(int i=0;i<flights.length;i++){
            int n1=flights[i][0];
            int d=flights[i][1];
            int price=flights[i][2];
            Pair p=new Pair(d,price);
            if(!map.containsKey(n1)){
                HashSet<Pair> set=new HashSet<>();
                set.add(p);
                map.put(n1,set);
            }else{
                map.get(n1).add(p);
            }
        }
        boolean[] memo=new boolean[n];
        memo[src]=true;
        dfs(map,src,dst,K,0,0,memo);
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    void dfs(HashMap<Integer,HashSet<Pair>> map,int src,int dst,int k,int curPrice,int curStop,boolean[] memo){
        if(src==dst){
            rs=Math.min(rs,curPrice);
            return;
        }
        if(curStop>k){//这个curStop>k不好写对，比如0飞到1了，算是一个stop，再飞到2，而2是终点，则还是算一个stop
            return;
        }
        if(!map.containsKey(src)){//直接就没有flight去到src
            return;
        }
        for(Pair p:map.get(src)){

            int d=p.dest;
            int price=p.price;
            if(memo[d]){
                continue;
            }
            memo[d]=true;
            dfs(map,d,dst,k,curPrice+price,curStop+1,memo);
            memo[d]=false;
        }

    }

    class Pair{
        int dest;
        int price;
        public  Pair(int dest,int price){
            this.dest=dest;
            this.price=price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return dest == pair.dest;
        }

        @Override
        public int hashCode() {
            return dest;
        }
    }
//还有Dijkstra,太难写了,写bfs,但是这个bfs也是不常规的，关键这是有权图，不能用memo去判断一个节点是否访问过，而是要通过看该店到src的距离是否比当前已知距离小去判断
    //而且这个bfs像是二叉树层次遍历那样的，一层一层的poll出来，而不是一个个poll出来
    //https://blog.csdn.net/huanghanqian/article/details/79334886  参考他的bfs写的，虽然他的是错的，我改了才对的
    //https://www.cnblogs.com/grandyang/p/9109981.html
    //http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-787-cheapest-flights-within-k-stops/ 动态规划
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        int[][] grap=new int[n][n];
        for(int i=0;i<flights.length;i++){
            int b=flights[i][0];
            int e=flights[i][1];
            int price=flights[i][2];
            grap[b][e]=price;
        }
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        Queue<Integer> q=new LinkedList<>();
        q.offer(src);
        int step=0;
        while (!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int cur=q.poll();//得到当前的端点，再去filights里查，cur到"下一点"的距离，加上src到cur的距离，是否比src到"下一点"的距离小
                for(int[] f:flights){
                    int c=f[0];
                    int d=f[1];
                    if(d==dst){//真恶心，step这里不好写对，如果d是dst的话，那么最后一步不算一步，如果更新的d不是dst，那么就算是一步，所以下面要加上step+1<=K这个条件
                        if(c==cur&&grap[c][d]+dist[c]<dist[d]){//只有符合这样的才加进去q
                            dist[d]=grap[c][d]+dist[c];
                            q.offer(d);
                        }
                    }else{
                        if(step+1<=K&&c==cur&&grap[c][d]+dist[c]<dist[d]){//只有符合这样的才加进去q
                            dist[d]=grap[c][d]+dist[c];
                            q.offer(d);
                        }
                    }

                }
            }
            if(step+1>K){
                break;
            }
            step++;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

}
