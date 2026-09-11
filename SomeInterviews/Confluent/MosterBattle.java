package SomeInterviews.Confluent;

import java.util.*;

public class MosterBattle {
    /*
    You are given a hierarchy of monsters represented as a n-ary tree. Each monster has a unique name, a list of monsters it can defeat, and a hostile status. A monster marked with an asterisk ("*") is considered hostile.

Given a list of monsters and the root monster, return a list of non-hostile monsters that can defeat all hostile monsters in the hierarchy.

Each monster is defined by:

name: A string representing the monster's name.
isHostile: A boolean indicating if the monster is hostile (true) or not (false).
toBeat: A list of monsters that this monster can defeat.
Constraints:

The number of monsters in the hierarchy is between
1
1 and
10
4
10
4
 .
Each monster's name is unique and consists of lowercase and uppercase English letters.
The hierarchy forms a valid tree structure (no cycles).
There is at least one non-hostile monster in the hierarchy.
Example 1:

Input:

           Dragon
         /    |    \
    Goblin   Ghost   Vampire
     /  \             |
   Imp  Werewolf*   Zombie
     |      |
  Slime*  Demon
Output: ["Goblin", "Dragon"]

Explanation: "Goblin" can defeat both "Slime" and "Werewolf" (hostile). Since "Goblin" can defeat all hostile monsters, "Dragon" indirectly defeats all hostile monsters as well.

Example 2:

Input:

             Dragon
               |
            Skeleton
          /          \
      Goblin       Chimera
    /         \           |
  Demon     Zombie      Ghost*
   |           |
 Cerebus*  Lava Beast*
Output: ["Skeleton", "Dragon"]

Example 3:

Input:

            Demon*
              |
            Slime*
Output: []
Hint 1
Consider processing the hierarchy from the bottom up, starting at the leaf monsters.

Hint 2
Each monster must track the cumulative number of hostile monsters within its own defeated subtree.

Hint 3
A post-order traversal allows you to pass aggregated counts back up the recursion stack for comparison.
     */
    //原来题目直接给了所有的monster，那么就算出有几个monster，然后dfs自底向上找这个monster能beat几个，能全部beat就加入结果集
    public List<Monster> findEligibleMonsters(List<Monster> monsters, Monster root) {
        // TODO: Implement findEligibleMonsters logic.
        List<Monster> rs=new ArrayList<>();
        if(root.isHostile){//如果root直接是hostile那直接返回空
            return rs;
        }
        int num=0;
        for (Monster mo:monsters){
            if(mo.isHostile){
                num++;
            }
        }
        dfs(root,num,rs);
        return rs;
    }

    int dfs(Monster root,int num,List<Monster> rs){
        if(root==null){
            return 0;
        }
        List<Monster> neighbour=root.toBeat;
        int count=0;
        for (Monster mo:neighbour){
            count+=dfs(mo,num,rs);
        }
        if(root.isHostile){
            return count+1;
        }else{
            if(count==num){
                rs.add(root);
            }
            return count;
        }
    }

