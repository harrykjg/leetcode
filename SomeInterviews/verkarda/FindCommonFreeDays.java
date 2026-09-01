package SomeInterviews.verkarda;

import java.util.*;

public class FindCommonFreeDays {
    /*
    Given a list of time blocks [person_id, start_day, end_day] where each block indicates a person's busy days (inclusive),
     generate an array of days where all people are free.
Each person_id is used only to group intervals for the same person, which should be merged accordingly. Days are 1-indexed, meaning the count starts from day 1.
Find all days when everyone is simultaneously available.
Example 1:
Input: intervals = [[1, 1, 2], [1, 4, 5]]
Output: [3]
Explanation: Person 1 is busy on days [1, 2] and [4, 5]. Therefore, free days should be [3].
Example 2:
Input: intervals = [[1, 1, 3], [2, 2, 4], [3, 3, 5]]
Output: []
Example 3:
Input: intervals = [[1, 1, 2], [2, 3, 4], [3, 5, 6]]
Output: []
Hint 1
Hint 2
What Interviewers May Ask Next
How would you adapt this approach if the day range was massive but contained few intervals?
What modifications are needed if the requirement changes to finding days where at least K people are free?
Can you reduce the auxiliary space complexity while maintaining linear time relative to the input size?
     */
    //其实不必先按 person_id 分组，因为我们最终要找的是“所有人都 free”，只要任何一个人 busy，这一天就不能选。
    // 所以直接把所有 busy interval 做全局 merge 就行。
    public int[] everyoneAvailable(int[][] blocks) {
        // TODO: Implement everyoneAvailable logic
        List<int[]> merge=new ArrayList<>();
        Arrays.sort(blocks,(a, b)->a[1]-b[1]);
        merge.add(blocks[0]);
        for (int i=1;i<blocks.length;i++){
            if(merge.get(merge.size()-1)[2]+1>=blocks[i][1]){//注意要+1，因为比如【1，2】，【3，4】那么是应该merge的
                merge.get(merge.size()-1)[2]=Math.max(merge.get(merge.size()-1)[2],blocks[i][2]);
            }else{
                merge.add(blocks[i]);
            }
        }
        if(merge.size()==1){
            return new int[]{};
        }
        int last=merge.getLast()[2];
        int[] pre=merge.get(0);
        List<Integer> rs=new ArrayList<>();
        for (int i=1;i<merge.size();i++){
            for (int day=pre[2]+1;day<merge.get(i)[1];day++){//注意是pre[2]+1
                rs.add(day);
            }
            pre=merge.get(i);
        }

        return rs.stream().mapToInt(Integer::intValue).toArray();
    }
    /*
    Follow-up 1:
Given a list of time blocks [person_id, start_day, end_day], where each block indicates a person's busy days (inclusive), and an integer p representing the minimum number of people required to be free, determine all days when at least p people are simultaneously available.
Example 1:
Input: intervals = [[1, 1, 3], [2, 4, 5]], p = 1
Output: [1, 2, 3, 4, 5]
Explanation: At least 1 person is free every day.
Example 2:
Input: intervals = [[1, 1, 3], [2, 2, 5], [3, 4, 6]], p = 2
Output: [1, 6]
Example 3:
Input: intervals = [[1, 1, 3], [2, 2, 5], [3, 4, 6], [4, 6, 8]], p = 3
Output: [1, 7, 8]
Hint 1
Start by isolating each person's schedule and merging their overlapping busy periods to prevent counting the same individual twice on the same day.
Hint 2
Instead of iterating through every interval for each day, consider using a difference array to mark busy boundaries and compute daily busy counts via a running sum.
     */
    //线扫描吧,gpt写的不是以前那种建一个新的line class，这里只用map就行
    public int[] atLeastPAvailable(int[][] intervals, int p) {
        // TODO: Implement atLeastPAvailable logic
        //假设输入是某个人没有overlap的，否则就是先按person merge
        Set<Integer> set = new HashSet<>();
        Map<Integer,Integer> map=new TreeMap<>();//key是day，value是这个天是start则+1，end则end+1天-1，即得到这一天都多少个人在忙
        //为啥是end+1呢，因为end天还是算忙的，只有end+1才算-1。数组都不需要sort,因为用了treemap按天排序，因为线扫描就是得从前往后扫
        int max=0;
        for (int i=0;i<intervals.length;i++){
            map.put(intervals[i][1],map.getOrDefault(intervals[i][1],0)+1);
            map.put(intervals[i][2]+1,map.getOrDefault(intervals[i][2]+1,0)-1);
            max=Math.max(max,intervals[i][2]);
            set.add(intervals[i][0]);
        }
        List<Integer> rs=new ArrayList<>();
        int people=set.size();
        int busy=0;
        for (int i=1;i<=max;i++){//不是遍历map，是要从day1到maxday，不需要考虑max+1day，因为是不在结果集里
//            if(!map.containsKey(i)){注意这里不能这样写，因为假如[1,3]都是busy，则day2不会出现
//                rs.add(i);
//            }

            busy+=map.getOrDefault(i,0);//注意busy是累加的，注意get（i）可能是null
            int free=people-busy;
            if(free>=p){
                rs.add(i);
            }
        }
        return rs.stream().mapToInt(Integer::intValue).toArray();
    }
    /*
    Follow-up 2:
Given a list of time blocks [person_id, start_day, end_day], where each block indicates a person's busy days (inclusive), and two integers p and x,
find all days that lie inside any stretch of at least x consecutive days during which at least p people are free.
Example 1:
Input: intervals = [[1, 2, 3], [1, 6, 7], [2, 4, 5], [2, 8, 9]], p = 1, x = 4
Output: [1, 2, 3, 4, 5, 6, 7, 8, 9]
Explanation: The entire period forms a valid streak. Output starts at day 1.
Example 2:
Input: intervals = [[1, 1, 7], [2, 4, 8], [3, 6, 10], [4, 9, 12]], p = 3, x = 2
Output: [1, 2, 3, 11, 12]
Example 3:
Input: intervals = [[1, 1, 2], [1, 5, 7], [2, 3, 4], [2, 6, 8], [3, 5, 9]], p = 1, x = 2
Output: [1, 2, 3, 4, 5, 8, 9]
Hint 1
Consider tracking busy periods using a difference array to efficiently compute daily free counts without nested loops.
Hint 2
Once you have the daily free-person counts, a single left-to-right pass can easily group consecutive valid days and filter by the minimum length x.
     */
    /*
    hack2hire 思路，就是还是线扫描，找出一个freecount数组，再在这个数组上找连续的大于x长度的并且free的人数大于p的天数
1：Group all intervals by person_id.
For each person, sort their intervals by start day and merge any that overlap or are back-to-back. This yields a minimal set of disjoint busy ranges per person.
2：Track how many people are busy each day:
 For each person, merge overlapping busy intervals using a standard interval merging approach (sorted by start time).
Walk day from 1 to maxDay, maintaining runningBusy += diff[day]. Now runningBusy is the number of people busy on that day.
3： Calculate free people per day:
For each person, determine days when they are free (not in any busy interval).
Increment a global freeCount[day] for every day the person is free.
4： Find valid streaks:
Traverse freeCount and find contiguous ranges where at least P people are free.
For every such streak that is at least X days long, add all those days to the result.
     */
}
