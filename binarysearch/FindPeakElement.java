package binarysearch;

/**
 * Created by 502575560 on 7/24/17.
 */
public class FindPeakElement {
    //还是不会,可以看手机app的解释
    //非模版
    public int findPeakElement(int[] nums) {
        int b=0;
        int e=nums.length-1;
        while (b<e){
            int mid=b+(e-b)/2;
            if(mid+1<nums.length&&nums[mid+1]<nums[mid]&&mid-1>=0&&nums[mid-1]<nums[mid]){
                return mid;
            }
            if(mid+1<nums.length&&nums[mid+1]>nums[mid]){
                b=mid+1;
            }else {
                e=mid;
            }
        }
        return b;
    }
//用了模版
    public int findPeakElement2(int[] nums) {
        int b=0;
        int e=nums.length-1;
        while (b+1<e){//保证是有三个数的,那么mid加一或者减一都肯定不会越界
            int mid=b+(e-b)/2;
            if(nums[mid+1]<nums[mid]&&nums[mid-1]<nums[mid]){
                return mid;
            }
            if(nums[mid+1]>nums[mid]){
                b=mid;
            }else {
                e=mid;
            }
        }
        if(nums[b]<nums[e]){
            return e;
        }
        return b;

    }

}