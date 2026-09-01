package SomeInterviews.verkarda;

import java.util.HashMap;
import java.util.Map;

public class GuessWord {
    /*
    实现一个WordGuess的game feature
GREEN = 2
YELLOW = 1
GRAY = 0
exact match = GREEN
mismatch = YELLOW
no match = GRAY
For example,
secret = "h e l l o"
guess = "a b c d e"
result = [0, 0, 0, 0, 1]
guess = "h a b x z"
result =[2, 0, 0, 0, 0]
guess = "e a b c d"
result = [1 ,0, 0, 0, 0]

Need to throw exception when input is not valid

gpt说的
规则应该是：

GREEN = 2：guess[i] == secret[i]
YELLOW = 1：当前位置不一样，但这个字符在 secret 的其他位置存在
GRAY = 0：secret 里没有这个字符
非法输入要 throw exception
还有重复字符怎么处理，比如
secret = "hello"
guess  = "lllll"
不能所有 l 都 yellow/green，因为 secret 只有两个 l。
所以标准做法应该是 两遍扫描：

第一遍先找 GREEN，并统计 secret 中剩余未匹配字符的数量。

第二遍处理非 GREEN：

如果还有这个字符的剩余 count → YELLOW，并 count--
否则 → GRAY
     */
    public int[] guess(String secret, String guess) {
        if (secret == null || guess == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (secret.length() != guess.length()) {
            throw new IllegalArgumentException("Length must match");
        }
        int[] rs=new int[secret.length()];
        char[] ch1=secret.toCharArray();
        char[] ch2=guess.toCharArray();
        Map<Character,Integer> map=new HashMap<>();
        for (int i=0;i<ch1.length;i++){
            if(ch1[i]==ch2[i]){
                rs[i]=2;
            }else {
                //注意这里只统计没被消耗的
                map.put(ch1[i],map.getOrDefault(ch1[i],0)+1);
            }
        }
        // 第二遍：处理 YELLOW / GRAY
        for (int i = 0; i < ch2.length; i++) {
            if (rs[i] == 2) {
                continue;
            }
            if (map.containsKey(ch2[i]) && map.get(ch2[i]) > 0) {
                map.put(ch2[i], map.get(ch2[i]) - 1);
                rs[i] = 1;
            }
        }
        return rs;
    }
}
