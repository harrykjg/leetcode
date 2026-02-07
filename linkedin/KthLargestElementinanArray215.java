package linkedin;

public class KthLargestElementinanArray215 {
    public int findKthLargest(int[] nums, int k) {
        if(k>nums.length){
            return -1;
        }
        return quickSelect(nums,0,nums.length-1,nums.length-k);
    }
    int quickSelect(int[] nums,int begin,int end,int target){
        int x=nums[begin];
        int b=begin;
        int e=end;
        while (b<e){
            while (nums[e]>x){//注意不用大于等于
                e--;
            }
            if(b<e){
                nums[b]=nums[e];
                b++;
            }

            while (b<e&&nums[b]<x){
                b++;
            }

            if(b<e){
                nums[e]=nums[b];
                e--;
            }
        }
        nums[b]=x;
        if(b==target){
            return nums[b];
        }
        if(b>target){
            return quickSelect(nums,begin,b-1,target);
        }
        return quickSelect(nums,b+1,end,target);
    }
}
