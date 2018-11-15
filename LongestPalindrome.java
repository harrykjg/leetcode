import java.util.HashMap;

/**
 * Created by 502575560 on 6/21/17.
 */
public class LongestPalindrome {
    public static void main(String[] args){
        System.out.println(longestPalindrome("abcccddddd"));
    }
    //自己想的规律,就是扫描记录每个字母的个数,要是每个字母的个数都是偶数则最长就是他们的和,假如有1个或几个字母个数为奇数,则取最长的那个奇数,加上所欲偶数的和
    //就是结果,如果没有偶数只有奇数则取最长的奇数,加上所有奇数-1个的长度
    //自己写了2个版本,自以为longestPalindrome2比较慢因为多遍历了一次map 取找最大的奇数,所以会慢一点,结果反而比较快??
    //https://segmentfault.com/a/1190000007545629  别人的不用找到最长的那个奇数,见longestPalindrome2解释
    //http://www.cnblogs.com/grandyang/p/5931874.html
    public static int longestPalindrome(String s) {
        HashMap<Character,Integer> map=new HashMap<>();
        int rs=0;
        int maxOdd=0;
        for(int i=0;i<s.length();i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),1);
            }else {
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }
        }
        boolean alreadyAddOdd=false;
        for(Integer count :map.values()){
            if(count%2==0){
                rs+=count;
                continue;
            }
            if(count>maxOdd){
                if(alreadyAddOdd){
                    rs-=1;
                    rs+=count;
                }else{
                    alreadyAddOdd=true;
                    rs+=count;
                }
                continue;
            }
            rs+=count-1;


        }
        return rs;
    }
    public int longestPalindrome2(String s) {
        HashMap<Character,Integer> map=new HashMap<>();
        int rs=0;
        Character odd=' ';
        int maxOdd=0;
        for(int i=0;i<s.length();i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),1);
            }else {
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }

        }
        for(Character c :map.keySet()){
            int temp=map.get(c);
            if(temp%2!=0&&temp>maxOdd){
                maxOdd=temp;
                odd=c;
            }
        }
        for(Character c :map.keySet()){
            int count=map.get(c);
            if(count%2==0){
                rs+=count;
                continue;
            }
            if(!odd.equals(' ')&&c.equals(odd)){
                rs+=count;//只要是奇数的话都rs+=count-1,最大的奇数就不-1
                continue;//
            }
            rs+=count-1;
        }
        return rs;
    }
}
