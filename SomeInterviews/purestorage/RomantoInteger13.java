package SomeInterviews.purestorage;

import java.util.HashMap;
import java.util.Map;

public class RomantoInteger13 {
    static void main() {
        System.out.println(romanToInt("DCXXI"));
    }

    //4/22/2026写的不好，硬写改了好几次。看回以前的解法应该用map。
    public static int romanToInt(String s) {

        char[] ch=s.toCharArray();
        int i=0;
        int rs=0;
        while (i<ch.length){
            //遇到I就最多有3个I，也可能是1个I后面跟着V或X，
            if (ch[i]=='I'){
                int count=0;
                while ((i<ch.length&&ch[i]=='I')){
                    i++;
                    count++;
                }
                if(count>=2){
                    rs+=count;
                }else {
                    if(i<ch.length&&ch[i]=='V'){
                        rs+=4;
                        i++;
                    }else if(i<ch.length&&ch[i]=='X'){
                        rs+=9;
                        i++;
                    }else{
                        rs+=1;
                    }
                    i++;
                }
            }else if(ch[i]=='X'){
                int count=0;
                while (i<ch.length&&(ch[i]=='X')){
                    i++;
                    count++;
                }
                if(count>=2){
                    rs+=count*10;
                }else {
                    if(i<ch.length&&ch[i]=='L'){
                        rs+=40;
                        i++;
                    }else if(i<ch.length&&ch[i]=='C'){
                        rs+=90;
                        i++;
                    }else{
                        rs+=10;
                    }

                }
            }else if(ch[i]=='C'){
                int count=0;
                while (i<ch.length&&(ch[i]=='C')){
                    i++;
                    count++;
                }
                if(count>=2){
                    rs+=count*100;
                }else {
                    if(i<ch.length&&ch[i]=='D'){
                        rs+=400;
                        i++;
                    }else if(i<ch.length&&ch[i]=='M'){
                        rs+=900;
                        i++;
                    }else{
                        rs+=100;

                    }
                }
            }else if(ch[i]=='V'){
                rs+=5;
                i++;
            }else if(ch[i]=='X'){
                rs+=10;
                i++;
            }
            else if(ch[i]=='L'){
                rs+=50;
                i++;
            }else if(ch[i]=='C'){
                rs+=100;
                i++;
            }
            else if(ch[i]=='D'){
                rs+=500;
                i++;
            }else if(ch[i]=='M'){
                rs+=1000;
                i++;
            }
        }
        return rs;

    }
    //以前的解法
    public int romanToInt2(String s) {
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
