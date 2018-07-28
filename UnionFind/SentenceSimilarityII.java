package UnionFind;

import java.util.HashMap;

/**
 * Created by yufengzhu on 7/13/18.
 */
public class SentenceSimilarityII {
    //自己想法就是用一个map存每个单词的id，key是string，value也是string，用unionfind,写了挺久的但是改了一次就accept了，关键是
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if(words1.length!=words2.length){
            return false;
        }
        HashMap<String,String> map=new HashMap<>();
        for(int i=0;i<pairs.length;i++){
            for(int j=0;j<pairs[i].length;j++){
                String temp=pairs[i][j];
                if(!map.containsKey(temp)){
                    map.put(temp,temp);
                }
            }
        }
        for(int i=0;i<pairs.length;i++){
            String first=pairs[i][0];
            String firstRoot=find(map,first);
            for(int j=1;j<pairs[i].length;j++){
                String temp=pairs[i][j];
                String tempRoot=find(map,temp);
                if(!tempRoot.equals(firstRoot)){
                    map.put(tempRoot,firstRoot);
                }
            }
        }

        for(int i=0;i<words1.length;i++){
            String s1=words1[i];
            String rs1=find(map,s1);
            String s2=words2[i];
            String rs2=find(map,s2);
            if(!rs1.equals(rs2)){
                return false;
            }
        }
        return true;
    }
    String find(HashMap<String,String> map,String s){
        if(!map.containsKey(s)){//这个用来防止在word数组里的string在pair里没有的情况
            map.put(s,s);
            return s;
        }
        if(map.get(s).equals(s)){
            return s;
        }
        String rs=find(map,map.get(s));
        map.put(map.get(s),rs);//这一步其实就是路径压缩了，不太好想，就是模版的father【x】=find（father【x】），居然不写也对
        return rs;
    }
}
