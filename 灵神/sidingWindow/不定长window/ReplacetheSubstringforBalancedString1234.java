package 灵神.sidingWindow.不定长window;

import java.util.Map;

public class ReplacetheSubstringforBalancedString1234 {
    public static void main(String[] args) {
        System.out.println(balancedString("QQQWE"));
    }

    //看提示,但是左边要++也是看答案的
    //https://leetcode.cn/problems/replace-the-substring-for-balanced-string/solutions/2107520/ti-huan-zi-chuan-de-dao-ping-heng-zi-fu-f8fk8/
    public static int balancedString(String s) {
        int[] cnt = new int[26];
        int b=0;
        int e=0;
        int rs=Integer.MAX_VALUE;
        char[] ch=s.toCharArray();
        for(char c:ch){
            cnt[c-'A']++;
        }

        while (e<ch.length||b<e){//这里就算e=ch。length的时候，左边的b还得试着缩,他们是用for loop的保证b会++，我就得这样写有点别扭
            while (e<ch.length&&!good(cnt,ch.length)){
                char c=ch[e];
                cnt[c-'A']--;
                e++;
            }
            if(good(cnt,ch.length)){
                rs=Math.min(rs,e-b);
            }
            if(b<ch.length){
                cnt[ch[b]-'A']++;
                b++;
                if(b>=ch.length){
                    break;
                }
            }

        }
        return rs;
    }
    static boolean good(int[] cnt,int length){
        if(cnt['Q'-'A']<=length/4&&cnt['W'-'A']<=length/4&&cnt['E'-'A']<=length/4&&cnt['R'-'A']<=length/4){
            return true;
        }
        return false;
    }
    //1/15/2026题目不好理解，以为是直接统计字符就完事了，原来是是能replace连续的一段substring。看答案说的做法就是
//找到最短的「待替换子串」，使得「不替换的内容」中四个字符的出现次数小于等于n/4。做法是先扫一遍字符统计所有出现个数。然后再while便利字符串
    //出现的字符就在统计数组那减一，然后看统计字符的四个字符是不是都小于等于n/4，是的话说明e到end这一段是合法的，e到b这一段是多出来的应该被替换，
    //也就是答案的长度.关键还得缩b，不好想
    //https://leetcode.cn/problems/replace-the-substring-for-balanced-string/solutions/2108942/javahua-dong-chuang-kou-de-fan-xiang-si-2dz8w/

}
