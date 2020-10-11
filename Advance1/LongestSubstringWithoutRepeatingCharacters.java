package Advance1;

import java.util.*;

/**
 * Created by 502575560 on 7/15/17.
 */
//2 pointer
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args){
        LongestSubstringWithoutRepeatingCharacters ls=new LongestSubstringWithoutRepeatingCharacters();
        ls.lengthOfLongestSubstring3("tmmzuxt");

    }
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
    //3/19/2018，九章第二轮，还是写的不好，用了map而且还改了一两次才对，用set就行了，但是上面的写法感觉b++那里也容易错
    public int lengthOfLongestSubstring2(String s) {
        if(s.length()==0){
            return 0;
        }
        int i=0;
        int j=0;
        int rs=Integer.MIN_VALUE;
        HashMap<Character,Integer> map=new HashMap<>();
        char[] ch=s.toCharArray();
        while (j<ch.length){
            while (j<ch.length&&!map.containsKey(ch[j])){
                map.put(ch[j],1);
                j++;
            }
            rs=Math.max(rs,j-i);//这楼之前写的j-i+1就错了
            if(j<ch.length){
                map.put(ch[j],map.get(ch[j]+1));
            }
            while (i<j&&j<s.length()){//这里容易喽js.length的条件
                if(ch[i]==ch[j]){
                    map.put(ch[i],1);
                    i++;
                    break;
                }
                map.remove(ch[i]);
                i++;
            }
            j++;
        }
        return rs;
    }

    //04/23/2020，改了一次accept了，感觉这样写外层for循环比以前的外层while更容易些
    public int lengthOfLongestSubstring3(String s) {
        if(s.length()==0){
            return 0;
        }
        int rs=0;
        int b=0;
        Set<Character> set=new HashSet<>();
        for(int i=0;i<s.length();i++){
            if(!set.contains(s.charAt(i))){
                set.add(s.charAt(i));
                rs=Math.max(rs,i-b+1);
                continue;
            }else{
                while (b<i){
                    if(s.charAt(b)==s.charAt(i)){
                        b++;
                        break;
                    }
                    set.remove(s.charAt(b));//开始忘了remove了
                    b++;
                }
            }
        }
        return rs;
    }
}

