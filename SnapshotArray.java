import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnapshotArray {
    public static void main(String[] args){
        SnapshotArray sa=new SnapshotArray(3);
        sa.set(0,5);
        sa.snap();
        sa.set(0,6);
        System.out.println(sa.get(0,0));
    }

    //10/21/2021
    //https://leetcode.com/problems/snapshot-array/discuss/350648/Java-O(1)-snap-O(logN)-getset-using-TreeMap 参考下面评论的
    Map<Integer, Map<Integer,Integer>> map=new HashMap<>();
    int length;
    int snap=0;
    Map<Integer,Integer> cur=new HashMap<>();//思路就是每一个snap都用当前的cur 存着，snap的时候把cur放入map，cur变成一个新的map，那么如果调用get的时候，发现
    //这个snap里对应的map并没有所要的key，则要一层一层snap 版本往下找
    public  SnapshotArray(int length) {
        this.length=length;
        for (int i=0;i<length;i++){
            cur.put(i,0);
        }
    }

    public void set(int index, int val) { // 0 0,   1 1 ,   0 2  1 1
        cur.put(index,val);
    }

    public int snap() {
        map.put(snap,cur);
        cur=new HashMap<>();
        int rs=snap;
        snap++;
        return rs;
    }

    public int get(int index, int snap_id) {
         if (map.get(snap_id).containsKey(index)){
             return map.get(snap_id).get(index);
         }
         return get(index,snap_id-1);
    }
}
