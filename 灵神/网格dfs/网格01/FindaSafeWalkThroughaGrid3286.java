package 灵神.网格dfs.网格01;

import java.util.*;

public class FindaSafeWalkThroughaGrid3286 {
    public static void main(String[] args) {
        List<List<Integer>> al=new ArrayList<>();
        List<Integer> a1=new ArrayList<>();
        List<Integer> a2=new ArrayList<>();
        a1.add(0);
        a1.add(0);
        a2.add(1);
        a2.add(1);
        al.add(a1);
        al.add(a2);
        System.out.println(findSafeWalk2(al,1));
    }
    //和1293同样的方法，三维memo数组也能做？
    //https://leetcode.cn/problems/find-a-safe-walk-through-a-grid/solutions/2917627/0-1-bfs-xian-xing-zuo-fa-pythonjavacgo-b-zlzq/
//    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
//
//    }

    //1/24/2026,想的得用dijistra，改了好多次然后超时了，主要是这个邻居的距离（0还是1）应该是当前距离加邻居的（0或1）这里没想清楚
    public static boolean findSafeWalk2(List<List<Integer>> grid, int health) {
        int m=grid.size();
        int n=grid.get(0).size();
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[2]-b[2]);
        pq.offer(new int[]{0,0,grid.get(0).get(0)});
        HashMap<Integer,Integer> dist=new HashMap<>();
        dist.put(0,grid.get(0).get(0));
        while (!pq.isEmpty()){
            int[] cur=pq.poll();
            int r=cur[0];
            int c=cur[1];
            if(dist.containsKey(r*n+c)&&dist.get(r*n+c)<cur[2]){
                continue;
            }
            if(r==m-1&&c==n-1){
                return cur[2]<health;
            }
            int[] dx=new int[]{0,-1,0,1};
            int[] dy=new int[]{1,0,-1,0};
            for (int i=0;i<4;i++){
                int nx=dx[i]+r;
                int ny=dy[i]+c;
                int key=nx*n+ny;
                if(nx<0||nx>=m||ny<0||ny>=n){
                    continue;
                }
                //这里也不好想，之前写的dist.get(key)>cur[2]（到当前点的距离）就会超时，应该是到下一个点的距离
                if(!dist.containsKey(key)||dist.get(key)>cur[2]+grid.get(nx).get(ny)){
                    pq.offer(new int[]{nx,ny,cur[2]+grid.get(nx).get(ny)});
                    dist.put(key,cur[2]+grid.get(nx).get(ny));
                }
            }
        }

        return false;
    }
    //再写写0-1bfs，就是把priorityqueue简化成dequeue，0的点方前面，1的点放后面
    public static boolean findSafeWalk3(List<List<Integer>> grid, int health) {
        Deque<int[]> q=new LinkedList<>();
        int m=grid.size();
        int n=grid.get(0).size();
        int[][] dist=new int[m][n];
        for (int i=0;i<dist.length;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        q.offerFirst(new int[]{0,0,grid.get(0).get(0)});//存的其实就是0，0到这一点的health值
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        while (!q.isEmpty()){
            int[] cur=q.poll();
            int row=cur[0];
            int col=cur[1];
            if(cur[2]>=health){
                continue;
            }
            for (int i=0;i<4;i++){
                int nx=row+dx[i];
                int ny=col+dy[i];
                //这个dist[nx][ny]>cur[2]+grid.get(nx).get(ny)还是要检测的,相当于relexation？否则就不是dijistra了
                if(nx>=0&&nx<m&&ny>=0&&ny<n&&dist[nx][ny]>cur[2]+grid.get(nx).get(ny)){
                    if (grid.get(nx).get(ny)==0){
                        q.offerFirst(new int[]{nx,ny,cur[2]});
                        dist[nx][ny]=cur[2];
                    }else{
                        q.offerLast(new int[]{nx,ny,cur[2]+1});
                        dist[nx][ny]=cur[2]+1;
                    }
                }
            }
        }
        return dist[m-1][n-1]<health;
    }
}
