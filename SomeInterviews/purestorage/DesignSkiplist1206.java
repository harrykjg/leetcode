package SomeInterviews.purestorage;

import java.util.Random;

public class DesignSkiplist1206 {
    //4/27/2026还是很不好理解的
    //https://leetcode.com/problems/design-skiplist/solutions/441212/short-java-solution-average-90-in-time-a-c94t/
    //https://www.jointaro.com/interviews/questions/design-skiplist/?company=pure-storage
    //这个代码是直接抄gpt的
    class Skiplist {
        private static final int MAX_LEVEL = 16;//有16层，n = 10^5最高层大概 ~ log2(n) ≈ 17，但每一层有不同数量的节点
        private static final double P = 0.5;
        private final Node head;
        private final Random rand;
        static class Node {
            int val;
            // next[i] 表示：这个节点在第 i 层的下一个节点
            // 如果 next.length = 3，说明这个节点存在于 level 0,1,2
            Node[] next;
            Node(int val, int level) {
                this.val = val;
                this.next = new Node[level];
            }
        }
        public Skiplist() {
            // head 是哨兵节点，存在于所有层
            // 它相当于每一层链表的起点
            head = new Node(-1, MAX_LEVEL);
            rand = new Random();
        }
        public void add(int num) {
            Node[] update = new Node[MAX_LEVEL];//这个应该代表的是要update的层数吧，就是先初始化所成所有层，再在下面这个for循环吧pointer挪到该插入这个num
            //的位置的前一个节点，每一层都是从cur=head节点开始往后找
            Node cur = head;
            //从上往下找该插入的点的前一个点
            // 在第 level 层，num 应该插在 update[level] 后面
            for (int level = MAX_LEVEL - 1; level >= 0; level--) {
                while (cur.next[level] != null && cur.next[level].val < num) {
                    cur = cur.next[level];
                }
                update[level] = cur;
            }
            // 随机决定新节点高度
            int level = randomLevel();
            // 如果 level = 3，说明这个节点出现在 0,1,2 三层
            Node newNode = new Node(num, level);
            // 把新节点插入它出现的每一层
            for (int i = 0; i < level; i++) {
                // 原来： update[i] -> oldNext
                // 插入后：
                // update[i] -> newNode -> oldNext
                newNode.next[i] = update[i].next[i];//就是先把newnode后面接上，再把前面的节点接上newnode
                update[i].next[i] = newNode;
            }
        }
        // search的意思是把上层都当做索引，然后到最后才在第一层判断是否存在。不知道为啥不能再上层遇到的话直接判断？gpt说理论上是可以但不是标准写法，只能算优化
        // add search erase都是这样
        public boolean search(int target) {
            Node cur = head;
        //从上往下找，一步步接近元素，然后再在最后一层一举确定。
            for (int level = MAX_LEVEL - 1; level >= 0; level--) {
                // 在当前层，只要下一个节点小于 target，就继续往右走
                while (cur.next[level] != null && cur.next[level].val < target) {
                    cur = cur.next[level];
                }
                // 走不动了，就降到下一层
            }
            // 最后到了 level 0，cur 是最后一个 < target 的节点
            cur = cur.next[0];
            return cur != null && cur.val == target;
        }
        public boolean erase(int num) {
            Node[] update = new Node[MAX_LEVEL];
            Node cur = head;
            // 和 add 一样，先找每一层的前驱节点
            for (int level = MAX_LEVEL - 1; level >= 0; level--) {
                while (cur.next[level] != null && cur.next[level].val < num) {
                    cur = cur.next[level];
                }
                update[level] = cur;
            }
            // level 0 的下一个节点才可能是要删除的 num
            cur = cur.next[0];
            if (cur == null || cur.val != num) {
                return false;
            }
            // cur 可能只存在于部分层
            // 所以只遍历 cur.next.length，我以为他cur.next.length固定是16，其实不对，普通节点是这样创建Node node = new Node(num, level);
            //因此他的length是random出来的层数
            for (int i = 0; i < cur.next.length; i++) {
                // 如果这一层确实连到了 cur，就跳过 cur
                if (update[i].next[i] == cur) {
                    update[i].next[i] = cur.next[i];
                }
            }
            return true;
        }
        private int randomLevel() {
            int level = 1;
            // 每次 50% 概率升一层
            // 大部分节点低，少数节点高
            while (level < MAX_LEVEL && rand.nextDouble() < P) {
                level++;
            }
            return level;
        }
    }
}
