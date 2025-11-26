package 灵神.bit;

public class NumberComplement476 {
    public static void main(String[] args) {
        System.out.println(findComplement(5));
    }
    //不会
    //https://leetcode.cn/problems/number-complement/solutions/1050060/shu-zi-de-bu-shu-by-leetcode-solution-xtn8/
    //
    // https://leetcode.cn/problems/number-complement/solutions/1050060/shu-zi-de-bu-shu-by-leetcode-solution-xtn8/ 看golang的能理解，例子.就是先找到原数字的高位，把这个高位+1 再减1就是11111000
    //num          = 00000101
    //mask         = 11111000
    //~mask & ~num = 00000010
    public static int findComplement(int num) {
        int height=0;
        for(int i=31;i>=0;i--){
            if(((1<<i)&num) >0){ //是1左移i位再&num 还是num右移i位再&1呢? 都行
                height=i;
                break;
            }
        }
        int mask= (1<<(height+1)) -1;
        return num^mask;
    }
}
