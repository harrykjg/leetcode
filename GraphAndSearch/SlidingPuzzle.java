package GraphAndSearch;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by yufengzhu on 7/21/18.
 */
public class SlidingPuzzle {
    public static void main(String[] args){
        SlidingPuzzle sp=new SlidingPuzzle();
        int[][] b={{4,1,2},{5,0,3}};
        System.out.println(sp.slidingPuzzle(b));
    }
    //9/26/2021 不会
    //https://www.youtube.com/watch?v=jkkKPkOJFu8
    //把board转化成一个1维string，就等于从开始的状态，通过把0于其上下左右的点交换，得到最终状态的过程，用bfs。关键是想到转成1维string。而找0的上下左右的时候
    //需要再把他在1维的index转成二维的x，y，看上下左右是哪个数字，这些数字的点再转成1维的index，就知道是0与哪个节点互换了。知道思路就好写了
    public int slidingPuzzle(int[][] board) {
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                sb.append(board[i][j]);
            }
        }
        String end="123450";
        Queue<StringBuilder> q=new LinkedList<>();
        Set<String> set=new HashSet<>();
        set.add(sb.toString());
        q.offer(sb);
        int rs=0;
        int[] x={-1,0,1,0};
        int[] y={0,1,0,-1};
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                StringBuilder cur=q.poll();
                if (cur.toString().equals(end)){
                    return rs;
                }
                //找到0的index
                int j=0;
                for (;j<cur.length();j++){
                    if (cur.charAt(j)=='0'){
                        break;
                    }
                }
                int row=j/3;
                int col=j%3;
                for (int k=0;k<4;k++){
                    int r=row+x[k];
                    int c=col+y[k];
                    if (r>=0&&r<2&&c>=0&&c<3){
                        int next=r*3+c;
                        StringBuilder temp=new StringBuilder(cur);
                        char cc=temp.charAt(next);
                        temp.setCharAt(next,'0');
                        temp.setCharAt(j,cc);
                        if (!set.contains(temp.toString())){
                            set.add(temp.toString());
                            q.offer(temp);
                        }
                    }
                }
            }
            rs++;
        }
        return -1;
    }
}
