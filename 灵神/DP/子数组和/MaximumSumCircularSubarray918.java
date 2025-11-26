package 灵神.DP.子数组和;

public class MaximumSumCircularSubarray918 {
    public static void main(String[] args) {
        int[] nums={5,-3,5};
        System.out.println(maxSubarraySumCircular(nums));
    }
    //不好想，要背。我想可以用sliding window，但是gpt说不行，一般滑动窗口是要有单调性的，既所有数都是正数。
    //貌似就是这种环形题就是这个Kadane 算法。maximumsubarray也可以用prefixsum就行了https://leetcode.cn/problems/maximum-subarray/solutions/2533977/qian-zhui-he-zuo-fa-ben-zhi-shi-mai-mai-abu71/
    public static int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int curMax = 0, maxSum = Integer.MIN_VALUE;
        int curMin = 0, minSum = Integer.MAX_VALUE;

        for (int x : nums) {
            total += x;

            // 最大子数组和（普通 Kadane）
            curMax = Math.max(x, curMax + x);
            maxSum = Math.max(maxSum, curMax);

            // 最小子数组和（反向 Kadane）
            curMin = Math.min(x, curMin + x);
            minSum = Math.min(minSum, curMin);
        }

        // 如果所有数都是负数，maxSum 就是最大（即最不负的）那个数
        // 此时 total == minSum，不能用 total - minSum（会变成 0，非法，因为子数组不能为空）
        if (maxSum < 0) {
            return maxSum;
        }

        // 否则，答案是 “不跨环的最大子数组” 与 “跨环的：总和 - 最小子数组” 取最大
        return Math.max(maxSum, total - minSum);//5，-3,5这个例子就是拿7和10比

    }
    /*GPT 解释
    短答案：一般意义上的 sliding window 不适合这题，至少不能像「左右指针一推一缩就完事」那种常规写法。这个题标准做法还是 Kadane（最大子段和）+ 最小子段和。
我来慢慢解释为啥👇

1️⃣ 什么时候 sliding window 好用？
典型的滑动窗口（双指针）套路是这样的：

右指针不断往右扩展窗口

当窗口不满足某个条件时，左指针往右收缩窗口，直到恢复满足条件

这个套路依赖一个“单调性”：

比如：元素全是 正数，你要求「和 ≤ K 的最长子数组」

如果当前窗口和 > K，再往右扩只会更大，所以必须缩左边

这样你能确定：左指针只会往右走，右指针只会往右走，整体 O(n)


也就是说：滑动窗口适合有约束条件的、或者所有元素正数、或者「长度固定 / 上下界」这种问题。

2️⃣ 这题的目标是什么？

在一个（环形）数组中，找任意长度的子数组，使 sum 最大。

特点：

子数组长度不固定
数组里有正有负

没有“和 ≤ K”这种硬约束，只要 sum 越大越好

这就导致一个问题：

窗口是扩还是缩，你没有一个明确的条件来控制指针移动。

3️⃣ 为什么普通 sliding window 会翻车？举个例子
我们看一个简单的一维（非环形）例子就够了：
nums = [4, -1, 2, -10, 5]

最大子数组和是：[4, -1, 2]，和为 5。
如果你用滑动窗口：

一开始右指针扩：

sum = 4 → 看起来不错
sum = 4 + (-1) = 3 → 变小了，但你不能断定要不要缩
sum = 3 + 2 = 5 → 又变大了
sum = 5 + (-10) = -5 → 很差，想缩？

如果你因为 sum 变小就缩左边，很多时候会把后面能组成更大和的部分删掉；
但你又不能说“只要 sum < 0 就全部清空”，因为中间也可能负但总体最优从更前面开始。
可见：

你没有一个简单规则决定「什么时候该收缩左指针」，滑动窗口就失去了意义。
这就是为什么最大子数组和的经典算法是 Kadane 的 DP，而不是双指针滑窗。

4️⃣ 那环形的情况，更难用 sliding window
环形版相当于两种情况：

不跨环：就是普通最大子数组和（Kadane）

跨环：相当于选一段“不要的连续子数组”最小和，把总和减掉它

第二种情况的标准思路：

maxSub = 普通最大子数组和

minSub = 普通最小子数组和

sum = 总和

若 minSub == sum（说明全是负数），答案就是 maxSub
否则答案是：max(maxSub, sum - minSub)

这里用到的还是 Kadane 思想，对 最大和 和 最小和 各跑一遍。

5️⃣ 那有没有“广义”上的滑动窗口解法？
如果你把「滑动窗口」放得很宽泛，比如：

用前缀和数组
再用双端队列维护最小前缀和
在 环形展开 的数组上做一些操作

是可以搞出一些看起来像“窗口”的写法（比如做「环形最小/最大子数组」之类），但那已经不是我们平时说的那种 two pointers sliding window 模板了，更接近“前缀和 + 单调队列”。
这题在面试/刷题语境下：

面试官/题解讨论的不会是 sliding window，而是 Kadane / DP / 前缀和。


6️⃣ 标准解：Kadane + 环形（Java）
给你顺手贴一下标准写法（非滑窗），方便你对比思路：
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int curMax = 0, maxSum = Integer.MIN_VALUE;
        int curMin = 0, minSum = Integer.MAX_VALUE;

        for (int x : nums) {
            total += x;

            // 最大子数组和（普通 Kadane）
            curMax = Math.max(x, curMax + x);
            maxSum = Math.max(maxSum, curMax);

            // 最小子数组和（反向 Kadane）
            curMin = Math.min(x, curMin + x);
            minSum = Math.min(minSum, curMin);
        }

        // 如果所有数都是负数，maxSum 就是最大（即最不负的）那个数
        // 此时 total == minSum，不能用 total - minSum（会变成 0，非法，因为子数组不能为空）
        if (maxSum < 0) {
            return maxSum;
        }

        // 否则，答案是 “不跨环的最大子数组” 与 “跨环的：总和 - 最小子数组” 取最大
        return Math.max(maxSum, total - minSum);
    }
}


✅ 总结回答你的问题

这题能用 sliding window 做吗？

如果你指的是那种「左右指针扩缩、窗口里维护一个 sum」的经典滑动窗口模板：不适合 / 几乎做不了，因为：

没有单调性或硬约束去指导“何时收缩”

元素有正有负，目标又是最大和，逻辑会非常混乱，甚至错误

这题的正确典型解法是：

一维 Kadane 求最大子数组和

一维 Kadane 求最小子数组和

加一个环形的拆分逻辑

如果你愿意，我可以给你画一张小图，对比：

普通最大子数组（Kadane）

环形最大子数组（怎么拆成“中间扔掉最小和子数组”）
这样你会记得非常牢。




     */
}
