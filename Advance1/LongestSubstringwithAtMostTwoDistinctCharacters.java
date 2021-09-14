package Advance1;

import java.util.*;

/**
 * Created by 502575560 on 7/16/17.
 */
//2 pointer
public class LongestSubstringwithAtMostTwoDistinctCharacters {
    //http://blog.csdn.net/whuwangyi/article/details/42451289 貌似不需要像他说的那样删除左边出现左后出现的字母
    //http://www.cnblogs.com/jiajiaxingxing/p/4452360.html
    //http://www.cnblogs.com/grandyang/p/5185561.html
    //http://blog.csdn.net/xudli/article/details/43222593
    //https://segmentfault.com/a/1190000005824694
    //3/21/2018,九章第二轮但是第一次写这题,自己写的比较慢，我觉得应该对把，lintcode上可以run
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

            rslen=Math.max(rslen,e-b);

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

    //8/8/2021 看了答案还是用map记录某个字符最后一次出现的地方比较好
    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        Map<Character,Integer> map=new HashMap<>();
        char[] ch=s.toCharArray();
        int i=0;
        int j=0;
        int rs=0;
        while (j<ch.length){
            while (j<ch.length){
                if (map.containsKey(ch[j])){
                    map.put(ch[j],j);
                }else if(map.size()==2){
                    break;
                }else {
                    map.put(ch[j],j);
                }
                j++;
            }
            rs=Math.max(rs,j-i);
            //找在map中最后一位最小的那个，作为要删除的元素
            if (j<ch.length){
                int min=j-1;
                for (int m:map.values()){
                    min=Math.min(min,m);
                }
                char key=ch[min];
                int index=map.get(key);
                i=index+1;
                map.remove(key);
            }
        }
        return rs;
    }
//8/13/2021 这里用linkedhashmap的话搞的挺恶心的，假如加入先put（a，1）在put（b，2）再put（a，3），那么取出来的第一个还是a：3！所以说
// 要在put的时候判断map是否有a，然后先删除再put！这样后面取map的第一个值时才是对的
    public int lengthOfLongestSubstringTwoDistinct3(String s) {
        HashMap<Character,Integer> map=new LinkedHashMap();
        char[] ch=s.toCharArray();
        int rs=0;
        int i=0;
        int b=0;
        while(i<ch.length){
            if(map.size()<2){
                if(map.containsKey(ch[i])){
                    map.remove(ch[i],i);
                }
                map.put(ch[i],i);
                rs=Math.max(rs,i-b+1);
                i++;
                continue;
            }else{//size等于2了
                if(map.containsKey(ch[i])){
                    map.remove(ch[i]);
                    map.put(ch[i],i);
                    rs=Math.max(rs,i-b+1);
                    i++;
                    continue;
                }else{
                    Iterator<Map.Entry<Character,Integer>> it=map.entrySet().iterator();
                    Map.Entry<Character,Integer> entry=it.next();
                    int index=map.get(entry.getKey());
                    b=index+1;
                    map.remove(entry.getKey());
                }
            }
        }
        return rs;
    }
}
