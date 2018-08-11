import java.util.*;

/**
 * Created by yufengzhu on 8/9/18.
 */
public class RandomPickwithBlacklist {
    public static void main(String[] args){
        RandomPickwithBlacklist rp=new RandomPickwithBlacklist(6,new int[]{0,4,2});
        rp.pick();

    }

    //先写了暴力法，没什么技术含量，就是用个set撞着blacklist，然后删掉了

    //题目要求是要minimise random的call，说明不能像暴力法那样random一个出来看是不是blacklist，是的话要再去random，就是说要尽量每个random出来的都是valid的。
    //知0到n-1是候选号码，但是中间夹杂了一些不能选的数字，导致不能直接random出来一个0到n-1这个范围的数字，那咋办呢？他们的办法就是，先缩小random的范围，使其变成0到N-blacklist.size个，
    //那么random出来的数字要不是直接就valid，要不是就是不valid，对于不valid的数字，（结合图片想），用一个map装着key为不valid的数，value为他们所指向的某个valid的数字，有点像把valid的全部
    //放到左边，不valid的全部放到右边，那么random的数字在0到N-blacklist.size这个范围内就肯定是合法的。想起来和答案第一种方法whitelist比较像，但是whitelist的缺点是要装太多的valid的
    //数字了，而他们的方法巧妙之处就是用map记录不valid的数字，如果random到了这些不valid的数字的话，让他们指向valid的数字。真tm难想
//https://my.oschina.net/yysue/blog/1846164
    //https://leetcode.com/problems/random-pick-with-blacklist/discuss/144624/Java-O(B)-O(1)-HashMap
    int M;
    Map<Integer, Integer> map;
    Random r;
    public RandomPickwithBlacklist(int N, int[] blacklist) {
        M = N - blacklist.length;
        map = new HashMap<>();
        r = new Random();
        for (int tmp : blacklist) {
            map.put(tmp, -1);
        }

        for (int tmp : blacklist) {//比如N是6，blacklist是（0，2，4），先初始化map，key是blacklist里的值，value是他们对应的一个valid是值，初始化为-1。
            if (tmp < M) {
                while (map.containsKey(N-1)) {//比如说先来了tmp是0，则先看map中有没有存在从5开始到4，3，2，1，0的数字，有的话说明5这个数本来也是blacklist的，而我们要找一个valid的数字，所以继续往下找，
                    N--;                   //直到找到一个valid的数字，作为0这个不合法的值对应的合法的值
                }
                map.put(tmp, --N);
            }
        }
    }

    public int pick() {
        int p = r.nextInt(M);
        if (map.containsKey(p)) return map.get(p);
        return p;
    }

}
