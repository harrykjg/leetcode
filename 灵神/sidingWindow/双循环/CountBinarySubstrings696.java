package 灵神.sidingWindow.双循环;

import java.util.ArrayList;

public class CountBinarySubstrings696 {
    public static void main(String[] args) {

    }
    //想不到最优解， 看https://leetcode.cn/problems/count-binary-substrings/solutions/367704/ji-shu-er-jin-zhi-zi-chuan-by-leetcode-solution/
    public int countBinarySubstrings(String s) {
        ArrayList<Integer> al=new ArrayList<>();
        char[] ch=s.toCharArray();
        int i=0;
        int rs=0;
        while (i<ch.length){
            int count=1;
            while (i+1<ch.length&&ch[i]==ch[i+1]){
                count++;
                i++;
            }
            al.add(count);
            i++;
        }
        for(i=0;i+1<al.size();i++){
            int min=Math.min(al.get(i),al.get(i+1));
            rs+=min;
        }

        return rs;
    }
}
