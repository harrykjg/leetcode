package ArrayListAndNumbers;

import java.util.Arrays;
import java.util.HashMap;

public class FindOriginalArrayFromDoubledArray {
    public static void main(String[] args){
        FindOriginalArrayFromDoubledArray fo=new FindOriginalArrayFromDoubledArray();
        int[] rs=fo.findOriginalArray(new int[]{1,2,3,4,6,8});
        for (int i:rs){
            System.out.println(i);
        }
    }
    //10/21/2021 还有点难,确定是sort和hashmap之后写的，主要是0的情况要想一想。
    //排序数组，建一个map记录数字出现的次数，然后从小到达遍历数组，遇到一个数x，则看2x是否存在，否则返回空，如果存在，则先把x从map里减去1，如果减完后是0则remove，
    //然后看2x是否存在，存在则减1，减完后为0则remove。如果只有一个0的情况，在检测2x的时候就知道是错的了。遍历数组时，比如遇到1，然后把1和2都减去了，然后走到2，发现
    //map已经没有2了，就继续
    public int[] findOriginalArray(int[] changed) {
        Arrays.sort(changed);
        if (changed.length%2==1){
            return new int[0];
        }
        int[] rs=new int[changed.length/2];
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<changed.length;i++){
            map.put(changed[i],map.getOrDefault(changed[i],0)+1);
        }
        int index=0;
        for (int i=0;i<changed.length;i++){//0,1,2,4
            int cur=changed[i];
            if (!map.containsKey(cur)){
                continue;
            }
            if (!map.containsKey(cur+cur)){
                return new int[0];
            }else{
                map.put(cur,map.get(cur)-1);//1 2 3 4 6 8
                if (map.get(cur)==0){
                    map.remove(cur);
                }
                if (!map.containsKey(cur+cur)){//处理只有一个0的情况
                    return new int[0];
                }

                map.put(cur+cur,map.get(cur+cur)-1);
                if (map.get(cur+cur)==0){
                    map.remove(cur+cur);
                }
                rs[index++]=cur;
            }
        }
        return rs;
    }
}
