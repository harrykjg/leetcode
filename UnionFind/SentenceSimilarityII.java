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
        map.put(s,rs);//这一步其实就是路径压缩了，不太好想，就是模版的father【x】=find（father【x】），居然不写也对
        return rs;
    }

    //10/17/2018,基本一次过
    public boolean areSentencesSimilarTwo2(String[] words1, String[] words2, String[][] pairs) {
        HashMap<String,String> map=new HashMap<>();
        if(words1.length!=words2.length){
            return false;
        }
        for(String[] p:pairs){
            String first=p[0];
            String firstId=find2(map,first);
            for(int i=1;i<p.length;i++){
                String temp=p[i];
                String tempId=find2(map,temp);
                if(!firstId.equals(tempId)){
                    map.put(tempId,firstId);
                }
            }
        }

        for(int i=0;i<words1.length;i++){
            String a=words1[i];
            String b=words2[i];
            String ida=find2(map,a);
            String idb=find2(map,b);
            if(!ida.equals(idb)){
                return false;
            }
        }
        return true;


    }
    String find2(HashMap<String,String> map,String s){
        if(!map.containsKey(s)){
            map.put(s,s);
            return s;
        }
        if(map.get(s).equals(s)){
            return s;
        }
        String temp=find2(map,map.get(s));
        map.put(s,temp);//注意这里以前写的是map.put(map.get(s),temp);那样应该是错的，这样才是对的，但是之前那样写居然也对了
        return temp;
    }
}
