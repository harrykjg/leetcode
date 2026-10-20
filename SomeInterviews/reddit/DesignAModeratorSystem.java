package SomeInterviews.reddit;

import java.util.*;

public class DesignAModeratorSystem {

}
/*
You are designing a system that tracks moderator privileges for an online community using a time-ordered log of moderation actions. Each log record captures when a user gains or loses moderator status. The system must support permission checks for removing moderators and also return the current moderator list in the correct hierarchy.

In this system, moderators have a ranking based on when they most recently became moderators. Among all active moderators, a user who became a moderator earlier has a higher level than a user who became a moderator later.

Implement the ModSystem class:

ModSystem(List<String> logs) Initializes the moderator system using the given chronological list of log entries. Each entry is a string in the format "<targetUser>,<action>,<actorUser>,<timestamp>", where

targetUser is the user whose moderator status is affected.
action is either "added" or "removed", indicating whether the user is granted or revoked moderator status.
actorUser is the user who performed the action.
timestamp is a string represented integer.
The log entries are sorted in increasing order by timestamp.

boolean canRemoveMod(String targetUser, String actorUser) Returns true only if all of the following conditions are met. Otherwise, returns false.

Both targetUser and actorUser are currently moderators.
actorUser is not the same user as targetUser.
actorUser has a higher moderator level than targetUser according to the ranking rules.
List<String> getModRanking() Returns the current list of moderators ordered from highest level to lowest level.

Constraints:

You may assume all the operations in the logs are valid.
All log entries are sorted in increasing order by timestamp.
1
1 ≤ logs.size() ≤
10
5
10
5

All user names are non-empty strings without commas.
All timestamps are positive numbers.
Example:

Input:
["ModSystem", "getModRanking", "canRemoveMod", "canRemoveMod"]
[["alice,added,admin,1", "bob,added,alice,2", "charlie,added,bob,3"], [], ["bob", "alice"], ["alice", "bob"]]

Output:
[null, ["alice", "bob", "charlie"], true, false]

Explanation:

ModSystem modSystem = new ModSystem(logs);
modSystem.getModRanking(); // Returns ["alice", "bob", "charlie"]. Three users are moderators; "alice" is the most senior and "charlie" is least senior.
modSystem.canRemoveMod("bob", "alice"); // Returns true. Both are moderators, and "alice" became a moderator before "bob", so "alice" is more senior.
modSystem.canRemoveMod("alice", "bob"); // Returns false. "bob" is less senior than "alice" and cannot remove her.

Hint 1
Track each user's last successful addition timestamp separately from their current active status, as reactivation resets their seniority position.

Hint 2
When comparing ranks, remember that an earlier timestamp means a higher level, so the actor's recorded timestamp must be strictly less than the target's to grant removal rights.


 */
//貌似就是比较先后顺序的的系统吧，到底moderator是什么不用管，能不能remove无非是比较二者的timestamp。ranking也是比较timestamp，add和remove的时候
//更新timestamp。但是只有初始化的时候才有add或者remove，因此这个rank一开始就是定了的，直接初始化的时候就记录rank就行了
class ModSystem {
    Map<String,Integer> map=new HashMap<>();
    Set<String> rank;
    public ModSystem(List<String> logs) {
        // TODO: Initialize ModSystem
        rank=new LinkedHashSet<>();
        for(String log:logs){
            String[] l=log.split(",");
            if(l[1].equals("added")){
                map.put(l[0],Integer.valueOf(l[3]));
                rank.add(l[0]);
            }else if (l[1].equals("removed")){
                //map.put(l[1],-1);应该直接remove，不要写-1
                map.remove(l[0]);
                rank.remove(l[0]);//用LinkedHashSet好点
            }
        }
    }

    public boolean canRemoveMod(String targetUser, String actorUser) {
        // TODO: Implement canRemoveMod logic
        if(!map.containsKey(targetUser)||!map.containsKey(actorUser)||targetUser.equals(actorUser)){
            return false;
        }
        int actor=map.get(actorUser);
        int target=map.get(targetUser);
        if(actor<target){
            return true;
        }
        return false;
    }

