package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BalanceaBinarySearchTree {
    //8/18/2021 还想着inplace，结果就是inorder弄出来再重新建树
    List<TreeNode> al=new ArrayList<>();
    public TreeNode balanceBST(TreeNode root) {
        if (root==null){
            return null;
        }
        Stack<TreeNode> st=new Stack<>();
        TreeNode cur=root;
        while (cur!=null){
            st.push(cur);
            cur=cur.left;
        }
        while (!st.isEmpty()){
            TreeNode n=st.pop();
            al.add(n);
            if (n.right!=null){
                cur=n.right;
                while (cur!=null){
                    st.push(cur);
                    cur=cur.left;
                }
            }
        }
        return construct(0,al.size()-1);//这个是手机app的写法
    }
    TreeNode construct(int b,int e){
        if (b>e){
            return null;
        }
        int m=(b+e)/2;
        TreeNode left=construct(b,m-1);
        TreeNode mid=new TreeNode(al.get(m).val);
        TreeNode right=construct(m+1,e);
        mid.left=left;
        mid.right=right;
        return mid;
    }

    //10/3/2021 用的递归inorder应该简单些
    List<Integer> ls=new ArrayList();
    public TreeNode balanceBST2(TreeNode root) {
        List<Integer> al=new ArrayList<>();
        inorder(root,al);
        ls=al;
        return build(0,ls.size()-1);
    }

    TreeNode build(int b,int e){
        if(b>e){
            return null;
        }
        int m=(b+e)/2;
        TreeNode left=build(b,m-1);
        TreeNode node=new TreeNode(ls.get(m));
        TreeNode right=build(m+1,e);
        node.left=left;
        node.right=right;
        return node;
    }

    void inorder(TreeNode root,List<Integer> al){
        if(root==null){
            return;
        }
        inorder(root.left,al);
        al.add(root.val);
        inorder(root.right,al);
    }
}
