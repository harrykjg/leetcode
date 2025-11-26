package 灵神.sidingWindow.不定长window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FruitIntoBaskets904 {
    public static void main(String[] args) {
        int[] nums={0,1,2,2};
        System.out.println(totalFruit(nums));
    }

//基本不定长操作
    public static int totalFruit(int[] fruits) {

            int rs=0;
            int b=0;
            int e=0;
            int count=0;
            Map<Integer,Integer> map=new HashMap<>();
            while (e<fruits.length){
                while (e<fruits.length){
                    if(map.containsKey(fruits[e])){
                        map.put(fruits[e],map.get(fruits[e])+1);
                        count++;
                    }else{
                        if(map.size()==2){
                            break;
                        }else {
                            map.put(fruits[e],1);
                            count++;
                        }
                    }
                    e++;
                    rs=Math.max(rs,count);
                }
                while (map.size()>=2&&b<e){
                    if(map.get(fruits[b])==1){
                        map.remove(fruits[b]);
                    }else{
                        map.put(fruits[b],map.get(fruits[b])-1);
                    }
                    count--;
                    b++;
                }
            }

            return rs;

    }
}
