package SomeInterviews.purestorage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class O1Set implements Iterable<Integer>{
    //https://leetcode.com/discuss/post/6019471/pure-storage-set-problem-by-anonymous_us-150v/
    //看了题目就想到直接set加doubly linkedlist就像LRU一样，但是gpt说也行，但是题目说了数字的范围是[0，N]则是暗示你用 sparse set
    int[] dense;
    int[] sparse;
    int size;
    public O1Set(int n){
        dense=new int[n];
        sparse=new int[n];
    }
     public boolean lookup(int a){
        if(!valid(a)){
            return false;
        }
        if(sparse[a]<size&&dense[sparse[a]]==a){//因为可能这个index上没有值
            return true;
        }
        return false;
     }

     public boolean set(int a){
        valid(a);
        if(lookup(a)){
            return false;
        }
        dense[size]=a;//注意是append到dense的末尾，
        sparse[a]=size;
        size++;
        return true;
     }

     public boolean remove(int a){
        if(!lookup(a)){
            return false;
        }
        int toRemoveIdx=sparse[a];
        int lastVal=dense[size-1];
        //把最后一位换过来
        dense[toRemoveIdx]=lastVal;
        //更新最后一位缓过来之后的index
        sparse[lastVal]=toRemoveIdx;
        size--;//那么现在最后的那个值还是那个值，但是size减少了，因此lookup的时候还得检测一下size，因为有可能这个换过来的值后来被删了，
         // 但是数组里还有他，因此不检查size就会错误的认为他还在数组里
        return true;
     }
     public void clear(){
        size=0;
     }
     public int[] iterate(){
        return Arrays.copyOf(dense,size);
     }
    private boolean valid(int a){
        if(a>=0&&a<dense.length){
            return true;
        }
        throw new IllegalArgumentException("Value out of range: " + a);
    }
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return dense[index++];
            }
        };
    }


}
