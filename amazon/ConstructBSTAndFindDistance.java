package amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 10/24/18.
 */
public class ConstructBSTAndFindDistance {
    public static void main(String[] a){
        ConstructBSTAndFindDistance cb=new ConstructBSTAndFindDistance();
        List<Integer> ls=new ArrayList<>();
        ls.add(3);
        ls.add(5);
        ls.add(2);
        ls.add(1);
        ls.add(4);
        System.out.print(cb.buildAndFind(ls,3,4));

    }
//和PinterestPathBetween2nodesInBST 一样的，只是多了构造树
    public int buildAndFind(List<Integer> ls,int n1,int n2){
        TreeNode root=new TreeNode(ls.get(0));
        for(int i=1;i<ls.size();i++){
            insert(root,ls.get(i));
        }
        TreeNode lca=findlca(root,n1,n2);
        int rs=0;
        if(lca.val==n1){
            TreeNode cur=lca;
            while (true){
                if(cur.val==n2){
                    return rs;
                }
                if(cur.val>n2){
                    cur=cur.left;
                }else{
                    cur=cur.right;
                }
                rs++;
            }
        }else if(lca.val==n2){
            TreeNode cur=lca;
            while (true){
                if(cur.val==n1){
                    return rs;
                }
                if(cur.val>n1){
                    cur=cur.left;
                }else{
                    cur=cur.right;
                }
                rs++;
            }
        }else{
            TreeNode cur=lca;
            while (true){
                if(cur.val==n2){
                    break;
                }
                if(cur.val>n2){
                    cur=cur.left;
                }else{
                    cur=cur.right;
                }
                rs++;
            }
            cur=lca;
            while (true){
                if(cur.val==n1){
                    break;
                }
                if(cur.val>n1){
                    cur=cur.left;
                }else{
                    cur=cur.right;
                }
                rs++;
            }
        }

        return rs;


    }

    TreeNode findlca(TreeNode root,int n1,int n2){
        if(root==null){
            return null;
        }
        if(root.val==n1||root.val==n2){
            return root;
        }
        TreeNode left=findlca(root.left,n1,n2);
        TreeNode right=findlca(root.right,n1,n2);;
        if(left!=null&&right!=null){
            return root;
        }
        if(left!=null){
            return findlca(root.left,n1,n2);
        }
        return findlca(root.right,n1,n2);


    }


    void insert(TreeNode root,int n){
        if(root.val>n){
            if(root.left==null){
                root.left=new TreeNode(n);
                return;
            }else{
                insert(root.left,n);
                return;
            }
        }else{
            if(root.right==null){
                root.right=new TreeNode(n);
            }else{
                insert(root.right,n);
            }
        }
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
