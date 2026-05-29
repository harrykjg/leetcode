package SomeInterviews.tiktok;

public class MakeCostsofPathsEqualinaBinaryTree2673 {
    //就是先得找到最大的那条path的值，然后再遍历别的path，把别的path的node加上某个值使得别的path的值都等于最大的值。但是这里要注意不能加在
    //最大的path上，否则最大的值会被改变。而别的path你加的时候肯定是加在靠近根节点的点上，这要子树就能共享增加的值。那么这个节点该加的值又得
    //取决于左右两个子树需要加的值的最小的那个。但是这个perfect二叉树，是不是有什么隐含条件？
    //再看图发现其实我只要遍历的时候，比如到i节点，则看其左右节点2i+1和2i+2，看谁小，就是大减小的差值累加就是答案吧，接近了，但是错了
    /*
       1
      /   \
     2     3
    / \   / \
   4  5  6  7     这个例子可以看出你从上到下算diff的话，到了第三层你只能算4和5的差值，算不了4，5和6，7差值啊，那怎么算？
                    可以算出6得加1，然后更新3这个节点成3+7=10，然后2节点的时候更新成2+5=7，为啥要这样呢，
                    因为到更新1的时候，1看左右两个子树，可以知道左边是7右边是10，因此需要把2+3，挺巧妙的
     */
    //https://leetcode.com/problems/make-costs-of-paths-equal-in-a-binary-tree/solutions/3494915/javacpython-bottom-up-and-follow-up-by-l-dfei/
    public int minIncrements(int n, int[] cost) {
        int rs=0;
        for (int i=n/2-1;i>=0;i--){
            int left=cost[i*2+1];
            int right=cost[i*2+2];
            int diff=Math.abs(left-right);
            int max=Math.max(left,right);
            rs+=diff;
            cost[i]+=max;
        }
        return rs;
    }
}
