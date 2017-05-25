import java.util.Collections;

/**
 * Created by 502575560 on 5/23/17.
 */
public class IsSubsequence {

    public static void main(String[] args){
        System.out.println(IsSubsequence.isSubsequence("axc","ahbgdc"));
    }

    //看了tag时greddy,然后自己想的,比较简单,typo之后一次过
    //这follow up的答案没看明白
    //http://blog.csdn.net/qz530308783/article/details/52463203 找到s中每个字符在t中的位置,若存在大于前一个字符在t中的位置就算成功,否则失败,有点绕
    //https://discuss.leetcode.com/topic/58367/binary-search-solution-for-follow-up-with-detailed-comments
    //https://discuss.leetcode.com/topic/67167/java-code-for-the-follow-up-question
    public static boolean isSubsequence(String s, String t) {
        if(t.length()<s.length()){
            return false;
        }
        int b=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            int j=b;
            for(;j<t.length();j++){
                if(t.charAt(j)==c){
                    b=j+1;
                    break;
                }
            }
            if(j==t.length()){
                return false;
            }
        }
        return true;

    }
}
