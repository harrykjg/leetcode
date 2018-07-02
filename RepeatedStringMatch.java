import java.util.HashMap;
import java.util.Map;

/**
 * Created by yufengzhu on 7/1/18.
 */
public class RepeatedStringMatch {
    //瞎写的居然一次过，就是贪心法，先把A加到长度大于等于B,然后检测有没有substring是B,不行的话再append一个A,再检测，没有的话就是没有了
    //https://leetcode.com/problems/repeated-string-match/solution/  第一个答案也是这个方法
    public int repeatedStringMatch(String A, String B) {
        int curLen=A.length();
        int count=1;
        String AA=A;
        while (curLen<B.length()){
            curLen+=A.length();
            AA+=A;
            count++;
        }
        for(int i=0;i<=AA.length()-B.length();i++){
            if(AA.substring(i,B.length()+i).equals(B)){
                return count;
            }
        }
        AA+=A;
        count++;
        for(int i=0;i<=AA.length()-B.length();i++){
            if(AA.substring(i,B.length()+i).equals(B)){
                return count;
            }
        }
        return -1;

    }

}
