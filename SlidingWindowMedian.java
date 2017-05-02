import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by 502575560 on 4/30/17.
 */
public class SlidingWindowMedian {
    public static void main(String[] args){
        int[] n={1,3,-1,-3,5,3,6,7};
//        int[] n={1,2};
//        int[] n={1,2,3,4,2,3,1,4,2};
//        int[] n={1,3,1,4,1,2,3};
        double[] rs=SlidingWindowMedian.medianSlidingWindow(n,3);
        for(double d:rs){
            System.out.println(d);
        }
    }
    //自己应该只能想到暴力法,参考别人的思路其实我以为我易理解了其实理解错了,是写2个堆增加或删除元素,开始以为就是简单的扫描每一个数都扔进两个堆里就行了,
    //其实不是,他们要相互倒腾而且要看数和堆顶大小的比较才决定加到哪个堆里
    //http://blog.csdn.net/mebiuw/article/details/54408831
    //http://bookshadow.com/weblog/2017/01/08/leetcode-sliding-window-median/

    public static double[] medianSlidingWindow(int[] nums, int k) {
        if(nums==null||nums.length<1){
            return new double[0];
        }
        double[] rs=new double[nums.length-k+1];

        int k1=k/2;
        int k2=k-k1;
        boolean isEven=k%2==0;
        if(k==1){
            for(int i=0;i<nums.length;i++){
                rs[i]=nums[i];

            }
            return rs;
        }
//        Collections.reverseOrder()
        PriorityQueue<Integer> q1=new PriorityQueue<>(k, new Comparator<Integer>() {//最大堆,留的是最小的元素
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        PriorityQueue<Integer> q2=new PriorityQueue<>(k);//默认是升序那么应该是最小堆,那么留下的是最大的k2个
        int cur=0;
        int index=0;
        for(int i=0;i<nums.length;i++){
            if(cur<k){//这个自己看了别人思路之后自己写的居然运行到第41个test case时错了看不出为什么, 我知道了!就是把q1的comparator才成Collections.reverseOrder()就对了!??
                      //后来发现应该写成o2.compareTo(o1)就对了,写成o2-o1时不对的,不知道为啥
                if(q2.size()==0||nums[i]>=q2.peek()){//我自己想的就是q2是最小堆,而且k是奇数时q2的大小比q1大,q2存的就是k这个
                    q2.offer(nums[i]);//范围内最大的那几个数,如果k是奇数,那么q2.peek就是中位数
                }else{
                    q1.offer(nums[i]);
                }
                cur++;
                if(q2.size()>k2){     //如果q2大小超了就给q1
                    q1.offer(q2.poll());
                }
                if(q1.size()>k1){
                    q2.offer(q1.poll());
                }
            }
            if(cur==k){
                if(isEven){
                    rs[index]=(double)q1.peek()/2+(double)q2.peek()/2;
                }else{
                    rs[index]=q2.peek();
                }
                index++;
                if(q1.contains(nums[i-k+1])){//q1或者q2肯定有一个存有这个值,但是如果q1和q2都有这个值的话删谁呢,我选的删q1因为本来想着就是q2比q1大
                    q1.remove(nums[i-k+1]); //实验结果证明删谁都能accept
                }else{
                    q2.remove(nums[i-k+1]);
                }
                cur--;
            }
        }

        return rs;
    }


    //这个是//http://blog.csdn.net/mebiuw/article/details/54408831 的代码
    public static double[] medianSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        int m = n - k + 1; // 结果的尺寸
        double[] res = new double[m];
        //两个堆，一个最大堆，一个最小
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
        for ( int i=0; i<n; i++){
            int num = nums[i];
            // 让maxHeap始终保存小于一半的值，minHeap保存大于一半的，正好两半
            if( maxHeap.size() == 0 || maxHeap.peek() >= num)
                maxHeap.add(num);
            else minHeap.add(num);
            // 维护两个堆，保证两个堆得大小，要么保持一致（偶数时），要么maxHeap多一个（奇数时）
            if( minHeap.size() > maxHeap.size() )
                maxHeap.add(minHeap.poll());
            if( maxHeap.size() > minHeap.size() + 1 )
                minHeap.add(maxHeap.poll());
            // 如果需要输出
            if ( i-k+1 >=0 ){
                if( k % 2 == 1 )
                    res[i- k + 1] = maxHeap.peek();
                else
                    res[i- k + 1] = (maxHeap.peek()/2.0 + minHeap.peek()/2.0); // 小心溢出
                //移除并更新
                int toBeRemove = nums[i - k + 1];
                if( toBeRemove <= maxHeap.peek())
                    maxHeap.remove(toBeRemove);
                else minHeap.remove(toBeRemove);
                // 维护两个堆，保证两个堆得大小，要么保持一致（偶数时），要么maxHeap多一个（奇数时）
                if( minHeap.size() > maxHeap.size() )
                    maxHeap.add(minHeap.poll());
                if( maxHeap.size() > minHeap.size() + 1 )
                    minHeap.add(maxHeap.poll());

            }
        }
        return res;

    }
}
