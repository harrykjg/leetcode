package contest;

import java.util.HashSet;
import java.util.Set;

public class LexicographicallySmallestStringAfterApplyingOperations {
    //只能暴力法,dfs居然一次过
    String rs;
    public String findLexSmallestString(String s, int a, int b) {
        Set<String> set=new HashSet<>();
        rs=s;
        dfs(s,a,b,set);

        return rs;
    }
    void dfs(String s,int a,int b, Set<String> set){
        if(set.contains(s)){
            return;
        }
        set.add(s);
        StringBuilder sb1=new StringBuilder(s);
        for(int i=0;i<sb1.length();i++){
            if(i%2!=0){
                sb1.setCharAt(i,(char)(((sb1.charAt(i)-'0')+a)%10+'0'));
            }
        }
        if(sb1.toString().compareTo(rs)<0){
            rs=sb1.toString();
        }
        dfs(sb1.toString(),a,b,set);

        StringBuilder sb2=new StringBuilder(s);
        String s2=rotate(sb2,b);
        if(s2.compareTo(rs)<0){
            rs=s2;
        }
        dfs(s2,a,b,set);
    }

    String rotate(StringBuilder sb,int b){
        int len=sb.length();
        b=b%len;
        while (b>0){
            char temp=sb.charAt(sb.length()-1);
            sb=sb.deleteCharAt(sb.length()-1);
            sb.insert(0,temp);
            b--;
        }
        return sb.toString();
    }
}
