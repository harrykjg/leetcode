import java.util.*;

/**
 * Created by yufengzhu on 7/3/18.
 */
public class BusRoutes {
    public static void main(String[] args){
        BusRoutes br=new BusRoutes();
        int[][] r={{1,2,7},{3,6,7}};
        br.numBusesToDestination(r,1,6);
    }
    //自己写的，先创造图的节点，然后bfs，结果超时。而第二个方法就不超时了，我觉得因为第一个方法这里构造图的时候有3重循环导致慢了，第二个方法巧妙就在
    //没有完全构造一般理解上的图，某个点的邻居用bus的编号来替代了，在queue。poll的时候才拿出来遍历，不知道为啥就快了，难道不是一样吗
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S==T){
            return 0;
        }
        HashMap<Integer,point> map=new HashMap<>();
        for(int i=0;i<routes.length;i++){
            for(int j=0;j<routes[i].length;j++){
                int temp=routes[i][j];

                if(!map.containsKey(temp)){
                    point p=new point(temp);
                    map.put(temp,p);
                }
                for(int k=0;k<routes[i].length;k++){
                    if(k==j){
                        continue;
                    }
                    if(map.containsKey(routes[i][k])){
                        map.get(temp).nei.add(map.get(routes[i][k]));
                    }else{
                        point p2=new point(routes[i][k]);
                        map.put(routes[i][k],p2);
                        map.get(temp).nei.add(p2);
                    }
                }
            }
        }
        Set<Integer> memo=new HashSet<>();
        if(!map.containsKey(S)){
            return -1;
        }
        if(!map.containsKey(T)){
            return -1;
        }
        point end=map.get(T);
        int rs=0;
        int count1=1;
        int count2=0;
        Queue<point> q=new LinkedList<>();
        q.add(map.get(S));
        while (!q.isEmpty()){
            point cur=q.poll();
            memo.add(cur.n);
            count1--;
            if(cur.nei.contains(end)){
                return rs+1;
            }
            for(point nn:cur.nei){
                if(!memo.contains(nn.n)){
                    q.offer(nn);
                    memo.add(nn.n);
                    count2++;
                }
            }
            if(count1==0){
                rs++;
                count1=count2;
                count2=0;
            }
        }
        return -1;
    }
//https://www.jianshu.com/p/4d08439d6419 他们的思路都是把bus整个route作为一个邻居,然后按自己理解写的还是超时
    //https://leetcode.com/problems/bus-routes/discuss/122712/Simple-Java-Solution-using-BFS
    //https://blog.csdn.net/laputafallen/article/details/79859416
    public int numBusesToDestination2(int[][] routes, int S, int T) {
        if(S==T){
            return 0;
        }
        HashMap<Integer,Set<Integer>> map=new HashMap<>();
        for(int i=0;i<routes.length;i++){
            for(int j=0;j<routes[i].length;j++){
                int temp=routes[i][j];
                if(!map.containsKey(temp)){
                    map.put(temp,new HashSet<>());
                }
                map.get(temp).add(i);//map就代表这个站点可以上bus的数量和编号
            }
        }
        Set<Integer> memo=new HashSet<>();
        if(!map.containsKey(S)||!map.containsKey(T)){
            return -1;
        }

        int rs=0;
        int count1=1;
        int count2=0;
        Queue<Integer> q=new LinkedList<>();
        q.add(S);
        while (!q.isEmpty()){
            int cur=q.poll();
            Set<Integer> bus=map.get(cur);//拿到这个cur站点能上的bus，和bus能去的地方，然后遍历这些地方，有T就返回，没有的话就继续把这个bus能取得地方都加进queue里
            count1--;
            memo.add(cur);
            Iterator<Integer> it=bus.iterator();
            while (it.hasNext()){
                int temp=it.next();
                for(int i=0;i<routes[temp].length;i++){
                    if(routes[temp][i]==T){
                        return rs+1;
                    }
                    if(!memo.contains(routes[temp][i])){
                        q.offer(routes[temp][i]);
                        memo.add(routes[temp][i]);//之前是少了这个条件，否则会超时！
                        count2++;
                    }
                }
            }

            if(count1==0){
                rs++;
                count1=count2;
                count2=0;
            }
        }
        return -1;
    }
}
class point{
    int n;
    Set<point> nei;
    public point(int n){
        this.n=n;
        nei=new HashSet<>();
    }
    public point(int n,Set<point> set){
        this.n=n;
        nei=set;
    }
}
