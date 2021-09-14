import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by yufengzhu on 8/28/18.
 */
public class ShortestWordDistanceII {


    //自己想的，就是constractor的时候要费n平方时间，调用shortest的时候是o1，这样LC会超时
    //https://leetcode.com/problems/shortest-word-distance-ii/discuss/67067/Clean-and-concise-Java-solution.-O(n)-time 答案就是constractor的时候O(n），调用shortest的时候是也是O(n）
    HashMap<String,Integer> map;
    public ShortestWordDistanceII(String[] words) {
        map =new HashMap<>();
        for(int i=0;i<words.length;i++){
            for(int j=i+1;j<words.length;j++){
                if(words[i]==words[j]){
                    continue;
                }
                String temp=words[i]+"#|"+words[j];
                if(!map.containsKey(temp)){
                    map.put(temp,j-i);
                }else{
                    int dist=Math.min(map.get(temp),j-i);
                    map.put(temp,dist);
                }
            }
        }
    }

    public int shortest(String word1, String word2) {//就是看word1和word2加一起这个string，在map里对应有的话，存的就是他们之间最短的距离
        String temp1=word1+"#|"+word2;
        String temp2=word2+"#|"+word1;
        if(map.containsKey(temp1)){
            if(map.containsKey(temp2)){
                return Math.min(map.get(temp1),map.get(temp2));
            }
            return map.get(temp1);
        }else{
            return map.get(temp2);

        }
    }

    //7/15/2021,没想到最好的方法。
    //https://leetcode.com/problems/shortest-word-distance-ii/discuss/67028/Java-Solution-using-HashMap
    HashMap<String, List<Integer>> map2=new HashMap<>();
    public void ShortestWordDistanceII2(String[] words) {
        for (int i=0;i<words.length;i++){
            List<Integer> al=map2.getOrDefault(words[i],new ArrayList<Integer>());
            al.add(i);
            map2.put(words[i],al);
        }
    }

    public int shortest2(String word1, String word2) {
        List<Integer> al=map2.get(word1);
        List<Integer> al2=map2.get(word2);
        int i=0;
        int j=0;
        int rs=Integer.MAX_VALUE;
        while (i<al.size()&&j<al2.size()){//这里不那么好想，不是二重循环，移动比较小的那个，因为移动小的才可能使i和j靠近
            rs=Math.min(Math.abs(al2.get(j)-al.get(i)),rs);
            if (al.get(i)<al2.get(j)){
                i++;
            }else{
                j++;
            }
        }

        return rs;
    }

    public void ShortestWordDistanceII3(String[] words) {
        for (int i=0;i<words.length;i++){
            if (!map2.containsKey(words[i])){
                map2.put(words[i],new ArrayList<Integer>());
            }
            map2.get(words[i]).add(i);
        }
    }

    public int shortest3(String word1, String word2) {
        List<Integer> l1=map2.get(word1);
        List<Integer> l2=map2.get(word2);
        int rs=Integer.MAX_VALUE;
        int i=0;
        int j=0;
        while (i<l1.size()&&j<l2.size()){
            rs=Math.min(rs,Math.abs(l1.get(i)-l2.get(j)));
            if (i<j){
                i++;
            }else {
                j++;
            }
        }
        return rs;
    }
}
