package contest;

public class SplitTwoStringstoMakePalindrome {
    public static void main(String[] args){
        SplitTwoStringstoMakePalindrome st=new SplitTwoStringstoMakePalindrome();
        System.out.println(st.checkPalindromeFormation("abda","acmc"));
    }
    //直接暴力法会超时，暴力法应该是n方复杂度
    //https://leetcode.com/problems/split-two-strings-to-make-palindrome/discuss/888885/C%2B%2BJava-Greedy-O(n)-or-O(1)
    //他们的想法貌似是直接从a开头和b结尾向中间夹逼（a从i开始，b从j开始），或者b开口和a结尾向中间夹逼,然后只要再检查a的i到j这中间的一段是否是palindorm，或者b的i到j中间这段是否palindorm就行了
    //还比较不好想
    //https://leetcode.com/problems/split-two-strings-to-make-palindrome/discuss/888985/Java-clean-O(n)-with-explanations
    public boolean checkPalindromeFormation(String a, String b) {
        return helper(a,b)||helper(b,a);
    }
    boolean helper(String s1, String s2){
        int i=0;
        int j=s2.length()-1;
        while (i<j&&s1.charAt(i)==s2.charAt(j)){
            i++;
            j--;
        }
        return check(s1,i,j)||check(s2,i,j);
    }
    boolean check(String s1,int b,int e){
        while (b<e){
            if(s1.charAt(b)!=s1.charAt(e)){
                return false;
            }
            b++;
            e--;
        }
        return true;
    }
}
