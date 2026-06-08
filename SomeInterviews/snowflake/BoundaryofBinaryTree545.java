package SomeInterviews.snowflake;

import javax.swing.plaf.InsetsUIResource;
import java.util.ArrayList;
import java.util.List;

public class BoundaryofBinaryTree545 {
    //大概想到其实直接按定义走left boundry，再找出leaves，再找right boundry反过来,然后想左下角的节点不是和leaf node重复了吗，那要用set去重吗?
    //貌似不用，定义说了最左边的不属于left boundry，但最左边的是属于leaf的，刚好就不需要去重
    /*
            1
        2       3
          4
         5
         这样的情况left boundry应该只有1，2，4，而5属于leftmost leaf，因此不算left boundry，
            1
        2      3
          4      5
         这种的话4算left还是leaf？应该算leaf，因为题目说了The leftmost leaf is not in the left boundary.即4不属于left
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        rs.add(root.val);
        leftbound(root.left,rs);
        leaves(root.left,rs);//因为一开始加了root了,所以只有一个root的情况会重复，所以这样写
        leaves(root.right,rs);
        rightBound(root.right,rs);
        return rs;
    }
    public void leftbound(TreeNode root,List<Integer> rs){
        if(root==null){
            return;
        }
        if(root.left==null&&root.right==null){
            return;
        }
        rs.add(root.val);
        if(root.left!=null){
            leftbound(root.left,rs);
        }else{
            leftbound(root.right,rs);
        }
    }
    public void leaves(TreeNode root,List<Integer> rs){
        if(root== null){
            return;
        }
        if(root.left==null&&root.right==null){
            rs.add(root.val);
        }
        leaves(root.left,rs);
        leaves(root.right,rs);
    }
    //这里用recursion，或者用stack也能反着装进来吧
    public void rightBound(TreeNode root,List<Integer> rs){
        if(root==null){
            return;
        }
        if(root.left==null&&root.right==null){
            return;
        }
        if(root.right!=null){
            rightBound(root.right,rs);
        }else{
            rightBound(root.left,rs);
        }
        rs.add(root.val);
    }
//变形，如果是full complete tree的话就更简单了，直接left。left。。就是左boundry，然后要找leaves也一样吧。如果给n的话就不用建树，
// 直接是i*2+1和i*2+2，后半个n就是leaf

}
class TreeNode{
    TreeNode left;
    TreeNode right;
    int val;
}
