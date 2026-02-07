package 灵神.DP.前后缀;

public class FlipStringtoMonotoneIncreasing926 {
    static void main() {

    }
    //不会，看答案.自己想不出
    //https://leetcode.com/problems/flip-string-to-monotone-increasing/solutions/189751/c-one-pass-dp-solution-0ms-on-o1-one-lin-pslr/
    public int minFlipsMonoIncr(String s) {
        char[] ch=s.toCharArray();
        int count1=0;
        int flip=0;
        for (int i=0;i<ch.length;i++){
            if(ch[i]=='1'){
                count1++;
            }else{
                flip++;
            }
            flip=Math.min(count1,flip);
        }
        return Math.min(flip,count1);

    }
}
