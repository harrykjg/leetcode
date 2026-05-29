package SomeInterviews.tiktok;

public class RotateArray189 {
    //5/20/2026 要in place o1 memory
    //想复杂了，就是reverse前面n-k个和后面k个，再整个reverse
    public void rotate(int[] nums, int k) {
        k%=nums.length;
        int n=nums.length;
        reverse(nums,0,n-1-k);
        reverse(nums,n-k,n-1);
        reverse(nums,0,n-1);
    }
    void reverse(int[] nums,int b,int e){
        while (b<e){
            int temp=nums[b];
            nums[b]=nums[e];
            nums[e]=temp;
            b++;
            e--;
        }
    }
    void swap(int[] nums,int a,int b){
        int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
    }
}
