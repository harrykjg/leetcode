import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by 502575560 on 7/10/16.
 */
public class BinaryTreePreorderTraversal {
    //july2017 有点虚也一次过,记住是由于是stack,所以先加又再加左
    public List<Integer> preorderTraversal(DataStruct.tree.TreeNode root) {
        List<Integer> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        Stack<DataStruct.tree.TreeNode> st=new Stack();
        st.push(root);
        while(!st.isEmpty()){
            DataStruct.tree.TreeNode temp=st.pop();
            rs.add(temp.val);
            if(temp.right!=null){
                st.push(temp.right);
            }
            if(temp.left!=null){
                st.push(temp.left);
            }
        }
        return rs;

    }
}
