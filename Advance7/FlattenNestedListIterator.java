package Advance7;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 502575560 on 8/16/17.
 */
//还是不会,不好写
public class FlattenNestedListIterator {
    //http://www.cnblogs.com/grandyang/p/5358793.html
    //https://segmentfault.com/a/1190000005339301
    //用stack倒着装元素,开始想着是queue,但是不行
    Stack<NestedInteger> st=new Stack<>();
    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        for(int i=nestedList.size()-1;i>=0;i--){
            st.push(nestedList.get(i));
        }
    }
    //flatten的逻辑放在了hasnext里,只要hasnext返回true,那么next就保证得到的是一个integer,因为调用的时候就是肯定现调用hasnext的
    public Integer next() {
       return st.pop().getInteger();
    }

    public boolean hasNext() {
        while(!st.isEmpty()){//开始写成了if,要一直找直到找到一个是integer的数,才会return true
            NestedInteger n=st.peek();
            if(n.isInteger()){
                return true;
            }else{
                List<NestedInteger> ls=n.getList();
                st.pop();//开始pop的位置放在for循环后面就错了
                for(int i=ls.size()-1;i>=0;i--){
                    st.push(ls.get(i));
                }
            }
        }
        return false;
    }
//6/25/2018,还是不会，不好写
    class FlattenNestedListIterator2{
        Stack<NestedInteger> st;
        public FlattenNestedListIterator2(List<NestedInteger> nestedList) {
            // Initialize your data structure here.
            st=new Stack<>();
            for(int i=nestedList.size()-1;i>=0;i--){
                st.push(nestedList.get(i));
            }
        }

        // @return {int} the next element in the iteration
        public Integer next() {//开始写的是递归调用next，是可以，但是遇到[[],[]]这样的就不行了，应为递归之后就不知道st是不是空了，按理说空的话就不能调用next了
            // Write your code here
            return st.pop().getInteger();
        }

        // @return {boolean} true if the iteration has more element or false
        public boolean hasNext() {//还一定要在hasnext里面处理，在next那里搞的话处理不了[[]]这样的
            // Write your code here
            while (!st.isEmpty()){
                NestedInteger temp=st.peek();
                if (temp.isInteger()){
                    return true;
                }else{
                    List<NestedInteger> ls=temp.getList();
                    st.pop();
                    for(int i=ls.size()-1;i>=0;i--){
                        st.push(ls.get(i));
                    }
                }
            }
            return false;
        }
    }
    //9/6/2018,还是不会，也可以用queue啊,一直搞不好的是[[]]这个case，貌似别的都可以过，应该是[]这个在hasnext时候应该要检测到他是空然后返回false
    // 他们用queue的是在constructor的时候就把整个list里的不是integer的全都转乘integer了
    //https://leetcode.com/problems/flatten-nested-list-iterator/discuss/165032/My-Simple-Java-Solution-with-Queue-or-Beats-98  用q的
    class FlattenNestedListIterator3{
        Stack<NestedInteger> st=new Stack<>();
        Deque<NestedInteger> q=new ArrayDeque<>();
        public FlattenNestedListIterator3(List<NestedInteger> nestedList) {
            for (NestedInteger i:nestedList){
                q.offer(i);
            }
        }

        public Integer next() {
            return q.poll().getInteger();
        }

        public boolean hasNext() {
            if(q.size()==0){
                return false;
            }
            NestedInteger cur=q.peek();
            if(cur.isInteger()){
                return true;
            }
            List<NestedInteger> ls=cur.getList();
            q.poll();

            for(int i=ls.size()-1;i>=0;i--){
                q.addFirst(ls.get(i));
            }
            return true;

        }
    }
    //05/23/2020,还是不会，hasnext那里其实还是没想明白
    class FlattenNestedListIterator4{
        Stack<NestedInteger> st;
        public FlattenNestedListIterator4(List<NestedInteger> nestedList) {
            st=new Stack<>();
            for(int i=nestedList.size()-1;i>=0;i--){
                st.push(nestedList.get(i));
            }
        }
        public Integer next() {
            return st.pop().getInteger();
        }
        public boolean hasNext() {
            while (!st.isEmpty()){//注意这个是while，而不是if，while的话其实就是一直找到第一个是integer的数为止
                if(st.peek().isInteger()){
                    return true;
                }else{
                    List<NestedInteger> ls=st.pop().getList();
                    for(int i=ls.size()-1;i>=0;i--){
                        st.push(ls.get(i));
                    }
                }
            }
            return false;
        }
    }

    //6/23/2021这里噁心的是他会有[[],[3]]这种case和各种【【【】】，【】】case，开始想的istrue就是看看stack是不是空，这样的话不行.还是要hasNext把
    //list都拿出来放进st里。这里写的比较容易懂，但是hasnext是递归的，以前的写法好一些但是没那么容易写
    class FlattenNestedListIterator5{
        Stack<NestedInteger> st=new Stack<>();
        public FlattenNestedListIterator5(List<NestedInteger> nestedList) {
            for (int i=nestedList.size()-1;i>=0;i--){
                st.push(nestedList.get(i));
            }
        }

        public Integer next() {
            NestedInteger cur=st.pop();
//            if (cur.isInteger()){
//                return cur.getInteger();
//            }
//            List<NestedInteger> list=cur.getList();//由于有了hasNext的处理，就不需要这里flatten了
//            for (int i=list.size()-1;i>=0;i--){
//                st.push(list.get(i));
//            }
            return cur.getInteger();
        }

        public boolean hasNext() {
            if (st.isEmpty()){
                return false;
            }
            if (st.peek().isInteger()){
              return true;
            }else {
                List<NestedInteger> n=st.peek().getList();
                if (n.isEmpty()){
                    st.pop();
                    return hasNext();
                }else {
                    st.pop();
                    for (int i=n.size()-1;i>=0;i--){
                        st.push(n.get(i));
                    }
                    return hasNext();
                }

            }
        }
    }
    //8/12/2021这个是直接初始化的时候就flatten所有的值到al里，估计面试官会问怎么把初始化的复杂度降低，那么就可以回答以前的做法那样的了
    class NestedIterator implements Iterator<Integer> {
        List<Integer> al=new ArrayList<>();
        int index=0;
        //这个是直接暴力把所有数字都在初始化的时候弄出来放到al里
        public NestedIterator(List<NestedInteger> nestedList) {
            for(int i=0;i<nestedList.size();i++){
                NestedInteger ni=nestedList.get(i);
                helper(ni);

            }
        }
        void helper(NestedInteger ni){
            if(ni.isInteger()){
                al.add(ni.getInteger());
            }else{
                List<NestedInteger> ls=ni.getList();
                for(NestedInteger n:ls){
                    helper(n);
                }
            }
        }

        @Override
        public Integer next() {
            return al.get(index++);
        }

        @Override
        public boolean hasNext() {
            if(al.size()==0||index>=al.size()){
                return false;
            }
            return true;
        }
    }
}
