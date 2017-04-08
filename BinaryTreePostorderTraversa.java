import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by 502575560 on 7/10/16.
 */
public class BinaryTreePostorderTraversa {

    //july2017 居然也一次过,和以前写的不一样,以前是先push 左边再右边的,但是先左之后就continue了,而我这里是没有continue的
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rs=new LinkedList<>();
        if(root==null){
            return rs;
        }
        Stack<TreeNode> st=new Stack();
        st.push(root);
        while(!st.isEmpty()){
            TreeNode temp=st.peek();
            if(temp.left==null&&temp.right==null){
                rs.add(temp.val);
                st.pop();
                continue;
            }
            if(temp.right!=null){
                st.push(temp.right);
                temp.right=null;
            }
            if(temp.left!=null){
                st.push(temp.left);
                temp.left=null;
            }

        }
        return rs;

    }

}
