package contest;

import java.util.HashSet;
import java.util.Set;

public class SplitaStringIntotheMaxNumberofUniqueSubstrings {
    public static void main(String[] args){
        SplitaStringIntotheMaxNumberofUniqueSubstrings ss=new SplitaStringIntotheMaxNumberofUniqueSubstrings();
        ss.maxUniqueSplit("ababccc");
    }
    //只能暴力法,开始还想dp结果是不行的
    int rs=0;
    public int maxUniqueSplit(String s) {
        Set<String> set=new HashSet<>();

        dfs(0,s,set);
        return rs;
    }
    void dfs(int b,String s,Set<String> set){
        if(b>=s.length()){
            rs=Math.max(set.size(),rs);
            return;
        }
        for(int i=1;i+b<=s.length();i++){
            String cur=s.substring(b,b+i);
            if(!set.contains(cur)){
                set.add(cur);
                dfs(b+i,s,set);
                set.remove(cur);
            }
        }
    }
}
