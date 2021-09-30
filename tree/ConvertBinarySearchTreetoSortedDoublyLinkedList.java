package tree;

import java.util.Stack;

public class ConvertBinarySearchTreetoSortedDoublyLinkedList {
    //7/22/2021 写不出.看了别人的，其实就是按inorder顺序，maintain一个头一个尾节点，便利的时候apend就行了
    TreeNode first;
    TreeNode last;
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root==null){
            return null;
        }
        Stack<TreeNode> st=new Stack<TreeNode>();
        st.push(root);
        TreeNode cur=st.peek();
        while (cur.left!=null){//这里要写不改变结构的inorder
            st.push(cur.left);
            cur=st.peek();
        }
        while (!st.isEmpty()){
            cur=st.pop();
            if (first==null){
                first=cur;
                last=cur;
            }else {
                cur.left=last;//这个容易漏，容易想成左边本来就是连上，其实是要连上last才对
                last.right=cur;
                last=cur;
            }
            if (cur.right!=null){//加右节点时要一直加完他的左节点
                cur=cur.right;
                while (cur!=null){
                    st.push(cur);
                    cur=cur.left;
                }
            }
        }
        first.left=last;
        last.right=first;
        return first;
    }

}
