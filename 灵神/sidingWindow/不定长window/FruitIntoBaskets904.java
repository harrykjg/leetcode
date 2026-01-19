package 灵神.sidingWindow.不定长window;

import java.util.*;

public class FruitIntoBaskets904 {
    public static void main(String[] args) {
        int[] nums={3,3,3,1,2,1,1,2,3,3,4};
        System.out.println(totalFruit2(nums));
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
//1/14/2026感觉这样写好一些，先直接把e加进来，再缩
    public static int totalFruit2(int[] fruits) {
        int rs=0;
        Map<Integer,Integer> map=new TreeMap<>();
        int b=0;
        int e=0;
        while (e<fruits.length){
            map.put(fruits[e],map.getOrDefault(fruits[e],0)+1);
            e++;
            while(map.size()>2){
                int remove=fruits[b];
                map.put(remove,map.get(remove)-1);
                if(map.get(remove)==0){
                    map.remove(remove);
                }
                b++;
            }
            int len=e-b;
            rs=Math.max(rs,len);

        }
        return rs;
    }
}