    public List<String> getModRanking() {
        // TODO: Implement getModRanking logic
        return rank.stream().toList();
    }
}
/*
Follow-up 1:
Extend the previous question to support multiple independent communities. Each community maintains its own moderator ranking, and all rules from the previous question apply independently within each community. Each log entry now has the format: "<community>,<targetUser>,<action>,<actorUser>,<timestamp>".

Implement the updated ModSystem class:

ModSystem(List<String> logs) Parses all logs in chronological order and maintains the moderator state and moderator ranking separately for each community.

boolean canRemoveMod(String community, String targetUser, String actorUser) Applies the same removal rules as the previous question, but scoped to the given community. Returns false if the community does not exist.

List<String> getModRanking(String community) Returns the current list of moderators ordered from highest level to lowest level for the given community. Returns an empty list if the community does not exist.

Constraints:

All constraints and guarantees from the previous question still apply.
Example:

Input:
["ModSystem", "getModRanking", "canRemoveMod", "canRemoveMod", "getModRanking"]
[["java,alice,added,admin,1", "java,bob,added,alice,2", "java,charlie,added,bob,3"], ["java"], ["java", "bob", "alice"], ["java", "alice", "bob"], ["python"]]

Output:
[null, ["alice", "bob", "charlie"], true, false, []]

Explanation:

ModSystem modSystem = new ModSystem(logs);
modSystem.getModRanking("java"); // Returns ["alice", "bob", "charlie"], the moderator ranking for "java" from most senior to least senior.
modSystem.canRemoveMod("java", "bob", "alice"); // Returns true; "alice" is more senior than "bob" in the "java" community.
modSystem.canRemoveMod("java", "alice", "bob"); // Returns false; "bob" joined after "alice" and is less senior.
modSystem.getModRanking("python"); // Returns [], since "python" does not exist.
 */
//就是多加了一层community，加一层map，key作为community，别的和第一问一样
class ModSystem {
    Map<String,Set<String>> rank=new HashMap<>();
    Map<String,Map<String,Integer>> map=new HashMap<>();
    public ModSystem(List<String> logs) {
        // TODO: Initialize ModSystem
        for(String log:logs){
            String[] l=log.split(",");
            map.putIfAbsent(l[0],new HashMap<>());
            if(l[2].equals("added")){
                map.get(l[0]).put(l[1],Integer.valueOf(l[4]));
                rank.putIfAbsent(l[0],new LinkedHashSet<>());
                rank.get(l[0]).add(l[1]);
            }else if (l[2].equals("removed")){
                //map.put(l[1],-1);应该直接remove，不要写-1
                map.get(l[0]).remove(l[1]);
                rank.get(l[0]).remove(l[1]);//用LinkedHashSet就可以直接remove，否则还得遍历找
            }
        }

    }

    public boolean canRemoveMod(String community, String targetUser, String actorUser) {
        // TODO: Implement canRemoveMod logic
        if(!map.containsKey(community)){
            return false;
        }
        if (!map.get(community).containsKey(targetUser) || !map.get(community).containsKey(actorUser)
                || targetUser.equals(actorUser)) {
            return false;
        }
        int actor=map.get(community).get(actorUser);
        int target=map.get(community).get(targetUser);
        if(actor<target){
            return true;
        }
        return false;

    }

