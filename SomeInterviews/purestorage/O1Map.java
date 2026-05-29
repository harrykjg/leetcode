package SomeInterviews.purestorage;

import java.util.Arrays;
import java.util.Iterator;

public class O1Map {
    /*
    设计一个Map, 满足 add: O(1)  deletion: O(1)  lookup: O(1)  clear:O(1)  iterate: O(number of elements)。
    关键在于clear和iterate了，mitbbs上的帖子暗示了一下，但是不是很明白： number of elements 不是N，一定要严格的number of elements
     如果我们用randomly accessed array，复杂度如下： add: O(1)  deletion: O(1)  lookup: O(1)  clear: O(size of array)
      iterate: O(size of array) 如果我么用sequential array, 复杂度如下： add: O(1)  deletion: O(number of elements)
       lookup:O(number of elements)    clear: O(1) iterate:O(number of elements) 所以我们需要把这两个方法整合起来。
        网上的global version number的方法面试官不认可 整合的办法就是把没个数都存两遍，在random array存一遍，在sequential array村一边，
        但是sequential array里存的是index，不是数据本身(或者相反？我有点忘了)
     */
    //懒得写了感觉就是o1SET吧
    int[] keys;
    int[] values;
    int[] indexes;
    int size;
    public O1Map(int n){
        keys=new int[n];
        values=new int[n];
        indexes=new int[n];
    }
    public boolean containsKey(int k){
        int idx=values[k];
        if(idx<size&&keys[idx]==k){
            return true;
        }
        return false;
    }

    public void put(int k, int v){

    }
    public int get(int k){

    }
    public boolean remove(int k){

    }
    @Override
    public Iterator<Integer> iterator() {

    }
}
