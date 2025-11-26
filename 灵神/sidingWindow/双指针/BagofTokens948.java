package 灵神.sidingWindow.双指针;

import java.util.Arrays;

public class BagofTokens948 {
    public static void main(String[] args) {

    }
//https://leetcode.cn/problems/bag-of-tokens/solutions/15987/ling-pai-fang-zhi-by-leetcode/
    //当没点数的时候，用能量换点数，找最小的。当能量小于最小的时候，去把点数能量换成能量，找大的换
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int rs=0;
        int b=0;
        int e=tokens.length-1;
        while (b<=e){
            while (b<=e&&power>=tokens[b]){
                power-=tokens[b];
                rs++;
                b++;
            }
            if (b<e&&rs>0){//这里开始写成b《=e就错了，因为b=e时，既最后一个的时候，没有必要去换power了
                power+=tokens[e];
                rs--;
                e--;
            }else {
                return rs;
            }

        }
        return rs;

    }
}
