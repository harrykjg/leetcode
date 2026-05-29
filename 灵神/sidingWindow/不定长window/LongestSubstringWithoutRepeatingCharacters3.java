package 灵神.sidingWindow.不定长window;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters3 {
    //5/6/2026
    public int lengthOfLongestSubstring(String s) {
        char[] ch=s.toCharArray();
        Set<Character> set =new HashSet<>();
        int b=0;
        int e=0;
        int rs=0;
        while (e<ch.length){
            while (e<ch.length&&!set.contains(ch[e])){
                set.add(ch[e]);
                e++;
            }
            rs=Math.max(e-b,rs);
            while (b<e){
                set.remove(ch[b]);
                if(e<ch.length&&ch[b]==ch[e]){
                    b++;
                    break;
                }else{
                    b++;
                }
            }
        }
        return rs;
    }
}
