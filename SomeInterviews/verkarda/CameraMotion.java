package SomeInterviews.verkarda;

import java.util.ArrayList;
import java.util.List;

public class CameraMotion {
    /*
    第一个题目是有一个摄像头，处理摄像头的数据，输入是一个vector和一个threshold，vector里面的每一个element是一个time stamp和一个motion level，
     找出序列里面motion level 大于 threshold的时间区间， 比如输入:
     vector = [[1, 0.4], [5, 0.2], [11, 0.9], [15, 0.9], [17, 0.8], [25, 0.5], [27, 0.8], [36, 0.9]];
     threshold = 0.8; 输出： [[11, 17], [27, 36]];
     第二题：在第一题的基础上，有一堆摄像头，输入是vector<vector<....>>和threshold，找出所有摄像头的motion level都大于threshold的时间区间。
    就是把每一个摄像头用第一题的code算出大于threshold的区间，然后把所有摄像头的区间算出overlap。 follow up是摄像头很多怎么办，bottleneck是啥。
     */
    /*
    (This question is a variation of the LeetCode question 986. Interval List Intersections. If you haven't completed that question yet, it is recommended to solve it first.)
A surveillance camera produces a chronologically ordered time series of readings, where each reading captures a timestamp and a motion intensity value. Given the readings and a sensitivity threshold, find all contiguous time periods during which the camera's motion intensity is at or above the threshold.
A motion period starts at the first reading that meets or exceeds threshold and ends at the last one in the unbroken run, regardless of gaps between adjacent timestamps. Both endpoints are inclusive, so a single qualifying reading produces a valid period [t, t].
Given two parallel arrays times and intensities and a threshold, return all motion periods as [start, end] pairs sorted by start time. Return an empty array if no reading meets the threshold.
Constraints:
1 ≤ times.length ≤ 105
1 ≤ times[i] ≤ 109
times is strictly increasing
0.0 ≤ intensities[i] ≤ 1.0
0.0 ≤ threshold ≤ 1.0
Example 1:
Input: times = [2, 7, 10, 11, 16], intensities = [0.5, 0.8, 0.9, 0.9, 0.4], threshold = 0.8
Output: [[7, 11]]
Explanation:
The readings at times 7 (0.8), 10 (0.9), and 11 (0.9) are all at or above 0.8, forming one contiguous active run. The readings at times 2 and 16 fall below the threshold, so the only motion period is [7, 11].
Example 2:
Input: times = [1, 5, 11, 15, 17, 25, 27, 36], intensities = [0.4, 0.2, 0.9, 0.9, 0.8, 0.5, 0.8, 0.9], threshold = 0.8
Output: [[11, 17], [27, 36]]
Example 3:
Input: times = [1, 5, 10, 15], intensities = [0.9, 0.4, 0.9, 0.4], threshold = 0.6
Output: [[1, 1], [10, 10]]
Hint 1
Consider maintaining a simple boolean flag to track whether the current reading is inside or outside an active motion period.
Hint 2
Record the starting timestamp when entering a period, continuously update the ending timestamp while inside, and only commit the range when the condition breaks or the data ends.
What Interviewers May Ask Next
How would you adapt this solution to process a continuous stream of incoming readings without storing the entire history?
What adjustments are needed if the input arrays were not guaranteed to be sorted by time?
How would you efficiently handle floating-point comparison inaccuracies when determining if an intensity meets the threshold?
     */
    //输入是time自增
    public int[][] getMotionPeriods(int[] times, double[] intensities, double threshold) {
        // TODO: Implement getMotionPeriods logic
        List<int[]> rs=new ArrayList<>();
        for (int i=0;i<times.length;i++){
            //需要知道这是新的一段还是延续上一段
            if(intensities[i]>=threshold){
                if(rs.size()==0){
                    //新的一段
                    rs.add(new int[]{times[i],times[i]});
                }else if(i>0&&intensities[i-1]<threshold){
                    //新的一段
                    rs.add(new int[]{times[i],times[i]});
                }else if(rs.size()>0){
                    //延续上一段
                    rs.get(rs.size()-1)[1]=times[i];
                }
            }
        }
        return rs.toArray(new int[0][]);//注意这个写法
    }
    //第二问，一堆摄像头overlap
    public int[][] getAllCameraMotionPeriods(int[][] times, double[][] intensities, double threshold) {
        if (times.length == 0) {
            return new int[0][];
        }
        // 先拿第一个 camera 的 high-motion intervals
        int[][] rs = getMotionPeriods(times[0], intensities[0], threshold);
        // 依次和其他 camera 的 intervals 求 overlap
        for (int i = 1; i < times.length; i++) {
            int[][] cur = getMotionPeriods(times[i], intensities[i], threshold);
            rs = intersect(rs, cur);
            // 已经没有公共区间了，后面不用继续
            if (rs.length == 0) {
                return rs;
            }
        }
        return rs;
    }
    public int[][] intersect(int[][] a, int[][] b) {
        List<int[]> rs = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            // overlap 的开始取较大的 start
            int start = Math.max(a[i][0], b[j][0]);//就是按start排序，右边那段的start如果小于较小的end就死有交集
            // overlap 的结束取较小的 end
            int end = Math.min(a[i][1], b[j][1]);
            // 有交集
            if (start <= end) {
                rs.add(new int[]{start, end});
            }
            // 谁先结束，谁往后走
            if (a[i][1] < b[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return rs.toArray(new int[0][]);
    }
    //follow up 如果input是data stream咋办。实际上就是input 变成一个一个来的，那么就需要知道上一个数据是否是high，如果pre是high但current是low
    //则终结上一段。如果pre是hight现在也是high则啥也不做。如果pre是low现在是high就记录现在作为start，如果pre是low现在也是low就啥也不错
    class MotionTracker {
        private double threshold;
        private Double prevIntensity = null;
        private int prevTime = -1;//标记上一个的timestamp
        private int start = -1;//标记一个high的start time，不一定是prevTime
        private List<int[]> result = new ArrayList<>();
        public MotionTracker(double threshold) {
            this.threshold = threshold;
        }
        public void process(int time, double intensity) {
            if (intensity >= threshold) {
                // 第一条就是 high
                // 或者上一条是 low -> 当前 high，记录这段的开始
                if (prevIntensity == null || prevIntensity < threshold) {
                    start = time;
                }
            } else {
                // 上一条是 high -> 当前 low，终结上一段，用标记的start和prevtime
                if (prevIntensity != null && prevIntensity >= threshold) {
                    result.add(new int[]{start, prevTime});
                }
            }
            prevIntensity = intensity;
            prevTime = time;
        }
        //假如结束steam，就补上最后一段
        public int[][] finish() {
            // 最后一条仍然是 high
            if (prevIntensity != null && prevIntensity >= threshold) {
                result.add(new int[]{start, prevTime});
            }
            return result.toArray(new int[0][]);
        }
    }
    /*
    变种:
Given a surveillance system with multiple cameras, each camera produces its own chronologically ordered time series of readings. A camera is active during the motion periods computed using the same rules as in the previous question.

Find all time ranges during which every camera is active at the same time. In other words, a common motion period is a time range fully covered by at least one motion period from every camera.

Return all maximal common motion periods sorted by start time. Both endpoints are inclusive, so [t, t] is a valid result. Camera time series do not need to share the same timestamps.

You are given allTimes, allIntensities, and threshold. For camera i, allTimes[i] contains its timestamps, and allIntensities[i][j] is the intensity at time allTimes[i][j]. Return an empty list if no common motion period exists.

Constraints:

1 ≤ allTimes.size() ≤ 104 (number of cameras)
The total number of readings across all cameras is at most 105
Each camera's time series follows the Q1 constraints
Example 1:

Input: allTimes = [[2, 7, 10, 11, 16], [5, 8, 9, 13, 20], [6, 7, 8, 17]], allIntensities = [[0.5, 0.8, 0.9, 0.9, 0.4], [0.8, 0.9, 0.8, 0.5, 0.5], [0.1, 0.8, 0.9, 0.8]], threshold = 0.8
Output: [[7, 9]]
Explanation:
Camera 1 is active during [7, 11], Camera 2 during [5, 9], and Camera 3 during [7, 17]. The intersection between [7, 11], [5, 9], and [7, 17] is [7, 9].
Example 2:
Input: allTimes = [[2, 7, 10, 11, 14], [5, 11], [1, 8, 11, 21]], allIntensities = [[0.9, 0.9, 0.5, 0.9, 0.9], [0.9, 0.9], [0.8, 0.9, 0.9, 0.9]], threshold = 0.8
Output: [[5, 7], [11, 11]]
Example 3:
Input: allTimes = [[1, 2, 3, 4, 5], [1, 2, 3, 4, 5], [1, 2, 3, 4, 5]], allIntensities = [[0.9, 0.9, 0.9, 0.9, 0.9], [0.9, 0.9, 0.9, 0.9, 0.9], [0.1, 0.2, 0.3, 0.4, 0.4]], threshold = 0.5
Output: []
Hint 1
Separate the task into two distinct steps: first filter each camera's raw readings into discrete active intervals, then treat the core challenge as intersecting K sorted interval lists.

Hint 2
Use one cursor per camera to track the current interval; calculate the overlap using the latest start and earliest end, then advance the cursor belonging to the interval that ends soonest to maintain
     */
    //还是线扫描吧
}
