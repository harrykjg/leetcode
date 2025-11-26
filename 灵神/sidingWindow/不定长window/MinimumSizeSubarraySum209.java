package 灵神.sidingWindow.不定长window;

public class MinimumSizeSubarraySum209 {
    public static void main(String[] args) {
        int[] nums={1,2,3,4,5};
        System.out.println(minSubArrayLen(11,nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int rs=Integer.MAX_VALUE;
        int b=0;
        int e=0;
        int cur=0;
        while (e<nums.length){
            while (e<nums.length&&cur<target){
                cur+=nums[e];
                e++;
            }
            if (cur>=target){
                rs=Math.min(rs,e-b);
            }
            while (b<=e&&b<nums.length&&cur>=target){
                cur-=nums[b];

                b++;
                if (cur>=target){//这里开始漏了
                    rs=Math.min(rs,e-b);
                }
            }

        }
        if (rs==Integer.MAX_VALUE){
            return 0;
        }
        return rs;
    }


}
