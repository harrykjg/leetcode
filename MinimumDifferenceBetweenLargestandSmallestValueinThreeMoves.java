import java.util.Arrays;

public class MinimumDifferenceBetweenLargestandSmallestValueinThreeMoves {
    //9/1/2021
    //https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/discuss/730567/JavaC%2B%2BPython-Straight-Forward
    //排序之后肯定就是两端是最大最小值。然后就是上面链接列的那几种可能，实际上就是左边第一个和右边倒数第四个的差值，或左边第二和右边倒数第三。以此类推，中间的差值是长度-4
    public int minDifference(int[] nums) {
        int rs=Integer.MAX_VALUE;
        if (nums.length<=4){
            return 0;
        }
        Arrays.sort(nums);
        for (int i=0;i<4;i++){// 1 2 3 4 5 6 7 8
            rs=Math.min(rs,nums[nums.length-4+i]-nums[i]);
        }
        return rs;
    }
}
