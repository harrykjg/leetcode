package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AllElementsinTwoBinarySearchTrees {
//9/13/2021  直接各自preorder成2个list再merge
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1=preorder(root1);
        List<Integer> l2=preorder(root2);
        int i=0;
        int j=0;
        List<Integer> rs=new ArrayList<>();
        while (i<l1.size()&&j<l2.size()){
            if (l1.get(i)<l2.get(j)){
                rs.add(l1.get(i++));
            }else {
                rs.add(l2.get(j++));
            }
        }
        while (i<l1.size()){
            rs.add(l1.get(i++));
        }
        while (j<l2.size()){
            rs.add(l2.get(j++));
        }
        return rs;
    }
    List<Integer> preorder(TreeNode root){
        List<Integer> rs=new ArrayList<>();
        if (root==null){
            return rs;
        }
        Stack<TreeNode> st=new Stack<TreeNode>();
        TreeNode cur=root;
        while (cur!=null){
            st.push(cur);
            cur=cur.left;
        }
        while (!st.isEmpty()){
             cur=st.pop();
            rs.add(cur.val);
            if (cur.right!=null){
                TreeNode temp=cur.right;
                while (temp!=null){
                    st.push(temp);
                    temp=temp.left;
                }
            }
        }
        return rs;
    }
}
