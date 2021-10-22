package tree;

public class FlattenBinaryTreetoLinkedList {
    //10/10/2021 写不出了
    public void flatten(TreeNode root) {
        dfs(root);
    }
    TreeNode dfs(TreeNode root){//开始想的是dfs需要返回2个node，即左边这一遛节点捋直之后的第一个点和最后一个点。其实不需要。
        //只要返回捋直之后的最后一个点，因为当前root本来就知道第一个点就是root。left。然后把dfs左边返回的node（即最后一个点）连上
        //root。right就行了。
        if(root==null){
            return null;
        }
        if(root.left==null&&root.right==null){
            return root;
        }
        TreeNode l=dfs(root.left);
        TreeNode r=dfs(root.right);
        if(l!=null){
            l.right=root.right;//注意root.right和r要分清楚
            root.right=root.left;
            root.left=null;
        }
        if(r!=null){
            return r;
        }else{
            return l;
        }
    }
}
