package SomeInterviews.Confluent;

import java.util.*;

public class DesignInvertedIndex {

}
class DocumentLibrary {
    /*
    Given a list of documents, each with a unique document ID and associated text. Implement a system that allows efficient searching of phrases within these documents. The search is case-insensitive and the system should be capable of handling searching over one million documents.

Implement the DocumentLibrary class:

DocumentLibrary(List<List<String>> documents) Initialize a document library with a list of document IDs and text.
List<String> search(String phrase) Find all document IDs that contain the phrase (case-insensitive).
Constraints:

Number of documents ≤ 106.
Length of each document's text ≤ 104 characters.
Number of search queries ≤ 105.
The length of the search phrase is between [1, 104 ] characters.
Document text contains only words, spaces, and specific symbols (".", ",", "?", "!").
Example

Input:
["DocumentLibrary", "search", "search", "search", "search"]

[
[
["1", "Cloud computing is the on-demand availability of computer system resources."],
["2", "One integrated service for metrics uptime cloud monitoring dashboards and alerts reduces time spent navigating between systems."],
["3", "Monitor entire cloud infrastructure, whether in the cloud computing is or in virtualized data centers."]
],
["cloud"],
["cloud monitoring"],
["Cloud computing is"],
["serverless computing"]
]

Output:
[null, ["1", "2", "3"], ["2"], ["1", "3"], [ ]]

Explanation:

DocumentLibrary library = new DocumentLibrary(documents); // Initializes with the given list of documents.
library.search("cloud"); // Return ["1", "2", "3"]. All documents contain the word "cloud".
library.search("cloud monitoring"); // Return ["2"]. Only document 2 matches.
library.search("Cloud computing is"); // Return ["1", "3"].
library.search("serverless computing"); // Return []. No documents match the query.

Hint 1
Focus on normalizing the input text first; consider how removing punctuation and standardizing case simplifies matching.

Hint 2
Instead of storing just document IDs, track the exact position of each word within its document to enable phrase verification.

Hint 3
When searching, use the first word of the phrase to quickly narrow down candidate documents, then verify the remaining words appear at strictly consecutive positions.
     */
    //我想的就是key是word，value是map，其中key是doc ID，value是这个doc里出现的位置
    Map<String, Map<String,List<Integer>>> map=new HashMap<>();
    public DocumentLibrary(List<List<String>> documents) {
        int id=0;
        //先要tokenize这些词，把特殊符号去掉
        for(int i=0;i<documents.size();i++){
            String docId = documents.get(i).get(0);//0位是id，后面才是text
            String text = documents.get(i).get(1);
            String[] words=tokenize(text);
            for (int j=0;j<words.length;j++){
                map.putIfAbsent(words[j],new HashMap<>());
                map.get(words[j]).putIfAbsent(docId,new ArrayList<>());
                map.get(words[j]).get(docId).add(j);
            }
        }
    }

    public List<String> search(String phrase) {
        // TODO: Implement search logic
        List<String> rs=new ArrayList<>();
        phrase=phrase.trim();
        String[] words = tokenize(phrase);
        if (words.length == 0) {
            return rs;
        }
        // phrase 第一个 word 都不存在，肯定没有 match
        if (!map.containsKey(words[0])) {
            return rs;
        }
        Map<String, List<Integer>> candidates = map.get(words[0]);
        for (String docId : candidates.keySet()) {
            if (matchPhrase(docId, words)) {
                rs.add(docId);
            }
        }
        return rs;
    }
    //这里就是看每个document里能不能找到这个phase了
    boolean matchPhrase(String id,String[] phase){
        List<Integer> positions=map.get(phase[0]).get(id);//注意是get 第一个word，再get docid得到这个doc里这个phase[0]出现的位置
        //关键是这里，现在是没有documents原文了，所有的词都被装进map了，因此你得再找第2，3，4个phase所在的document，
        // 然后再去这个document找某个index是否存在
        for(int pos:positions){//这个pos代表这个document里的各个出现过phase【0】的位置，因此要看pos+1，pos+2.。。位置是否有phase[1],[2]这个词
            boolean match= true;
            for (int i=1;i<phase.length;i++){//那现在就看这个document里的 第2，3，4。。个位置是否符合phase【1】【2】【3】。。
                String target=phase[i];
                if (!map.containsKey(target)) {//直接不存在就肯定不行
                    match = false;
                    break;
                }
                Map<String,List<Integer>> docs=map.get(target);//得到这个target词在这个docid出现的所有位置
                if (!docs.containsKey(id)) {//这里很容易漏检查，因为不一定存在，因为上面这个docs是包含所有这个target的文件，
                    //但是你这个target是第二，3，4个词，所以又不一定包括第一个词出现的文件id， 真的很绕
                    match = false;
                    break;
                }
                List<Integer> indexs=docs.get(id);//现在获得了这个target出现在这个文档的所有index了，上面解释了要找pos+1，post+2.。
                // 位置是否有这个单词，即看看这个index里是否存在pos+i这个index。。
                if (Collections.binarySearch(indexs, pos + i) < 0) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }
    String[] tokenize(String text){
        text=text.toLowerCase();
        text=text.trim();
        if (text.isEmpty()) {
            return new String[0];
        }
        return text.split("[\\s.,?!]+");//\s代表空格，要转义即\\s，
    }
}
