package DataStruct;

/**
 * Created by yufengzhu on 7/26/18.
 */
public class SubtreeAnotherTree {
    public static void main(String[] a){
        SubtreeAnotherTree sa=new SubtreeAnotherTree();
        TreeNode n=new TreeNode(3);
        n.left=new TreeNode(4);
        n.right=new TreeNode(5);
        n.left.left=new TreeNode(1);
        n.left.right=new TreeNode(2);
        n.left.left.left=new TreeNode(0);
        TreeNode n2=new TreeNode(4);
        n2.left=new TreeNode(1);
        n2.right=new TreeNode(2);
        System.out.print(sa.isSubtree(n,n2));

    }
    //preorder之后再查substring那个方法就不写了，recursive的方法写的很烂，要练。发现这里虽然isSubtree和helper方法输入参数和返回参数都一样，以为可以合并成一个，结果就是不能!而same tree那题就可以！
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null&&t==null){
            return true;
        }
        if(s==null&&t!=null){
            return false;
        }
        if(s!=null&&t==null){
            return false;
        }

        if(helper(s,t)){
            return helper(s.left,t.left)&&helper(s.right,t.right);
        }
        return isSubtree(s.left,t)||isSubtree(s.right,t);

    }
    boolean helper(TreeNode n1,TreeNode n2){
        if(n1==null&&n2==null){
            return true;
        }
        if(n1==null&&n2!=null){
            return false;
        }
        if(n1!=null&&n2==null){
            return false;
        }
        if(n1.val==n2.val){
            return helper(n1.left,n2.left)&&helper(n1.right,n2.right);
        }
        return false;
    }
}
