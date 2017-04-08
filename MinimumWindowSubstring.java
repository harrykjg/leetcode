

import java.util.HashMap;

/**
 * Created by 502575560 on 7/2/16.
 */
public class MinimumWindowSubstring {
    public static void main(String[] args){
       System.out.println(minWindow("bba","ab"));
    }

    //2016年7月自己写的改了几次accept了
    public static String minWindow(String s, String t) {
        if(t.length()>s.length()){
            return "";
        }
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<t.length();i++){
            if(!map.containsKey(t.charAt(i))){
                map.put(t.charAt(i),1);
            }else{
                map.put(t.charAt(i),map.get(t.charAt(i))+1);
            }
        }
        int wal=0;
        int run=1;
        int b=0;
        int count=0;
        int rs=Integer.MAX_VALUE;
        while (run<=s.length()){
            char c=s.charAt(run-1);
            if(map.containsKey(c)){
                map.put(c,map.get(c)-1);
                if(count<t.length()&&map.get(c)>=0){
                    count++;
                }
            }
            while(count==t.length()&&(run-wal)>t.length()){
                char c2=s.charAt(wal);
                if(!map.containsKey(c2)){
                    wal++;
                    continue;
                }else if(map.get(c2)<0){
                    wal++;
                    map.put(c2,(map.get(c2)+1));
                    continue;
                }else{
                    break;
                }
            }
            if(count==t.length()){
                if(run-wal<rs){
                    rs=run-wal;
                    b=wal;
                }
                if(rs==t.length()){
                    break;
                }
            }
            run++;
        }
        if(rs==Integer.MAX_VALUE){
            return "";
        }
        return s.substring(b,b+rs);
    }
}
