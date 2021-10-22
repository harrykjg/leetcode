package SomeInterviews.facebook;

public class KsortedArray {
    //就是一个数组每个元素都在他该在的地方的index+k或index-k的范围之内，要求排序。那么就用一个大小为k+1的heap，先装满k+1的heap。比如第一位0，k=3，那么他排序之后
    // 是在0到3中间，0到3有4个元素（k+1个），因此我先放进去4个元素，然后index从0开始，i从第4开始，原输入数组填上heap poll出来的元素，位置肯定是对的，每填上一
    // 个元素也把heap加上第i个元素，实际上就是维护一个大小为k+1的sliding window，从头开始，填heap poll出来的数字，并且把右边届的数字加进heap里
    //
    //https://www.geeksforgeeks.org/nearly-sorted-algorithm/

}
