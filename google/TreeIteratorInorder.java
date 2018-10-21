package google;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yufengzhu on 10/17/18.
 */
public class TreeIteratorInorder {
    public static void main(String[] a){
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.right.left=new TreeNode(5);
        root.right.right=new TreeNode(6);
        root.right.left.right=new TreeNode(7);

        TreeIteratorInorder ti=new TreeIteratorInorder(root);
        System.out.println(ti.hasNext());
        System.out.println(ti.next());
        System.out.println(ti.hasNext());
        System.out.println(ti.next());
        System.out.println(ti.hasNext());
        System.out.println(ti.next());
        System.out.println(ti.hasNext());
        System.out.println(ti.next());
        System.out.println(ti.hasNext());
        System.out.println(ti.next());
        System.out.println(ti.hasNext());
        System.out.println(ti.next());
        System.out.println(ti.hasNext());
        System.out.println(ti.next());
        System.out.println(ti.hasNext());
//        System.out.println(ti.next());
//        System.out.println(ti.hasNext());


        ArrayList<Integer> al=new ArrayList<>();
        al.add(1);
        al.add(3);
        al.add(5);
        al.add(7);
//        al.add(1);
        System.out.println(ti.isFromPath(al,root));

    }
    //谷歌面经就是写一个tree的iterator inorder打印， 应该对吧
    Stack<TreeNode> st;
    public  TreeIteratorInorder(TreeNode root){
        st=new Stack<>();
        st.push(root);
    }
    public boolean hasNext() {
        return !st.isEmpty();
    }

    public int next() {
        TreeNode cur=st.peek();
        int rs=0;
        if(cur.left==null){
            rs=cur.val;
            st.pop();
            if(cur.right!=null){
                st.push(cur.right);
            }
        }else{
            st.push(cur.left);
            cur.left=null;
            rs=next();
        }
        return rs;
    }

    //判断一个list里的node是不是从root到leath,我假设数有重复的数值
    public boolean isFromPath(List<Integer> list,TreeNode root){
        return helper(0,list,root);
    }
    boolean helper(int i, List<Integer> list, TreeNode root){
        if(root==null){
            return false;
        }
        if(i==list.size()-1){
            if(root.val==list.get(i)&&root.left==null&&root.right==null){
                return true;
            }
            return false;
        }
        if(root.val==list.get(i)){
            return helper(i+1,list,root.left)||helper(i+1,list,root.right);
        }else{
            return false;
        }
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}