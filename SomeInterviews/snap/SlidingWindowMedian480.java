package SomeInterviews.snap;


public class SlidingWindowMedian480 {
    static void main() {
        int[] a={1,2,3,4,2,3,1,4,2};
        double[] rs=SlidingWindowMedian480.medianSlidingWindow(a,3);
        for (double d:rs){
            System.out.println(d);
        }
    }
    //3/5/2026,我就是记得要倒腾的但是没想出例子了，例子就是1，3，-1，k=3,然后超时,原因是缩的时候不知道是该删哪个pq里的吧，别人用的是懒删除法
    //https://leetcode.cn/problems/sliding-window-median/solutions/3628827/295-ti-lan-shan-chu-dui-pythonjavacgojsr-66ch/
    //思路是要删的元素先不删，把他记录在hashmap里，value是要删的次数，等计算中位数时peek的时候再看是否是要删的元素,那这样会影响pq1的size不一定>=pq2吧,
    //所以又要balance，balance完之后可能又要看peek的位置是不是要删的元素，很不好写。
    //或者看别人用treeset的写法，那样删除就快了，treeset里装的是index因为会有重复元素
    //https://leetcode.com/problems/sliding-window-median/solutions/96346/java-using-two-tree-sets-on-logk-by-kido-d02p/
    public static double[] medianSlidingWindow(int[] nums, int k) {

    }
}
