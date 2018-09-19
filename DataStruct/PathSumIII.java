package DataStruct;

import java.util.ArrayList;

/**
 * Created by yufengzhu on 8/15/18.
 */
public class PathSumIII {
    public static void main(String[] args){
        PathSumIII ps=new PathSumIII();
//        TreeNode root=new TreeNode(5);
//        root.left=new TreeNode(2);
//        root.right=new TreeNode(-3);
//        root.left.left=new TreeNode(3);
//        root.right.right=new TreeNode(3);
        TreeNode root=new TreeNode(10);
        root.left=new TreeNode(5);
        root.right=new TreeNode(-3);
        root.left.left=new TreeNode(3);
        root.left.right=new TreeNode(2);
        root.right.right=new TreeNode(11);
        root.left.left.left=new TreeNode(3);
        root.left.left.right=new TreeNode(-2);
        root.left.right.right=new TreeNode(1);
        System.out.print(ps.pathSum3(root,8));
    }
    //妈的写的不好!要再练！想晕了
    //https://www.cnblogs.com/reboot329/articles/6028932.html
    //https://blog.csdn.net/mebiuw/article/details/52901592
    int rs=0;
    public int pathSum(TreeNode root, int sum) {
        if(root==null){
            return  0;
        }
        dfs(root,root.val,sum);//dfs到了当前的root就说明肯定要取他的值，不能说dfs到了那个node再决定取不娶他的值(其实是可以的，见第2个链接的写法）. 如果写成dfs(root,0,sum)那么如果输入是0，0，0的树并且sum是0那样就不对了
        if(root.left!=null){
            pathSum(root.left,sum);
        }
        if(root.right!=null){
            pathSum(root.right,sum);
        }
        return rs;
    }
    void dfs(TreeNode root,int cur,int sum){
        if(cur==sum){
            rs++;
        }
        if(root.left!=null){//不用再考虑不娶当前节点的情况了，因为那已经被上面的两个pathsum cover了
            dfs(root.left,root.left.val+cur,sum);
        }
        if(root.right!=null){
            dfs(root.right,root.right.val+cur,sum);
        }
    }

    //uber面筋说要打印所有路径,应该是对的
    ArrayList<ArrayList<TreeNode>> rs2=new ArrayList<>();
    public  ArrayList<ArrayList<TreeNode>> pathSum2(TreeNode root, int sum) {
        if(root==null){
            return  rs2;
        }
        ArrayList<TreeNode> al=new ArrayList<>();
        al.add(root);
        dfs2(root,root.val,sum,al);

        if(root.left!=null){
            pathSum2(root.left,sum);
        }
        if(root.right!=null){
            pathSum2(root.right,sum);
        }
        return rs2;
    }
    void dfs2(TreeNode root,int cur,int sum,ArrayList<TreeNode> al){
        if(cur==sum){
            rs2.add(new ArrayList<>(al));
        }
        if(root.left!=null){
            al.add(root.left);
            dfs2(root.left,cur+root.left.val,sum,al);
            al.remove(al.size()-1);
        }
        if(root.right!=null){
            al.add(root.right);
            dfs2(root.right,cur+root.right.val,sum,al);
            al.remove(al.size()-1);
        }

    }
//9/13/2018，卧槽还是写的不好会错，关键是当前节点的值算不算进cur里
    public int pathSum3(TreeNode root, int sum) {
        if(root==null){
            return 0;
        }
        dfs3(root,root.val,sum);
        pathSum3(root.left,sum);
        pathSum3(root.right,sum);
        return rs;
    }
    void dfs3(TreeNode root,int cur,int sum){
        if(cur==sum){
           rs++;

        }
        if(root.left!=null){
            dfs3(root.left,root.left.val+cur,sum);
        }
        if(root.right!=null){
            dfs3(root.right,root.right.val+cur,sum);
        }
    }
    //uber的要打印路径的
    public ArrayList<ArrayList<TreeNode>> pathSum4(TreeNode root, int sum) {
        if(root==null){
            return  rs2;
        }
        ArrayList<TreeNode> al=new ArrayList<>();
        al.add(root);
        dfs4(root,root.val,sum,al);
        pathSum4(root.left,sum);
        pathSum4(root.right,sum);
        return rs2;
    }
    void dfs4(TreeNode root,int cur,int sum,ArrayList<TreeNode> al){
        if(cur==sum){
            rs2.add(new ArrayList<>(al));
        }
        if(root.left!=null){
            al.add(root.left);
            dfs4(root.left,cur+root.left.val,sum,al);
            al.remove(al.size()-1);
        }
        if(root.right!=null){
            al.add(root.right);
            dfs4(root.right,cur+root.right.val,sum,al);
            al.remove(al.size()-1);
        }
    }
}
