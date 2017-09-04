package DataStruct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 502575560 on 8/24/17.
 */
public class SearchRangeinBinarySearchTree {
    //自己想的一次过,就是inorder这样看到符合条件的value就加入结果集里就是按升序了
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        List<Integer> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        helper(root,k1,k2,rs);
        return rs;
    }
    void helper(TreeNode root,int k1,int k2, List<Integer> rs){
        if(root==null){
            return;
        }
        helper(root.left,k1,k2,rs);
        if(root.val>=k1&&root.val<=k2){
            rs.add(root.val);
        }
        helper(root.right,k1,k2,rs);

    }
}
