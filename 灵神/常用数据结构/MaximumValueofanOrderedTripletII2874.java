package 灵神.常用数据结构;

public class MaximumValueofanOrderedTripletII2874 {
    static void main() {
        int[] n={1000000,1,1000000};
        System.out.println(maximumTripletValue(n));
    }
    //自己只能想到暴力法是n3次方。看他第一种方法好想一些，就是枚举i《j《k中间的j，那么只要知道j右边最大的数和j左边最大的数，就可以枚举出答案
    //右边的话就维护一个右边最大的数组，左边的话就维护一个premax变量就可以了
    //https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-ii/solutions/2464857/mei-ju-jzhao-qian-hou-zui-da-zhi-pythonj-um8q/
    public static long maximumTripletValue(int[] nums) {
        long[] right=new long[nums.length];
        int rmax=nums[nums.length-1];
        for (int i=nums.length-2;i>=0;i--){
            right[i]=rmax;
            rmax=Math.max(nums[i],rmax);
        }
        long lmax=nums[0];
        long rs=0;
        for(int i=1;i<nums.length-1;i++){
            rs=Math.max(rs,(lmax-nums[i])*right[i]);
            lmax=Math.max(nums[i],lmax);
        }
        return rs;
    }
}
