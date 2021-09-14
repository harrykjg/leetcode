package dp;

import java.util.ArrayList;
import java.util.Arrays;

public class maximumProductSubarray {
    //7/19/2021 写的不好，用的是n方复杂度的dp，看别人的.其实自己想挺难的
    //https://leetcode.com/problems/maximum-product-subarray/discuss/48330/Simple-Java-code
    //https://www.cnblogs.com/grandyang/p/4028713.html
    //https://blog.csdn.net/whuwangyi/article/details/39577455
    public int maxProduct(int[] nums) {
        int max=nums[0];
        int min=nums[0];
        int rs=max;
        for (int i=1;i<nums.length;i++){
                int temp=Math.max(nums[i],Math.max(nums[i]*max,min*nums[i]));
                min=Math.min(nums[i],Math.min(nums[i]*min,max*nums[i]));
                max=temp;
                rs=Math.max(max,rs);
        }
        return rs;
    }
    //8/12/2021记混了。居然改了几次超时.不要看这个
    public int maxProduct2(int[] nums) {
        ArrayList<Integer> al=new ArrayList<>();
        al.add(1);
        int rs=Integer.MIN_VALUE;
        for(int i=1;i<=nums.length;i++){
            if(nums[i-1]==0){
                rs=Math.max(0,rs);
                al=new ArrayList<>();
                al.add(1);
                continue;
            }
            al.add(al.get(al.size()-1)*nums[i-1]);
            for(int j=0;j<al.size()-1;j++){
                rs=Math.max(rs,al.get(al.size()-1)/al.get(j));
            }
        }
        return rs;
    }

    public int maxProduct3(int[] nums) {
        int max=nums[0];
        int min=nums[0];
        int rs=Math.max(max,min);
        for(int i=1;i<nums.length;i++){
            if (nums[i]==0){//不写这个if 也对
                max=0;
                min=0;
                rs=Math.max(rs,0);
                continue;
            }

            int newMax=Math.max(nums[i],Math.max(nums[i]*min,nums[i]*max));
            int newMin=Math.min(nums[i],Math.min(nums[i]*min,nums[i]*max));
            rs=Math.max(rs,newMax);
            max=newMax;
            min=newMin;
        }
        return rs;
    }

}
