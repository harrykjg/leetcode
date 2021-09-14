import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RomantoInteger {
    //8/15/2021
    public int romanToInt(String s) {
        if(s.length()==0){
            return 0;
        }
        int rs=0;
        char[] ch=s.toCharArray();
        int i=0;
        Map<String,Integer> map=new HashMap<>();
        map.put("M",1000);
        map.put("CM",900);
        map.put("D",500);
        map.put("CD",400);
        map.put("C",100);
        map.put("XC",90);
        map.put("L",50);
        map.put("XL",40);
        map.put("X",10);
        map.put("IX",9);
        map.put("V",5);
        map.put("IV",4);
        map.put("I",1);
        while (i<ch.length){//开始想的是先看第一个字符，再看第二个字符，这样就很麻烦了，要基于第一个字符来看第二个可能和第一个字符组合的字符。
            if (i+1<ch.length){//这里直接看2个字符是否在map里。
                String temp=s.substring(i,i+2);
                if (map.containsKey(temp)){
                    rs+=map.get(temp);
                    i+=2;
                    continue;
                }
            }
            String temp=s.substring(i,i+1);
            rs+=map.get(temp);
            i+=1;

        }
        return rs;
    }
}
