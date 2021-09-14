package Advance7;

import java.util.*;

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
//6/23/2021服了这题完全没技术含量，可以看看答案的写法
class Flatten2DVector3 {
    Queue<Integer> q = new LinkedList<>();

    public Flatten2DVector3(int[][] vec) {
        for (int i = 0; i < vec.length; i++) {
            for (int j = 0; j < vec[i].length; j++) {
                q.offer(vec[i][j]);
            }
        }

    }
    public int next() {
        return q.poll();
    }

    public boolean hasNext() {
        return q.isEmpty();
    }
}
//答案

class Vector2D {

    // Constructor will put all the nums into this list.
    private List<Integer> nums = new ArrayList<>();
    // Keep track of where the Iterator is up to.
    private int position = 0;

    public Vector2D(int[][] v) {
        // We need to iterate over the 2D vector, getting all the integers
        // out of it and putting them into nums (a field).
        for (int[] innerVector : v) {
            for (int num : innerVector) {
                nums.add(num);
            }
        }
    }

    public int next() {
        // In Java, we throw a NoSuchElementException when next() is called
        // on an exhausted Iterator.
        if (!hasNext()) throw new NoSuchElementException();
        // Store the number we need to return, as we still need to move position forward.
        int result = nums.get(position);
        // Move the position pointer forward by 1, so that it's ready for
        // the next call to next, and gives a correct hasNext result.
        position++;
        return result;
    }

    public boolean hasNext() {
        // There's nums left as long as position is a valid index of the list.
        return position < nums.size();
    }
}