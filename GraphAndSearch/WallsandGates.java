package GraphAndSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 7/31/18.
 */
public class WallsandGates {
    //值想到暴力法，他答案的暴力法居然不用memo的？正确的解法是反向bfs,并且是从所有gate同时开始。开始担心这么多起始点会不会导致问题，要不要多个memo去记录这个点访问过没有，会不会说从一个gate开始bfs到某个点
    //的距离比如是3，会不会出现从另一个点出发到这个点的距离会更短，画图发现是不会的，因为是层次遍历
    //https://leetcode.com/problems/walls-and-gates/solution/  答案的写法不太一样，是当层就更新下一层一层的数据了，而且还省了memo
    //https://segmentfault.com/a/1190000003906674 他这个bfs好像有点原始，就是多个bfs，这样会快吗
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length==0){
            return;
        }
        int count1=0;
        int count2=0;
        int max=Integer.MAX_VALUE;
        boolean[] memo=new boolean[rooms.length*rooms[0].length];
        int len=rooms[0].length;
        Queue<int[]> q=new LinkedList<>();
        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms[0].length;j++){
                if(rooms[i][j]==0){
                    q.offer(new int[]{i,j});
                    count1++;
                    memo[i*len+j]=true;
                }
            }
        }
        int step=0;
        while (!q.isEmpty()){
            int[] cur=q.poll();
            int row=cur[0];
            int col=cur[1];
            rooms[row][col]=step;
            count1--;

            if(row>0&&rooms[row-1][col]!=-1&&rooms[row-1][col]!=0&&!memo[(row-1)*len+col]){
                q.offer(new int[]{row-1,col});
                memo[(row-1)*len+col]=true;
                count2++;
            }
            if(col+1<rooms[0].length&&rooms[row][col+1]!=-1&&rooms[row][col+1]!=0&&!memo[row*len+col+1]){
                q.offer(new int[]{row,col+1});
                memo[row*len+col+1]=true;
                count2++;
            }
            if(row+1<rooms.length&&rooms[row+1][col]!=-1&&rooms[row+1][col]!=0&&!memo[(row+1)*len+col]){
                q.offer(new int[]{row+1,col});
                memo[(row+1)*len+col]=true;
                count2++;
            }
            if(col>0&&rooms[row][col-1]!=-1&&rooms[row][col-1]!=0&&!memo[row*len+col-1]){
                q.offer(new int[]{row,col-1});
                memo[row*len+col-1]=true;
                count2++;
            }
            if(count1==0){
                step++;
                count1=count2;
                count2=0;
            }

        }
    }
}
