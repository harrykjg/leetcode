package dp;

/**
 * Created by yufengzhu on 8/4/18.
 */
public class MaximumSumof3NonOverlappingSubarrays {
    public static void main(String[] args){
        MaximumSumof3NonOverlappingSubarrays ms=new MaximumSumof3NonOverlappingSubarrays();
        int[] n={1,2,1,2,6,7,5,1};
        ms.maxSumOfThreeSubarrays(n,2);
    }
    //https://www.jianshu.com/p/9f79c250c4fa  这个解释好,先把左右的subarray的最大取值都记录在左右两个数组里（left[i]的意义是起点为i终点为i+k-1，这个范围以左的所有subarray的最大的那个值的subarray的起始位置,注意存的是下标
    // right数组的意义同样，不过是往右，所以下面写forloop的时候就要从右往左写了，否则就错了）
    // ，再for loop中间范围的时候，判断左+中+右的值是否最大就可以了

    //下标非常恶心
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if(nums.length==0){
            return new int[0];
        }
        int[] sums=new int[nums.length+1];
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            sums[i+1]=sum;
        }
        int[] left=new int[nums.length];
        int maxLeft=Integer.MIN_VALUE;
        for(int i=0;i+k-1<nums.length-2*k;i++){
            int end=i+k-1;
            int cursum=sums[end+1]-sums[end-k+1];//下标有点恶心，因为sums是多了以为sums[0]的
            if(cursum>maxLeft){
                maxLeft=cursum;
                left[i]=i;
            }else{
                left[i]=left[i-1];
            }
        }

        int[] right=new int[nums.length];
        int maxRight=Integer.MIN_VALUE;
        for(int i=nums.length-k;i>=2*k;i--){
            int end=i+k-1;
            int cursum=sums[end+1]-sums[end-k+1];
            right[i]=cursum;
            if(cursum>=maxRight){//这里多了等号，因为题目说了要lexicographically order那个条件
                maxRight=cursum;
                right[i]=i;
            }else{
                right[i]=right[i+1];
            }
        }
        int max=Integer.MIN_VALUE;
        int[] rs=new int[3];
        for(int i=k;i+k-1<nums.length-k;i++){
            int end=i+k-1;
            int mid=sums[end+1]-sums[end-k+1];
            int lindex=left[i-k];
            int rindex=right[i+k];
            int cursum=sums[lindex+k]-sums[lindex]+mid+sums[rindex+k]-sums[rindex];
            if(cursum>max){
                rs[0]=lindex;
                rs[1]=i;
                rs[2]=rindex;
                max=cursum;
            }

        }
        return rs;

    }
}
