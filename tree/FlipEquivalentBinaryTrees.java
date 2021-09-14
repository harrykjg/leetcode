package tree;


public class FlipEquivalentBinaryTrees {
    //9/9/2021
    public boolean flipEquiv(MaximumDepthofBinaryTree.TreeNode root1, MaximumDepthofBinaryTree.TreeNode root2) {
        if(root1==null&&root2==null){
            return true;
        }
        if((root1!=null&&root2==null)||(root2!=null&&root1==null)){
            return false;
        }
        if(root1.val!=root2.val){
            return false;
        }
        return (flipEquiv(root1.left,root2.left)&&flipEquiv(root1.right,root2.right))||(flipEquiv(root1.left,root2.right)&&flipEquiv(root1.right,root2.left));
    }
}
