package 灵神.sidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MaximumNumberofOccurrencesofaSubstring1297 {
    public static void main(String[] args) {
        System.out.println(maxFreq("xxxxx",1,3,3));


    }
    //注意不是说要找到最长的substring，而是找到符合条件的最多的，因此符合条件的substring就要统计.但其实只需要找最短的符合条件的，因为最短符合条件的
    //substring出现次数肯定大于等于长的substring，因此用定长sliding window就可以了。之前想的是e往右扩的时候要考虑到maxsize，那样搞很久b往右缩的时候不好写
    //https://leetcode.cn/problems/maximum-number-of-occurrences-of-a-substring/solutions/3747384/nao-jin-ji-zhuan-wan-ding-chang-hua-dong-0hol/
    public static int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<Character,Integer> map=new HashMap<>();
        int rs=0;
        int b=0;
        int e=0;
        Map<String, Integer> candidate=new HashMap<>();
        String cur="";
        while (e<s.length()){
            cur=s.substring(b,e);
            //e会被允许++,进来这个while之后再判断到底行不行
            while (e<s.length()){
                int len=e-b;//先在e位置的char还没决定能不能加进来
                if (!map.containsKey(s.charAt(e))){
                    if(map.size()+1<=maxLetters&&len+1<=minSize){//确定可以加进来
                        map.put(s.charAt(e),1);
                        cur=cur+s.charAt(e);
                        if (cur.length()==minSize){
                            candidate.put(cur,candidate.getOrDefault(cur,0)+1);
                            rs=Math.max(rs,candidate.get(cur));
                        }


                    }else {
                        break;
                    }
                }else {
                    if(len+1<=minSize){
                        map.put(s.charAt(e),map.get(s.charAt(e))+1);
                        cur+=s.charAt(e);
                        if (cur.length()==minSize){
                            candidate.put(cur,candidate.getOrDefault(cur,0)+1);
                            rs=Math.max(rs,candidate.get(cur));
                        }
                    }else {
                        break;
                    }
                }
                e++;
            }
            //开始缩，由于是定长的所以直接缩一个
            if (map.get(s.charAt(b))==1){
                map.remove(s.charAt(b));
                b++;
            }else {
                map.put(s.charAt(b),map.get(s.charAt(b))-1);
                b++;
            }

        }
        return rs;

    }
}
