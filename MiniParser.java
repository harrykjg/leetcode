import java.util.List;
import java.util.Stack;

/**
 * Created by 502575560 on 5/13/17.
 */
public class MiniParser {
    //注意题目说的是NestedInteger可以是一个只有integer的对象,或者是一个只有list的对象,而不是二者都有,比如example2的"[123,[456,[789]]]",
    //这个不是一个有一个integer123,和一个list的对象,而是一个有2个元素的list的对象,然后后一个元素又是一个有2个元素的list的对象,其中第一个元素是integer,
    //第二个是有一个元素的list
    //不太好想,有点乱,还是看别人的算了
    //http://blog.csdn.net/yeqiuzs/article/details/52208388  他这个Integer.parseInt(tokenNum)貌似连负数"-123"这样的也能处理
    //http://bookshadow.com/weblog/2016/08/15/leetcode-mini-parser/
    //http://blog.csdn.net/xiexingshishu/article/details/52300936
//    public NestedInteger deserialize(String s) {
//
//    }

}

  interface NestedInteger {
      //public NestedInteger();//卧槽不允许constructor在这,可能姚放到一个新的类里去
      //public NestedInteger(int value);
     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     public boolean isInteger();

     // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
     public Integer getInteger();

     // Set this NestedInteger to hold a single integer.
     public void setInteger(int value);

     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
     public void add(NestedInteger ni);

     // @return the nested list that this NestedInteger holds, if it holds a nested list
     // Return null if this NestedInteger holds a single integer
     public List<NestedInteger> getList();
 }