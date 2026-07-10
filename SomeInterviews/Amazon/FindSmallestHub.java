package SomeInterviews.Amazon;

import java.util.List;

public class FindSmallestHub {
    /*
    AI Insights
A cloud provider needs to schedule a new computing task on one of its available server hubs. Your goal is to design an algorithm that selects the most efficient hub for the job based on capacity and availability.

You are given n hubs, indexed from 0 to n - 1. You are provided with the following information:

An integer array capacities of length n, where capacities[i] is the total processing capacity of hub i.
A 2D array of strings intervals, where intervals[i] contains a list of strings representing time intervals already booked on hub i. Each interval is a string in the format "start:end", indicating a booking from start to end (inclusive).
An integer requiredCapacity, which is the processing capacity needed for the new task.
A string taskInterval in the format "start:end", representing the start and end time for the new task, inclusive.
You need to find the index of a hub that meets all of the following criteria:

Its capacity is sufficient for the task (i.e., capacities[i] >= requiredCapacity).
It has no scheduling conflicts (i.e., the taskInterval does not overlap with any of the existing intervals for that hub).
Among all hubs that satisfy the first two conditions and have the smallest capacity.
If multiple hubs satisfy all three conditions and share the same minimal capacity, you should return the one with the smallest index. If no hub meets the requirements, return -1.

Notice that two intervals [a, b] and [c, d] are considered overlapping if a < d and c < b.

Constraints

capacities and intervals have the same length.
Example 1:

Input: capacities = [100, 150, 90, 200], intervals = [["10:20"], ["15:25"], ["5:15", "25:35"], ["0:40"]], requiredCapacity = 100, taskInterval = "21:24"
Output: 1
Explanation:

Hub 0: Capacity (100) meets the requirement. The task interval [21, 24] does not overlap with the booked interval [10, 20] because 20 < 21. This hub is a valid candidate.
Hub 1: Capacity (150) is sufficient, but the task [21, 24] overlaps with its booked interval [15, 25]. This hub is not available.
Hub 2: Capacity (90) is less than the required capacity of 100. This hub is not suitable.
Hub 3: Capacity (200) is sufficient, but the task [21, 24] overlaps with its booked interval [0, 40]. This hub is not available.
The only valid hub is Hub 0. Therefore, the output is 0.

Example 2:

Input: capacities = [150, 100, 200, 100], intervals = [["10:20"], [], ["0:100"], []], requiredCapacity = 100, taskInterval = "30:40"
Output: 1

Example 3:

Input: capacities = [100, 200], intervals = [["20:30"], []], requiredCapacity = 100, taskInterval = "10:20"
Output: 0


Hint 1
Convert the time strings into integer start and end values to simplify the overlap comparison logic.

Hint 2
Maintain the index and capacity of the best candidate found so far; update only when a valid hub offers a strictly lower capacity.
     */

    static void main() {
        FindSmallestHub fs=new FindSmallestHub();
    }
    public int findSmallestHub(List<Integer> capacities, List<List<String>> intervals, int requiredCapacity, String taskInterval) {
        int min=Integer.MAX_VALUE;
        int rs=-1;
        for (int i=0;i<capacities.size();i++){
            if(capacities.get(i)>=requiredCapacity){
                if(!overlap(intervals.get(i),taskInterval)){
                    if(min>capacities.get(i)){
                        min=capacities.get(i);
                        rs=i;
                    }
                }
            }
        }
        return rs;

    }
    boolean overlap(List<String> ls, String task){
        String[] tasktime=task.split(":");
        int t1=Integer.valueOf(tasktime[0]);
        int t2=Integer.valueOf(tasktime[1]);
        for (String s:ls){
            String[] interval=s.split(":");
            int s1=Integer.valueOf(interval[0]);
            int s2=Integer.valueOf(interval[1]);
            if(s2<=t1||s1>=t2){
                continue;
            }else{
                return true;
            }
        }
        return false;
    }
}
