package DataStruct;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yufengzhu on 12/10/17.
 */
//array //increasing subsequence/subarray
public class LongestConsecutiveSequence {
    //还是不好想啊，做不出来，开始想的是用map，其实用set，看会old package里的解释
    public int longestConsecutive(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        HashSet<Integer> set=new HashSet<>();
        int rs=1;
        for(int i:nums){
            set.add(i);
        }

        for(int i:nums){
            int count=1;
            int lcount=0;
            int rcount=0;
            int left=i-1;
            int right=i+1;
            while (set.contains(left)){
                lcount++;
                set.remove(left);
                left--;
            }
            while (set.contains(right)){
                rcount++;
                set.remove(right);
                right++;
            }
            count+=lcount+rcount;
            rs=Math.max(rs,count);
        }
        return rs;
    }
//6/7/2021,想的是用map,后来想的set就行了
    public int longestConsecutive2(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for (int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        int rs=0;
        for (int i:nums){

            if(set.contains(i)){
                int temp=i;
                int count=1;
                set.remove(temp);
                while (set.contains(temp-1)){
                    count++;
                    temp--;
                }
                temp=i;
                while (set.contains(temp+1)){
                    count++;
                    temp++;
                }
                rs=Math.max(count,rs);
            }
        }
        return rs;
    }
}
