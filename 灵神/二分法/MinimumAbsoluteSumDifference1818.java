package 灵神.二分法;

public class MinimumAbsoluteSumDifference1818 {

    //不会，看https://leetcode.cn/problems/minimum-absolute-sum-difference/solutions/874160/jue-dui-chai-zhi-he-by-leetcode-solution-gv78/
    //意思就是想让nums1[i]-nums2[i]的绝对值尽可能小，就是找另一个j，使得nums1[j]-nums2[j]的绝对值最小。 而结果求得是每一个i位置上两个数组的绝对值的和的最小值，
    //既我要找到一个j，使得用j替换i之后，他们的绝对值差的最小。。不好解释也不好写
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {

    }

    //1/17/2026第二轮想的就是对于每一位i，知道nums1[i]-nums2[i]的绝对值，那现在要找另一个j，使得nums1[j]-nums2[i]的绝对值变得更小
    //那普通写法就是n方了，优化就是查找的时候写二分法。并且要记录这些所有替换方案中最好的那个。代码见
    //https://leetcode.cn/problems/minimum-absolute-sum-difference/solutions/874665/gong-shui-san-xie-tong-guo-er-fen-zhao-z-vrmq/
    public int minAbsoluteSumDiff2(int[] nums1, int[] nums2) {

    }

}
