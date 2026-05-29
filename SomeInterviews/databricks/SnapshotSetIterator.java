package SomeInterviews.databricks;

import java.util.*;

public class SnapshotSetIterator {

    //这题不好想，开始想的是add和remove都视为当前版本的操作，因此不改变版本号，只有getIterator才改变，其实是不行的。
    // 后来以为应该是每个元素都有自己的版本号，然后snapshot的版本是这一堆版本的快照，但这题不是太适用，因为我们只在乎这个snap下的元素到底是有还是没有，
    //不在乎他的版本。还有一个关键的理解是，我现在调用getIterator，他这个iterator不是说马上要使用，他可以说在遍历next的途中，又有别的operation
    //在发生，比如add remove，getiterator，但要保证都不影响这个iterator。
    // 答案的思想是每一个add和remove都会增加版本号，给每一个元素设birth和dead（初始化为无穷），删掉的时候就从当前active的map里删掉。同时还maintain
    //一个arraylist用来维护iterator的顺序，新add的append在后面，但是删除的不从这里面删，而是把那个元素的dead设置一下。
    // 然后还有一个priorityqueue应该是optional的，用来维护现存的iterator（因为假如说现在version是3，产生了一个iterator，而这个iterator会调用
    //advance方法取找到下一个元素的位置，如果发现没有下一个元素了，意味着这个iterator用完了，那么就可以把他clean了，因为未来的operation会使version++，
    //而version++之后再调用iterator就是得到新的iterator了，意味着用不着老的iterator了，就从pq里删除这个iterator的版本，但是iteratior对象是
    // 没有被删的。这个应该是follow up，这个gc逻辑貌似也不太好？因为假如我连续调用两个getiterator的话又得重新搞创建iterator了。

    List<Node> al=new ArrayList<>();
    Map<Integer,Node> map=new HashMap<>();
    long version;
    public boolean add(int n) {
        if(map.containsKey(n)){
            return false;
        }
        Node node=new Node(n,version);
        map.put(n,node);
        al.add(node);
        version++;
        return true;
    }
    public boolean remove(int n) {
        if(!map.containsKey(n)){
            return false;
        }
        version++;//注意version++要在设置dead之前，否则不对
        map.get(n).dead=version;
        //不从al里删掉node，因为旧的iterator还要用他，但是要从map里删除，因为add的map语义是只保存现在valid的值，而且add方法就是用map判断的
        map.remove(n);
        return true;
    }

    public boolean contains(int n) {
        if(!map.containsKey(n)){
            return false;
        }
        return map.get(n).isLive(version);
    }
    public Iterator<Integer> getIterator() {
        myIterator mi=new myIterator(version,al);
        return mi;
    }

    class myIterator implements Iterator<Integer>{
        long version;
        List<Node> al;
        int index;
        public myIterator(long version,List<Node> al){
            this.version=version;
            this.al=al;
            advance();
        }

        @Override
        public boolean hasNext() {
            while (index<al.size()){
                if(al.get(index).isLive(version)){
                    return true;
                }
                index++;
            }
            return false;
        }

        @Override
        public Integer next() {
            Node rs= al.get(index);
            //这里漏了index++,否则advance会卡在当前元素上
            index++;
            advance();
            return rs.val;
        }
        //应该写个helper方法，使得调用next之后pointer会前进。
        void advance(){
            while (index<al.size()){
                if(al.get(index).isLive(version)){
                    return;
                }
                index++;
            }
        }
    }


    class Node{
        int val;
        long start;
        long dead;
        public Node(int val,long start){
            this.val=val;
            this.start=start;
            dead=Long.MAX_VALUE;
        }
        public boolean isLive(long version){
            return version>=start&&version<dead;
        }
    }
}
