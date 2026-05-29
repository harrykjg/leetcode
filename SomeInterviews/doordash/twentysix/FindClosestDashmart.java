package SomeInterviews.doordash.twentysix;

import java.util.*;

public class FindClosestDashmart {

    //2026
    public int[] getClosestDashmart(char[][] city, int[][] locations) {
        int[] rs=new int[locations.length];
        Arrays.fill(rs,-1);
        int m=city.length;
        int n=city[0].length;
        Queue<int[]> q=new LinkedList<>();
        boolean[][] memo=new boolean[city.length][city[0].length];
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<locations.length;i++){
            int key=locations[i][0]*n+locations[i][1];
            map.put(key,i);
        }
        for (int i=0;i<city.length;i++){
            for (int j=0;j<city[0].length;j++){
                if(city[i][j]=='D'){
                    q.offer(new int[]{i,j});
                    memo[i][j]=true;
                }
            }
        }
        int dist=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                int key=cur[0]*n+cur[1];
                if(map.containsKey(key)){
                    rs[map.get(key)]=dist;
                }
                int[] dx={0,1,0,-1};
                int[] dy={1,0,-1,0};
                for(int j=0;j<4;j++){
                    int r=dx[j]+cur[0];
                    int c=dy[j]+cur[1];
                    if(r>=0&&r<m&&c>=0&&c<n&&!memo[r][c]&&city[r][c]!='X'){
                        q.offer(new int[]{r,c});
                        memo[r][c]=true;
                    }
                }
            }
            dist++;

        }
        return rs;

    }
}
