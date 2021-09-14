package DataStruct;

import apple.laf.JRSUIUtils;

import java.util.*;

/**
 * Created by yufengzhu on 8/27/18.
 */
public class FindDuplicateSubtrees {
    //不会，看答案的
    // https://leetcode.com/problems/find-duplicate-subtrees/discuss/106011/Java-Concise-Postorder-Traversal-Solution看他的复杂度分析，容易错
    // https://leetcode.com/problems/find-duplicate-subtrees/discuss/106055/C%2B%2B-Java-Clean-Code-with-Explanation
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

    //7/6/2021,自己想的是把所有子树找出来放到list里，然后2重循环对比每个子树睡否相同，这样会出现很多重复的结果，加一个Set<Integer>可鞥可以解决，但是还是看以前的方法把
    //貌似前中后跟顺序都行，中跟的分隔符写的地方会不一样，用前跟和后跟把
    public List<TreeNode> findDuplicateSubtrees3(TreeNode root) {

    }
}
