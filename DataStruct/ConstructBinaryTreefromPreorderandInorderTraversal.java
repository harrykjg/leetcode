package DataStruct;

/**
 * Created by yufengzhu on 8/15/18.
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {
    //8／14／2018，写的还算顺，改了2次accept
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return helper(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    TreeNode helper(int[] preOrder, int b1,int e1,int[] inOrder,int b2,int e2){
        if(b1>e1||b2>e2||b1>=preOrder.length||b1<0){//这里漏了b1>=preOrder.length||b1<0
            return null;
        }

        int rootval=preOrder[b1];

        int index=0;
        for(int i=b2;i<e2;i++){//这里漏写等号
            if(inOrder[i]==rootval){
                index=i;
                break;
            }
        }
        int len=index-b2;
        TreeNode left=helper(preOrder,b1+1,b1+1+len-1,inOrder,b2,index-1);
        TreeNode right=helper(preOrder,b1+1+len,e1,inOrder,index+1,e2);
        TreeNode root=new TreeNode(rootval);
        root.left=left;
        root.right=right;
        return root;
    }
}
