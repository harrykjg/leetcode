package 灵神.sidingWindow.双指针;

public class MinimumLengthofStringAfterDeletingSimilarEnds1750 {

    public static void main(String[] args) {
        System.out.println();
    }
    //不难，就是边界条件
    //https://leetcode.cn/problems/minimum-length-of-string-after-deleting-similar-ends/solutions/2033813/shan-chu-zi-fu-chuan-liang-duan-xiang-to-biep/
    public int minimumLength(String s) {
        int b=0;
        int e=s.length()-1;

        while (b<e){
            if(s.charAt(b)==s.charAt(e)){
                while (b+1<e&&s.charAt(b)==s.charAt(b+1)){
                    b++;
                }
                while (e-1>b&&s.charAt(e)==s.charAt(e-1)){
                    e--;
                }
                b++;
                e--;
            }else{
                break;
            }
        }

        if(e<b){
            return 0;
        }
        return e-b+1;

    }
}
