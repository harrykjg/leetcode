package Advance7;

import sun.jvm.hotspot.debugger.win32.coff.COFFFileParser;

import java.util.*;

/**
 * Created by 502575560 on 8/16/17.
 */
public class FlattenList {
    //leetcode 的Flatten Nested List Iterator,那里要重写next和hasnext方法,这里就不用
    //http://www.jiuzhang.com/solutions/flatten-list/
    //自己写的dfs
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        // Write your code here
        List<Integer> rs=new ArrayList<>();
        for(NestedInteger ni:nestedList){
            helper(ni,rs);
        }
        return rs;
    }
    void helper(NestedInteger ni,List<Integer> rs){
        if(ni.isInteger()){
            rs.add(ni.getInteger());
            return;
        }else{
            List<NestedInteger> ls=ni.getList();
            for(NestedInteger n:ls){
                helper(n,rs);
            }
        }
    }
    //非递归版本还挺不好写的,以为用queue,后来换stack貌似也是顺序不对,看九章的答案吧
    public List<Integer> flatten2(List<NestedInteger> nestedList) {

        List<Integer> rs=new ArrayList<>();
        return rs;
    }
//6/25/2018,试着写non recursive的,想的用queue和stack貌似都不行，还容易遇到concurent modify list的问题，所以要建另一个list来用来copy操作,还是不好写
    public List<Integer> flatten3(List<NestedInteger> nestedList) {

        List<Integer> rs=new ArrayList<>();
        List<NestedInteger> copy=nestedList;
        boolean flag=true;
        while (flag){
            List<NestedInteger> ls=new ArrayList<>();
            flag=false;
            for(NestedInteger ni:copy){
                if(ni.isInteger()){
                    ls.add(ni);//这里开始直接写rs.add(ni.getInteger())，这样是错的，举个例子【【1，1】，2，【1，2】】就知道了
                }else {
                    ls.addAll(ni.getList());
                    flag=true;
                }
            }
            copy=ls;
        }
        for(NestedInteger ni:copy){
            rs.add(ni.getInteger());
        }
        return rs;
    }

}
interface NestedInteger {
     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
     public Integer getInteger();

     // @return the nested list that this NestedInteger holds, if it holds a nested list
     // Return null if this NestedInteger holds a single integer
     public List<NestedInteger> getList();
 }