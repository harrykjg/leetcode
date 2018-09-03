package DataStruct;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 8/27/18.
 */
public class FindDuplicateSubtreesWithSize {
    //uber面经题 http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=439778&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D22%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
    //就是FindDuplicateSubtree但是要size大于4的subtree,没有写test case验证
    boolean flag;
    HashMap<String,Integer> size=new HashMap<>();
    public boolean findDuplicateSubtrees(TreeNode root) {
        if(root==null){
            return false;
        }
        HashMap<String,Integer> map=new HashMap<>();

        dfs(root,map);
        return flag;
    }
    String dfs(TreeNode root,HashMap<String,Integer> map){
        if(root==null){
            return "#";
        }
        if(flag){
            return "";
        }
        String s=root.val+","+dfs(root.left,map)+","+dfs(root.right,map);
        if(!map.containsKey(s)){
            map.put(s,1);
        }else{
            map.put(s,map.get(s)+1);
        }
        if(!size.containsKey(s)){
            String[] ss=s.split(",");
            int count=ss.length;//有多少个node，减去null的个数就是这个子树的size
            int numOfNull=0;
            for(String val:ss){
                if(val.equals("#")){
                    numOfNull++;
                }
            }
            size.put(s,count-numOfNull);
        }
        if(map.get(s)>=2&&size.get(s)>=4){
            flag=true;
        }
        return s;
    }

}

