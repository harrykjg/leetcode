package linkedin;

public class MaximumProductSubarray152 {
    static void main() {
        int[] a={2,-2,3};
        System.out.println(maxProduct(a));
    }
    //2/2/2026还是写不出,居然直接不用dp数组
    public static int maxProduct(int[] nums) {
        int max=nums[0];
        int min=nums[0];
        int rs=nums[0];
        for (int i=1;i<nums.length;i++){
            int newMax=Math.max(nums[i],Math.max(nums[i]*min,nums[i]*max));
            int newMin=Math.min(nums[i],Math.min(nums[i]*min,nums[i]*max));
            rs=Math.max(rs,newMax);
            max=newMax;
            min=newMin;
        }
        return (int)max;
    }
}
