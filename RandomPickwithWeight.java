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


    int sums2[];
    //04/14/2020,还是不记得，看回以前的
    public Solution(int[] w) {
            sums2=new int[w.length];
            sums2=w;//举个例在看看还真是要这样才能算前缀和
            for(int i=1;i<w.length;i++){
                sums2[i]=w[i]+sums2[i-1];
            }
    }

    public int pickIndex() {//看别人答案的代码，自己用模版的二分法貌似用模版就是不行
        int rn=ran.nextInt(sums2[sums2.length-1])+1;//他这里+1再结合二分法，还是不好想。
        int b=0;
        int e=sums2.length-1;
        int m=0;

        while (b<e){
            m=b+(e-b)/2;
            if(sums2[m]==rn){
                return m;
            }
            if(sums2[m]>rn){
                e=m;
            }
            if(sums2[m]<rn){
                b=m+1;
            }
        }
        return b;
    }
    //8/12/2021 这次sum是长度+1的。长度加一的原因是因为一般取subarray 当前这个index的值的时候是要减去sum【i-1】这样，所以方便起见多加前面一个位置把。
    //这里是不需要的
    int[] sum;
    public void Solution(int[] w) {
        sum=new int[w.length+1];
        for(int i=1;i<sum.length;i++){
            sum[i]=sum[i-1]+w[i-1];
        }
    }

    public int pickIndex3() {//比如说现在w是2，3，4，前缀和是 0 ，2， 5  9。现在random出来一个数是【1，9】之间，二分法找，若是值是【1，2】则落到第一个值
        Random ran=new Random(); //若是【3，5】则第二个，若【6，9】则第三个。那么二分法这里刚好sum[m]==r，比如2，的话就肯定是落在那个第一个区间上了，sum对应
        int r=ran.nextInt(sum[sum.length-1])+1; //的下标是1，返回m-1刚好对。
        int b=0;
        int e=sum.length-1;
        while(b<e){
            int m=(b+e)/2;
            if(sum[m]==r){
                return m-1;
            }else if(sum[m]>r){
                e=m;
            }else{
                b=m+1;
            }
        }
        return b-1;
    }

    //8/24/2021 这次写的sum就是没有长度加1的，原因是我觉得不需要找某个区间的sum像sum【i】-sum【j】这样，因此也没有j小于0这种情况。我到时random出来一个数
    //我只需要找到第一个大于这个random的数就是他所在的区间。比如ran出来是0，那么第一个区间就是第一个大于他的数，0就是要返回的值。
    int[] sum4;
    public void Solution4(int[] w) {
        sum=new int[w.length];
        sum[0]=w[0];
        for(int i=1;i<sum.length;i++){
            sum[i]=sum[i-1]+w[i];
        }
    }

    public int pickIndex4() {
        Random ran=new Random();
        int n=ran.nextInt(sum[sum.length-1]);//正好ran出来0到最大值-1，因此只要找到第一个sum大于n的index就是结果
        int b=0;
        int e=sum.length-1;
        while(b<e){
            int m=(b+e)/2;
            if(sum[m]>n){//发现当前值大于n了，但是不知道是不是第一个大于n的，因此向左继续找
                e=m;
            }else{
                b=m+1;
            }
        }
        if(sum[b]<n){//由于while里是不断试左边的，因此最后退出来是b可能是不符合条件的，就+1，结果不限这个判断直接返回b也对。
            return b+1;
        }
        return b;
    }

    //9/28/2021,这次二分法写的好一些。
    int[] sums5;
    Random ran5=new Random();
    public void Solution5(int[] w) {
        sums=new int[w.length+1];
        for(int i=1;i<sums.length;i++){
            sums[i]=w[i-1]+sums[i-1];
        }
    }

    public int pickIndex5() {//就是举例子看，比如【1。3】那么sums是【0，1，4】，那么random出来的int应该是nextInt（4），得出0，1，2，3，然后就要找
        //0，1，2，3回落到sums【0，1，4】的哪个区间，这个sums的index就是结果。二分法b=0，e=sums。length-1，判断r<sums[m+1]&&r>=sums[m]的话就是找到
        //区间了，return m。否则走左边或右边。完了直接返回b
        int r=ran.nextInt(sums[sums.length-1]);
        int b=0;
        int e=sums.length-1;
        while(b<e){
            int m=(b+e)/2;
            if(r<sums[m+1]&&r>=sums[m]){
                return m;
            }else if(r<sums[m]){
                e=m;
            }else{
                b=m+1;
            }
        }
        return b;
    }
}
