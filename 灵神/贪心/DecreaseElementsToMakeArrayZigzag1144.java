package 灵神.贪心;

import java.util.Arrays;

public class DecreaseElementsToMakeArrayZigzag1144 {
    static void main() {
        int[] a={9,6,1,6,2};
        System.out.println(movesToMakeZigzag(a));
    }
    //12/31/2025 就是直接遍历i看两边的数，算差额，两种情况，就可以用i的开始位置不同来算,
    // 参考的答案来写的,但是改了很多次，和他写的其实是想法不一样的，他那里不需要更新原数组，其实没看懂答案为什么那样写
    //答案的意思貌似和我这反过来了，我想的是i位要大于两边的，而他是i位要小于两边的
    //https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag/solutions/2134526/di-jian-yuan-su-shi-shu-zu-cheng-ju-chi-o30ye/
    public static int movesToMakeZigzag(int[] nums) {
        int[] n1=Arrays.copyOf(nums,nums.length);
        int[] n2=Arrays.copyOf(nums,nums.length);
        return Math.min(helper(n1,0),helper(n2,1));
    }
    static int helper(int[] nums,int b){
        int rs=0;
        for(int i=b;i<nums.length;i+=2){
            int cur=0;
            if(i-1>=0){//这里容易搞错谁减谁，题目说了只能减，而这里要求i大于两边，即在小于的情况下，两边是要减小的，i自己肯定是不变的
                //那就是符合条件的时候nums[i-1]-(nums[i]-1)肯定是负数，否则就是需要减的数量，
                cur=Math.max(0,nums[i-1]-(nums[i]-1));
            }
            if(i+1<nums.length){
                cur+=Math.max(0,nums[i+1]-(nums[i]-1));
                nums[i+1]-=Math.max(0,nums[i+1]-(nums[i]-1));//注意这里容易漏更新nums[i+1]，而nums[i-1]是不用更新的，因为他不会被重复计算
            }
            rs+=cur;
        }
        return rs;
    }
}
