package SomeInterviews.snap;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring76 {
    static void main() {
        System.out.println(MinimumWindowSubstring76.minWindow("ADOBECODEBANC","ABC"));
    }
    public static String minWindow(String s, String t) {
        int b=0;
        int e=0;
        StringBuilder sb =new StringBuilder();
        int min=Integer.MAX_VALUE;
        Map<Character,Integer> map=new HashMap<>();
        for (int i=0;i<t.length();i++){
            map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)+1);
        }
        String rs="";
        int need=t.length();
        while (e<s.length()){
            while (e<s.length()){//遇到的e直接先加上
                sb.append(s.charAt(e));
                if(map.containsKey(s.charAt(e))){
                    if(map.get(s.charAt(e))>0){
                        need--;
                    }
                    map.put(s.charAt(e),map.get(s.charAt(e))-1);
                }
                e++;
                if(need<=0){
                    break;
                }

            }

            int len=e-b;
            if(len<min){
                min=len;
                rs=sb.toString();
            }
            while (b<e){
                if(!map.containsKey(s.charAt(b))){
                    b++;
                }else if(map.get(s.charAt(b))<0){
                    map.put(s.charAt(b),map.get(s.charAt(b))+1);
                    b++;
                }else {
                    break;
                }
            }
             len=e-b;
            if(len<min){
                min=len;
                rs=sb.toString().substring(e-len,e);
            }

        }
        if(need!=0){
            return "";
        }
        return rs;

    }

}
