package SomeInterviews.doordash;

import java.util.ArrayList;
import java.util.List;

public class TreeMximumPath {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(9, false);
        root.left = new TreeNode(2, false);
        root.left.left = new TreeNode(100, true);
        root.left.right = new TreeNode(-5, true);
        root.right = new TreeNode(-3, false);
        root.right.right = new TreeNode(55, true);
        root.right.left = new TreeNode(4, true);
        TreeMximumPath tm = new TreeMximumPath();
        System.out.println(tm.maxPath2(root));

        /*
                            9
                        2       -3
                    100   -5   4   55
         */

    }

    //https://www.1point3acres.com/bbs/thread-796259-1-1.html
    int rs = Integer.MIN_VALUE;

    public int maxPath(TreeNode root) {
        if (root == null) {
            return rs;
        }
        helper(root);
        return rs;
    }

    TreeNode helper(TreeNode node) {
        if (node == null) {
            return new TreeNode(0, false);//这里不返回null，而是返回这个，这样就省了上层检测左右是否为空再检测是否active了，直接检测是否active
        }
        TreeNode left = helper(node.left);
        TreeNode right = helper(node.right);

        if (left.active && right.active) {
            rs = Math.max(rs, node.value + left.value + right.value);
            return new TreeNode(Math.max(left.value, right.value) + node.value, true);
        }
        if (left.active) {

            return new TreeNode(node.value + left.value, true);
        }
        if (right.active) {

            return new TreeNode(node.value + right.value, true);
        }
        return new TreeNode(node.value, node.active ? true : false);
    }

    //10/6/2021  注意是要2个node的path，因此必须left和right都是active的才计算max，单独的点不算
    int rs2 = Integer.MIN_VALUE;

    public int maxPath2(TreeNode root) {
        if (root == null) {
            return rs2;
        }
        helper2(root);
        return rs2;
    }

    TreeNode helper2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = helper2(root.left);
        TreeNode right = helper2(root.right);
        if ((left != null && left.active) && (right != null && right.active)) {
            rs2 = Math.max(left.value + right.value + root.value, rs2);
            int temp = root.value + Math.max(left.value, right.value);
            return new TreeNode(temp, true);
        } else if (left != null) {
            if (left.active) {
                return new TreeNode(left.value + root.value, true);
            } else {
                return new TreeNode(left.value + root.value, false);
            }
        } else if (right != null) {
            if (right.active) {
                return new TreeNode(right.value + root.value, true);
            } else {
                return new TreeNode(right.value + root.value, false);
            }
        }
        return root;
    }

    static class TreeNode {
        Integer value;
        TreeNode left;
        TreeNode right;
        boolean active;

        public TreeNode(Integer value, boolean active) {
            this.value = value;
            this.active = active;
        }
    }
}
