package SomeInterviews.moveworks;

import java.util.*;

public class SynonymousSentences1258 {
    //每个synonyms长度固定是2的,但是synomyms会联通，即一个单词可以有多个同义词。关键是还要通过union find找到一个词的所有同义词，否则
    //一个词可能就只有1个同义词，下面直接抄gpt的
    Map<String, String> parent = new HashMap<>();//存并查集的id
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        String[] arr = text.split(" ");
        List<String> rs = new ArrayList<>();
        // 1. Union all synonym pairs
        for (List<String> syn : synonyms) {
            String a = syn.get(0);
            String b = syn.get(1);
            parent.putIfAbsent(a, a);
            parent.putIfAbsent(b, b);
            union(a, b);
        }
        // 2. Build root -> all words in this connected component
        Map<String, List<String>> groups = new HashMap<>();//这个里面的list包含了这个root自己
        for (String word : parent.keySet()) {
            String root = find(word);
            groups.putIfAbsent(root, new ArrayList<>());
            groups.get(root).add(word);//如果root==word，也会加进来
        }
        // Sort each group so DFS generates lexicographical order more naturally，不加这一步也行，因为后面sort了，或者这里sort，
        //结果集不用sort也对
        for (List<String> group : groups.values()) {
            Collections.sort(group);
        }
        // 3. DFS generate sentences
        dfs(0, "", arr, groups, rs);
        Collections.sort(rs);
        return rs;
    }
    void dfs(int b, String cur, String[] arr, Map<String, List<String>> groups, List<String> rs) {
        if (b == arr.length) {
            rs.add(cur.trim());
            return;
        }
        String word = arr[b];
        if (!parent.containsKey(word)) {//之前只对有同义词的进行了id设置，因此没有id就意味着没用同义词，直接用上
            dfs(b + 1, cur + " " + word, arr, groups, rs);
        } else {//否则用他的邻居+自己。自己已经加在goupds里了
            String root = find(word);
            List<String> options = groups.get(root);
            for (String option : options) {
                dfs(b + 1, cur + " " + option, arr, groups, rs);
            }
        }
    }
    String find(String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }
    void union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);
        if (!rootA.equals(rootB)) {
            parent.put(rootA, rootB);
        }
    }
}
