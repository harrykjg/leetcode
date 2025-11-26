package 灵神.图论.dijistra;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindMinimumTimetoReachLastRoomI3341 {
    public static void main(String[] args) {
        int[][] m={{17,56},{97,80}};
        System.out.println(minTimeToReach(m));

    }
    public static int minTimeToReach(int[][] moveTime) {
        int[][] dist=new int[moveTime.length][moveTime[0].length];
        for (int[] i:dist){
            Arrays.fill(i,Integer.MAX_VALUE);
        }
        dist[0][0]=0;
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[2]-b[2]);
        pq.offer(new int[]{0,0,0});
        while (!pq.isEmpty()){
            int[] cur=pq.poll();
            int curRow=cur[0];
            int curCol=cur[1];
            int curDist=cur[2];
            if(curDist>dist[curRow][curCol]){
                continue;
            }
            int[] dx={0,1,0,-1};
            int[] dy={1,0,-1,0};
            for(int i=0;i<dx.length;i++){
                int nextr=curRow+dx[i];
                int nextc=curCol+dy[i];
                if(nextr>=0&&nextr<moveTime.length&&nextc>=0&&nextc<moveTime[0].length){
                    int nextDist=0;
                    if(moveTime[nextr][nextc]<=curDist){ //这里开始漏了，这里就是比如下一点是5，但是本来cur已经是6了，因此不用等下一点
                        nextDist=0;
                    }else{
                        nextDist=moveTime[nextr][nextc]-curDist;//否则就是等下一点减去这一点已经等了的时间
                    }
                    //还是按dijstra的写法
                    if(dist[nextr][nextc]>nextDist+curDist+1){
                        dist[nextr][nextc]=nextDist+curDist+1;
                        pq.offer(new int[]{nextr,nextc,dist[nextr][nextc]});
                    }
                }

            }

        }

        return dist[dist.length-1][dist[0].length-1];
    }
}
