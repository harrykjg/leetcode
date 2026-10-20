package SomeInterviews.reddit;

import java.util.*;

public class RelatedCommunityFinder {
    /*
    You are given two lists describing the following relationships on a social platform:

communityToFollowers: A 2D list where the
i
th
i
th
  element (0-indexed) represents a list of follower IDs (e.g., "F0", "F1", "F2") that follow community
C
i
C
i
​
 .
followerToCommunities: A 2D list where
j
th
j
th
  element represents a list of community IDs (as strings, e.g., "C0", "C1", "C2") that are followed by the follower
F
j
F
j
​
 .
Given a community index communityIdx, return a list of all distinct community IDs that share at least one follower with the community at communityIdx, excluding communityIdx itself.

Note that only direct shared-follower relationships are considered. Do not include communities that are connected only through chains of relationships.

If communityIdx is out of bounds or the community at that index has no followers, return an empty list.

Constraints:

0 ≤ communityIdx < 10⁴
0 ≤ communityToFollowers.size() ≤ 10⁴
0 ≤ the number of followers per community ≤ 10⁴
Each follower may follow up to 10³ communities.
Example 1:

Input: communityToFollowers = [["F0", "F2"], ["F0", "F1"], ["F1"], ["F2"]], followerToCommunities = [["C0", "C1"], ["C1", "C2"], ["C0", "C3"]], communityIdx = 3
Output: ["C0"]
Explanation: Community at index 3 ("C3") is followed by "F2". "F2" also follows "C0", so "C0" shares a follower with "C3".

Example 2:

Input: communityToFollowers = [["F0", "F2"], ["F0", "F1"], ["F1"], ["F2"]], followerToCommunities = [["C0", "C1"], ["C1", "C2"], ["C0", "C3"]], communityIdx = 1
Output: ["C0", "C2"]

Example 3:

Input: communityToFollowers = [["F0"], []], followerToCommunities = [["C0"]], communityIdx = 1
Output: []
Hint 1
Treat the two input arrays as a bipartite graph; you only need to move from the target community to its followers, and then immediately to the other communities those followers engage with.

Hint 2
Accumulate discovered community IDs in a hash set during iteration to automatically filter duplicates, then simply remove the original community identifier before finalizing the output list.
     */
    //就是给定一个communityID，去看他的followers都follow了什么别的community，这样这些community就是和这个给定的communityID share follower了
    //不需要map，这里直接copy gpt
    public List<String> getRelatedCommunities(List<List<String>> communityToFollowers, List<List<String>> followerToCommunities, int communityIdx) {
        if (communityIdx < 0 || communityIdx >= communityToFollowers.size()) {
            return new ArrayList<>();
        }
        Set<String> set = new HashSet<>();
        List<String> followers = communityToFollowers.get(communityIdx);
        for (String foll : followers) {
            // "F2" -> 2
            int idx = Integer.parseInt(foll.substring(1));
            if (idx < 0 || idx >= followerToCommunities.size()) {
                continue;
            }
            // 直接根据下标找到该 Follower 关注的所有 Community
            List<String> communities = followerToCommunities.get(idx);
            set.addAll(communities);
        }
        // 排除自己
        set.remove("C" + communityIdx);

        return new ArrayList<>(set);
    }
    /*
    Follow-up:
Extend the function to find communities related to the community at communityIdx through both direct and indirect shared-follower connections, up to a specified number of steps.

A community is considered related if it can be reached by repeatedly traversing direct shared-follower links, with the maximum number of steps controlled by the integer degree.

If degree = 1, return all communities that share at least one follower with the target community.
For larger degree values, each additional step includes communities directly related to those discovered in the previous step.
Return all unique community IDs that are reachable from the target community within at most degree steps, but do not include the community at communityIdx itself.

Constraints:

0 ≤ degree ≤ 10⁴
0 ≤ communityIdx < 10⁴
Example 1:

Input: communityToFollowers = [["F0", "F2"], ["F0", "F1"], ["F1"], ["F2"]], followerToCommunities = [["C0", "C1"], ["C1", "C2"], ["C0", "C3"]], degree = 2, communityIdx = 3
Output: ["C0", "C1"]
Explanation: Both "C0" and "C1" are reachable from "C3" in at most 2 steps:

Step 1: Community at index 3 ("C3") shares "F2" with "C0", so "C0" is found.
Step 2: "C0" shares "F0" with "C1", so "C1" is found.
Example 2:

Input: communityToFollowers = [["F0", "F2"], ["F0", "F1"], ["F1"], ["F2"]], followerToCommunities = [["C0", "C1"], ["C1", "C2"], ["C0", "C3"]], degree = 2, communityIdx = 1
Output: ["C0", "C2", "C3"]

Example 3:

Input: communityToFollowers = [["F0"], ["F0"]], followerToCommunities = [["C0", "C1"]], degree = 0, communityIdx = 0
Output: []
Hint 1
Visualize the input as a bipartite graph where communities and followers are nodes, and shared membership forms the edges.

Hint 2
Implement a level-by-level BFS expansion using a queue for the current frontier and a hash set to track all discovered communities, stopping after exactly degree iterations.
     */
    //注意题目说的是找community to community的关系，是要通过follower建立的。indirect的也算，然后距离小于给定的degree。
    // 但是建图的时候注意，communityToFollowers和followerToCommunities按理来说是等效的，
    //所以这题其实和常见的bfs有点不一样，因为他有community和follower两种角色，题目给了这两个list，那么还有必要用一个map建图吗？
    //其实不需要，就是里面的F0，C1这样的你得处理取得int部分
    public List<String> getRelatedCommunitiesWithDegree(List<List<String>> communityToFollowers, List<List<String>> followerToCommunities, int degree, int communityIdx) {
        // TODO: Implement getRelatedCommunitiesWithDegree logic
        List<String> rs=new ArrayList<>();
        if(degree<=0){
            return rs;
        }
        Queue<Integer> q=new LinkedList<>();
        q.offer(communityIdx);
        Set<Integer> memo=new HashSet<>();
        memo.add(communityIdx);
        int dist=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int com=q.poll();
                //拿follower
                List<String> follower=communityToFollowers.get(com);
                for(String fol:follower){
                    //通过follower再去拿community
                    int id=Integer.valueOf(fol.substring(1));
                    List<String> comus=followerToCommunities.get(id);
                    for(String c:comus){
                        int comId=Integer.valueOf(c.substring(1));
                        if(!memo.contains(comId)){
                            memo.add(comId);
                            q.offer(comId);
                            rs.add("C"+comId);
                        }
                    }
                }
            }
            dist++;
            if (dist>=degree){
                break;
            }
        }
        return rs;
    }

}
