package contest;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class LongestSubarrayof1sAfterDeletingOneElement {
    //自己写的，复杂度不好，也过了
    public int longestSubarray(int[] nums) {
        int[] dp1=new int[nums.length];
        int[] dp2=new int[nums.length];
        int index=0;
        while (index<nums.length){//dp就是用来存，每找到一个0的时候，让其向左边/右边延申，记录这个0的左边/右边连续为1的长度
            if(nums[index]==0){
                int count=0;
                for(int i=index-1;i>=0;i--){
                    if(nums[i]==1){
                        count++;
                    }else{
                        break;
                    }
                }
                dp1[index]=count;
            }
            index++;
        }
        index=nums.length-1;
        while (index>=0){
            if(nums[index]==0){
                int count=0;
                for(int i=index+1;i<nums.length;i++){
                    if(nums[i]==1){
                        count++;
                    }else{
                        break;
                    }
                }
                dp2[index]=count;
            }
            index--;
        }

        boolean found=false;
        int rs=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                found=true;
                int cur=dp1[i]+dp2[i];
                rs=Math.max(rs,cur);
            }
        }
        if(!found){
            return nums.length-1;
        }
        return rs;
    }

    //别人o（n）的解法，滑动窗口，这个思想可以借鉴
    //https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/discuss/708201/javaPython-3-Sliding-Window-with-at-most-one-zero-w-detailed-explanation-and-brief-analysis.
    public int longestSubarray2(int[] nums) {
        int b=0;
        int e=0;
        int rs=0;
        int sum=0;
        for(e=0;e<nums.length;e++){
            sum+=nums[e];
            while (b<e&&sum<e-b){
                sum-=nums[b];
                b++;
            }
            rs=Math.max(e-b,rs);//注意这里写max(sum,rs)是不对的，就是要e-b
        }
        return rs;
    }
}

