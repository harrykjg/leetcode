package 灵神.链表二叉树回溯.回溯;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class MaximumLengthofaConcatenatedStringwithUniqueCharacters1239 {
    static void main() {

    }//12/28/2025
    /*
    主要是不会怎么快速判断两个字符串否没有相同字符，要学医用bitmask判断，而且这个字符串自己如果有重复的字符那肯定也不行
把字符串转成 bitmask
对每个 word：
扫一遍字符 c，位 1 << (c-'a')
如果在同一个 word 内出现重复（mask 已经有这个 bit），那这个 word 无效，跳过
否则记录它的 mask 和 len

    }
     */
    int rs;
    public int maxLength(List<String> arr) {
        //先排除自己有重复字符的
        List<Integer> al=new ArrayList<>();
        for(String s:arr){
            int mask=0;
            boolean ok=true;
            for(int i=0;i<s.length();i++){
                int a=1<<s.charAt(i)-'a';
                if((mask&a)>0){
                    //有重复字符
                    ok=false;
                    break;
                }
                mask|=a;
            }
            if(ok){
                al.add(mask);//存的是mask，不是s！
            }
        }
        dfs(0,0,al);//不需要string了，只要mask，看mask有几个1就知道长度了！
        return rs;
    }
    void dfs(int b,int curMask,List<Integer> al){
        if(b==al.size()){
            rs=Math.max(rs,Integer.bitCount(curMask));
            return;
        }
        //看是想的是for循环对b，b+1，b+2.。。个string去试，其实不需要，只要两种情况，选b和不选b
        dfs(b+1,curMask,al);
        if((al.get(b)&curMask)==0){
            int now=al.get(b)|curMask;
           dfs(b+1,now,al);
        }
    }
}
