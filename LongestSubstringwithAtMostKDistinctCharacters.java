import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LongestSubstringwithAtMostKDistinctCharacters {
    //8/10/2021 和Longest Substring with At Most Two Distinct Characters应该是一样的，就是这题答案可以用linkedhashmap更好
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character,Integer> map=new HashMap<>();
        char[] ch=s.toCharArray();
        int i=0;
        int j=0;
        int rs=0;
        while (j<ch.length){
            while (j<ch.length){
                if (map.containsKey(ch[j])){
                    map.put(ch[j],j);
                }else if(map.size()==k){
                    break;
                }else {
                    map.put(ch[j],j);
                }
                j++;
            }
            rs=Math.max(rs,j-i);
            //找在map中最后一位最小的那个，作为要删除的元素.如果用linkedhashmap就直接可以用iterator取第一个key，就是靠左的那一个.但是注意，linkedhashmap
            if (j<ch.length){  //加入先put（a，1）在put（b，2）再put（a，3），那么取出来的第一个还是a：3！所以说要在put的时候判断map是否有a，然后先删除再put！
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

    //8/21/2022 是linkedhashmap，开始记成是treemap了，
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        if(k==0){
            return 0;
        }
        int b=0;
        int e=0;
        LinkedHashMap<Character,Integer> map=new LinkedHashMap<>();
        char[] ch=s.toCharArray();
        int rs=0;
        while(e<ch.length){
            while(e<ch.length&&map.size()<k){
                if(!map.containsKey(ch[e])){
                    map.put(ch[e],e);
                }else{
                    map.remove(ch[e]);
                    map.put(ch[e],e);
                }
                e++;
            }
            while(e<ch.length&&map.size()==k&&map.containsKey(ch[e])){//这个有点别扭，还不如上面那样写
                map.remove(ch[e]);
                map.put(ch[e],e);
                e++;
            }
            rs=Math.max(e-b,rs);
            Iterator<Map.Entry<Character,Integer>> it=map.entrySet().iterator();
            Map.Entry<Character,Integer> entry=it.next();
            b=entry.getValue()+1;
            map.remove(entry.getKey());
        }
        return rs;
    }

}
