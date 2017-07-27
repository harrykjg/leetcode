package Advance1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Created by 502575560 on 7/15/17.
 */
//2 pointer
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        //由于做了前一题minimumsizesubarraysum,这题改了2次accept了,也是2 pointer
        if(s.length()==0){
            return 0;
        }
        Math.abs(3-2);
        int rs=Integer.MIN_VALUE;
        int b=0;
        int e=0;
        HashSet<Character> set=new HashSet<>();
        while (e<s.length()){
            if(!set.contains(s.charAt(e))){
                set.add(s.charAt(e));
                e++;
                continue;
            }else {
                rs=Math.max(rs,e-b);
                while (s.charAt(b)!=s.charAt(e)){
                    set.remove(s.charAt(b));
                    b++;
                }
                b++;
                e++;
            }
        }
        rs=Math.max(rs,e-b);
        return rs;
    }
}

