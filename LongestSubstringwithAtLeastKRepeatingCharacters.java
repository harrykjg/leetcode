import java.util.*;

/**
 * Created by 502575560 on 5/31/17.
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {
    public static void main(String[] args){
        System.out.println(LongestSubstringwithAtLeastKRepeatingCharacters.longestSubstring("aaabb",3));
    }
    //自己也想不出来好的方法,看别人思路,递归那还是有点不好想
    //https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/87738/Java-20-lines-very-easy-solution-7ms-with-explanation
    //http://www.cnblogs.com/reboot329/p/5875863.html
    //http://www.cnblogs.com/grandyang/p/5852352.html 太高级看不懂
    public static int longestSubstring(String s, int k) {
        if(k<=1){
            return s.length();
        }
        Map<Character,Integer> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else{
                map.put(c,1);
            }
        }
        Set<Character> set2=new HashSet<>();
        ArrayList<Character> al=new ArrayList<>();
        Set<Character> set=map.keySet();
        Iterator<Character> it=set.iterator();
        while (it.hasNext()){
            char c=it.next();
            if(map.get(c)<k){
                set2.add(c);
            }
        }
        if(set2.size()==0){//少了这个判断就会爆栈
            return s.length();
        }
        int rs=0;
        int start=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(set2.contains(c)){
                start=i+1;
                continue;
            }else{
                rs=Math.max(rs,longestSubstring(s.substring(start,i+1),k));
            }

        }
        return rs;
    }
}
