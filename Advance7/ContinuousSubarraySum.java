package Advance7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 6/23/18.
 */
public class ContinuousSubarraySum {
    //https://www.jiuzhang.com/solution/continuous-subarray-sum/
    //自己瞎写的也对了,但是用了一个map记录，看了答案发现不需要map
    public List<Integer> continuousSubarraySum(int[] A) {
        // write your code here
        List<Integer> rs=new ArrayList<>();
        if(A.length==1){
            rs.add(0);
            rs.add(0);
            return rs;
        }
        int b=0;
        int max=Integer.MIN_VALUE;
        int cursum=0;
        rs.add(0);
        rs.add(0);
        for(int i=0;i<A.length;i++){
            if(cursum>=0){
                cursum+=A[i];
            }else{
                cursum=A[i];
                b=i;
            }
            if(cursum>max){
                max=cursum;
                rs.set(0,b);
                rs.set(1,i);
            }
        }
        return rs;
    }
}
