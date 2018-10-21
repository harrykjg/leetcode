import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 502575560 on 5/23/17.
 */
public class IsSubsequence {

    public static void main(String[] args){
        System.out.println(IsSubsequence.isSubsequence4("abc","ahbgdc"));
    }

    //看了tag时greddy,然后自己想的,比较简单,typo之后一次过
    //这follow up的答案没看明白
    //http://blog.csdn.net/qz530308783/article/details/52463203 找到s中每个字符在t中的位置,若存在大于前一个字符在t中的位置就算成功,否则失败,有点绕
    //https://discuss.leetcode.com/topic/58367/binary-search-solution-for-follow-up-with-detailed-comments   看他的例子
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
    //9/24/2018,写的很不好,要再练
    public static boolean isSubsequence2(String s, String t) {
        if(t.length()<s.length()){
            return false;
        }
        if(s.length()==0){
            return true;
        }
        int i=0;
        int j=0;
        while (i<s.length()&&j<t.length()){
            if(s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            }else{
                j++;
            }
            if(i==s.length()){
                return true;
            }

        }
        return false;
    }
    //follou up 看这个的例子https://leetcode.com/problems/is-subsequence/discuss/87302/Binary-search-solution-for-follow-up-with-detailed-comments
    public static boolean isSubsequence3(String s, String t) {
        HashMap<Character,ArrayList<Integer>> map=new HashMap<>();
        char[] ch=t.toCharArray();
        for(int i=0;i<t.length();i++){
            if(!map.containsKey(ch[i])){
                ArrayList<Integer> al=new ArrayList<>();
                map.put(ch[i],al);
            }
            map.get(ch[i]).add(i);
        }

        ch=s.toCharArray();
        int pre=-1;//这里和他写的不一样，他那个binarysearch返回的东西有点没看懂，
        for(int i=0;i<ch.length;i++){
            if(!map.containsKey(ch[i])){
                return false;
            }
            ArrayList<Integer> al=map.get(ch[i]);
            int next=searchInsertPos(al,pre+1);//要找到下一个match的位置
            if(next==-1){
                return false;
            }else{
                pre=next;
            }
        }
        return true;
    }
    static int searchInsertPos(ArrayList<Integer> al,int key){//应该可以改成binarysearch，这里我就直接遍历了
        for(int i=0;i<al.size();i++){
            if(al.get(i)==key){//注意是返回al.get(i)而不是返回i
                return al.get(i);
            }
            if(al.get(i)>key){
                return al.get(i);
            }
        }
        return -1;
    }
    //基础方法,妈的还写的不太顺
    public static boolean isSubsequence4(String s, String t) {
        if(s.length()==0){
            return true;
        }
        if(t.length()<s.length()){
            return false;
        }
        int j=0;
        for(int i=0;i<t.length();i++){
            if(s.charAt(j)==t.charAt(i)){
                j++;
                if(j==s.length()){
                    return true;
                }
                continue;
            }
        }
        return false;
    }
    //follow up
    public static boolean isSubsequence5(String s, String t) {
        HashMap<Character,List<Integer>> map=new HashMap<Character, List<Integer>>();
        for(int i=0;i<t.length();i++){
            if(!map.containsKey(t.charAt(i))){
                ArrayList<Integer> al=new ArrayList<>();
                map.put(t.charAt(i),al);
            }
            map.get(t.charAt(i)).add(i);
        }
        int pre=-1;

        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(!map.containsKey(c)){
                return false;
            }
            List<Integer> al=map.get(c);
            int next=findNext(al,pre);
            if(next==-1){
                return false;
            }else{
                pre=next;
            }
        }
        return true;
    }
    static int findNext(List<Integer> al,int pre){
        for(int i=0;i<al.size();i++){
            if(al.get(i)>pre){
                return al.get(i);
            }
        }
        return -1;
    }
}
