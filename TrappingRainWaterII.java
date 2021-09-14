import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by 502575560 on 6/16/17.
 */
public class TrappingRainWaterII {
    //自己回想不出来
    //http://www.cnblogs.com/grandyang/p/5928987.html 自己很难想,有的帖子说和第一题核心思想一样的但我看不大出来,主要是思路,想想为啥bfs只看周围
    //四个临近点就够了. 它这个解释有一点貌似不准确,海平面不是逐一上升的,而是看queue里poll出来的元素的高度上升的,可以想象为海平面上升,比如高度到达了
    //某个点四周最矮的那个柱那,就可以理解为水会漫过来填平这个点
    //http://blog.csdn.net/mebiuw/article/details/52664669
    //http://blog.csdn.net/qq508618087/article/details/52698175  没看出来怎么是第一题的思维一样的
    public static void main(String[] a){
        TrappingRainWaterII tr=new TrappingRainWaterII();
        int[][] m={{12,13,0,12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}};

        System.out.println(tr.trapRainWater3(m));
    }
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
        int[][] dir={{-1,0},{0,1},{1,0},{0,-1}};//顺时针方向某点在m里的上,右,下,左边的点的坐标,类似的见numbersofisland2
        while (!q.isEmpty()){
            cord c=q.poll();
            h=Math.max(h,c.z);
            for(int i=0;i<dir.length;i++){//某个点被poll出来后,再看四个方向上的点,可以想想成这一步就是水漫过去的瞬间,漫过去的那些点才计算体积,而不是poll这个点计算
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
        int z;//高度
        public cord(int a, int b, int c){
            x=a;
            y=b;
            z=c;
        }
    }


    //九章第二轮只记得是海平面上升发，但是还是写不出,还是挺难想的，但是写倒不是很难，4／5／2018
    public int trapRainWater2(int[][] m) {
        if(m.length==0||m[0].length==0){
            return 0;
        }
        boolean[][] memo=new boolean[m.length][m[0].length];
        PriorityQueue<cord> pq=new PriorityQueue<>(new Comparator<cord>() {
            @Override
            public int compare(cord o1, cord o2) {//升序，每次出最小的数，最小堆
                return o1.z-o2.z;
            }
        });
        for(int i=0;i<m[0].length;i++){
            cord c=new cord(0,i,m[0][i]);
            memo[0][i]=true;
            pq.offer(c);
            cord c2=new cord(m.length-1,i,m[m.length-1][i]);
            memo[m.length-1][i]=true;
            pq.offer(c2);
        }
        for(int i=1;i<m.length-1;i++){
            cord c=new cord(i,0,m[i][0]);
            pq.offer(c);
            memo[i][0]=true;
            cord c2=new cord(i,m[0].length-1,m[i][m[0].length-1]);
            pq.offer(c2);
            memo[i][m[0].length-1]=true;
        }

        int h=Integer.MIN_VALUE;
        int rs=0;
        while (!pq.isEmpty()){//这里想水是从四周开始，从最矮那漫进去，加入当前cur的高度为5，四周的四个点当中有个点为3，这个高度为3的点再旁边有个点为1，那么在poll出cur的时候
            cord cur=pq.poll(); //要不要也去计算cur四周的四周的点呢，应该是不用的，因为能装多少水是看海平面的高度，只要把这个1的点再丢进pq中，下次poll他出来的时候，海平面已经上升到5了，
            h=Math.max(h,cur.z);//因此可以正确计算出高度

            if(cur.x>0&&memo[cur.x-1][cur.y]==false){
                cord c=new cord(cur.x-1,cur.y,m[cur.x-1][cur.y]);
                pq.offer(c);
                memo[cur.x-1][cur.y]=true;
                if(h>m[cur.x-1][cur.y]){
                    rs+=h-m[cur.x-1][cur.y];
                }
            }
            if(cur.y+1<m[0].length&&memo[cur.x][cur.y+1]==false){
                cord c=new cord(cur.x,cur.y+1,m[cur.x][cur.y+1]);
                pq.offer(c);
                memo[cur.x][cur.y+1]=true;
                if(h>m[cur.x][cur.y+1]){
                    rs+=h-m[cur.x][cur.y+1];
                }
            }
            if(cur.x+1<m.length&&memo[cur.x+1][cur.y]==false){
                cord c=new cord(cur.x+1,cur.y,m[cur.x+1][cur.y]);
                pq.offer(c);
                memo[cur.x+1][cur.y]=true;
                if(h>m[cur.x+1][cur.y]){
                    rs+=h-m[cur.x+1][cur.y];
                }
            }
            if(cur.y>0&&memo[cur.x][cur.y-1]==false){
                cord c=new cord(cur.x,cur.y-1,m[cur.x][cur.y-1]);
                pq.offer(c);
                memo[cur.x][cur.y-1]=true;
                if(h>m[cur.x][cur.y-1]){
                    rs+=h-m[cur.x][cur.y-1];
                }
            }
        }
        return rs;
    }
    //6/16/2021 这次想法不一样，我全部柱子都扔进pq里然poll出最小的，只要这个柱子不是在边上，那么我找这个柱子的上下左右方向上遇到的最大高度，再取这四个方向上最大
    //高度的最小高度就知道这个点上能装多少水了，难道不行吗?这样写lc能过18个case第19个错了.我知道错了，如果说这种情况，x点上下左右方向最大柱子都是12，但是这个
    // 上方向的12的柱子不是紧贴着x点的，而紧贴x上面的点高度是10，然后这个10的左边或者右边最高柱子是9，那么这个x点上的水会先向10方向流然后再流到9，所以说这个方法是不行的
    //可以参考lintcode的图想象。
    public int trapRainWater3(int[][] m) {



    }

}
