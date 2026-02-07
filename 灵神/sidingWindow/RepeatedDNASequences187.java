package 灵神.sidingWindow;

import java.util.*;

public class RepeatedDNASequences187 {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> rs=new ArrayList<>();
        if(s.length()<=10){
            return rs;
        }
        int b=0;
        int e=9;
        Map<String,Integer> map=new HashMap<>();
        while (e<s.length()){
            StringBuilder sb=new StringBuilder(s.substring(b,e+1));
            String cur=sb.toString();
            map.put(cur,map.getOrDefault(cur,0)+1);
            e++;
            b++;

        }
        for(Map.Entry<String,Integer> en :map.entrySet()){
            if(en.getValue()>1){
                rs.add(en.getKey());
            }
        }
        return rs;
    }
}
