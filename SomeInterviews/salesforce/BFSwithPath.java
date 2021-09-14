package SomeInterviews.salesforce;

import java.util.*;

public class BFSwithPath {
    public static void main(String[] args) {
        BFSwithPath bp = new BFSwithPath();
        int[][] m = {{0, 0, 0, 0},
                     {0, 0, 0, 0},
                     {0, 1, 0, 1},
                     {1, 0, 0, 0}};
        bp.shortestDistance(m,new int[]{3,3});
    }
//https://www.1point3acres.com/bbs/thread-777424-1-1.html
    //做法就是建一个新的matrix，对于每一个点，记录他的父节点。每一个点其实就是当前节点的四个方向的邻居，这四个邻居都把当前点当父亲就行了，由于每个点只是访问一次的，所以不会被
    //overwrite，最后从target那个点dfs找他指向的父亲点就行了
    public List<int[]> shortestDistance(int[][] m, int[] target) {
        int[][][] path = new int[m.length][m[0].length][2];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        boolean[][] memo = new boolean[m.length][m[0].length];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        memo[0][0] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                if (cur[0] == target[0] && cur[1] == target[1]) {
                   findPath(new int[]{0,0},target, path);
                    Collections.reverse(rs);
                    rs.add(target);
                    for (int j=0;j<rs.size();j++){
                        System.out.println(rs.get(j)[0]+" "+rs.get(j)[1]);
                    }
                    return rs;

                }
                for (int j = 0; j < 4; j++) {
                    int r = cur[0] + dx[j];
                    int c = cur[1] + dy[j];
                    if (r >= 0 && r < m.length && c >= 0 && c < m[0].length && memo[r][c] == false&&m[r][c]==0) {
                        path[r][c][0] = cur[0];
                        path[r][c][1] = cur[1];
                        q.offer(new int[]{r, c});
                        memo[r][c] = true;
                    }
                }
            }

        }
        return null;
    }
    List<int[]> rs=new ArrayList<>();
    void findPath(int[] source,int[] target,int[][][] path){
        int[] p={path[target[0]][target[1]][0],path[target[0]][target[1]][1]};
        if (p[0]==source[0]&&p[1]==source[1]){
            return;
        }
        rs.add(p);
        findPath(source,p,path);
    }
}
