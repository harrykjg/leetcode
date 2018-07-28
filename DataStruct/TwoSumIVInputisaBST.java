package DataStruct;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by yufengzhu on 7/26/18.
 */
public class TwoSumIVInputisaBST {
    public static void main(String[] a){

        TwoSumIVInputisaBST ts=new TwoSumIVInputisaBST();
        TreeNode n=new TreeNode(4);
        n.left=new TreeNode(3);
        n.right=new TreeNode(2);
        n.left.left=new TreeNode(9);
        n.left.right=new TreeNode(12);
//        n.left.left.left=new TreeNode(0);
        ts.findTarget(n,3);
    }

//还是写不出recursive的方法,想着是强行写一个一个试的2sum，并利用2茶树性质判断左右，但是确实不好写，应该是这个链接的方法3 https://leetcode.com/problems/two-sum-iv-input-is-a-bst/discuss/106059/JavaC++-Three-simple-methods-choose-one-you-like
    //https://leetcode.com/problems/two-sum-iv-input-is-a-bst/solution/
    //这个是看到答案之后以为用hashmap的，然后自己想的，其实他们用hashset就行了
    public boolean findTarget(TreeNode root, int k) {
        HashMap<Integer,TreeNode> map=new HashMap<>();
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        while (!st.isEmpty()){
            TreeNode n=st.peek();
            if(map.containsKey(k-n.val)&&map.get(k-n.val)!=n){//我想的是要检测这个node不是本来自己，其实不需要，因为只有n.left==null的时候map才put进去n，并且pop掉了，所以说每个节点只会
                return true;//经过这一行代码一次，每一个经过这一行的node都是唯一一次，所以其实是不需要map.get(k-n.val)!=n这个条件的
            }

            if(n.left==null){
                st.pop();
                map.put(n.val,n);
                if(n.right!=null){
                    st.push(n.right);
                }
            }else{
                st.push(n.left);
                n.left=null;
            }

        }
        return false;
    }
}
