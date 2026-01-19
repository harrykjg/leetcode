package 灵神.常用数据结构.队列;

public class SlidingWindowMaximum239 {
    static void main() {

    }
    //看视频解释。开始的想法可能就是sliding window，但又一个问题，你只知道两端的两个数，如果左端是最大值，然后即将要被缩掉，此时
    //无法知道谁是第二小的值（除非用pq，那复杂度就高了），这里就要用单调的dequeue，就是比最大值出现的早的并且小的值是不值得被记录的，
    //因为他肯定不会成为这个窗口的最大值，这样的话这个dequeue就会必然是第一个元素是最大的，被从左边缩的时候如果去掉头了，那么第二个元素就是最大了
    //https://www.bilibili.com/video/BV1bM411X72E/
    public int[] maxSlidingWindow(int[] nums, int k) {

    }

}
