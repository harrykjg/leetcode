import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 7/18/18.
 */
public class IsGraphBipartite {
    public static void main(String[] a){
        IsGraphBipartite ig=new IsGraphBipartite();
        int[][] m={{1},{0,3},{3},{1,2}};
        System.out.print(ig.isBipartite4(m));
    }
    //不会，看别人的思路才知道，而且写的时候也不好写，。写法也有点奇怪，for loop里面用queue
    // 一开始写错了，我以为只要遍历graph，看每个节点的邻居和他自己的颜色是否不等就行了，那样其实有问题，
    //比如{{1},{0,3},{3},{1,2}};，来到节点2时，发现没人访问过他，就先把他染色了，其实2的邻居3在他之前已经染过色了，那么2就不能随便染色而应该看他邻居染色。原因就是从某个节点出发染色但并没有遍历完所有的邻居,所以要dfs或者bfs？
    //https://blog.csdn.net/magicbean2/article/details/79732401
    //https://www.jianshu.com/p/72651093efc6
    //https://blog.csdn.net/mikeyan01/article/details/79715287
    public boolean isBipartite(int[][] graph) {
        HashMap<Integer,Integer> map=new HashMap<>();//用来存端点的颜色
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<graph.length;i++){
            if(map.containsKey(i)){//检测过就continue
                continue;
            }
            q.offer(i);//从i点出发遍历他能走过的所有邻居
            map.put(i,1);
            while (!q.isEmpty()){
                int node=q.poll();//这里的node拿出来其实就是父亲节点，肯定染过色了，不用检测，而是要去检测他的邻居有没染过色，如果染过就看有没有冲突

                for(int j=0;j<graph[node].length;j++){
                    if(map.containsKey(graph[node][j])){//染过就不要加进queue了
                       if(map.get(graph[node][j])==map.get(node)){
                           return false;
                       }
                    }else{
                        map.put(graph[node][j],map.get(node)==1?2:1);
                        q.offer(graph[node][j]);
                    }
                }
            }
        }
        return true;
    }
//dfs,写的也有点奇怪，每次dfs 新建一个memo，用于防止每次dfs走回头路，他们的dfs不用这样，他们的不怎么看得懂
    public boolean isBipartiteDFS(int[][] graph) {
        HashMap<Integer,Integer> map=new HashMap<>();//用来存端点的颜色
        for(int i=0;i<graph.length;i++){
            boolean[] memo=new boolean[graph.length];
            boolean flag=dfs(map,graph,i,memo);
            if(!flag){
                return false;
            }
        }
        return true;
    }
    boolean dfs(HashMap<Integer,Integer> map, int[][] graph,int node,boolean[] memo){
        memo[node]=true;
        if(!map.containsKey(node)){
            map.put(node,1);
        }
        int colour=map.get(node);
        for (int i=0;i<graph[node].length;i++){
            if(!map.containsKey(graph[node][i])){
                map.put(graph[node][i],colour==1?2:1);
            }else{
                if(map.get(graph[node][i])==colour){
                    return false;
                }
            }
            if(!memo[graph[node][i]]){
                dfs(map,graph,graph[node][i],memo);
            }
        }
        return true;
    }
    //9/24/2018,还是不行，以为要写一个node 类,那样要重写hashcode和equal方法，搞得比较麻烦.并且还是没有实际理解
    public boolean isBipartite2(int[][] graph) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<graph.length;i++){
            map.put(i,-1);
        }
        HashSet<Integer> set=new HashSet<>();
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<graph.length;i++){
            if(map.get(i)!=-1){//如果已经染色了，则和他所有的联通的点都染色了并且没问题，因为如果有问题的话，在遍历他的所有的联通的点的时候就会有冲突，画图看看，不太好想
                continue;
            }
//            map.put(i,0);//先不在这里染色
            set.add(i);
            q.offer(i);
            while (!q.isEmpty()){
                int cur=q.poll();
                if(map.get(cur)==-1){
                    map.put(cur,0);
                }
                int curcol=map.get(cur);
                int[] nei=graph[cur];//注意这里容易写错成i
                for(int n:nei){
                    if(map.get(n)==-1){
                        map.put(n,curcol==1?0:1);
                    }
                    if(map.get(n)==curcol){
                        return false;
                    }
                    if(!set.contains(n)){
                        q.offer(n);
                        set.add(n);
                    }
                }
            }
        }
        return true;
    }
    //不用memo记录的,因为map也用作memo的作用了
    public boolean isBipartite3(int[][] graph) {
        HashMap<Integer,Integer> map=new HashMap<>();
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<graph.length;i++){
            if(map.containsKey(i)){
                continue;
            }
//            map.put(i,0);
            q.offer(i);
            while (!q.isEmpty()){
                int cur=q.poll();
                if(!map.containsKey(cur)){
                    map.put(cur,0);
                }
                int curcol=map.get(cur);
                int[] nei=graph[cur];
                for(int n:nei){
                    if(!map.containsKey(n)){
                        map.put(n,curcol==1?0:1);
                        q.offer(n);

                    }else{
                        if(map.get(n)==curcol){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    //9/29/2018,大概记得
    public boolean isBipartite4(int[][] graph) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<graph.length;i++){
            if(!map.containsKey(i)){
                Queue<Integer> q=new LinkedList<>();//这次我是分多个q的，一个queue的也行
                q.offer(i);
                while (!q.isEmpty()){
                    int cur=q.poll();
                    if(!map.containsKey(cur)){
                        map.put(cur,0);
                    }
                    int colour=map.get(cur);
                    for(int nei:graph[cur]){
                        if(!map.containsKey(nei)){
                            map.put(nei,colour==0?1:0);
                            q.offer(nei);
                        }else{
                            if(map.get(nei)==colour){
                                return false;
                            }
                        }
                    }
                }
            }

        }
        return true;
    }

}