    public List<String> getModRanking(String community) {
        // TODO: Implement getModRanking logic
        if(!rank.containsKey(community)){
            return new ArrayList<>();
        }
        return rank.get(community).stream().toList();
    }
    /*
    Follow-up 2:
Extend the previous problem to support moderator demotion within a community.

Enhance the updated ModSystem class by adding the following method:

void demote(String community, String user) Demotes the specified moderator within the given community.

A demotion decreases the user's moderator rank by exactly one position in the current ordering. For example, if the current moderator ranking from highest to lowest is "user1 -> user2 -> user3", then demoting "user1" results in "user2 -> user1 -> user3".
If user is already the lowest-ranked moderator, or if user is not currently a moderator in the specified community, the operation has no effect.
Constraints:

All constraints and guarantees from the previous question still apply.
Each log entry has the format "<community>,<targetUser>,<action>,<actorUser>,<timestamp>".
Example:

Input:
["ModSystem", "getModRanking", "demote", "getModRanking", "canRemoveMod", "canRemoveMod"]
[["java,user1,added,admin,1", "java,user2,added,admin,2", "java,user3,added,admin,3"], ["java"], ["java", "user1"], ["java"], ["java", "user1", "user2"], ["java", "user3", "user1"]]

Output:
[null, ["user1", "user2", "user3"], null, ["user2", "user1", "user3"], true, true]

Explanation:

ModSystem modSystem = new ModSystem(logs);
modSystem.getModRanking("java"); // Returns ["user1", "user2", "user3"], the initial ranking after all add operations.
modSystem.demote("java", "user1"); // Demotes "user1" by one position in the ranking.
modSystem.getModRanking("java"); // Returns ["user2", "user1", "user3"].
modSystem.canRemoveMod("java", "user1", "user2"); // Returns true. "user2" is now more senior than "user1".
modSystem.canRemoveMod("java", "user3", "user1"); // Returns true. "user1" is still more senior than "user3".

     */
    //多加一个demote方法，就是把某个人排位往后挪一位，
    //既然rank已经是排序的，那么就和他后一位交换一下不就行了，但是这是linkedhashset,没法直接找到下一个元素，因此得换成list
    //但是这里还有一个重要问题。之前的 canRemoveMod() 是根据加入时间判断，现在不能再这样判断了！因为 Demotion 改变的是 Moderator
    // 的实际排名，而不是加入时间。那样的话就不需要map存他们的时间了，直接通过rank,因为已经不是按时间排了
    class ModSystem {
        Map<String, List<String>> rank = new HashMap<>();
//        Map<String, Map<String, Integer>> map = new HashMap<>(); 不需要了
        public ModSystem(List < String > logs) {
            // TODO: Initialize ModSystem
            for (String log : logs) {
                String[] l = log.split(",");
                if (l[2].equals("added")) {
                    rank.putIfAbsent(l[0], new ArrayList<>());
                    rank.get(l[0]).add(l[1]);
                } else if (l[2].equals("removed")) {
                    rank.get(l[0]).remove(l[1]);//O(N),改进是用dublylinkedlist
                }
            }

        }

        public boolean canRemoveMod (String community, String targetUser, String actorUser){
        // TODO: Implement canRemoveMod logic
            //没有了map所以这里不能基于map确定同一个community里是有有target和actor，但是可以基于后面的indexof确定是否存在
            if (!rank.containsKey(community) || targetUser.equals(actorUser)) {
                return false;
            }
            int actor = rank.get(community).indexOf(actorUser);
            int target = rank.get(community).indexOf(targetUser);
            if (actor!=-1&&target!=-1&&actor < target) {
                return true;
            }
            return false;
        }

        public List<String> getModRanking (String community){
        // TODO: Implement getModRanking logic
            if (!rank.containsKey(community)) {
                return new ArrayList<>();
            }
            return rank.get(community).stream().toList();
        }
        public void demote(String community, String user) {
            // TODO: Implement demote logic
            if(!rank.containsKey(community)||rank.get(community).indexOf(user)==-1){
                return;
            }
            int idx=rank.get(community).indexOf(user);
            if(idx+1<rank.get(community).size()){
                rank.get(community).set(idx,rank.get(community).get(idx+1));
                rank.get(community).set(idx+1,user);
                //或者直接Collections.swap(rank.get(community), idx, idx + 1);
            }
        }
    }
}