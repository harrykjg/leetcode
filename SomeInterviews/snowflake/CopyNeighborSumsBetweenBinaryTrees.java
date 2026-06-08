package SomeInterviews.snowflake;

import java.util.List;

public class CopyNeighborSumsBetweenBinaryTrees {
    //实际上就是一边遍历某个树一边加上最后一个树上，下面直接抄gpt
    public TreeNode convertSumTreeMultiple(List<TreeNode> trees) {
        int n = trees.size();
        TreeNode dest = trees.get(n - 1);
        // 如果最后一棵树是 null，但前面 source 有贡献，
        // root 需要被创建
        if (dest == null) {
            dest = new TreeNode(0);
        }
        for (int i = 0; i < n - 1; i++) {
            TreeNode src = trees.get(i);
            if (src == null) {
                continue;
            }
            apply(src, dest);
        }
        return dest;
    }
    private void apply(TreeNode src, TreeNode dest) {
        if (src == null) {
            return;
        }
        int sum = src.val;
        if (src.left != null) {
            sum += src.left.val;
        }
        if (src.right != null) {
            sum += src.right.val;
        }
        dest.val += sum;
        if (src.left != null) {
            if (dest.left == null) {
                dest.left = new TreeNode(0);
            }
            apply(src.left, dest.left);
        }
        if (src.right != null) {
            if (dest.right == null) {
                dest.right = new TreeNode(0);
            }
            apply(src.right, dest.right);
        }
    }
}
