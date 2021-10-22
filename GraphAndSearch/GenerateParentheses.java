package GraphAndSearch;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> rs=new ArrayList<>();
        dfs(n,n,"",rs);
        return rs;
    }
    void dfs(int left,int right,String cur,List<String> rs){
        if (left==0&&right==0){
            rs.add(cur);
            return;
        }
        if (left>0){
            dfs(left-1,right,cur+"(",rs);
        }

        if (left<right){
            dfs(left,right-1,cur+")",rs);
        }
    }

}
