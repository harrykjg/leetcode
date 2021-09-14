package contest;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumberofWonderfulSubstrings {
    public static void main(String[] args){
        NumberofWonderfulSubstrings nw=new NumberofWonderfulSubstrings();
        System.out.println(nw.wonderfulSubstrings("aabb"));
    }
    //https://leetcode.com/problems/number-of-wonderful-substrings/discuss/1299773/Intuitive-explanation-easy-to-understand
    //https://leetcode.com/problems/number-of-wonderful-substrings/discuss/1299552/JavaC%2B%2BPython-Bit-Mask-%2B-Prefix
    //6/29/2021 bit mask的解法的不好理解
    //自己写的方法tle。就是想着移动窗口，检测每一个substring是否valid，而每一个substring都是由他之前的那个substring加上当前char的，所以我用map2存他之前的substring
    public long wonderfulSubstrings(String word) {//的每个字母的count【】，然后当前的就很快可以得到
        HashMap<String, Boolean> map=new HashMap<>();
        HashMap<String, int[]> map2=new HashMap<>();
        long rs=0;
        for (char c='a';c<='j';c++){
            int[] count=new int[10];
            count[c-'a']++;
            map2.put(String.valueOf(c),count);
        }
        for (int i=2;i<=word.length();i++){
            for (int j=0;j+i<=word.length();j++){
                String cur=word.substring(j,j+i);
                if (vaild(cur,map,map2)){
                    rs++;
                }
            }
        }
        return rs+word.length();
    }

    boolean vaild(String s,HashMap<String,Boolean> memo,HashMap<String,int[]> memo2){
        if (memo.containsKey(s)){
            return memo.get(s);
        }
        String pres=s.substring(0,s.length()-1);
        int[] count= Arrays.copyOf(memo2.get(pres),10);//注意这里要copy过来，不然的话他就是修改原来的int【】数组
        count[s.charAt(s.length()-1)-'a']++;

        memo2.put(s,count);
        boolean found=false;
        for (int c:count){
            if (c%2==1&&found==false){
                found=true;
            }else if (c%2==1){
                memo.put(s,false);
                return false;
            }
        }
        memo.put(s,true);
        return true;
    }
}
