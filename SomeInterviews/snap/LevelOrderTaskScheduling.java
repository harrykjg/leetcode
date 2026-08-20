package SomeInterviews.snap;

import java.util.*;

public class LevelOrderTaskScheduling {
    /*
    Level Order Task Scheduling
Medium
Graph


Interview Stages
Screening
Frequency
Asked By
SNAPCHAT-icon
NETFLIX-icon
Last Reported
1 weeks ago

AI Insights
(This question is a variation of the LeetCode question 210. Course Schedule II. If you haven't completed that question yet, it is recommended to solve it first.)

A company is asked to efficiently schedule a set of tasks, each identified by a unique, non-empty lowercase string. Tasks have dependencies: each dependency is a pair [fromTask, toTask], meaning fromTask must finish before toTask can begin.

The company has unlimited machines available, so at any given time, all tasks that are ready (no unmet dependencies) can be run in parallel. Your job is to determine the schedule by grouping tasks that can be executed together at each time step. The schedule should be a list of groups, where:

Each group contains all tasks that start at the same time.
The order of groups reflects the overall execution order (earlier groups must finish before later groups can begin).
Within each group, tasks must be ordered in lexicographic order.
Return an empty list if it's not possible to finish all tasks.

Constraints:

1 ≤ dependencies.length ≤ 104
Each dependency is a pair of two non-empty lowercase strings.
Task names are unique across all dependencies.
Example 1:

Input: dependencies = [["a", "b"], ["c", "d"], ["e", "f"]]
Output: [["a", "c", "e"], ["b", "d", "f"]]
Explanation: Tasks "a", "c", and "e" do not depend on any other task, so they can all start at the same time in the first group. "b" depends on "a", "d" depends on "c", and "f" depends on "e", so all three can only be started after their respective prerequisites have completed. They form the second group and can be run together.

Example 2:

Input: dependencies = [["a", "b"], ["c", "b"]]
Output: [["a", "c"], ["b"]]

Example 3:

Input: dependencies = [["a", "b"], ["b", "a"]]
Output: []
     */
    //居然一次过。就是拓扑排序，遍历的时候用个q的size就可以确定一堆一堆的现阶段的degree=0的值了
    public List<List<String>> parallelTaskOrder(List<List<String>> dependencies) {
        // TODO: Implement parallelTaskOrder logic
        List<List<String>> rs=new ArrayList<>();
        Map<String,Integer> degree=new HashMap<>();
        Map<String, Set<String>> map=new HashMap<>();
        Set<String> set=new HashSet<>();//就是保存所有出现的string，方便看谁没有入度
        for (List<String> dep:dependencies){
            String from=dep.get(0);
            String to=dep.get(1);
            map.putIfAbsent(from,new HashSet<>());
            map.get(from).add(to);
            degree.put(to,degree.getOrDefault(to,0)+1);
            set.add(from);
            set.add(to);
        }
        Queue<String> q=new LinkedList<>();
        List<String> first=new ArrayList<>();
        for(String s:set){
            if(!degree.containsKey(s)){
                first.add(s);
                q.offer(s);
            }
        }

        while (!q.isEmpty()){
            int size=q.size();
            List<String> al=new ArrayList<>();
            for (int i=0;i<size;i++){
                String cur=q.poll();
                al.add(cur);
                Set<String> neigbour=map.get(cur);
                if(neigbour!=null){
                    for(String nei:neigbour){
                        degree.put(nei,degree.get(nei)-1);
                        if (degree.get(nei)==0){
                            q.offer(nei);
                        }
                    }
                }
            }
            Collections.sort(al);
            rs.add(al);
        }
        return rs;

    }
}
