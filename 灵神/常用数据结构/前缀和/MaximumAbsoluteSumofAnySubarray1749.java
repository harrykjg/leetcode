package 灵神.常用数据结构.前缀和;

public class MaximumAbsoluteSumofAnySubarray1749 {
    static void main() {
        int[] n={1,-3,2,3,-4};
        System.out.println(maxAbsoluteSum(n));
    }
    //居然是o(n)的解法，我以为只能枚举要n方复杂度。先计算前缀和，有了前缀和数组，那么只要便利他，记录一个min和一个max，最后max-min就是答案
    //还真是不好想像，举例2,-5,1,-4,3,-2，prefixsum数组为0 2 -3 -2 -6 -3 -5，知 max是2，min是-6，答案就是2-（-6）=8
    public static int maxAbsoluteSum(int[] nums) {
            int[] prefix=new int[nums.length+1];
            for (int i=1;i<prefix.length;i++){
                prefix[i]=nums[i-1]+prefix[i-1];
            }
            int max=Integer.MIN_VALUE;
            int min=Integer.MAX_VALUE;
            for (int i=0;i<prefix.length;i++){//之前i从1开始就错了，因为那就漏了整个前半段的的情况
                max=Math.max(prefix[i],max);
                min=Math.min(prefix[i],min);
            }
            return max-min;
    }
}
