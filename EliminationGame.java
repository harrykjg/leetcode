/**
 * Created by 502575560 on 5/18/17.
 */
public class EliminationGame {
    //规律好想不太好找,暴力法用arraylist操作估计会超时把
    //看了别人的解法还是很巧妙的自己想不出

    //https://discuss.leetcode.com/topic/59293/java-easiest-solution-o-logn-with-explanation 这个和下面的比较像,只要维护head就行了
    //http://www.mamicode.com/info-detail-1658523.html 牛逼这样的规律都能找出来,就是两边向中间夹逼
    //http://blog.csdn.net/u011934885/article/details/52349484
    //http://www.cnblogs.com/grandyang/p/5860706.html
    //http://www.cnblogs.com/dongling/p/5823911.html  一大堆推理
    public int lastRemaining(int n) {
        int head=1;
        int len=n;
        int index=1;
        boolean left=true;
        while (len>1){//当长度大于1时级继续缩,缩到1就完事
            if(left||len%2==1){//如果时从左往右删,则head肯定要变,或者是从右往左时,长度为奇数,head也要变
                head+=index;
            }
            len/=2;//无论长度时奇数还是偶数,向左还是向右,第二层的长度都肯定时len/2
            index*=2;
            left=!left;
        }
        return head;
    }
}
