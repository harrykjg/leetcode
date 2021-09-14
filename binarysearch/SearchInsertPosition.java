package binarysearch;

/**
 * Created by 502575560 on 8/17/17.
 */
public class SearchInsertPosition {
    public static void main(String[] args){
        int[] a={1,3,5,6,8,9};
        System.out.println(searchInsert(a,7));
    }
    //这个没用模版的写法还不好写
    public static int searchInsert(int[] A, int target) {
        if(A.length==0){
            return 0;
        }
        int b=0;
        int e=A.length-1;
        int mid=0;
        while(b<e){
            mid=b+(e-b)/2;
            if(A[mid]==target){
                return mid;
            }
            if(A[mid]<target){
                b=mid+1;
            }else{
                e=mid;
            }
        }
        if(A[b]>=target){//{1,3,5,6,8,9}要插7这个例子,while循环得出来是b=4,这个条件要想清楚,因为while里条件是b=mid+1,所以说当while循环
            return b; //退出的时候,肯定是因为b+1了,那么这个while循环到了b=3,e=4时,mid+1等于4,那么7就刚好要插入4这个位置
        }
        if(b==0){
            return 0;
        }else{
            return A.length;
        }
    }
    //模版的写法
    public static int searchInsert2(int[] A, int target) {
        if(A.length==0){
            return 0;
        }
        int b=0;
        int e=A.length-1;
        int mid=0;
        while(b+1<e){
            mid=b+(e-b)/2;
            if(A[mid]==target){
                return mid;
            }
            if(A[mid]<target){
                b=mid;
            }else{
                e=mid;
            }
        }
        if(A[b]==target){
            return b;
        }
        if(A[e]==target){
            return e;
        }
        if(A[b]<target&&A[e]>target){
            return e;
        }
        if(b==0){
            return 0;
        }else{
            return A.length;
        }
    }
    //8/8/2021 随便写的感觉比以前的要好
    public int searchInsert3(int[] nums, int target) {
        int b=0;
        int e=nums.length-1;
        if(nums[nums.length-1]<target){
            return nums.length;
        }
        if(nums[0]>target){
            return 0;
        }
        while(b<e){
            int m=b+(e-b)/2;
            if(nums[m]==target){
                return m;
            }
            if(nums[m]>target){
                e=m;
            }else{
                b=m+1;
            }
        }
        return b;
    }

}
