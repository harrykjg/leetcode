package ArrayListAndNumbers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 502575560 on 10/8/17.
 */
public class MaximumSubarrayII {
    public static void main(String[]args){
        MaximumSubarrayII ms=new MaximumSubarrayII();
        ArrayList<Integer> al=new ArrayList<>();
        al.add(5);
        al.add(4);
        ms.maxTwoSubArrays(al);
    }
    //http://www.jiuzhang.com/solution/maximum-subarray-ii/ 九章的答案是用前缀和的,没研究
    //这题leetcode没有,和besttimebuyandsellIII是一个思路,两个dp合并的时候rs=dp1[i]+dp2[i+1]注意dp2的i要加1
    public int maxTwoSubArrays(List<Integer> nums) {
        if(nums.size()==0){
            return 0;
        }
        if(nums.size()==1){
            return nums.get(0);
        }
        int[] dp1=new int[nums.size()];
        dp1[0]=nums.get(0);
        int[] dp2=new int[nums.size()];
        dp2[nums.size()-1]=nums.get(nums.size()-1);
        int pre=nums.get(0);
        for(int i=1;i<dp1.length;i++){
            if(pre>0){
                dp1[i]=Math.max(pre+nums.get(i),dp1[i-1]);
                pre+=nums.get(i);
            }else{
                dp1[i]=Math.max(nums.get(i),dp1[i-1]);
                pre=nums.get(i);
            }
        }
        pre=nums.get(nums.size()-1);
        for(int i=nums.size()-2;i>=0;i--){
            if(pre>0){
                dp2[i]=Math.max(pre+nums.get(i),dp2[i+1]);
                pre+=nums.get(i);
            }else{
                dp2[i]=Math.max(nums.get(i),dp2[i+1]);
                pre=nums.get(i);
            }
        }
        int rs=Integer.MIN_VALUE;
        for(int i=0;i<dp1.length-1;i++){
            rs=Math.max(dp1[i]+dp2[i+1],rs);
        }
        return rs;

    }
}
