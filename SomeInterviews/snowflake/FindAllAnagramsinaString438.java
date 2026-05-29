package SomeInterviews.snowflake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsinaString438 {
    static void main() {
        List<Integer> rs=FindAllAnagramsinaString438.findAnagrams("abaacbabc","abc");
        for (int i:rs){
            System.out.println(i);
        }
    }
    //5/20、2026写的很不好，看回以前的写法吧
    //好想没有特别好的方法，就是sliding window，缩放还是比较麻烦。
    //用数组记录char比用map方便一点吧
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> rs=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        char[] ch=p.toCharArray();
        int[] a=new int[26];
        for(char c:ch){
            a[c-'a']++;
        }
        int count=p.length();
        int b=0;
        int e=0;
        char[] cc=s.toCharArray();
        while (e<s.length()){
            int index=cc[e]-'a';//进来直接把e纳入考虑
            if(a[index]>=1){
                count--;
            }
            a[index]--;
            if(count==0){
                rs.add(b);
                int indexb=cc[b]-'a';
                a[indexb]++;
                b++;
                e++;
                count++;
                continue;
            }
            if(e-b+1<p.length()){//如果长度不够直接下一轮
                e++;
                continue;
            }else{//否则直接缩b一个，那e也得前进吧
                int indexb=cc[b]-'a';
                if(a[indexb]>=0){
                    count++;
                }
                a[indexb]++;
                b++;
                e++;
            }
        }
        return rs;
    }
}
