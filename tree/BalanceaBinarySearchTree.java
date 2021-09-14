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
}
