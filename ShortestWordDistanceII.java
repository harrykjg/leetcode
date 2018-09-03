import java.util.HashMap;

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
}
