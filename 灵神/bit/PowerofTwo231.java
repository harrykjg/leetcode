package 灵神.bit;

public class PowerofTwo231 {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(16));
    }
    //自己想的就是只能第一位是1，那么久把1左移31,30.。。去and n，只能找到一次1。也能pass但不好
    //看别人的解法https://leetcode.cn/problems/power-of-two/solutions/2973442/yan-ge-zheng-ming-yi-xing-xie-fa-pythonj-h04o/
    public static boolean isPowerOfTwo(int n) {

        if(n<=0){
            return false;
        }
        return (n&(n-1))==0; //必须是1000，-1就是0111，&就必须是0
    }
}
