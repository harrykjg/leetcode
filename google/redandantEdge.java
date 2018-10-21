package google;

/**
 * Created by yufengzhu on 10/20/18.
 */
public class redandantEdge {
    public static void main(String[] args){
        redandantEdge re=new redandantEdge();
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.left=new TreeNode(5);
        root.right.left=new TreeNode(5);
        root.right.right=new TreeNode(6);
        root.right.left.right=new TreeNode(7);
        re.redandant(root);
    }

    /*
    第一个是684变种，用union find和BFS解决。把root放进queue里，对于每个邻居做find，如果是同一个集合就删掉这个边并返回，否则就union, 并把邻居加入queue做下一层检查
followup1：同样的方法，只是找到第一个边不返回，接着找完所有的边并删除
followup2：每找到一个边加入一个list里面
     */

//可能是输入是一个node，这个node包含n个邻居，是有向的。每个节点的值应该要是unique的。按上面的思路应该可以把，但是还是不确定到底node是怎样的形式。
    public void redandant(TreeNode root){

    }
}
