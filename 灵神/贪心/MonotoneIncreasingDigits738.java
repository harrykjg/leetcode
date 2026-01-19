package 灵神.贪心;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MonotoneIncreasingDigits738 {
    static void main() {
        System.out.println(monotoneIncreasingDigits(999998));
    }
    //1/4/2026,写不对。看答案，其实是要找到第一个i>i+1的数字，然后看0到i这一段找最大的那个index，然后index这个数字-1，index+1之后都设成9
    //https://leetcode.cn/problems/monotone-increasing-digits/solutions/521966/jian-dan-tan-xin-shou-ba-shou-jiao-xue-k-a0mp/
    public static int monotoneIncreasingDigits(int n) {
        String s=Integer.toString(n);
        char[] ch=s.toCharArray();
        int rs=0;
        int i=0;
        int max=ch[0]-'0';
        int index=0;
        for(;i+1<ch.length;i++){
            if(ch[i]>max){
                max=ch[i];
                index=i;
            }
            if(ch[i]-'0'<=ch[i+1]-'0'){
                continue;
            }else{//注意不是把i-1，是当前最大的那个，如399443，则index是1，答案是389999
                ch[index]= (char) (ch[i]-1);//如果这样写 ch[i] = (char)((ch[i] - '0' - 1) + '0')要注意要再加'0'才能转成字符
                for(int j=index+1;j<ch.length;j++){
                    ch[j]='9';
                }
            }
        }

        return Integer.valueOf(String.copyValueOf(ch));
    }
}
