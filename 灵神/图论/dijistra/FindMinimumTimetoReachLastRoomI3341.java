package 灵神.图论.dijistra;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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

    //2/19/2026不是bfs找q里最小的邻居,那样的话就是贪心找最小的那个邻居，但可能有邻居现在不是最小，但是走了那条路之后更快大道终点的道路
    //dijistra写的不顺，因为不太好想relextion 是怎么弄的，
    public static int minTimeToReach2(int[][] moveTime) {
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[2]-b[2]);
        int[][] dist=new int[moveTime.length][moveTime[0].length];
        for (int[] i:dist){
            Arrays.fill(i,Integer.MAX_VALUE);
        }
        dist[0][0]=0;
        pq.offer(new int[]{0,0,0});
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        while (!pq.isEmpty()){
            int[] cur=pq.poll();
            int row=cur[0];
            int col=cur[1];
            int d=cur[2];
            if(d>dist[row][col]){
                continue;
            }
            //现在问题来了，怎么relex？应该是到当前row，col这一点目前是最短的，那么要看别的边能不能缩短，那谁是别的边呢？
            // 就应该是这个row，col四周的点，那四周的点的距离是dist数组里有，但是从row，col到邻居的距离是多少呢？应该就是1+moveTime[r][c]
            for (int i=0;i<moveTime.length;i++){
                for (int j=0;j<moveTime[0].length;j++){
                    for (int k=0;k<4;k++){
                        int r=cur[0]+dx[k];
                        int c=cur[1]+dy[k];
                        //貌似dijistra不需要memo，因为到某点的距离可以重复relex？那么比如从a点走到b，当下个循环到b的时候，又会把
                        //a放进q吗？
                        if(r>=0&&r<moveTime.length&&c>=0&&c<moveTime[0].length){
                            int nextMove=Math.max(d,moveTime[r][c]);//这里不是很好想,要看现在row，col这已经达到的时间是不是已经大于moveTime[r][c])
                            if(dist[r][c]>1+nextMove){
                                dist[r][c]=1+nextMove;
                                pq.offer(new int[]{r,c,dist[r][c]});
                            }


                        }
                    }
                }
            }

        }
        return dist[dist.length-1][dist[0].length-1];
    }
}
