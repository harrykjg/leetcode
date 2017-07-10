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
        return (int)b;
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
}
