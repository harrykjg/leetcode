package linkedin;

public class AllOoneDataStructure432 {
    //没想出来，主要是如果说已经有一堆数据了，如果我排名是修改中间的数据，那么如何维护他的排名顺序？用doubly linkedlist只能知道头尾，中间的顺序
    //怎么调？

    //看回花花的视频，思想是用hashmap key是string，value是一个doubly linkedlist的node，而这个node是包含两个属性，count和count都是这个数的
    //别的所有string。因此修改count的时候就可以通过hashmap定位到node，然后看这个node的前面或后面得节点的count是不是和他本身的count相差1，
    //是的话就直接把这个要修改的string从这里挪出来放去那里，否则就创建一个新的节点，这样就能位置大小顺序，然后保持头尾节点就可以快速得到最大最小值

    // 这个人的话就是用两个hashmap，一个是key是count，value和花花一样是doubly linkedlist，但是还有另一个hashmap，key是string，value是count
    //因此要定位string的时候就先通过第二个map得到count，再去定位这个count所对应的node，也是要单独维持一个head，end节点，修改count的操作同样
    //https://leetcode.com/problems/all-oone-data-structure/solutions/91416/java-ac-all-strict-o1-not-average-o1-eas-8spb/

    public AllOne() {

    }

    public void inc(String key) {

    }

    public void dec(String key) {

    }

    public String getMaxKey() {

    }

    public String getMinKey() {

    }
}
