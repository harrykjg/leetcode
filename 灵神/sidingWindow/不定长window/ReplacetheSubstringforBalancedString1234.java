package 灵神.sidingWindow.不定长window;

import java.util.Map;

public class ReplacetheSubstringforBalancedString1234 {
    public static void main(String[] args) {
        System.out.println(balancedString("QQWE"));
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

}
