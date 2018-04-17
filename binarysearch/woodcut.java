package binarysearch;

/**
 * Created by 502575560 on 6/27/17.
 */
//题目 http://www.lintcode.com/zh-cn/problem/wood-cut/
//有一些原木，现在想把这些木头切割成一些长度相同的小段木头，需要得到的小段的数目至少为 k。当然，我们希望得到的小段越长越好，你需要计算能够得到的小段木头的最大长度。
//
//        注意事项
//
//        木头长度的单位是厘米。原木的长度都是正整数，我们要求切割得到的小段木头的长度也要求是整数。无法切出要求至少 k 段的,则返回 0 即可。
public class woodcut {
    //题目说的要切出长度相等的k个木头，所以说一段木头切出了一个长度但是剩下一段太短的废的
    //也行
    //和leetcode那个splitarraylagestsum一样基本上,按着那个自己想的
    public int woodCut(int[] L, int k) {
        // write your code here
        long sum=0;
        for(int i=0;i<L.length;i++){
            sum+=L[i];
        }
        if(sum<k){
            return 0;
        }
        long b=1;
        long e=sum/k;
        while(b<e-1){
            long mid=e-(e-b)/2;
            if(validate(sum,mid,k,L)){
                b=mid;//能符合切出k个mid长的木头,返回true,所以试着增大mid
            }else{
                e=mid;
                continue;
            }
        }
        if(validate(sum,e,k,L)){
            return (int)e;
        }
        return (int)b;//奇怪这里不会出现无解吗？那样return0才对，但是这样也accept了
    }
    boolean validate(long sum,long mid,int k,int[] l){
        int count=0;
        for(int i=0;i< l.length;i++){
            int temp=l[i];
            while (temp>=mid){
                count++;
                if(count>=k){
                    return true;
                }
                temp-=mid;
            }
        }
        return false;

    }
    //第二次做
    //初始b和e的值开始想错了,想成了L数组的最大的那个数是和最小的数,还有用模版的话还是结果要检测两个
    public int woodCut2(int[] L, int k) {
        int sum=0;
        for(int i:L){
            sum+=i;
        }
        int b=1;
        int e=sum/k;
        int mid=0;
        while (b+1<e){
            mid=b+(e-b)/2;
            if(good(mid,L,k)){
                b=mid;
            }else{
                e=mid;
            }
        }
        return b;
    }
    boolean good(int len,int[] l,int k){
        int count=0;
        for(int i=0;i<l.length;i++){
            int n=l[i];

            while (n>len){
                n-=len;
                count++;
                if(count>=k){
                    return true;
                }
            }
        }
        return false;
    }

    //1/21/2018,九章第二轮，觉得写的很顺，结果还是不能一次过,因为错误的以为b初始值可以是L中间最小的那个，其实应该是1,还有e的值以为是L中最大的，
    //其实应该是sum／k，而且还要用long才行，否则会越界
    public int woodCut3(int[] L, int k) {
        if(L.length==0){
            return 0;
        }
        long b=1;

        long sum=0;
        for(int i:L){
            sum+=i;
        }
        long e=sum/k;
        long m=0;
        while (b+1<e){
            m=b+(e-b)/2;
            if(good2(L,m,k)){
                b=m;
            }else{
                e=m;
            }
        }
        if(good2(L,e,k)){
            return (int)e;
        }
        return (int)b;

    }
    boolean good2(int[] l, long m,int k){
        int count=0;
        for (int i=0;i<l.length;i++){
            int cur=l[i];
            while (cur>=m){
                count++;
                cur-=m;
                if(count>=k){
                    return true;
                }
            }

        }
        return false;
    }
}
