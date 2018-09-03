package DataStruct;

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
}
