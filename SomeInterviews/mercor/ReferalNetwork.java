package SomeInterviews.mercor;

import java.util.*;

public class ReferalNetwork {
    /*
    The coding is to implement a referral network, 这一轮coding跟OAI的social network面经题很相似
Part 1: implement a referral network, 有推荐人和被推荐人，跟social network那里的follower and followee的关系差不多
Part 2: built on part 1, implement two ranking functions that rank top k users by 1/ total number of candidates
 from a user, count both direct and indirect candidates, 2/ flow centrality, the number of times that a user appears in the social path
Part 3: implement a function to estimate expected network size after days N, every referrer has probability p to make a
successful referral every day, and each person can make at most 10 successful referral lifetime
公开版本强调几个约束：
- 不能 self-referral
- 一个 candidate 不能有多个 referrer
- 不能形成 cycle
- 能查 direct referrals
     */
    class ReferralNetwork {
        Map<String, Set<String>> children = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        public boolean addReferral(String referrer, String candidate) {
            if (referrer.equals(candidate)) {
                return false;
            }
            // candidate 已经有 referrer
            if (parent.containsKey(candidate)) {
                return false;
            }
            // 防止 cycle：
            // candidate 不能是 referrer 的 ancestor
            String cur = referrer;
            while (cur != null) {
                if (cur.equals(candidate)) {
                    return false;
                }
                cur = parent.get(cur);
            }
            children.computeIfAbsent(referrer, k -> new HashSet<>()).add(candidate);
            parent.put(candidate, referrer);
            return true;
        }
        //看user 直接refer了几个人
        public Set<String> getDirectReferrals(String user) {
            return children.getOrDefault(user, Collections.emptySet()
            );
        }
//part2.1 从这个 user 出发，所有 direct + indirect descendants 数量。找top k个refer（直接加间接）最多人的人
        private int getReach(String user, Map<String, Integer> memo) {
            if (memo.containsKey(user)) {
                return memo.get(user);
            }
            int total = 0;
            for (String child : children.getOrDefault(user, Collections.emptySet())) {
                total += 1 + getReach(child, memo);
            }
            memo.put(user, total);
            return total;
        }
//找top k个
        public List<String> topKByReach(int k, Set<String> users) {
            Map<String, Integer> memo = new HashMap<>();
            PriorityQueue<UserScore> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.score));
            for (String user : users) {
                int score = getReach(user, memo);
                pq.offer(new UserScore(user, score));
                if (pq.size() > k) {
                    pq.poll();
                }
            }
            List<String> result = new ArrayList<>();
            while (!pq.isEmpty()) {
                result.add(pq.poll().user);
            }
            Collections.reverse(result);
            return result;
        }
    }
    part2.2



    class UserScore {
        String user;
        int score;

        UserScore(String user, int score) {
            this.user = user;
            this.score = score;
        }
    }
}
