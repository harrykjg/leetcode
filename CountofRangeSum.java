/**
 * Created by 502575560 on 3/1/17.
 */
public class CountofRangeSum {


    //只能想到n方的做法,就是从nums的第0,1,2,3..位开始取长度位1,2,3..的间隔,算出sum,符合的就加1

    //http://blog.csdn.net/wuxiangyu666/article/details/50496523
    //https://discuss.leetcode.com/topic/33738/share-my-solution/2
    //http://www.cnblogs.com/EdwardLiu/p/5138198.html
    //还有树状数组（Fenwick Tree）解法,懒得看了
    public static void main(String[] args){
        CountofRangeSum cr=new CountofRangeSum();
        int[] n={2147483647,-2147483648,-1,0};
        System.out.println(cr.countRangeSum(n,-1,0));
    }

    int rs=0;
    public int countRangeSum(int[] nums, int lower, int upper) {

        if(nums==null||nums.length==0){
            return 0;
        }
        long sum[]=new long[nums.length+1];//这里也要想一下,这个和的数组要比原素组多一位,并把第一位作为0,那么mergesort的时候肯定会merge第0和1位merge,
        for(int i=1;i<=nums.length;i++){    // sum[1]-sum[0]就确实会算上nums[0]到nums[0],如果符合nums[0]符合条件的话.即sum数组第一位是0的意义是让右边
            sum[i]=sum[i-1]+nums[i-1];     //的数组有机会减去这个0即得到他自身的值的机会
        }
        devideAndconquer(sum,0,nums.length,(long)lower,(long)upper);
        return rs;

    }
    public long[] devideAndconquer(long[] sum,int b,int e,long low,long up){
        if(b>e){
            //好像不会出现这种情况,b=e时就处理掉了
            return new long[0];
        }
        long[] temp=new long[e-b+1];
        if(e==b){
            temp[0]=sum[b];
            return temp;
        }
        int mid=(b+e)/2;
        long[] left=devideAndconquer(sum,b,mid,low,up);
        long[] right=devideAndconquer(sum,mid+1,e,low,up);
        return merge(left,right,low,up);


    }
    public long[] merge(long[] l,long[] r,long low,long up){
        //好像不会有l或者r长度为0的情况
        int m=0;
        int n=0;
        int b=0;
        int e=0;
        int mid=0;
        long[] temp=new long[l.length+r.length];
        for(int i=0;i<l.length;i++) {
            if(r.length==1&&r[0]-l[i]>=low&&r[0]-l[i]<=up){
                rs++;
                continue;
            }
            m=0;
            n=0;
            //m is the first map satisfy sums[m] - sums[i] > upper and
            //n is the first map satisfy sums[n] - sums[i] >= lower.  然后m-n就得出这段区间符合条件的个数,
            // 自己举个例子,假如0,1,2,3四个数,r[3]-l[i]的时候就大于up了,说明3时零界点,如果r[1]-l[i]>=low说明到r数
            //组中index为1,2即2个数符合条件,即3-1=2个
            //开始想用用二分法找到m和n,m-n就是符合条件的个数,但是有一种情况不好处理就是比如r数组是{2,2},然后这两个数都符合r-l[i]符合low和up,那么二分法
            //得出的m就是1,实际上应该是2才对,所以只能线性查找了,然而就超时了,运行第二次又accept了,然后多次都超时,说明基本上是快超时的,估计是devideAndconquer
            //里面创建新数组花的时间吧,人家用的system.copyarray貌似性能快

            while(m<r.length&&r[m]-l[i]<=up){
                m++;
            }

            //找左边点,即从左到右第一个下标n,使得ums[n-1]-sums[i]<low,且sums[n]-sums[i]>=low,实际上和m点一起看来,是左闭右开,使得m-n就是区间中间符合条件的个数
           while(n<r.length&&r[n]-l[i]<low){
               n++;
           }
            rs+=m-n;

        }
        //merge数组
        int p=0;
        int q=0;
        int t=0;
        while(p<l.length&&q<r.length){
            if(l[p]<=r[q]){
                temp[t++]=l[p++];
            }else{
                temp[t++]=r[q++];
            }
        }
        while(p!=l.length){
            temp[t++]=l[p++];
        }
        while(q!=r.length){
            temp[t++]=r[q++];
        }
        return temp;

    }
}
