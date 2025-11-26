package 灵神.bit;

public class FindthePrefixCommonArrayofTwoArrays2657 {
    public static void main(String[] args) {

    }
    //bit的解法
    //https://leetcode.cn/problems/find-the-prefix-common-array-of-two-arrays/solutions/2250788/li-yong-wei-yun-suan-jia-su-pythonjavacg-uzme/
    //普通方法
    //https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/solutions/6276375/beats-100-find-the-prefix-common-array-b-4i3s/
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int[] rs=new int[A.length];
        long a=0;
        long b=0;
        for(int i=0;i<A.length;i++){
            a |=1L<<A[i]; //把1左移A【i】位，假如A【i】是3，就是把1变成 1000，相当于把A【i】这个位标记了，a |=这个数就还是这个数
            b |=1L<<B[i]; //题目说了A,B都是permutation，没有重复的数字。把第B【i】位也标记了
            rs[i]= Long.bitCount(a&b); //假如a b是一样的话那a&b就是一样的，如1000&1000=1000，long。bitcount就是数有几个1。如 i=1的时候，把A，B数组的数标记了对应位的1，再&，
            //如果有交集的话，那么1的数量就是交集的数量。GPT上有解释
        }
        return rs;
    }
}
