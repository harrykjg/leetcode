package linkedin;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings205 {
    //1/26/2026 楼考虑这个例子"badc"和baba，b先和b对应，后来d又和b对应，因此还要另一个map去对应
    //还有一个trick，如"paper"和"title"，其中e对应l，并且r对应e，这样是可以的，因此不是要一一对应，只要s里的一样的字符对应唯一一个字符就行！
    //好的解法是用两个int数组，存的是char所对应的position，拿badc"和baba这个例子看，i+1是为了区别i=0时和默认数组初始值都是0
    //https://leetcode.com/problems/isomorphic-strings/solutions/4960160/beats-100-easiest-code-with-comments-exp-oxmr/
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> map=new HashMap<>();
        if(s.length()!=t.length()){
            return false;
        }
        for(int i=0;i<s.length();i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),t.charAt(i));
            }else{
                if(map.get(s.charAt(i))!=t.charAt(i)){
                    return false;
                }
            }
            if(!map.containsKey(t.charAt(i))){
                map.put(t.charAt(i),s.charAt(i));
            }else{
                if(map.get(t.charAt(i))!=s.charAt(i)){
                    return false;
                }
            }
        }
        return true;
    }
}
