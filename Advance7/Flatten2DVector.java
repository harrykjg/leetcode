package Advance7;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yufengzhu on 6/27/18.
 */
//自己想的就是用个stack就完事了
public class Flatten2DVector {
    Stack<Integer> st;
    public Flatten2DVector(List<List<Integer>> vec2d) {
        // Initialize your data structure here
        st=new Stack<>();
        for(int i=vec2d.size()-1;i>=0;i--){
            List<Integer> ls=vec2d.get(i);
            for(int j=ls.size()-1;j>=0;j++){
                st.push(ls.get(j));
            }
        }
    }

    public Integer next() {
        // Write your code here
        return st.pop();
    }

    public boolean hasNext() {
        // Write your code here
        return !st.isEmpty();

    }

    public void remove() {}
}
//05/23/2020，基本上和flattenlist一样
class Flatten2DVector2 {
    Stack<Integer> st;
    public Flatten2DVector2(List<List<Integer>> vec2d) {
        // Initialize your data structure here
        st=new Stack<>();
        for(int i=vec2d.size()-1;i>=0;i--){
            List<Integer> ls=vec2d.get(i);
            for(int j=ls.size()-1;j>=0;j--){
                st.push(ls.get(j));
            }

        }
    }

    public Integer next() {
        // Write your code here
        return st.pop();
    }

    public boolean hasNext() {
        // Write your code here
        return !st.isEmpty();
    }

    public void remove() {

    }
}