package GraphAndSearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 8/13/18.
 */
public class ZeroOneMatrix {
    public static void main(String[]args){
        ZeroOneMatrix zo=new ZeroOneMatrix();
        int[][] a={{1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 0}};
        zo.updateMatrix(a);
    }
    //类似WallsandGates
    //答案是从所有0开始找1，不太理解为啥这样就会快一点
    //https://www.cnblogs.com/grandyang/p/6602288.html
    //和wallsandgates一样，每次遍历到queue poll出来的点的四周的值就是poll出来这个点的距离+1，肯定是最小的，不需要去判断是否存在更小的点。注意WallsandGates是所有没遇到的点是maxvalue
    //并且是维护了一个step变量去记录层级，而这一题的写法是要靠旁边的点的数值加1去得到当前点的距离。注意这里我用了memo数组，而答案不需要，但是那样的话就要判断这个现在算出来的rs[i][j]+1和当前点本来的值谁比较小
    public int[][] updateMatrix(int[][] matrix) {
        int[][] rs=new int[matrix.length][matrix[0].length];
        boolean[][] memo=new boolean[matrix.length][matrix[0].length];
        Queue<int[]> q=new LinkedList<>();

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    q.offer(new int[]{i,j});
                    memo[i][j]=true;

                }

            }
        }
        while (!q.isEmpty()){
            int cur[]=q.poll();
            int i=cur[0];
            int j=cur[1];
            if(i-1>=0&&matrix[i-1][j]!=0&&!memo[i-1][j]){
                rs[i-1][j]=Math.min(rs[i][j]+1,rs[i-1][j]);
                q.offer(new int[]{i-1,j});
                memo[i-1][j]=true;
            }

            if(j+1<matrix[0].length&&matrix[i][j+1]!=0&&!memo[i][j+1]){
                rs[i][j+1]=Math.min(rs[i][j]+1,rs[i][j+1]);
                q.offer(new int[]{i,j+1});
                memo[i][j+1]=true;
            }
            if(i+1<matrix.length&&matrix[i+1][j]!=0&&!memo[i+1][j]){
                rs[i+1][j]=Math.min(rs[i][j]+1,rs[i+1][j]);
                q.offer(new int[]{i+1,j});
                memo[i+1][j]=true;
            }

            if(j-1>=0&&matrix[i][j-1]!=0&&!memo[i][j-1]){
                rs[i][j-1]=Math.min(rs[i][j]+1,rs[i][j-1]);
                q.offer(new int[]{i,j-1});
                memo[i][j-1]=true;
            }
        }
        return rs;
    }
}
