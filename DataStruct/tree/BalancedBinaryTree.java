package DataStruct.tree;

/**
 * Created by 502575560 on 7/22/17.
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        root.right.right.left = new TreeNode(4);
        root.right.right.left.right = new TreeNode(4);
        BalancedBinaryTree bb = new BalancedBinaryTree();
        System.out.println(bb.isBalanced2(root));

    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int d1 = depth(0, root.left);
        int d2 = depth(0, root.right);
        if (Math.abs(d1 - d2) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);//开始这里直接返回true了就错了,应该要左右再检查,因为左右虽然深度一样,
    }                                              //但是由于深度是算最深的那个,所以说其实左右子树可能各自不平衡

    int depth(int cur, TreeNode n) {
        if (n == null) {
            return cur;
        }
        return Math.max(depth(cur + 1, n.left), depth(cur + 1, n.right));
    }

    //05/24/2020,还是搞错了，不是说看根结点左右的深度就行了，是每个子树也要是balanced的，例如[1,2,2,3,null,null,3,4,null,null,4]
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        int dleft = getDept(root.left);
        int dright = getDept(root.right);
        if (Math.abs(dleft - dright) > 1) {
            return false;
        }
        return isBalanced2(root.left) && isBalanced2(root.right);
    }

    int getDept(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int dleft = getDept(root.left);
        int dright = getDept(root.right);
        return Math.max(dleft, dright) + 1;
    }

    //6/1/2021,还是没想对，要练,应该是和 Validate Binary Search Tree不太一样。
    //看他分析top down和bottom up，体会一下怎么区别
    //https://leetcode.com/problems/balanced-binary-tree/discuss/35943/JAVA-O(n)-solution-based-on-Maximum-Depth-of-Binary-Tree 这个是o（n）的
    public boolean isBalanced3(TreeNode root) {//我这个是n方版本也accept了，因为从上到下每一层都调用了getDepth
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (Math.abs(getDepth(root.left) - getDepth(root.right)) > 1) {
            return false;
        }
        return isBalanced3(root.left) && isBalanced3(root.right);

    }

    int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }
}