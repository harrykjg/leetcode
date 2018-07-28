import java.util.HashMap;
import java.util.Map;

/**
 * Created by yufengzhu on 7/14/18.
 */
public class PalindromePermutation {
    //如果s长度是偶数数，则字母要成对出现，如果长度是奇数，则必须有一个单数,一次过
    public boolean canPermutePalindrome(String s) {
        if(s.length()==0){
            return true;
        }
        Map<Character,Integer> map=new HashMap<>();
        char[] ch=s.toCharArray();
        for(char c:ch){
            if(!map.containsKey(c)){
                map.put(c,1);
            }else{
                map.remove(c);
            }
        }
        if(ch.length%2==0){
            return map.size()==0;
        }else{
            return map.size()==1;
        }
    }
}
