package DataStruct;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yufengzhu on 1/7/18.
 */
//就是lintcode的Data Stream Median，但是leetcode是要写的datastructre
public class FindMedianFromDataStream {
    public static void main(String[] a){
        FindMedianFromDataStream4 ff=new FindMedianFromDataStream4();
        ff.addNum(1);
        ff.addNum(2);
        System.out.println(ff.findMedian());
        ff.addNum(3);
        System.out.println(ff.findMedian());
//        ff.addNum(-4);
//        System.out.println(ff.findMedian());
//        ff.addNum(-5);
//        System.out.println(ff.findMedian());
    }

    PriorityQueue<Integer> pq1;//最小堆，装大的
    PriorityQueue<Integer> pq2;

    //自己记得一点点思路，细节是试验出来的，就是两个堆大小相差1,另pq1总是大于等于pq2，使得pq1和pq2分别装的是最大的半边元素和最小的半边元素，
    // ，这样如果是总元素奇数的话那么pq1。peek就肯定是中位数，要相互倒腾才行，细节不是很熟
    public FindMedianFromDataStream() {
        pq1=new PriorityQueue<>((o1, o2) -> o1-o2);//最小堆，装大的
        pq2=new PriorityQueue<>((o1, o2) -> o2-o1);
    }

    public void addNum(int num) {
        if(pq1.isEmpty()){
            pq1.offer(num);
            return;
        }
        if(pq1.peek()<=num){
            pq1.offer(num);
        }else{
            pq2.offer(num);
        }
        while(pq1.size()>pq2.size()+1){
            pq2.offer(pq1.poll());
        }
        while(pq2.size()>pq1.size()){//开始漏了，因为pq2可能装的比pq1多
            pq1.offer(pq2.poll());
        }

    }

    public double findMedian() {
        if((pq1.size()+pq2.size())%2==0){
            if(pq1.size()==0){
                return -1d;
            }
            return (double)(pq1.peek()+pq2.peek())/2;//妈的没转类型就错了
        }
        return pq1.peek();
    }

    //9／8／2018, 不记得怎么做,直到2个pq之后还是写错了，不能简单的说心来的num就先去pq1再从pq1倒倒pq2，要判断
    class FindMedianFromDataStream2 {
        PriorityQueue<Integer> pq1;//最小堆，装大的
        PriorityQueue<Integer> pq2;

        public FindMedianFromDataStream2() {
            pq1 = new PriorityQueue<>();
            pq2 = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void addNum(int num) {
            if(pq1.isEmpty()){
                pq1.offer(num);
                return;
            }
            if(num>=pq1.peek()){//不写等号也accept了，没仔细想
                pq1.offer(num);
            }else{
                pq2.offer(num);
            }
            while (pq1.size()>pq2.size()+1){
                pq2.offer(pq1.poll());
            }
            while (pq2.size()>pq1.size()){
                pq1.offer(pq2.poll());
            }

        }

        public double findMedian() {
            if(pq1.size()>pq2.size()){
                return pq1.peek();
            }else{
                return (pq1.peek()+pq2.peek())/2.0;
            }
        }
    }

    //04/17/2020思路是对的，改了一下，之前写的总是pq1.size>=pq2.size,这次就不一定了，有可能是pq2.size大一点
    //https://leetcode.com/problems/find-median-from-data-stream/discuss/343662/JAVA-HEAP-SOLUTION-%2B-2-FOLLOW-UPS  follow up的解释
    static class FindMedianFromDataStream3 {
        PriorityQueue<Integer> pq1;
        PriorityQueue<Integer> pq2;
        int size=0;
        public FindMedianFromDataStream3() {
            pq1=new PriorityQueue<Integer>();
            pq2=new PriorityQueue<Integer>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });
        }

        public void addNum(int num) {
            if (size==0){
                pq1.offer(num);
                size++;
                return;
            }

            if(pq1.peek()<num){
                pq1.offer(num);
                while (pq1.size()>pq2.size()+1){
                    pq2.offer(pq1.poll());
                }
            }else{
                pq2.offer(num);
                while (pq2.size()>pq1.size()+1){
                    pq1.offer(pq2.poll());
                }
            }

        }


        public double findMedian() {
            if (size==0){
                return 0;
            }
            if (pq1.size()==pq2.size()){
                double rs=(pq1.peek()+pq2.peek())/2d;
                return rs;
            }
            if(pq1.size()>pq2.size()){
                return pq1.peek();
            }
            return pq2.peek();
        }
    }

    //6/8/2021想的是不管啥数进来都放在min里，再倒导max里，是对的而且比较好想。但是超时，还是判断一下新进来的数可以减少操作
    static class FindMedianFromDataStream4 {
        PriorityQueue<Integer> min=new PriorityQueue<>();
        PriorityQueue<Integer> max=new PriorityQueue<>(Collections.reverseOrder());
        public FindMedianFromDataStream4() {

        }

        public void addNum(int num) {
            if(min.size()==0){
                min.offer(num);
                return;
            }
            if(num<min.peek()){
                max.offer(num);
            }else {
                min.offer(num);
            }
            while (min.size()>max.size()+1){
                max.offer(min.poll());
            }
            while (max.size()>min.size()){//
                min.offer(max.poll());
            }
        }

        public double findMedian() {
            if(min.size()==0){
                return 0;
            }
            if(min.size()==max.size()){
                return (min.peek()+max.peek())/2d;
            }
            return min.peek();
        }
    }
}
