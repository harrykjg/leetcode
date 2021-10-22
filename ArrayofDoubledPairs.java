import java.util.Arrays;
import java.util.HashMap;

public class ArrayofDoubledPairs {
    //10/22/2021,自己想的，也是答案的思路，就是统计数字及其出现的次数，然后排序，如果是小于0的数如-4，就算算-2是否存在，然后-4和-2的count都减1，而且要检测-4%2是否==0，
    //否则肯定就不存在答案。
    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<arr.length;i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        for (int i=0;i<arr.length;i++){
            int cur=arr[i];
            if (cur==0){
                if (map.get(0)<=0){
                    continue;
                }
                if (map.get(0)<2){
                    return false;
                }else {
                    map.put(cur,map.get(cur)-2);
                }
            }else if (cur>0){
                if (map.get(cur)<=0){
                    continue;
                }
                if (map.containsKey(2*cur)&&map.get(2*cur)>0){
                    map.put(2*cur,map.get(2*cur)-1);
                    map.put(cur,map.get(cur)-1);//开始以为只要从小到大就不写这个也没问题。其实是要的
                }else {
                    return false;
                }
            }else {
                if (map.get(cur)<=0){
                    continue;
                }
                if (cur%2==0&&map.containsKey(cur/2)&&map.get(cur/2)>0){
                    map.put(cur/2,map.get(cur/2)-1);
                    map.put(cur,map.get(cur)-1);
                }else {
                    return false;
                }
            }
        }
        return true;
    }
}
