public class ProductofArrayExceptSelf {
    //8/25/2021  居然改了一次过了  其实这个方法下标有点恶心其实不太好写，尤其是面试的时候
    public int[] productExceptSelf(int[] nums) {//思想就是分成3中情况，1。数组没有0，则rs【i】=sum[sum.length-1]/sum[i+1]*sum[i+1-1]，
        // 注意sum是比nums多了一位。2，数组有1个0。3，数组有2个以上0，则直接返回全是0的数组
        int[] sum=new int[nums.length+1];
        sum[0]=1;
        int[] rs=new int[nums.length];
        int zero=-1;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0){
                if (zero==-1){
                    zero=i;
                }else{//发现有多于1个0
                    return rs;
                }
                sum[i+1]=sum[i];//发现一个0，则记录0的位置，然后算上除了这个位置之外的所有数的乘积，最后返回的rs只有0这个位置上有值
            }else {
                sum[i+1]=sum[i]*nums[i];
            }
        }
        if (zero!=-1){
            rs[zero]=sum[sum.length-1];
            return rs;
        }
        for (int i=0;i<nums.length;i++){
            rs[i]=sum[sum.length-1]/sum[i+1]*sum[i+1-1];
        }
        return rs;
    }
    //答案1的做法是左右数组。这个方法好写一些把。
    //            [1,2,3,4]
      //  left     1 1 2 6    left数组第一个是1，然后第二个是nums【0】*left【0】，第三个是nums【1】*left【1】。。
        // right  24 12 4 1   right数组从右往左，第一个是1，然后第二个是nums【3】*right【3】，。。以此类推
    //   然后答案就是rs【i】=left【i】*right【i】
    //https://leetcode.com/problems/product-of-array-except-self/discuss/65622/Simple-Java-solution-in-O(n)-without-extra-space 这个才是最优解不容extra space
}
