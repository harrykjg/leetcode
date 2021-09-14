import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by yufengzhu on 7/5/18.
 */
public class MinimumDistanceBetweenBSTNodes {
    //inorder找每个值之间差值最小的,叼，居然一次过，inorder也写对了虽然有点慌
    public int minDiffInBST(tree.TreeNode root) {
        if(root==null){
            return 0;
        }
        ArrayList<Integer> rs=new ArrayList<>();
        int diff=Integer.MAX_VALUE;
        Stack<tree.TreeNode> st=new Stack<>();
        st.push(root);
        while (!st.isEmpty()){
            tree.TreeNode temp=st.peek();
            if(temp.left==null){
                rs.add(temp.val);
                st.pop();
                if(temp.right!=null){
                    st.push(temp.right);
                }
                continue;
            }else{
                st.push(temp.left);
                temp.left=null;
                continue;
            }
        }
        for(int i=1;i<rs.size();i++){
            diff=Math.min(diff,rs.get(i)-rs.get(i-1));
        }
        return diff;
    }
}
