package Advance1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 502575560 on 7/16/17.
 */
//2 pointer
public class MinimumWindowSubstring {
    public static void main(String[] args){
        System.out.println(minWindow3("abcxxddeacb","abcd"));
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

    //9/28/2018,是记得的，就是不是太好写，改了一两次accept了
    public static String minWindow3(String source, String target) {
        String rs="";
        HashMap<Character,Integer> map=new HashMap<>();
        char[] ch=target.toCharArray();
        int count=0;
        for(int i=0;i<ch.length;i++){
            if(!map.containsKey(ch[i])){
                map.put(ch[i],1);
            }else{
                map.put(ch[i],map.get(ch[i])+1);
            }
            count++;
        }

        int run=0;
        int wal=0;
        int len=Integer.MAX_VALUE;
        while (run<source.length()){
            if(map.containsKey(source.charAt(run))&&map.get(source.charAt(run))>0){
                count--;
            }//注意只有需要他的时候才count--，并且，不管需不需要他的时候只要他在map里出现就要-1，容易漏
            if(map.containsKey(source.charAt(run))){
                map.put(source.charAt(run),map.get(source.charAt(run))-1);
            }

            if(count==0){
                while (wal<run){
                    if(!map.containsKey(source.charAt(wal))){
                        wal++;
                        continue;
                    }else if(map.containsKey(source.charAt(wal))&&map.get(source.charAt(wal))>=0){
                        break;
                    }else{
                        map.put(source.charAt(wal),map.get(source.charAt(wal))+1);
                        wal++;
                    }
                }
            }
            int curlen=run-wal+1;
            if(count==0&&len>curlen){
                len=curlen;
                rs=source.substring(wal,wal+curlen);
            }
            run++;

        }
        return rs;
    }

    //6/13/2021,开始没想到用count去记录需要的有效字符,改了2辞accept了
    public static String minWindow4(String source, String target) {
        Map<Character,Integer> map=new HashMap<>();
        int count=0;
        for (int i=0;i<target.length();i++){
            if(!map.containsKey(target.charAt(i))){
                map.put(target.charAt(i),1);
            }else {
                map.put(target.charAt(i),map.get(target.charAt(i))+1);
            }
            count++;
        }
        int len=Integer.MAX_VALUE;
        String rs="";
        int b=0;
        for (int i=0;i<source.length();i++){
            char cur=source.charAt(i);
            if (map.containsKey(cur)){
                if (map.get(cur)>0){
                    count--;
                }
                map.put(cur,map.get(cur)-1);
            }
            if (count==0){
                while (b<i){
                    char begin=source.charAt(b);
                    if (map.containsKey(begin)){
                        if (map.get(begin)>=0){//开始少写了=号
                            //can't remove
                            break;
                        }else {
                            map.put(begin,map.get(begin)+1);
                            b++;
                        }
                    }else {
                        b++;
                    }
                }
                if (i-b+1<len){
                    len=i-b+1;
                    rs=source.substring(b,b+len);
                }
            }

        }
        if (count>0){
            return "";
        }
        return rs;//开始写的是直接source.subString(b,b+len)就错了，因为b可能会在找到最短的字符串之后挪动到别的地方
    }

    //8/13/2021 又写的很烂，看回以前的方法.他的想法不是和Longest Substring with At Most K Distinct Characters那样，那里是扩展到k个之后，就开始
    //缩，缩的时候会删掉一个东西，然后在扩展。而这里是扩展到count=0的时候开始缩，缩的话不会把现在必须的char删掉，只会把不必要的char删掉，而再扩展的时候
    //可以包含必须的但多余的char，
    public static String minWindow5(String source, String target) {
        Map<Character,Integer> map=new HashMap<>();
        int count=0;
        for (int i=0;i<target.length();i++){
            if(!map.containsKey(target.charAt(i))){
                map.put(target.charAt(i),1);
            }else {
                map.put(target.charAt(i),map.get(target.charAt(i))+1);
            }
            count++;
        }
        int len=Integer.MAX_VALUE;
        String rs="";
        int b=0;
        int e=0;
        char[] ch=source.toCharArray();
        while (e<source.length()){
            if(map.containsKey(ch[e])){
                if (map.get(ch[e])>0){
                    count--;
                }
                map.put(ch[e],map.get(ch[e])-1);
            }
            if (count==0){
                while (b<e){
                    if (map.containsKey(ch[b])){
                        if (map.get(ch[b])>=0){

                            break;
                        }else{
                            map.put(ch[b],map.get(ch[b])+1);
                        }
                    }
                    b++;
                }
                if (e-b+1<len){
                    len=e-b+1;
                    rs=source.substring(b,b+len);
                }
            }
            e++;

        }
        return rs;
    }
}
