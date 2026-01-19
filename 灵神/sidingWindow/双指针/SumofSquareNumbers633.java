package 灵神.sidingWindow.双指针;

public class SumofSquareNumbers633 {
    public static void main(String[] args) {

    }
    //https://leetcode.cn/problems/sum-of-square-numbers/solutions/2973811/liang-chong-fang-fa-mei-ju-shuang-zhi-zh-c26z/
//不会，看答案
    public boolean judgeSquareSum(int c) {
        int b=0;
        int e=(int)Math.sqrt(c);
        while (b<=e) {
            if(b*b==c-e*e){
                return true;
            }
            if(b*b<c-e*e){
                b++;
            }else{
                e--;
            }

        }

        return false;

    }

    //1/16/2026还是不会，想是不是要同时枚举a和b，其实不用，只要枚举a那么b就能通过a方+b方=c得到
    //https://www.bilibili.com/video/BV1XPDHY7ErC/
}
