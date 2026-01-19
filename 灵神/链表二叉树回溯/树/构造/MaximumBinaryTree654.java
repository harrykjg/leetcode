package 灵神.链表二叉树回溯.树.构造;


import 灵神.链表二叉树回溯.树.TreeNode;

import java.util.Deque;
import java.util.LinkedList;


public class MaximumBinaryTree654 {
    static void main() {
        int[] n={3,2,1,6,0,5};
        System.out.println(constructMaximumBinaryTree(n));
    }
    //12/25/2025 n方的方法能想到，这次看他单调栈的做法,自己想的话想不出
    //https://leetcode.cn/problems/maximum-binary-tree/solutions/1762400/zhua-wa-mou-si-by-muse-77-myd7/
    static public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> st=new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            TreeNode now=new TreeNode(nums[i]);
            if(st.isEmpty()){
                st.push(now);
                continue;
            }
            if(st.peek().val>nums[i]){
                st.peek().right=now;
                st.push(now);
                continue;
            }

            while (!st.isEmpty()&&st.peek().val<nums[i]){
                now.left=st.pop();
            }
            if (!st.isEmpty()){//比如第一个例子，st里有6，0，现在5 来了，前面的while循环把0 pop了，但是这个5还要成为6的right才行，否则不对
                                            // 先在这个和上面的代码写的不一样，不知道他咋写的
                st.peek().right=now;
            }
            st.push(now);
        }

        return st.peekLast();
    }
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode() {}
        public TreeNode(int val) { this.val = val; }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

