package Advance1;

import java.util.HashMap;

/**
 * Created by 502575560 on 7/16/17.
 */
//2 pointer
public class MinimumWindowSubstring {
    public static void main(String[] args){
        System.out.println(minWindow("bdab","ab"));
    }
    //自己写的,改了几次accept了
    public static String minWindow(String source, String target) {
        //
        if(source.length()==0||target.length()==0){
            return "";
        }
        int b=0;
        int e=0;
        int rsb=-1;
        int len=Integer.MAX_VALUE;
        int count=0;
        HashMap<Character,Integer> map=new HashMap<>();//意义是记录所需的字符及其数量
        for(int i=0;i<target.length();i++){
            if(!map.containsKey(target.charAt(i))){
                map.put(target.charAt(i),1);
                count++;
            }else {
                map.put(target.charAt(i),map.get(target.charAt(i))+1);
                count++;
            }
        }
        while (b<=e&&e<source.length()){
            char c=source.charAt(e);
            if(map.containsKey(c)&&map.get(c)>0){
                count--;
                map.put(c,map.get(c)-1);
            }else if(map.containsKey(c)){
                map.put(c,map.get(c)-1);
            }
            if(count==0){
                while (b<=e){
                    char cb=source.charAt(b);
                    if(!map.containsKey(cb)){
                        b++;
                        continue;
                    }
                    if(map.get(cb)<0){
                        b++;
                        map.put(cb,map.get(cb)+1);
                        continue;
                    }
                    if(map.get(cb)==0){
                        break;
                    }
                }
                if(e-b+1<len){
                    rsb=b;
                    len=e-b+1;
                }
            }
            e++;
        }
        return rsb==-1?"":source.substring(rsb,rsb+len);

    }
}
