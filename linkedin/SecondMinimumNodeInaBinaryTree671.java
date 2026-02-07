package linkedin;
import 灵神.链表二叉树回溯.树.TreeNode;
public class SecondMinimumNodeInaBinaryTree671 {
    //想简单了，以为肯定是找第一层子节点就有答案，看回以前的解释
    Integer rs;//这里要是设成int=Integer maxvalue的话，如果答案恰好是maxvalue的话就不好判断到底是找到答案没有
    public int findSecondMinimumValue(TreeNode root) {
        if(root==null){
            return -1;
        }
        int min=root.val;
        helper(root.left,min);
        helper(root.right,min);
        if(rs==null||rs==min){
            return -1;
        }
        return rs;
    }
    void helper(TreeNode node,int min){
        if(node==null){
            return;
        }
        if(node.val>min){
            if(rs==null){
                rs=node.val;
            }else{
                rs=Math.min(node.val,rs);
            }
            //不用再往下找了，因为下面的都是大于等于此val的
        }else{//==min的情况
            helper(node.left,min);
            helper(node.right,min);
        }
    }
}
