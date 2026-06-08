package SomeInterviews.snowflake;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InsertDoc {
    /*
    实现2个API：InsertDoc(filename), CheckContains(filename, predicate). check contains是说给你一个predicate,
    让你判断这个file里是不是符合这个predicate。比如 a || b || c, 就是问你这个file
    是不是有a 或者 有b 或者 有c；a && b || c 就是这个file 是不是（有 a 有 b） 或者 （有c）。
    Followup1: 多实现一个API GetAllFiles(predicate) 就是说返回所有的符合这个predicate的files
    Followup2: Distributed System, sharding/partition policy and replica Policy
     */
    Map<String, Set<String>> map1=new HashMap<>();
    Map<String,Set<String>> map2=new HashMap<>();//就是inverted index，key是word，value是对应的文件
    public void insertDoc(String fileName,String content){
        Set<String> words = tokenize(content);
        if(map1.containsKey(fileName)){
            remove(fileName);
        }
        map1.put(fileName,words);
        for (String s:words){
            map2.putIfAbsent(s,new HashSet<>());
            map2.get(s).add(fileName);
        }
    }
    public boolean CheckContains(String fileName,String predicate){
        Set<String> words = map1.get(fileName);
        if (words == null) {
            return false;
        }
        // 先按 || 分组
        String[] orParts = predicate.split("\\|\\|");
        for (String part : orParts) {
            // 每个 part 是一组 && 条件，因为&&优先于||
            String[] andParts = part.split("&&");
            boolean allExist = true;
            for (String token : andParts) {
                String word = token.trim();
                if (!words.contains(word)) {
                    allExist = false;
                    break;
                }
            }
            if (allExist) {
                return true;
            }
        }
        return false;
    }
    Set<String> tokenize(String word){
        String[] w=word.split(" ");
        Set<String> set=new HashSet<>();
        for (String s:w){
            set.add(s);
        }
        return set;
    }
    void remove(String fileName){
        if(!map1.containsKey(fileName)){
            return;
        }
        Set<String> words=map1.get(fileName);
        for (String s:words){
            Set<String> files=map2.get(s);
            if (files!=null){
                files.remove(fileName);
                if(files.isEmpty()){
                    map2.remove(s);
                }
            }
        }
        map1.remove(fileName);
    }
}
