package DataStruct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 9/27/18.
 */
public class PinterestPathBetween2nodesInBST {
    public static void main(String[] args){
        PinterestPathBetween2nodesInBST pp=new PinterestPathBetween2nodesInBST();
        TreeNode n=new TreeNode(10);
        n.left=new TreeNode(6);
        n.right=new TreeNode(15);
        n.left.left=new TreeNode(3);
        n.left.right=new TreeNode(8);
        n.right.left=new TreeNode(13);
        n.right.right=new TreeNode(20);
        n.right.right.left=new TreeNode(16);
        n.right.right.right=new TreeNode(22);

        pp.path(n.right,n.right.right.right,n);
    }
    //pinterest店面,找两个node在bst中的path，是LCA的变形，得先找到LCA再从LCA去分别到两份Node去构造path
    public List<TreeNode> path(TreeNode n1,TreeNode n2,TreeNode root){
        TreeNode lca=lca(n1,n2,root);

        List<TreeNode> rs=new ArrayList<>();
        ArrayList<TreeNode> al=new ArrayList<>();
        TreeNode cur=lca;
        if(lca==n1){//
            while (cur!=null){
                rs.add(cur);
                if(cur.val==n2.val){
                    return rs;
                }
                if(cur.val>n2.val){
                    cur=cur.left;
                }else{
                    cur=cur.right;
                }
            }
        }else if(lca==n2){
            while (cur!=null){
                rs.add(cur);
                if(cur.val==n1.val){
                    return rs;
                }
                if(cur.val>n1.val){
                    cur=cur.left;
                }else{
                    cur=cur.right;
                }
            }
        }else{//n1和n2在lca左右两边时
            if(n1.val<n2.val){//n1在左边时
                while (cur!=null){
                    rs.add(0,cur);
                    if(cur.val==n1.val){
                        break;
                    }
                    cur=cur.left;
                }
                cur=lca.right;
                while (cur!=null){
                    rs.add(cur);
                    if(cur.val==n2.val){
                        break;
                    }
                    cur=cur.right;
                }
            }else{//n2在右边时
                while (cur!=null){
                    rs.add(0,cur);
                    if(cur.val==n2.val){
                        break;
                    }
                    cur=cur.left;
                }
                cur=lca.right;
                while (cur!=null){
                    rs.add(cur);
                    if(cur.val==n1.val){
                        break;
                    }
                    cur=cur.right;
                }
            }
        }
        return rs;
    }
    //和普通二叉树写法一样，就是加了n1，n2和root的大小判断
    TreeNode lca(TreeNode n1,TreeNode n2,TreeNode root){
        if(root==null){
            return null;
        }
        if(n1==root||n2==root){
            return root;
        }
        if(n1.val<root.val&&n2.val<root.val){
            return lca(n1,n2,root.left);
        }else if(n1.val>root.val&&n2.val>root.val){
            return lca(n1,n2,root.right);
        }else{
            TreeNode left=lca(n1,n2,root.left);
            TreeNode right=lca(n1,n2,root.right);
            if(left!=null&&right!=null){
                return root;
            }

            if(left==null){
                return right;
            }
            return left;
        }
    }

}
