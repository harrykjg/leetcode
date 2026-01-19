package 灵神.贪心;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class CourseScheduleIII630 {
    static void main() {
        int[][] c={{5,15},{3,19},{6,7},{2,10},{5,16},{8,14},{10,11},{2,19}};
        System.out.println(scheduleCourse(c));
    }
    //1/2/2026 以为是直接贪心，按最短的课程排序，或者按最早结束的排序，都不行，这里就要用反悔贪心，见答案
    //而且这里写法必须是先按lastday排序，再反悔时间长的课，不能翻过来先按duration排，再反悔lastday，因为你可能先选了很多截止日很
    // 晚的短课，导致你在遇到截止日很早的课时，已经没法补救。
    //https://leetcode.cn/problems/course-schedule-iii/solutions/2436667/tan-xin-huan-neng-fan-hui-pythonjavacgoj-lcwp/
    public static int scheduleCourse(int[][] courses) {
        int rs=0;
        Arrays.sort(courses,(a,b)->{//按lastday来排
            if(a[1]==b[1]){
                return a[0]-b[0];
            }
            return a[1]-b[1];
        });
        //存duration就行了
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());

        int day=0;
        for(int i=0;i<courses.length;i++){
            if(day+courses[i][0]<=courses[i][1]){
                rs++;
                day+=courses[i][0];
                pq.offer(courses[i][0]);
            }else {
                if(!pq.isEmpty()&&pq.peek()>courses[i][0]){//当前面这个课时比现在这个长的才反悔
                    int temp=pq.poll();
                    day=day-temp+courses[i][0];
                    pq.offer(courses[i][0]);
                }
            }
        }
        return rs;//return pq.size()也对
    }
}
