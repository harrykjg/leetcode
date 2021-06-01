package contest;

import java.util.*;

public class DetermineifTwoStringsAreClose {
    public static void main(String[] args) {
        DetermineifTwoStringsAreClose dt=new DetermineifTwoStringsAreClose();
        dt.closeStrings("cabbba","aabbss");
    }
    //居然还不好写，注意身体，换位置和换字符都是existing character！如'uau'和'ssx'这种的因为word1里没有s或x就怎么也换不出来，
//https://leetcode.com/problems/determine-if-two-strings-are-close/discuss/936056/JavaPython3-1-line-Python3-Solution-beat-100!!!-Check-this-out.
    //https://leetcode.com/problems/determine-if-two-strings-are-close/discuss/935917/Java-O(n)-solution.
    public boolean closeStrings(String word1, String word2) {
        if(word1.length()!=word2.length()){
            return false;
        }

        Map<Character,Integer> map1=new HashMap<>();
        Map<Character,Integer> map2=new HashMap<>();
        for(char ch:word2.toCharArray()){
            map2.put(ch,map2.getOrDefault(ch,0)+1);
        }

        for(char ch:word1.toCharArray()){
            map1.put(ch,map1.getOrDefault(ch,0)+1);
        }

        if (!map1.keySet().equals(map2.keySet())) {//居然可以这样比,这个条件就复合了existing charter的条件了
            return false;
        }

        List<Integer> word1FrequencyList = new ArrayList<>(map1.values());
        List<Integer> word2FrequencyList = new ArrayList<>(map2.values());
        Collections.sort(word1FrequencyList);
        Collections.sort(word2FrequencyList);
        return word1FrequencyList.equals(word2FrequencyList);

    }
}
