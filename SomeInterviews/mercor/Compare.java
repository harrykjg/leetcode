package SomeInterviews.mercor;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Compare {
    /*
    1. Coding（45-min）：给定 n 个数字和一个比较函数 cmp(a,b)，该函数有 10% 的deterministic错误概率
    (用同样的argument X, Y call cmp(X,Y)若是错的 call几次都是错的)，如何从 n 个数字中找出前 k 个？

Follow-up A: 如何最小化 cmp(a,b) 的调用次数？
Follow-up B：如果 cmp(a,b) 调用速度非常慢（每次调用约 5 秒），如何最小化实际运行时间？
*/
    //这个是gpt的代码，由于有10%的错误率，因此要把这个数和所有的数都比较，大的分++，小的分--，即把风险都分散了，最后按分数取前topk个，这样是n方个比较

    interface ComparatorAPI {
        // > 0 表示 a > b
        // < 0 表示 a < b
        int cmp(int a, int b);
    }
    //用来算score
    static class Pair {
        int id;//index作为id，这样输入是可以有duplicate的
        int val;
        int score;
        Pair(int id, int val) {
            this.id = id;
            this.val = val;
        }
    }
    public static List<Integer> topK(int[] nums, int k, int rounds, ComparatorAPI api) {
        int n = nums.length;
        Pair[] nodes = new Pair[n];//存每个num的socre
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int result = api.cmp(nums[i], nums[j]);
                if (result > 0) {
                    nodes[i].score++;
                } else if (result < 0) {
                    nodes[j].score++;
                }
            }
        }
        // score 高的认为更可能是真正较大的元素
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.score - b.score);
        for (Pair node : nodes) {
            pq.offer(node);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().val);
        }
        Collections.reverse(result);
        return result;
    }
    private static String makeKey(int a, int b) {
        if (a < b) {
            return a + "#" + b;
        }
        return b + "#" + a;
    }
    //Follow-up A,不让每个元素和所有 n - 1 个元素比较，只随机选 comparisonsPerElement 个不同的 opponent。
    public List<Integer> topKSampled(int[] nums, int k, int comparisonsPerElement, ComparatorAPI api) {
        int n = nums.length;
        Pair[] nodes = new Pair[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Pair(i, nums[i]);
        }
        Random random = new Random();
        // 保证同一个 pair 不重复调用 cmp
        Set<Long> compared = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int count = 0;
            while (count < comparisonsPerElement) {
                int j = random.nextInt(n);
                if (i == j) {
                    continue;
                }
                long key = pairKey(i, j);
                if (!compared.add(key)) {
                    continue;
                }
                int result = api.cmp(nums[i], nums[j]);
                if (result > 0) {
                    nodes[i].score++;
                } else if (result < 0) {
                    nodes[j].score++;
                }
                count++;
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.score - b.score);
        for (Pair node : nodes) {
            pq.offer(node);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().val);
        }
        Collections.reverse(result);
        return result;
    }
    private long pairKey(int a, int b) {
        int x = Math.min(a, b);
        int y = Math.max(a, b);
        return ((long) x << 32) | (y & 0xffffffffL);
    }
    //followup B，用多线程
    public List<Integer> topKParallel(int[] nums, int k, ComparatorAPI api) throws Exception {
        int n = nums.length;
        Pair[] nodes = new Pair[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Pair(i, nums[i]);
        }
        ExecutorService pool = Executors.newFixedThreadPool(20);
        List<Future<int[]>> futures = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                final int a = i;
                final int b = j;
                futures.add(pool.submit(() -> {
                    int result = api.cmp(nums[a], nums[b]);
                    return new int[]{a, b, result};
                }));
            }
        }
        // 汇总结果
        for (Future<int[]> future : futures) {
            int[] result = future.get();
            int a = result[0];
            int b = result[1];
            int cmpResult = result[2];
            if (cmpResult > 0) {
                nodes[a].score++;
            } else if (cmpResult < 0) {
                nodes[b].score++;
            }
        }
        pool.shutdown();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.score - b.score);
        for (Pair node : nodes) {
            pq.offer(node);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().val);
        }
        Collections.reverse(result);
        return result;
    }
}
