package Advance4;

/**
 * Created by yufengzhu on 4/15/18.
 */
public class FindPeakElement {
    //4/15/2018，九章第二轮，是模版记错了，b和e不用m+1，直接等于m就行了
    public int findPeakElement(int[] nums) {
        if(nums.length==1){
            return 0;
        }
        int b=0;
        int e=nums.length-1;
        int m=0;
        while (b<e-1){
            m=b+(e-b)/2;
            if(nums[m]>nums[m-1]&&nums[m]<nums[m+1]){
                return m;
            }
            if(nums[m]<nums[m+1]){
                b=m;//开始写层m+1，和m-1都可以accept
            }else{
                e=m;
            }
        }
        if(nums[b]>nums[e]){
            return b;
        }
        return e;

    }
}
