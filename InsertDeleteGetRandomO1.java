import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by 502575560 on 5/9/17.
 */
public class InsertDeleteGetRandomO1 {

}
//类似Random Pick Index
//getrandom好像不太好想,看到提示说array,那么我想维护一个arraylist,每次插入一个数字进set里同时也加入这个arraylist里,
//每次remove也从arraylist里remove,要常O(1)remove操作的话arraylist又不行,所以说想用一个hashmap,key 是就是这个数的值,value是在array里的位置
//其实有一步是想错了,比如现在map里又1,2,3 三个key,各自对应的value(即数组的index)是2,1,0,现在我把key=2删了,那么array.size变成2了,但是key为1
//的value还是2,那么久错了,还是看他们的解法就是要删除一个元素时不是只是从array里删掉,要同时把数组最后一个数字移到这个位置上,并且更新map的value值,
//而且直接array.remove(map)好想底层还是要把后面所有的元素shift过来,貌似不是O(1)操作
//http://www.cnblogs.com/reboot329/p/5894784.html
//http://www.cnblogs.com/grandyang/p/5740864.html
class RandomizedSet {

    ArrayList<Integer> al;
    HashMap<Integer,Integer> map;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        al=new ArrayList<>();
        map=new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(!map.containsKey(val)){
            map.put(val,al.size());
            al.add(val);
            return true;
        }else{
            return false;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }else{
            int last=al.get(al.size()-1); //想清楚,array里存的就是map的key,先得到数组里最后一个数,然后把他放到要删除的那个元素的位置上,
            al.set(map.get(val),last);    //然后再把数组最后一个值删掉,然后update这个last的index,并且再map中删除要除去的那个元素
            al.remove(al.size()-1);
            map.put(last,map.get(val));
            map.remove(val);
            return true;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random ran=new Random();
        int r=ran.nextInt(al.size());
        return al.get(r);
    }


//8／16／2018
    ArrayList<Integer> al2;
    HashMap<Integer,Integer> map2;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        al2=new ArrayList<>();
        map2=new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert2(int val) {
        if(map2.containsKey(val)){
            return false;
        }
        map2.put(val,al2.size());
        al2.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove2(int val) {
        if(!map2.containsKey(val)){
            return false;
        }
        int index=map2.get(val);
        if(index==al2.size()-1){
            al2.remove(al2.size()-1);
        }else{
            int last=al2.get(al2.size()-1);
            al2.set(index,last);
            map2.put(last,index);
            al2.remove(al2.size()-1);
        }
        map2.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom2() {
        Random ran =new Random();
        int index=ran.nextInt(al2.size());
        return al2.get(index);
    }

//04/11/2020
    public RandomizedSet3() {
        al=new ArrayList<>();
        map=new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert3(int val) {

        if(!map.containsKey(val)){
            map.put(val,al.size()-1);
            al.add(val);
            return true;
        }
        return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove3(int val) {
        if(!map2.containsKey(val)){
            return false;
        }
        int index=map2.get(val);
        if(index==al2.size()-1){
            al2.remove(al2.size()-1);
        }else{
            int last=al2.get(al2.size()-1);
            al2.set(index,last);
            map2.put(last,index);
            al2.remove(al2.size()-1);
        }
        map2.remove(val);
        return true;

    }

    /** Get a random element from the set. */
    public int getRandom3() {
        Random ran=new Random();
        ran.nextInt(al.size());
        return al.get(ran.nextInt(al.size()));
    }
}

