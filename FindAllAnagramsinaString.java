import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yufengzhu on 8/30/18.
 */
public class FindAllAnagramsinaString {
    public static void main(String[] args){
        FindAllAnagramsinaString fa=new FindAllAnagramsinaString();
        fa.findAnagrams("abacbabc","abc");
    }
    //自己有点想歪了，想成group anaragrm那个count方法了,写的不好，就是如何利用count和对应数组加减取舍有点恶心
    //https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92015/ShortestConcise-JAVA-O(n)-Sliding-Window-Solution
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> rs=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        char[] ch=p.toCharArray();
        int[] a=new int[26];
        for(char c:ch){
            a[c-'a']++;
        }
        int count=p.length();
        int b=0;
        int e=0;
        char[] cc=s.toCharArray();
        while (e<s.length()){//就是sliding window，如果当前这个窗口满足anargram那就把起始点b加入结果集，然后窗口往右移动，b++，e++
            int index=cc[e]-'a';
            if(a[index]>=1){
                count--;
            }
            a[index]--;//之前这个放在上面的if里面了，其实不管他当前字母是不是所需的字母，只要在当前b到e的范围里，就把它--
            if(count==0){
                rs.add(b);
                int indexb=cc[b]-'a';
                a[indexb]++;
                b++;
                e++;
                count++;
                continue;
            }

            if(e-b+1<p.length()){
                e++;
                continue;
            }else{
                int indexb=cc[b]-'a';
                if(a[indexb]>=0){//大于等于0就是说明把这个b除掉之后，肯定需要indexb这个字母了
                    count++;
                }
                a[indexb]++;//同上面，也是开始放在上面的if里了，其实只要排除在当前b到e之外了，就把它++
                b++;
                e++;
            }
        }
        return rs;
    }
}
