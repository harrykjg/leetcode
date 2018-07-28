package dp;

/**
 * Created by yufengzhu on 7/27/18.
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    public static void main(String[] ar){
        int[] a={1,2,5,4,7};
        int[] b={1,2,3,7,8};
        MinimumSwapsToMakeSequencesIncreasing ms=new MinimumSwapsToMakeSequencesIncreasing();
        System.out.print(ms.minSwap(a,b));
    }
    //dp的想法真难，第一次见这样的二维dp写法，dp[i][j]意义代表在i位之前换
    //https://www.youtube.com/watch?v=58COr4VlYZs&index=2&list=PLvwTK9zKEZO0oqcrsEWVDqpPFjDhGlKVB&t=0s
    //只能写dfs的了，会超时
    int rs=Integer.MAX_VALUE;
    public int minSwap(int[] A, int[] B) {

        if(A.length<2){
            return 0;
        }
        dfs(0,1,A,B);
        return rs;
    }
    void dfs(int cur,int index,int[] a,int[] b){
        if(index==a.length){
            rs=Math.min(rs,cur);
            return;
        }
        if(cur>rs){
            return;
        }
        if(a[index]>a[index-1]&&b[index]>b[index-1]){//两个数组都符合单调的时候，那么可以不换，但是如果b的后一位大于a的前一位并且a的后一位大于b的前一位的话，也可以交换，但是交换的话
             if(b[index]>a[index-1]&&a[index]>b[index-1])   {//也有两种换发，就是a和b第i位交换，或者ab第i-1位交换，那么这里肯定只取第二位交换，因为第i-1位交换只能对第i位有影响，但是第i位已经符合
                 swap(a,b,index);                             //单调要求了，所以第i-1位交换是不会得到更好结果的，但是第i位交换是会影响到i+1位的，所以是可以去dfs的，如果导致第i+1位和第i位不符合条件了
                 dfs(cur+1,index+1,a,b);                      //也无所谓，因为是dfs所以会去试更多的换法而已
                 swap(a,b,index);
             }
            dfs(cur,index+1,a,b);

        }
        //这里开始写错了，dfs这两个分之不应该放在if else里
        //这里开始以为肯定是a或者b不单调的情况了（因为之前放在if else里，其实是不对的），这里其实不管a和b第i和i-1位是否单调，反正符合可以交换的条件就换
        // 那么肯定要换了，这里换的话肯是a第i位大于b第i-1位，并且b第i位大于a第i-1位因为题目说了肯定有答案，但是换又有两种换法，换第i位或者换i-1位,但是换低i-1位的话，有可能导致
        if(b[index]>a[index-1]&&a[index]>b[index-1])   {//第i-2位不合法了
            swap(a,b,index);
            dfs(cur+1,index+1,a,b);
            swap(a,b,index);
        }

    }

    void swap(int[] a,int[] b,int i){
        int temp=a[i];
        a[i]=b[i];
        b[i]=temp;
    }
}
