package DataStruct;

/**
 * Created by 502575560 on 8/24/17.
 */
public class InsertNodeinaBinarySearchTree {
    //开始还想错了,没把新的node和其parent连上
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        if(root==null){
            return node;
        }

        if(root.val<node.val){
            if(root.right==null){
                root.right=node;
            }else{
                insertNode(root.right,node);
            }
        }else{
            if(root.left==null){
                root.left=node;
            }else{
                insertNode(root.left,node);
            }

        }
        return root;
    }

}
