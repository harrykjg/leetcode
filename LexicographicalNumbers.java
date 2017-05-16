import java.util.ArrayList;
import java.util.List;

/**
 * Created by 502575560 on 5/13/17.
 */
public class LexicographicalNumbers {
    //这题还挺难的,不好想
    //https://discuss.leetcode.com/topic/55184/java-o-n-time-o-1-space-iterative-solution-130ms  就是找规律,不好找
    //https://discuss.leetcode.com/topic/55377/simple-java-dfs-solution  dfs

    public List<Integer> lexicalOrder(int n) {//抄别人的代码
        List<Integer> rs=new ArrayList<>();
        int cur=1;
        for(int i=1;i<=n;i++){
            rs.add(cur);
            if(cur*10<=n){
                cur*=10;
            }else if(cur%10!=9&&cur+1<=n){
                cur++;
            }else{
                while ((cur/10)%10==9){
                    cur/=10;
                }
                cur=cur%10+1;
            }
        }

        return rs;
    }


}
