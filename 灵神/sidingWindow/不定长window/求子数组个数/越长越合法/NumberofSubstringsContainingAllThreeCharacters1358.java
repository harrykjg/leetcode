package 灵神.sidingWindow.不定长window.求子数组个数.越长越合法;

public class NumberofSubstringsContainingAllThreeCharacters1358 {
    public static void main(String[] args) {

    }
    //https://leetcode.cn/problems/number-of-substrings-containing-all-three-characters/solutions/2990226/mo-ban-yue-chang-yue-he-fa-xing-hua-dong-2g7a/
    //不会，看人家的解释。这种求子数组个数的都不太好想
    public int numberOfSubstrings(String s) {
        int b=0;
        int e=0;
        char[] ch=s.toCharArray();
        int[] count=new int[3];
        int rs=0;
        while (e<ch.length){
            count[ch[e]-'a']++;
            while (b<e&&count[0]>0&&count[1]>0&&count[2]>0){
                count[ch[b]-'a']--;
                b++;
            }
            e++;
            rs+=b;
        }
        return rs;
    }
}
