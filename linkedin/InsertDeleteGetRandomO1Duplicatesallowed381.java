package linkedin;

import java.util.*;

public class InsertDeleteGetRandomO1Duplicatesallowed381 {
    //2/2/2026，看回以前的笔记应该用linkedhashset，getrandom就是直接arraylist里直接装所有元素再random。还不太好写,尤其是remove的时候
    //如果要remove一个几经又好几个的值，那么是remove哪个index呢？是随便都行，第一个或最后一个都行。
    class RandomizedCollection {
        List<Integer> al=new ArrayList<>();
        Map<Integer, LinkedHashSet<Integer>> map;
        Random ran;
        public RandomizedCollection() {
            map=new HashMap<>();
            ran=new Random();
        }

        public boolean insert(int val) {
            boolean exist=false;
            if(!map.containsKey(val)){
                LinkedHashSet<Integer> set=new LinkedHashSet<>();
                set.add(al.size());
                map.put(val,set);
            }else{
                map.get(val).add(al.size());
                exist=true;
            }
            al.add(val);
            return !exist;
        }

        public boolean remove(int val) {
            if(!map.containsKey(val)){
                return false;
            }
            LinkedHashSet<Integer> set=map.get(val);
            int index=set.getLast();//取第一个也行
            int last=al.getLast();
            set.remove(index);
            if(index==al.size()-1){

                al.remove(al.size()-1);

            }else{
                al.set(index,last);
                int lastindex=al.size()-1;
                al.remove(lastindex);
                map.get(last).remove(lastindex);//注意这里不是removelast，应该是删掉对应的下标，因为下一行又给他加上一个index，因此
                //这个linkedhashset里面的值就不是排序的了，下次又要删某个值然后把al最后一个调过去的时候，如果是set.removeLast就是错的
                map.get(last).add(index);
            }
            if(set.size()==0){
                map.remove(val);
            }

            return true;
        }

        public int getRandom() {
            int index=ran.nextInt(al.size());
            return al.get(index);
        }
    }
}
