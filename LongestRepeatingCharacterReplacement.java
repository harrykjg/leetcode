import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 10/22/18.
 */
public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args){
        LongestRepeatingCharacterReplacement lr=new LongestRepeatingCharacterReplacement();
        System.out.print(lr.characterReplacement("AABABBA",1));
    }

    //自己想的不行，做不出,要再练，他这个就是2 pointer缩放，也叫sliding window？理解有点不同
    //https://www.cnblogs.com/grandyang/p/5999050.html
    //https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91271/Java-12-lines-O(n)-sliding-window-solution-with-explanation
    //https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91285/Sliding-window-similar-to-finding-longest-substring-with-k-distinct-characters
    public int characterReplacement(String s, int k) {
        char[] ch=s.toCharArray();
        int[] count = new int[26];
        int run=0;
        int wal=0;
        int max=1;
        int rs=0;
        while (run<ch.length){
            count[ch[run]-'A']++;
            max=Math.max(max,count[ch[run]-'A']);
            int gap=run-wal+1-max;//这里是关键的地方，找到最大的那个count，那么run-wal+1这个长度范围内，最frequent的的那个字母有max个，那么剩下的所有字母都要replace了，最多可以replace k个
            while(run-wal+1-max>k){//开始写的往左缩的时候，max是没更新的，居然也对，我觉得更新max更合理些，见第三个链接
                count[ch[wal]-'A']--;   //并且我觉得要用while，但是if也accept了
                wal++;
                int temp=0;//跟新max
                for(int i=0;i<count.length;i++){
                    temp=Math.max(temp,count[i]);
                }
                max=temp;
            }
            rs=Math.max(rs,run-wal+1);
            run++;

        }
        return rs;

    }

}
