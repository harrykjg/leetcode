package contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueLength3PalindromicSubsequences {
    public static void main(String[] args){
        UniqueLength3PalindromicSubsequences up=new UniqueLength3PalindromicSubsequences();
        System.out.println(up.countPalindromicSubsequence2("uuuuuu"));
    }
    //暴力法超时,维护一个左右数组记录i左边和右边字母的个数，如果左边有的字母右边也有则构造字符串放进set，然后中间的bar从1开始往后扫，每移动一次把右边的字母减掉，左边的加上
    public int countPalindromicSubsequence2(String s) {
        Set<String> set=new HashSet<>();
        int[] left=new int[26];
        int[] right=new int[26];
        for (int i=2;i<s.length();i++){
            right[s.charAt(i)-'a']++;
        }
        left[s.charAt(0)-'a']++;
        for (int i=1;i+1<s.length();i++){
            for (int j=0;j<left.length;j++){
                if (left[j]>0&&right[j]>0){
                    StringBuilder sb=new StringBuilder();
                    sb.append(j+'a');
                    sb.append(s.charAt(i));
                    sb.append(j+'a');
                    set.add(sb.toString());
                }
            }
            char mid=s.charAt(i);
            left[mid-'a']++;
            right[s.charAt(i+1)-'a']--;
        }
        return set.size();
    }
}
