import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 11/6/18.
 */
public class SnakesandLadders {
    public static void main(String[] a){
        SnakesandLadders sl=new SnakesandLadders();
        int[][] b={{1,1,-1},{1,1,1},{-1,1,1}};
        System.out.print(sl.snakesAndLadders(b));
    }
    //主要是题意要搞清楚
    //https://leetcode.com/problems/snakes-and-ladders/discuss/173682/Java-concise-solution-easy-to-understand
    public int snakesAndLadders(int[][] board) {
        if(board.length==0){
            return 0;
        }
        int len=board.length;
        Queue<Integer> q=new LinkedList<>();//开始想的是建一个新的class pair存储坐标，其实也不需要，就存id然后解析出来坐标
        int step=0;
        int count1=1;
        int count2=0;
        if(board[board.length-1][0]!=-1){
            q.offer(board[board.length-1][0]);
        }else{
            q.offer(1);
        }
        HashSet<Integer> set=new HashSet<>();
        set.add(1);
        while (!q.isEmpty()){
            int cur=q.poll();
            if(cur==len*len){
                return step;
            }
            count1--;
            for(int i=1;i<=6;i++){//原来固定是6的长度
                if(cur+i>len*len){
                    break;
                }
                int nextId=cur+i;
                int nextrow=len-1-(nextId-1)/len;
                int nextcol=0;
                if(nextrow%2!=len%2){//这个要举例子有点难想，就是要判断col到底是从左到右还是反过来，row是固定了的
                    nextcol=(nextId-1)%len;
                }else{
                    nextcol=len-1-(nextId-1)%len;
                }
                if(board[nextrow][nextcol]!=-1){
                    if(!set.contains(board[nextrow][nextcol])){
                        q.offer(board[nextrow][nextcol]);
                        set.add(board[nextrow][nextcol]);
                        count2++;
                    }
                }else{
                    if(!set.contains(nextId)){
                        q.offer(nextId);
                        set.add(nextId);
                        count2++;
                    }
                }
            }
            if(count1==0){
                count1=count2;
                count2=0;
                step++;
            }
        }
        return -1;
    }
}
