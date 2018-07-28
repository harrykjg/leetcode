/**
 * Created by yufengzhu on 7/6/18.
 */
public class ShortestPalindrome {
    //自己想的，就是贪心法，如果一个长度为n的string，先检测第0到倒数第二位是不是回文，是的话只要在前面加上最后一维就是回文了，而且是最短的，如果不是的话
    //再看0到倒数第三位是不是回文，是的话在前面加上string的后两位就行了
    //https://leetcode.com/problems/shortest-palindrome/solution/ 答案第三个是kmp
    public String shortestPalindrome(String s) {
        if(s.length()<=1){
            return s;
        }
        if(valid(s,0,s.length()-1)){
            return s;
        }

        int end=s.length()-2;
        String rs="";
        for(int i=end;i>=0;i--){
            int index=dfs(s,i);
            if(index!=-1){
                StringBuilder sb=new StringBuilder();
                for(int j=index;j>0;j--){//这里写错了好几次，举个例子看清楚
                    sb.append(s.charAt(s.length()-1-j));
                }
                sb.append(s);
                return sb.toString();
            }
        }
        return rs;

    }
    int dfs(String s,int end){
        if (valid(s, 0, end)) {
            return s.length()-end-1;
        }
        return -1;
    }
    boolean valid(String s,int b,int e){

        while (b<e){
            if(s.charAt(b)!=s.charAt(e)){
                return false;
            }
            b++;
            e--;
        }
        return true;
    }
}
