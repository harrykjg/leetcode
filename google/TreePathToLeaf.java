package google;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 10/17/18.
 */
public class TreePathToLeaf {
    public static void main(String[] args){
        TreePathToLeaf tp=new TreePathToLeaf();
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.right.left=new TreeNode(5);
        root.right.right=new TreeNode(6);
        root.right.left.right=new TreeNode(7);
        ArrayList<Integer> al=new ArrayList<>();
        al.add(1);
        al.add(3);
        al.add(5);
//        al.add(6);

        System.out.println(tp.isFromPath(al,root));
    }


    //判断一个list里的node是不是从root到leath,我假设数有重复的数值
    public boolean isFromPath(List<Integer> list, TreeNode root){
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
