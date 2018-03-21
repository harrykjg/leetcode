package Advance1;

import java.util.HashMap;

/**
 * Created by 502575560 on 7/16/17.
 */
//2 pointer
public class MinimumWindowSubstring {
    public static void main(String[] args){
        System.out.println(minWindow2("aaabbbbbcdd","abcd"));
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
    //3/20/2018,九章第二轮,自己想的，写的比较慢而且改了几次,基本没啥不同，如何判断找全了字母的那里想了比较久
    public static String minWindow2(String source, String target) {
        if(source.length()==0||target.length()==0){
            return "";
        }
        HashMap<Character,Integer> map=new HashMap<>();
        int count=0;
        char[] ch=target.toCharArray();
        for(int i=0;i<ch.length;i++){
            if(!map.containsKey(ch[i])){
                map.put(ch[i],1);
            }else{
                map.put(ch[i],map.get(ch[i])+1);
            }
        }
        count=map.size();
        boolean found=false;
        int b=0;
        int e=0;
        int rsbegin=-1;
        int rslength=Integer.MAX_VALUE;
        ch=source.toCharArray();
        while (e<source.length()){
            if(map.containsKey(ch[e])){
                map.put(ch[e],map.get(ch[e])-1);
                if(map.get(ch[e])==0){
                    count--;
                }
            }
            e++;
            if(count==0){
                found=true;
            }
            while (found&&b<e){
                if(map.containsKey(ch[b])){
                    if(map.get(ch[b])==0){
                        break;
                    }else {
                        map.put(ch[b],map.get(ch[b])+1);
                        b++;
                    }
                }else{
                    b++;
                }
            }
            if(found&&e-b<rslength){
                rsbegin=b;
                rslength=e-b;
            }
        }
        return rsbegin==-1?"":source.substring(rsbegin,rsbegin+rslength);

    }
}
