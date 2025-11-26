package 灵神.sidingWindow.不定长window;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring76 {
    public static void main(String[] args) {
        System.out.println(minWindow("aa","aa"));
    }

    public static String minWindow(String s, String t) {
        String rs=s;
        int length=s.length();
        int rsbegin=0;
        int b=0;
        int e=0;
        Map<Character,Integer> map=new HashMap<>();
        int count=t.length();
        for(int i=0;i<t.length();i++){
            map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)+1);
        }
        while (e<s.length()){//进来的都算进临时的string里，直到第一次找到所有需要的char，再试着缩b。缩不了无所谓，再继续右扩
            while (e<s.length()){
                char c=s.charAt(e);
                if(map.containsKey(c)){
                    if(map.get(c)>0){//这里开始携程map.get（c）-1=0才count--
                        count--;
                    }
                    map.put(c,map.get(c)-1);
                    e++;
                }else{
                    e++;
                }
                if (count==0){
                    break;
                }
            }
            while (b<e){
                if(!map.containsKey(s.charAt(b))){
                    b++;
                    continue;
                }
                if(map.containsKey(s.charAt(b))){
                    if(map.get(s.charAt(b))!=0){
                        map.put(s.charAt(b),map.get(s.charAt(b))+1);
                        b++;
                    }else {
                        break;
                    }
                }else {
                    b++;
                }
            }
            if(count==0){
                if(e-b<length){
                    rsbegin=b;
                    length=e-b;
                }
            }

        }
        if(count!=0){
            return "";
        }

        return rs.substring(rsbegin,rsbegin+length);
    }
}
