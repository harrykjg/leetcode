package DataStruct;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 8/27/18.
 */
public class FindDuplicateSubtrees {
    //不会，看答案的  https://leetcode.com/problems/find-duplicate-subtrees/solution/  看他的复杂度分析，容易错
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        HashMap<String,Integer> map=new HashMap<>();
        dfs(root,map,rs);

        return rs;
    }
    String dfs(TreeNode root,HashMap<String,Integer> map,List<TreeNode> ls){
        if(root==null){
            return "#";
        }
        String s=String.valueOf(root.val)+","+dfs(root.left,map,ls)+","+dfs(root.right,map,ls);
        if(!map.containsKey(s)){
            map.put(s,1);
        }else{
            map.put(s,map.get(s)+1);
        }
        if(map.get(s)==2){//就算有多个相等的，等于2只有一次。。
            ls.add(root);
        }
        return s;
    }

    //9/15/2018,记得，一次过
    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        List<TreeNode> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        HashMap<String,Integer> map=new HashMap<>();
        helper(root,rs,map);
        return rs;
    }
    String helper(TreeNode root,List<TreeNode> rs,HashMap<String,Integer> map){
        if(root==null){
            return "#";
        }
        String seri=root.val+","+helper(root.left,rs,map)+","+helper(root.right,rs,map);
        if(!map.containsKey(seri)){
            map.put(seri,1);
        }else{
            map.put(seri,map.get(seri)+1);
        }
        if(map.get(seri)==2){
            rs.add(root);
        }
        return seri;
    }
}
