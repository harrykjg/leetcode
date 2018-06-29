package Advance7;

/**
 * Created by 502575560 on 8/9/17.
 */
public class KthLargestElement {
    //这题我以为还是用heap,结果有O(n)的方法,而kthlargestinnarrays那题就只能用heap
    //貌似是quicksort的方法,但是要选第k个数,貌似相当高端的用法
    //http://www.jiuzhang.com/solutions/kth-largest-element/
    //这个是参考它的quickselect的写法
    public int kthLargestElement(int k, int[] nums) {
        //思路就是quicksort的,取一个pivot,这个pivot经过一轮quicksort之后被放到了该放的位置上(它左边的元素都小于它,右边的都大于它),
        //如果这个index刚好等于nums.length-k,则这个数就是第K大的(k是从1开始,这个quicksort是升序排序,所以是这样,如果是求第k小的话
        //应该就是index+1刚好等于k则nums[index]就是第k小的数)
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (k <= 0) {
            return 0;
        }
        return helper(nums, 0, nums.length - 1, nums.length-k);//举个例子,数组长为10,找第三大的数,则是升序排序中第nums[7],这里和九章的下标不一样
    }
    int helper(int[] nums, int b,int e,int k){
        if(b>=e){
            return nums[e];
        }
        int index=partition(nums,b,e,nums[b]);
        if(index==k){
            return nums[index];
        }else if(index<k){//index这个位置右边都是大于它的,如果index小于k说明应该要在右边才可能找到一个pivot并且它的index为k
            return helper(nums,index+1,e,k);
        }else{
            return helper(nums,b,index-1,k);
        }
    }
    int partition(int[] nums,int b,int e,int pivot){
        if(b>=e){
            return e;
        }
        int i=b;
        int j=e;
        while (i<j){
            while (i<j&&nums[j]>=pivot){//i<j这个条件漏了就错了
                j--;
            }
            if(i<j){
                nums[i]=nums[j];
                i++;
            }
            while (i<j&&nums[i]<=pivot){
                i++;
            }
            if(i<j){
                nums[j]=nums[i];
                j--;
            }
        }
        nums[i]=pivot;
        return i;
    }
//6／23／2018，还是以为用heap，结果是有O(n）的方法,还是不好写,partition的方法我以为不用b和e直接整个数组找pivit的位置，其实还是加上b和e比较好
    public int kthLargestElement2(int k, int[] nums) {
        return helper2(nums,k,0,nums.length-1);
    }
    int helper2(int[] nums,int k,int b,int e){
        if(b>=e){
            return nums[b];
        }
        int index=partition2(nums, b,e);
        if(index==nums.length-k){
            return nums[index];
        }
        if(index<nums.length-k){
            return helper2(nums,k,index+1,e);
        }else{
            return helper2(nums,k,b,index-1);
        }

    }
    int partition2(int[] nums,int b,int e){
        int p=nums[b];
        int i=b;
        int j=e;
        while (i<j){
            while (i<j&&nums[j]>=p){
                j--;
            }
            if(i<j){
                nums[i]=nums[j];
                i++;
            }
            while (i<j&&nums[i]<p){
                i++;
            }
            if(i<j){
                nums[j]=nums[i];
                j--;
            }
        }
        nums[i]=p;
        return i;

    }

}
