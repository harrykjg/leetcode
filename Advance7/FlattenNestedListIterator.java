package Advance7;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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

        public boolean hasNext() {
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
}
