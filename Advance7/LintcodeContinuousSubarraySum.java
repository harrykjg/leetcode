package Advance7;

import sun.nio.cs.ext.MacHebrew;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 502575560 on 8/9/17.
 */
//array
public class LintcodeContinuousSubarraySum {
    public static void main(String[] sa){
        continuousSubarraySum(new int[]{1,-1});
    }
    //我觉得就是leetcode的maximum subarray无非就是返回两个下标,这个是自己的版本写的不好
    //http://www.jiuzhang.com/solutions/continuous-subarray-sum  看他的写法
    public static ArrayList<Integer> continuousSubarraySum(int[] A) {
        // Write your code here
        HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
        int pre=A[0];
        int max=Integer.MIN_VALUE;
        int b=0;
        int e=0;
        if(A.length==1){
            ArrayList<Integer> a = new ArrayList<>();
            a.add(0);
            a.add(0);
            return a;
        }
        for(int i=1;i<A.length;i++){
            if(pre>max) {
                ArrayList<Integer> a = new ArrayList<>();
                a.add(b);
                a.add(e);
                map.put(pre, a);
                max=pre;
            }
            int temp=Math.max(0,pre);
            pre=A[i]+temp;
            if(pre<0){
                b=i;
                e=b;
            }else{
                e=i;
            }
            if(pre>max){
                ArrayList<Integer> a=new ArrayList<>();
                a.add(b);
                a.add(e);
                map.put(pre,a);
                max=pre;
            }

        }

        return map.get(max);
    }
}
