import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yufengzhu on 7/10/18.
 */
public class Flatten2DVector {
    //太简单，就是用了O(n）的空间，iteratior可能有O(1）的空间？
    //https://leetcode.com/problems/flatten-2d-vector/discuss/67652/7-9-lines-added-Java-and-C++-O(1)-space.
    //https://leetcode.com/problems/flatten-2d-vector/discuss/67669/Java-Iterator-Solution-Explained
    //https://leetcode.com/problems/flatten-2d-vector/discuss/67708/Simple-and-short-JAVA-solution-with-iterator
    List<Integer> ls;
    int index=0;
    public Flatten2DVector(List<List<Integer>> vec2d) {
        ls=new ArrayList<>();
        for(List<Integer> l:vec2d){
            for(int i:l){
                ls.add(i);
            }
        }
    }

    @Override
    public Integer next() {
        return ls.get(index++);
    }

    @Override
    public boolean hasNext() {
        return ls.size()>index;
    }

    //7/23/2018,试着用iterator方法写,漏写了个while之后对了
    class Flatten2DVector2 {
        Iterator<List<Integer>> outer;
        Iterator<Integer> inner;
        public Flatten2DVector2(List<List<Integer>> vec2d) {
            outer=vec2d.iterator();
        }

        @Override
        public Integer next() {
            return inner.next();
        }

        @Override
        public boolean hasNext()
        {
            while(inner==null||!inner.hasNext()){//他这个input可能是[[],[],[1,2]],所以一开始inner是null，然后outer给了他一个iterator，但是inner。hasNext（）还是false，那么继续从outer给他
                if(outer.hasNext()){
                    inner=outer.next().iterator();
                }else {
                    return false;
                }
            }
            return inner.hasNext();
        }

        public void remove(){//解释看深秋版面筋，remove的是将iterator的最后一个元素,原来iterator本来就有remove方法,但是这个默认的remove就是抛异常,还是不明白到底是要干嘛
            while (inner==null&&outer.hasNext()){
                inner=outer.next().iterator();
            }
            if(inner!=null){
                inner.remove();
            }
        }
    }
}