    /*
    Follow-up:
You are given a hierarchy of monsters represented as a n-ary tree. Each monster has a unique name, a list of monsters it can defeat, a hostile status, and an associated cost. A monster marked with an asterisk (*) is considered hostile.

Given the root monster, return a list of non-hostile monsters that can collectively defeat all hostile monsters in the tree while minimizing the total cost.

Each monster is defined by:

name: A string representing the monster's name.
cost: An integer representing the cost associated with selecting this monster.
isHostile: A boolean indicating if the monster is hostile (true) or not (false).
toBeat: A list of monsters that this monster can defeat.
Constraints:

The number of monsters in the hierarchy is between
1
1 and
10
4
10
4
 .
Each monster's name is unique and consists of lowercase and uppercase English letters.
The hierarchy forms a valid tree structure (no cycles).
There is at least one non-hostile monster in the hierarchy.
All costs are non-negative integers.
Example 1:

Input:

               Dragon-100
                   |
               Skeleton-80
              /          \
          Goblin-60       Chimera-10
        /         \           |
     Demon-30   Zombie-50   Ghost*
       |           |
     Cerebus*   LavaBeast*
Output: ["Goblin", "Chimera"]

Explanation:

"Goblin" (cost 60) can defeat both "Demon" and "Zombie", who in turn can defeat "Cerebus" and "LavaBeast" respectively. "Chimera" (cost 10) can defeat "Ghost". The total cost is 60 + 10 = 70, which is the lowest possible to cover all hostile monsters.
Alternative selections like "Skeleton" (cost 80) or "Dragon" (cost 100) are more expensive.
Example 2:

Input:

                Titan-200
              /           \
         Behemoth-90     Leviathan-10
           /     \             \
      Hydra-40  Kraken*     Serpent-30
                          /           \
                      Naga*         Merman*
Output: ["Behemoth", "Leviathan"]
Hint 1
Process the hierarchy from leaves to root so that each node can make decisions based on complete information about its descendants.

Hint 2
Define your recursive return value as the minimum cost to neutralize all hostile threats within the current subtree, accounting for whether the current node is included or excluded.

Hint 3
When encountering a hostile monster, propagate an infinite cost upward to signal that it must be covered by an ancestor, forcing the optimal choice higher in the tree.
     */
    //是dp思想，dp[node] =在“没有祖先已经被选中”的情况下，解决 node 整个 subtree 中所有 hostile monster 的最小 cost
    // 如下图，是选goblin=60能搞定两个monster还是不选goblin，让subtree自己解决
    /*
    Goblin cost = 60
    /        \
 Demon30    Zombie50
    |          |
 Cerebus*   LavaBeast*
     */

    private static final long INF = Long.MAX_VALUE / 4;//gpt说是常见写法，因为不想让skipCost += childCost;越界，我觉得写成max integer
    //也行吧，但是type是要long，这样后面应该不会越界吧
    // node -> 是否选择这个 node
    private Map<Monster, Boolean> choose = new HashMap<>();//直接用set记录selected也行
    public List<Monster> getMinimumCostMonsters(Monster root) {
        long minCost = dfs(root);
        // 如果 root 本身 hostile，而且没有任何祖先可以 defeat 它，那么不存在合法方案
        if (minCost >= INF) {
            return new ArrayList<>();
        }
        List<Monster> result = new ArrayList<>();
        // 根据第一遍 DP 的 decision 恢复答案
        buildResult(root, result);
        return result;
    }
    /*
     * 返回：在没有祖先已经被选择的情况下，解决 node 整个 subtree 所需的最小 cost
     */
    private long dfs(Monster node) {
        /*
         * 如果当前 node 本身就是 hostile：
         * 当前能递归到这里，说明祖先都没有被选。
         * hostile 自己又不能被选，
         * children 也不能反过来 defeat 它，
         * 所以当前方案不可能。
         */
        if (node.isHostile) {
            return INF;
        }
        // Option 1: 选择当前 node
        long selectCost = node.cost;
        // 一旦选择当前 node， 它可以覆盖整个 subtree，所以不需要再递归 children。
        // Option 2: 不选择当前 node
        long skipCost = 0;
        for (Monster child : node.toBeat) {
            //等所有child都算完才能得出总的children的cost，因此后面可以比较当前点的cost和所有children的cost是没问题
            long childCost = dfs(child);
            if (childCost >= INF) {
                skipCost = INF;
                break;
            }
            skipCost += childCost;
            if (skipCost >= INF) {
                skipCost = INF;
                break;
            }
        }
        // 选择成本更低
        if (selectCost <= skipCost) {
            choose.put(node, true);
            return selectCost;
        }
        // 不选当前节点，让 children 自己解决
        choose.put(node, false);
        return skipCost;
    }

    private void buildResult(Monster node, List<Monster> result) {
        if (node.isHostile) {
            return;
        }
        boolean selected = choose.getOrDefault(node, false);
        if (selected) {
            /*
             * 当前 node 被选择后，
             * 整个 subtree 已经被覆盖，
             * 不要再往下面加 monster。
             */
            result.add(node);
            return;
        }
        // 当前 node 不选，答案来自各个 child subtree
        for (Monster child : node.toBeat) {
            buildResult(child, result);
        }
    }
}

class Monster {
    String name;
    List<Monster> toBeat;
    boolean isHostile;
    int cost;
    public Monster(String name, List<Monster> toBeat, boolean isHostile) {
        this.name = name;
        this.toBeat = toBeat;
        this.isHostile = isHostile;
    }
    @Override
    public String toString() {
        return name;
    }
}

