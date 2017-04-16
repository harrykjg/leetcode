/**
 * Created by 502575560 on 3/1/17.
 */
public class CountofRangeSum {


    //只能想到n方的做法,就是从nums的第0,1,2,3..位开始取长度位1,2,3..的间隔,算出sum,符合的就加1

    //http://blog.csdn.net/wuxiangyu666/article/details/50496523
    //https://discuss.leetcode.com/topic/33738/share-my-solution/2
    //http://www.cnblogs.com/EdwardLiu/p/5138198.html
    //还有树状数组（Fenwick Tree）解法,懒得看了
    int rs=0;
    public int countRangeSum(int[] nums, int lower, int upper) {

        if(nums==null||nums.length==0){
            return 0;
        }
        long sum[]=new long[nums.length+1];
        for(int i=1;i<nums.length;i++){
            sum[i]=sum[i-1]+nums[i];
        }
        devideAndconquer(sum,0,nums.length,lower,upper);
        return rs;

    }
    public long[] devideAndconquer(long[] sum,int b,int e,int low,int up){
        if(b>e){
            //好像不会出现这种情况,b=e时就处理掉了
            return new long[0];
        }
        long[] temp=new long[e-b+1];
        if(e==b){
            temp[0]=sum[0];
            return temp;
        }
        int mid=(b+e)/2;
        long[] left=devideAndconquer(sum,b,mid,low,up);
        long[] right=devideAndconquer(sum,mid+1,e,low,up);
        return merge(left,right,low,up);


    }
    public long[] merge(long[] l,long[] r,int low,int up){
        //好像不会有l或者r长度为0的情况
        int m=-1;
        int n=-1;
        int b=0;
        int e=0;
        int mid=0;
        long[] temp=new long[l.length+r.length];
        for(int i=0;i<l.length;i++) {//用二分法找到m和n,m-n就是符合条件的个数
            //找右边点
            b=0;
            e=r.length-1;
            while (b < e) {
                mid = (b + e) / 2;
                if ((r[mid] - l[i] > up && mid - 1 >= 0 && r[mid - 1] - l[i] <= up) || mid == 0 && (r[mid] - l[i] > up)) {
                    //m is the first index satisfy sums[j] - sums[i] > upper and
                    //n is the first index satisfy sums[k] - sums[i] >= lower.  然后m-n就得出这段区间符合条件的个数,
                    // 自己举个例子,假如0,1,2,3四个数,r[3]-l[i]的时候就大于up了,说明3时零界点,如果r[1]-l[i]>=low说明到r数
                    //组中index为1,2即2个数符合条件,即3-1=2个
                    m = mid;
                    break;
                }
                if (r[mid] - l[i] <= up) {
                    b = mid + 1;
                } else {
                    e = mid;
                }
            }
            //找左边点
            b=0;
            e=r.length-1;
            while (b < e) {
                mid = (b + e) / 2;
                if ((r[mid] - l[i] >= low && mid - 1 >= 0 && r[mid - 1] - l[i] < low) || mid == 0 && (r[mid] - l[i] >= low)) {
                    //m is the first index satisfy sums[j] - sums[i] > upper and
                    //n is the first index satisfy sums[k] - sums[i] >= lower.  然后m-n就得出这段区间符合条件的个数,
                    // 自己举个例子,假如0,1,2,3四个数,r[3]-l[i]的时候就大于up了,说明3时零界点,如果r[1]-l[i]>=low说明到r数
                    //组中index为1,2即2个数符合条件,即3-1=2个
                    n = mid;
                    break;
                }
                if (r[mid] - l[i] <= low) {
                    b = mid + 1;
                } else {
                    e = mid;
                }
            }
            rs+=m-n;

        }
        //merge数组
        int p=0;
        int q=0;
        int t=0;
        while(p<l.length&&q<r.length){
            if(l[p]<=r[q]){
                temp[t]=r[q];
                q++;
            }else{
                temp[t]=l[p];
            }
            t++;
        }
        while(p!=l.length){
            temp[t]=l[p];
            p++;
        }
        while(q!=r.length){
            temp[t]=r[q];
            q++;
        }
        return temp;

    }
}
