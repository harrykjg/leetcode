import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 10/3/18.
 */
public class ReachaNumber {
    //https://www.cnblogs.com/grandyang/p/8456022.html
    //只能写bfs的，会超内存。
    public int reachNumber(int target) {
        Queue<Integer> q=new LinkedList<>();
        int rs=0;
        int step=1;
        int count1=1;
        int count2=0;
        q.offer(0);
        while (!q.isEmpty()){
            int cur=q.poll();
            count1--;
            int left=cur-step;
            int right=cur+step;
            if(left==target||right==target){
                return rs+1;
            }
            q.offer(left);
            q.offer(right);
            count2+=2;
            if(count1==0){
                rs++;
                step++;
                count1=count2;
                count2=0;
            }


        }
        return rs;


    }
}
