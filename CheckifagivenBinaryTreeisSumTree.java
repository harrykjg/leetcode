
//https://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-sumtree/
public class CheckifagivenBinaryTreeisSumTree {

    boolean checkSumTree(TreeNode node){
        if(node==null){
            return true;
        }
        if(node.left==null&&node.right==null){
            return true;
        }
        int sumleft=check(node.left);
        int sumright=check(node.right);
        if(node.val==sumleft+sumright){
            return checkSumTree(node.left)&&checkSumTree(node.right);
        }
        return false;
    }
    int check(TreeNode node){
        if(node==null){
            return 0;
        }
        return node.val+check(node.left)+check(node.right);
    }


}
