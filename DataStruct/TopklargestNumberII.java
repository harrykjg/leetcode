package DataStruct;

import java.util.*;

/**
 * Created by 502575560 on 7/12/17.
 */
//    实现一个数据结构，提供下面两个接口
//    1.add(number) 添加一个元素
//    2.topk() 返回前K大的数
//}
    //代码直接复制的九章的答案,貌似不难所以懒得写
public class TopklargestNumberII {
    private int maxSize;
    private Queue<Integer> minheap;
    public TopklargestNumberII(int k) {
        minheap = new PriorityQueue<>();
        maxSize = k;
    }

    public void add(int num) {
        if (minheap.size() < maxSize) {
            minheap.offer(num);
            return;
        }

        if (num > minheap.peek()) {
            minheap.poll();
            minheap.offer(num);
        }
    }

    public List<Integer> topk() {
        Iterator it = minheap.iterator();
        List<Integer> result = new ArrayList<Integer>();
        while (it.hasNext()) {
            result.add((Integer) it.next());
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }

}