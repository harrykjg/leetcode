import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yufengzhu on 10/17/18.
 */
public class SentenceSimilarity {
    //改了一次过了
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if(words1.length!=words2.length){
            return false;
        }
        HashMap<String,Set<String>> map=new HashMap<>();
        for(String[] p:pairs){
            String a=p[0];
            String b=p[1];
            if(!map.containsKey(a)){
                map.put(a,new HashSet<>());//就记录一个方向就行了，下面for循环检查的时候就看a对应的相似的集合里面有没有b，没有的话再看b的相似的集合里有没有a，都没有的话就肯定不对了
            }
            map.get(a).add(b);
        }
        for(int i=0;i<words1.length;i++){
            String a=words1[i];
            String b=words2[i];
            if(map.containsKey(a)&&map.get(a).contains(b)){
                continue;
            }
            if(map.containsKey(b)&&map.get(b).contains(a)){
                continue;
            }
            if(a.equals(b)){//这个容易漏
                continue;
            }
            return false;
        }
        return true;

    }
}
