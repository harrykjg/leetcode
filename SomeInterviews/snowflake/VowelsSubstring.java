package SomeInterviews.snowflake;

import java.util.HashMap;
import java.util.Map;

public class VowelsSubstring {
    static void main() {
        VowelsSubstring vs=new VowelsSubstring();
        System.out.println(vs.vowelSubstring("aeiouxaeaeiouu"));
    }

    //原来题目说的是substring必须只包含aeiou，因此有非元音的话就不算.还不太好写，感觉没见过这种，比如aeaeiouu,这里sliding window应该怎么做？
    // 当我扫到aeaeiou的时候我应该先缩的话就不对，但是如果我是继续到aeaeiouu的时候再缩那也不对，因为会漏掉eaeiou这种。据说是LC1358差不多
    //其实这sliding window做不了
    public int vowelSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        reset(lastSeen);
        int start = 0; // 当前连续元音段的起点
        int ans = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // 遇到非元音，重新开始
            if (!lastSeen.containsKey(c)) {
                start = right + 1;
                reset(lastSeen);
                continue;
            }
            // 更新当前元音最后出现的位置
            lastSeen.put(c, right);
            // 找五个元音 lastSeen 里的最小值
            int minLast = Integer.MAX_VALUE;
            for (int pos : lastSeen.values()) {//这个就是每次都遍历一下5个元音最后一次出现的位置，取最小的，没有值就设minkast=-1，以便
                //判断是否找到5个了，真难想。如 aeaeiou, minlast就是第二个a的位置2，start就是0，因此rs+=2-0+1
                if (pos == -1) {
                    minLast = -1;
                    break;
                }
                minLast = Math.min(minLast, pos);
            }
            // 五个元音都出现过
            if (minLast != -1) {
                ans += minLast - start + 1;//这个start就是遇到第一个元音开始，遇到非元音就rest
            }
        }
        return ans;
    }
    void reset(Map<Character,Integer> map){
        map.put('a', -1);
        map.put('e', -1);
        map.put('i', -1);
        map.put('o', -1);
        map.put('u', -1);
    }
}
