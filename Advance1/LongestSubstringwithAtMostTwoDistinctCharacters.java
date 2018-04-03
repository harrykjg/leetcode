package Advance1;

import java.util.HashMap;

/**
 * Created by 502575560 on 7/16/17.
 */
//2 pointer
public class LongestSubstringwithAtMostTwoDistinctCharacters {
    //http://blog.csdn.net/whuwangyi/article/details/42451289
    //http://www.cnblogs.com/jiajiaxingxing/p/4452360.html
    //http://www.cnblogs.com/grandyang/p/5185561.html
    //http://blog.csdn.net/xudli/article/details/43222593
    //https://segmentfault.com/a/1190000005824694
    //3/21/2018,九章第二轮但是第一次写这题,自己写的比较慢，我觉得应该对把，没有run过因为是要钱的题目
    public static void main(String[] args){
        LongestSubstringwithAtMostTwoDistinctCharacters ls=new LongestSubstringwithAtMostTwoDistinctCharacters();
        System.out.println(ls.lengthOfLongestSubstringTwoDistinct("ecebbaakkakskkkkkkkk"));
    }
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        HashMap<Character,Integer > map=new HashMap<>();
        int b=0;
        int e=0;
        int rslen=Integer.MIN_VALUE;
        while (e<s.length()){

            char c=s.charAt(e);
            if (map.size()<2){
                if(map.containsKey(c)){
                    map.put(c,map.get(c)+1);
                }else{
                    map.put(c,1);
                }
                e++;
                continue;
            }
            if(map.size()==2&&map.containsKey(c)){
                map.put(c,map.get(c)+1);
                e++;
                continue;
            }

            Math.max(rslen,e-b);

            while (map.size()==2){
                char c2=s.charAt(b);
                if(map.get(c2)==1){
                    map.remove(c2);
                    b++;
                    break;
                }
                map.put(c2,map.get(c2)-1);
                b++;
            }
        }
        return Math.max(rslen,e-b);//最后如果是"ecebbaakkakskkkkkkkk"例子的话，要再检查一下e-b是否最大，因为上面的while循环完了的话是没有检查e-b是否最大的
    }
}
