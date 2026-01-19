package 灵神.常用数据结构.堆;

public class MinimumCosttoHireKWorkers875  {
    static void main() {

    }
    //既然至少要给到对应的wage，那么就肯定是找最少得那个wage/quality的ratio，让k个人都拿这个ration，那么既然需要发最少得工资，
    // 还有一个变量要考虑的就是总的quality，如何处理这两个变量就是难点。做法就是设pair包含信息quality，按ratio排序这个list，但是我
    //开始的想法就是按ratio从低到高搞一个外层循环，内层循环再按quality从小到大选k个，这样就是n方了。正确的做法是for loop按ratio排，并且入pq，
    //pq按quality排最大堆，一旦pq size大于k，则poll出来，poll出来的就是打的quality，留着的就是最小的k个quality，此时按for loop的ratio
    //和总quality算的工资总数和rs相比就行
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {

    }
}
