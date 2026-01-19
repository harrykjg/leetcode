package 灵神.贪心;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MaximumPalindromesAfterOperations3035 {
    static void main() {
        String[] w={"abbb","ba","aa"};
        System.out.println(maxPalindromesAfterOperations(w));
    }
    //1/5/2025 想不出来
    //由于是任意换字符，自己给自己换也行，因此不是考虑每一对字符串怎么换可以达到最好效果，而是直接统筹全部字符，再塞给每个字符串看能组成多少个回文
    // 思路就是统计所有字符串的总长度，也统计所有字符的个数，可知对每一个字符串，如果是偶数长度，就得使用偶数个字符去填，如果是奇数长度就是长度-1之后
    //再需要偶数个字符。具体写法是求总字符串长度，减去出现奇数的字符(比如说aaa，则说奇数字符串出现了1次)，得到一个数n，
    // 这个n就是比如aaaa，bb这种出现偶数的字符的数量，再用他们去按字符串长度从小到大填空，能填多少就是答案
    //灵神是用了位运算去统计奇数字符，不熟
    //https://leetcode.cn/problems/maximum-palindromes-after-operations/solutions/2637686/gou-zao-tan-xin-pythonjavacgo-by-endless-ib49/
    public static int maxPalindromesAfterOperations(String[] words) {
        int rs=0;
        Map<Character,Integer> map=new TreeMap<>();
        int total=0;
        for(String s:words){
            char[] ch=s.toCharArray();
            total+=ch.length;
            for (char c:ch){
                map.put(c,map.getOrDefault(c,0)+1);//答案用mask ^= 1 << (c - 'a');这样mask有1的位数就是奇数的个数
            }
        }
        int odd=0;
        for(Integer count:map.values()){
            if(count%2!=0){
                odd++;
            }
        }
        int n=total-odd;
        Arrays.sort(words,(a,b)->a.length()-b.length());
        for(int i=0;i<words.length;i++){
            int len=words[i].length();
            if(len%2==0){
                n-=len;
            }else{
                n-=(len-1);
            }
            if(n<0){
                break;
            }
            rs++;
        }
        return rs;
    }
}
