import java.util.ArrayList;
import java.util.Random;

/**
 * Created by yufengzhu on 8/12/18.
 */
//randompickindex
public class RandomPickwithWeight {
    //这个方法果然内存用的太多，就是这个元素的weight是多少，那么我就在这个总的array里方济各他，再random就行了
    ArrayList<Integer> al=new ArrayList<>();
    Random random=new Random();
    public RandomPickwithWeight(int[] w) {
        for(int i=0;i<w.length;i++){
            int weight=w[i];
            for(int j=0;j<weight;j++){
                al.add(i);
            }
        }
    }

    public int pickIndex() {
        return al.get(random.nextInt(al.size()));
    }

    //这个是别人的方法 https://leetcode.com/problems/random-pick-with-weight/discuss/154044/Java-accumulated-freq-sum-and-binary-search
    int[] sums;
    Random ran=new Random();
    public RandomPickwithWeight2(int[] w) {//妈的原来前缀和那里算错了
        sums=new int[w.length];
        sums=w;//这里漏了之前先copy一次w否则就错了
        for(int i=1;i<w.length;i++){
            sums[i]+=w[i-1];
        }
    }

    public int pickIndex2() {//比如输入是1，2，3，代表0这个数权重是1，1权重是2，2权重是3，前缀和是1，3，6，画成区间就是【0，1），【1，3），【3，6）。那么randome出来是数字是[0,6)左开右闭，
        //如果ran出来的数字落在[0,1)则返回0，落在[1,3)，则返回1，以此类推，即random出一个数，在前缀和数组从左到右找到第一个比这个ran出来的数字大的那个数所在index就行了，他们链接用的二分法，他们写的不太好理解
        int index=ran.nextInt(sums[sums.length-1]);
        for(int i=0;i<sums.length;i++){
            if(index<sums[i]){
                return i;
            }
        }
        return -1;
    }
//8／16／2018才做不到一星期又忘了，试试二分法,二分法的话，就不好写了，等于要二分查找一个区间
}
