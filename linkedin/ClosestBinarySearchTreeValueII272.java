package linkedin;

import 灵神.链表二叉树回溯.树.TreeNode;

import java.util.*;

public class ClosestBinarySearchTreeValueII272 {
    static void main() {

    }
    //看回以前的用priorityqueue,其实也就是暴力把所有点入pq保留剩余的k个最接近的
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> rs=new ArrayList<>();
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->{
            if(a.diff>b.diff){
                return 1;
            }else if(a.diff==b.diff){
                return 0;
            }
            return -1;
        });
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        while (!st.isEmpty()){
            TreeNode cur=st.pop();
            double gap=Math.abs((double) cur.val-target);
            if(!pq.isEmpty()&&pq.peek().diff>gap){
                pq.offer(new Pair(gap,cur.val));
            }else if(pq.size()<k){
                pq.offer(new Pair(gap,cur.val));
            }
            if(pq.size()>k){
                pq.poll();
            }
            if(cur.left!=null){
                st.push(cur.left);
            }
            if(cur.right!=null){
                st.push(cur.right);
            }
        }
        for(Pair p:pq){
            rs.add(p.val);
        }
        return rs;
    }
    class Pair{
        double diff;
        int val;
        public Pair(double diff,int index){
            this.diff=diff;
            this.val =index;
        }
    }
    /*
    GPT的答案
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<TreeNode> pred = new ArrayDeque<>();
        Deque<TreeNode> succ = new ArrayDeque<>();

        // 1) init two stacks along the search path
        TreeNode cur = root;
        //这个就是在准备前驱和后驱的栈，那为啥要一直while呢，因为他还要保证前驱是小于target并且最接近target的。
        //(cur.val <= target)这个 cur 可以成为前驱（因为不超过 target）但也许还有更接近 target 的前驱，在哪里？
        //在 cur 的右子树里（因为右子树的值更大、更靠近 target，但仍可能 <= target）所以：
        //pred.push(cur) 记下来cur = cur.right 继续往更接近 target 的方向找
        while (cur != null) {
            if (cur.val <= target) {
                pred.push(cur);
                cur = cur.right;
            } else {
                succ.push(cur);
                cur = cur.left;
            }
        }

        // 2) pick k times
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (pred.isEmpty()) {
                ans.add(nextSucc(succ));
            } else if (succ.isEmpty()) {
                ans.add(nextPred(pred));
            } else {
                double dp = Math.abs(pred.peek().val - target);
                double ds = Math.abs(succ.peek().val - target);
                if (dp <= ds) {
                    ans.add(nextPred(pred));
                } else {
                    ans.add(nextSucc(succ));
                }
            }
        }
        return ans;
    }

    // 他就是要取pred栈顶的元素返回，但是取了之后还要保持pre栈顶是最接近target的的那咋办，由于bst，因此下一个最大的就是左边的最右节点
    private int nextPred(Deque<TreeNode> pred) {
        TreeNode node = pred.pop();
        int res = node.val;
        node = node.left;
        while (node != null) {
            pred.push(node);
            node = node.right;
        }
        return res;
    }

    // move successor iterator one step: pop node, then go to its right subtree and take leftmost chain
    private int nextSucc(Deque<TreeNode> succ) {
        TreeNode node = succ.pop();
        int res = node.val;
        node = node.right;
        while (node != null) {
            succ.push(node);
            node = node.left;
        }
        return res;
    }


     */

}
