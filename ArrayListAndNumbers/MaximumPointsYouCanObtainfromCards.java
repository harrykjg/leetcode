package ArrayListAndNumbers;

public class MaximumPointsYouCanObtainfromCards {
    //9/2/2021 看提示想的sliding window。和three moves那个像.就是左边取0个和右边取k个，或者左边取1个和右边取k-1个，以此类推。那么左边和右边那一段的和就可以
    //用prefix sum得到。而且左边的最右边和右边的最左边中间夹着的元素是length-k个。所以弄一个index从0开始，弄一段长度为length-k的窗口，那么左右两段的和就是
    //总的和减去中间这个窗口的和。别人的答案连prefix数组都不用，只要维护一个总的sum值，再维护两个值，分别是从左到i点的sum和从右到window右侧的值，不太好想，
    public int maxScore(int[] cardPoints, int k) {
        int[] sum=new int[cardPoints.length+1];
        for (int i=1;i<sum.length;i++){
            sum[i]=sum[i-1]+cardPoints[i-1];
        }
        int rs=0;
        for (int i=0;i<=k;i++){//这个窗口的边界不好想
            int midsum=sum[sum.length-1-k+i]-sum[i];
            rs=Math.max(rs,sum[sum.length-1]-midsum);
        }
        return rs;
    }
//10/22/2021 下标还是很恶心
    //  0 1 2 3
    //  1 2 4 5
    //  0 1 3 7 12

    // 0 1 2
    // 2 2 2
    // 0 2 4 6
    public int maxScore2(int[] cardPoints, int k) {
        int[] sums=new int[cardPoints.length+1];
        for(int i=1;i<sums.length;i++){
            sums[i]=cardPoints[i-1]+sums[i-1];
        }
        int rs=0;
        for (int i=0;i<=k;i++){
            int left=i;
            int right=cardPoints.length-k+i;//真恶心，这里的right意思时候中间window的右侧，left指中间window的左侧，left可以看作就是这个移动窗口的左侧，
            //那么其右侧就是窗口的长度+left，而窗口长度就是length-k，然后prefixsum的gap刚好是这一段。

            int midsum=sums[right]-sums[left];
            rs=Math.max(sums[sums.length-1]-midsum,rs);
        }
        return rs;

    }
}
