import java.util.*;

/**
 * Created by 502575560 on 5/10/17.
 */
public class InsertDeleteGetRandomO1Duplicatesallowed {

}

//开始自己想的就是map里存的时key和这个key在数组中的下标的数组,然后删除某个元素的时候,如果这个元素有多个,则删除index最大的那个,如果inset一个重复的元素
//那么也是添加到数组最后一位,所以说添加和删除都应该发生在这个下标数组的最后一位应该没问题,但是如果说删除某个元素的时候,要把al里的最后一个元素换到删掉的那个
//元素的位置上,那么也要更新这个最后元素的下标,这一更新就可能使这个元素的下标数组打乱了,原本这个下标数组的最后一位肯定使最大的,现在可能不是了,看网上说
//用priorityqueue 来代替list数组,但是这样的话不就不是O(1)操作了吗,然后自己写了priorityqueue的写法就accept了
//http://www.cnblogs.com/grandyang/p/5756148.html
//https://segmentfault.com/a/1190000008119229
 class RandomizedCollection {

     Map<Integer,PriorityQueue<Integer>> map;
     ArrayList<Integer> al;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map=new HashMap<>();
        al=new ArrayList<>();

    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if(!map.containsKey(val)){
            PriorityQueue<Integer> index=new PriorityQueue<>(Collections.reverseOrder());
            index.offer(al.size());
            map.put(val,index);
            al.add(val);
            return true;
        }else{
            map.get(val).offer(al.size());
            al.add(val);
            return false;
        }
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }else{
            PriorityQueue<Integer> a=map.get(val);//如果存在val的话就先得到他的下标数组,取出最大的那个index,如果这个数组只有1位的话那么
            int index=a.poll();     //就把这个val在map里直接删掉了
            if(a.size()==0){
                map.remove(val);
            }
            int last=al.get(al.size()-1);//现在要把al中的最后一个元素放到这个删掉的位置上
            if(last==val){//如果最后一个元素和val正好相等的话,那么就不用换啦,直接删掉最后一个元素,否则就换
                al.remove(al.size()-1);
                return true;
            }
            al.set(index,last);
            PriorityQueue<Integer> la=map.get(last);//换了之后把这个last在map中对应的下标更新
            la.poll();
            la.offer(index);
            al.remove(al.size()-1);
            return true;
        }
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        Random ran=new Random();
        int r=ran.nextInt(al.size());
        return al.get(r);
    }
}