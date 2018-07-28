/**
 * Created by yufengzhu on 7/26/18.
 */
public class HammingDistance {
    public static void main(String[] a){
        HammingDistance hd=new HammingDistance();
        hd.hammingDistance(1,7);
    }
    //看了解释才知道，就是比较两个数的二进制形式时对应bit位不同的个数
    //   1:      00001
    //   7:      00111   第2，3位不同，所以答案是2
    // https://www.cnblogs.com/grandyang/p/6201215.html，
    //
    public int hammingDistance(int x, int y) {
        int temp=x^y;//先异或，然后看每一位是1的话就说明是不同,链接解法3有一个trick是num & (num - 1)可以快速地移除最右边的bit 1，这里没用
        int rs=0;
        while (temp>0){//然后对于temp，用1模temp的最后一位，是1的话，说明temp的最后以为是1，说明之前异或产生的值是1，说明那个为止的值不同，所以rs++
            if((temp&1)==1){
                rs++;
            }
            temp>>=1;
        }
        return rs;

    }
}
