package linkedin;

import java.util.*;

public class InsertDeleteGetRandomOone380 {
    //2/2/2026这题是不允许insert duplicate的,主要是要个想到删除的时候要把最后一位换过来并update map
    class RandomizedSet {
        List<Integer> al;
        Map<Integer,Integer> map;
        Random ran=new Random();
        public RandomizedSet() {
            map=new HashMap<>();
            al=new ArrayList<>();
        }

        public boolean insert(int val) {
            if(map.containsKey(val)){
                return false;
            }
            map.put(val,al.size());
            al.add(val);
            return true;
        }

        public boolean remove(int val) {
            if(!map.containsKey(val)){
                return false;
            }

            int index=map.get(val);
            int last=al.getLast();//就算remove这个正好是last也是一样
            al.set(index,last);
            map.put(last,index);
            al.remove(al.size()-1);

            map.remove(val);
            return true;

        }

        public int getRandom() {
            int index=ran.nextInt(al.size());
            return al.get(index);
        }
    }
}
