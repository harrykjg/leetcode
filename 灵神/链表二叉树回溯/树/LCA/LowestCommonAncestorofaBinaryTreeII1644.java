package 灵神.链表二叉树回溯.树.LCA;

import 灵神.链表二叉树回溯.树.TreeNode;

public class LowestCommonAncestorofaBinaryTreeII1644 {
    static void main() {

    }
    //2/7/2026貌似和lca1的区别就是这里是有可能找不到p或q的,那么这题实际上就是要遍历整个树，要找到p和q 。看来是不能只用一个lowestCommonAncestor
    //搞定，必须另外dfs，为啥呢，因为单独的调用lowestCommonAncestor recursion，你不能只返回left或只返回right，因为必须p和q都要找到，
    //但是原来的lca1的写法就是找到一个就可以返回，而这里又必须找到left和right才返回，所以很矛盾
    int count=0;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }
        TreeNode node=dfs(root,p,q);
        if(count==2){
            return node;
        }
        return null;

    }

    TreeNode dfs(TreeNode root, TreeNode p,TreeNode q){
        if(root==null){
            return null;
        }
        TreeNode left=dfs(root.left,p,q);
        TreeNode right=dfs(root.right,p,q);//和lca1的关键不同点这里也是，就是要先dfs再返回，那里是遇到p或q就直接返回了
        if(root==p||root==q){
            count++;
            return root;
        }

        if(left!=null&&right!=null){
            return root;
        }
        if(left!=null){
            return left;
        }else if(right!=null){
            return right;
        }

        return null;
    }

    //3/16/2026不会了
    int count2=0;
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

    }
}
