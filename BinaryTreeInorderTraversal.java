import java.util.*;

/**
 * Created by 502575560 on 7/10/16.
 */
public class BinaryTreeInorderTraversal {

    //2016年7月,基本一次把递归和非递归的写出来了,非递归的就开始写错了用queue,其实要用stack
    List<Integer> rs=new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {


        helper(root);
        return rs;

    }

    public void helper(TreeNode n){
        if(n==null){
            return;
        }
        helper(n.left);
        rs.add(n.val);
        helper(n.right);
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        if(root==null){
            return rs;
        }
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        while(!st.isEmpty()){
            TreeNode temp=st.peek();
            if(temp.left==null){
                rs.add(temp.val);
                st.pop();
                if(temp.right!=null){
                    st.push(temp.right);
                }
            }else{
                st.push(temp.left);
                temp.left=null;
            }
        }
        return rs;

    }
    //04/20/2020,不怎么记得，重新想的也过了
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> rs=new ArrayList<>();
        if (root==null){
            return rs;
        }

        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        while (!st.isEmpty()){
            TreeNode cur=st.peek();
            if(cur.left==null){
                TreeNode temp=st.pop();
                rs.add(temp.val);
                if(temp.right!=null){
                    st.push(temp.right);
                }
            }else{
                st.push(cur.left);
                cur.left=null;
            }
        }
        return rs;
    }
    //05/24/2020
    public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        while (!st.isEmpty()){
           TreeNode node=st.peek();
           if(node.left!=null){
               st.push(node.left);
               node.left=null;
               continue;
           }else{
               st.pop();
               rs.add(node.val);
           }
           if(node.right!=null){
               st.push(node.right);
           }
        }
        return rs;
    }
}
