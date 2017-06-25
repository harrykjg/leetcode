import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by 502575560 on 6/16/17.
 */
public class TrappingRainWaterII {
    //自己回想不出来
    //http://www.cnblogs.com/grandyang/p/5928987.html 自己很难想,有的帖子说和第一题核心思想一样的但我看不大出来,主要是思路,想想为啥bfs只看周围
    //四个临近点就够了.
    //http://blog.csdn.net/mebiuw/article/details/52664669
    //http://blog.csdn.net/qq508618087/article/details/52698175  没看出来怎么是第一题的思维一样的
    public int trapRainWater(int[][] m) {
        if(m.length<3){
            return 0;
        }
        int rs=0;
        int h=Integer.MIN_VALUE;
        PriorityQueue<cord> q=new PriorityQueue<>(new Comparator<cord>() {//(v1,v2)->v1.h - v2.h lamda的写法
            @Override
            public int compare(cord o1, cord o2) {
                return o1.z-o2.z;
            }
        });
        boolean[][] visit=new boolean[m.length][m[0].length];
        for(int i=0;i<m[0].length;i++){
            visit[0][i]=true;
            visit[m.length-1][i]=true;
            q.offer(new cord(0,i,m[0][i]));
            q.offer(new cord(m.length-1,i,m[m.length-1][i]));
        }
        for(int i=1;i<m.length-1;i++){
            visit[i][0]=true;
            visit[i][m[0].length-1]=true;
            q.offer(new cord(i,0,m[i][0]));
            q.offer(new cord(i,m[0].length-1,m[i][m[0].length-1]));
        }
        int[][] dir={{-1,0},{0,1},{1,0},{0,-1}};//顺时针方向某点在m里的上,右,下,左边的点的坐标
        while (!q.isEmpty()){
            cord c=q.poll();
            h=Math.max(h,c.z);
            for(int i=0;i<dir.length;i++){
                int x=c.x+dir[i][0];
                int y=c.y+dir[i][1];

                if(x<0||x>=m.length||y<0||y>=m[0].length||visit[x][y]){
                    continue;
                }
                visit[x][y]=true;
                int z=m[x][y];
                if(z<h){
                    rs+=h-z;
                }
                q.offer(new cord(x,y,z));
            }
        }
        return rs;
    }
    class cord{
        int x;//x对应的是matrix里的行数
        int y;//对应matrix的列
        int z;//对应matrix的x,y位置的值
        public cord(int a, int b, int c){
            x=a;
            y=b;
            z=c;
        }
    }
}
