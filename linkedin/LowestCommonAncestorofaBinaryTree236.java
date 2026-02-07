package linkedin;

import 灵神.链表二叉树回溯.树.TreeNode;

import java.util.*;

public class LowestCommonAncestorofaBinaryTree236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }
        if(root==p||root==q){
            return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if (left!=null&&right!=null){
            return root;
        }else if(left!=null){
            return left;
        }
        return right;
    }
    //iterative不会
    /*
    问的gpt

     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // parent map: child -> parent
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        parent.put(root, null);//先加了null，后面找p的parent时会用到
        stack.push(root);

        // build parent pointers until we have found both p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {//就是当p或q还没被记录的时候，就继续遍历，实际上就是用stack来dfs
            TreeNode cur = stack.pop();              //left或right顺序没有讲究，就是都遍历

            if (cur.left != null) {
                parent.put(cur.left, cur);
                stack.push(cur.left);
            }
            if (cur.right != null) {
                parent.put(cur.right, cur);
                stack.push(cur.right);
            }
        }

        // collect all ancestors of p
        Set<TreeNode> ancestors = new HashSet<>();
        TreeNode x = p;
        while (x != null) {//到null了说明到头了
            ancestors.add(x);//自己也加进去了，这样找q的时候就有可能会遇到p了
            x = parent.get(x);
        }

        // walk q upwards until hitting an ancestor of p
        TreeNode y = q;
        while (!ancestors.contains(y)) {
            y = parent.get(y);
        }
        return y;
    }
}
