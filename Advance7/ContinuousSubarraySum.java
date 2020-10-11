package Advance7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 6/23/18.
 */
//array
public class ContinuousSubarraySum {
    //这是lintcode的，leetcode的同样的名字但是题目是不一样的！
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

    //05/22/2020，感觉和maximum subarray很像，以为用前缀和，那更麻烦，看回以前的方法
    public List<Integer> continuousSubarraySum2(int[] A) {

        List<Integer> rs=new ArrayList<>();
        if(A.length==1){
            rs.add(0);
            rs.add(0);
            return rs;
        }
        rs.add(0);
        rs.add(0);
        int pre=0;
        int preindex=0;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<A.length;i++){
            if(pre>=0){//这里缺个等号就不对了，因为lintcode的test case是，如果两个subarray都似乎最大值，那么取比较长的那个
                pre+=A[i];
            }else{
                pre=A[i];
                preindex=i;
            }
            if(pre>max){
                max=pre;
                rs.set(0,preindex);
                rs.set(1,i);
            }
        }
        return rs;
    }

}
