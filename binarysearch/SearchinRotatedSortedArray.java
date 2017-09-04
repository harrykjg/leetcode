package binarysearch;

/**
 * Created by 502575560 on 8/19/17.
 */
public class SearchinRotatedSortedArray {
    public static void main(String[] args){
        int[] a={1,3,5};
        System.out.println(search(a,1));
    }
    //用模版
    //还是没写对,就是只处理rotate的情况,但是处理不了没有rotate的情况,关键是找出一个条件去确定肯定有拐点的情况,然后处理它,还是要没种情况想清楚
    //答案的代码写的都很简单其实是合并了好几种情况,不想清楚写不对
    public static int search(int[] nums, int target) {
        if(nums.length==0){
            return -1;
        }
        int b=0;
        int e=nums.length-1;
        int mid=0;
        while (b+1<e){
            mid=b+(e-b)/2;
            if(nums[mid]==target){
                return mid;
            }
            if(nums[mid]<target){//首先nums[mid]<nums[b]就确定拐点在左边,要找大的那边,那么我就要判断应该去左边还是右边找
                if(nums[mid]<nums[b]&&target<nums[b]){//target肯定在右边
                    b=mid;
                }else if(nums[mid]<nums[b]){
                    e=mid;
                }else {//剩下2种情况,1:拐点在右边.2:无拐点.两种情况都是要找大的那边
                    b=mid;
                }
                //6,7,8,9,10,2,3,4,要找3
            }else{//要找小的那边
                if(nums[mid]>nums[e]&&nums[e]>=target){//注意nums[mid]>nums[b]就不能确定有拐点了,所以我用条件nums[mid]>nums[e]就肯定能
                    b=mid;                    //确定有拐点并且在右边
                }else if(nums[mid]>nums[e]){
                    e=mid;
                }else {//剩下的情况就是1:无拐点,2:有拐点在左边.而两者都是要找小的那边所以都是e=mid
                    e=mid;
                }
            }
        }
        if(nums[b]==target){
            return b;
        }
        if(nums[e]==target){
            return e;
        }
        return -1;
    }
}