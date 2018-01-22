package binarysearch;

/**
 * Created by 502575560 on 8/19/17.
 */
public class FindMinimuminRotatedSortedArray {
    public static void main(String[] args){
        int[] a={2,3,4,5,1};
        System.out.println(findMin2(a));
    }
    //非模版写的和以前不一样,不太熟悉
    public int findMin(int[] nums) {
        // write your code here
        if(nums.length==1){
            return nums[0];
        }
        int b=0;
        int e=nums.length-1;
        int mid=0;
        while (b<e){
            mid=b+(e-b)/2 ;
            if(nums[mid]<nums[e]&&nums[mid]<nums[b]){//其实这两个if就可以合并成nums[mid]<nums[e]就行了
                e=mid;
                continue;
            }
            if(nums[mid]<nums[e]&&nums[mid]>=nums[b]){//这个if是用来处理这个数组没rotate的情况
                e=mid;
                continue;
            }

            b=mid+1;

        }
        return nums[b];
    }
    //模版
    public static int findMin2(int[] nums) {
        // write your code here
        if(nums.length==1){
            return nums[0];
        }
        int b=0;
        int e=nums.length-1;
        int mid=0;
        while (b+1<e){
            mid=b+(e-b)/2 ;
            if(nums[mid]<nums[e]&&nums[mid]<nums[b]){
                e=mid;
                continue;
            }
            if(nums[mid]<nums[e]&&nums[mid]>nums[b]){
                e=mid;
                continue;
            }

            b=mid;

        }
        return nums[b]<nums[e]?nums[b]:nums[e];
    }
//1／21／2018，九章第二轮，用模版,居然还是不能一次过
    public int findMin3(int[] nums) {
        if(nums.length==0){
            return -1;
        }
        int b=0;
        int e=nums.length-1;
        int m=0;
        while (b+1<e){
            m=b+(e-b)/2;
            if(nums[m]<nums[e]&&nums[m]<nums[b]){//还是要画图看看才好理解
                e=m;
            }else if(nums[m]>nums[e]) {
                b=m;
            }else{
                e=m;
            }
        }
        if(nums[b]<nums[e]){
            return nums[b];
        }
        return nums[e];
    }
}
