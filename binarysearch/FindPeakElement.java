package binarysearch;

/**
 * Created by 502575560 on 7/24/17.
 */
public class FindPeakElement {
    //还是不会,可以看手机app solution2的解释
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
    //第二轮
    //如果不是知道是二分法的话,思路还是不容易直接想到,这题用模版好,mid+1不会越界,就是a[mid+1]>a[mid]的话,那么右边肯定有答案

    //1/21/2018,九章第二轮，还是思路不好想
    public int findPeakElement3(int[] nums) {
        if(nums.length==0){
            return -1;
        }
        int b=0;
        int e=nums.length-1;
        int m=0;
        while (b+1<e){
            m=b+(e-b)/2;
            if(nums[m]>nums[m+1]&&nums[m]>nums[m-1]){
                return m;
            }
            if(nums[m]<nums[m+1]){
                b=m;
            }else{
                e=m;
            }
        }
        if(nums[b]<nums[e]){
            return e;
        }
        return b;
    }

}
