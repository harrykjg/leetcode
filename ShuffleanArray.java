import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by 502575560 on 5/11/17.
 */
public class ShuffleanArray {
    //自己想的是先对数组排列组合,存在一个map里,key是从0自增的的数字,value是这个排列组合,然后随机产生一个key返回数组.写了一下有些bug就算了.看别人的思路就是是遍历
    //数组,然后随机把当前数字和下标random数字交换
    //http://www.cnblogs.com/grandyang/p/5783392.html
    //http://www.cnblogs.com/neweracoding/p/5763839.html
    class Solution {
        int[] origin;
        public Solution(int[] nums) {
            origin=nums;
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return origin;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int[] rs=new int[origin.length];
            for(int i=0;i<origin.length;i++){
                rs[i]=origin[i];
            }
            Random ran=new Random();
            for(int i=0;i<origin.length;i++){
                int change=ran.nextInt(origin.length);
                int temp=rs[i];
                rs[i]=rs[change];
                rs[change]=temp;
            }
            return rs;
        }
    }

}
