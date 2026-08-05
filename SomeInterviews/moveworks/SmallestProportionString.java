package SomeInterviews.moveworks;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class SmallestProportionString {
    /*
https://leetcode.com/discuss/post/4520538/moveworks-sde-2-l4-interview-by-anonymou-vgby/
Part 1
Given a list of strings, select the strings where the most common character has the smallest proportion in its string.
If multiple strings have the same proportion, select all the strings with the smallest proportion.

Return a new string composed of the characters that are only present in the selected strings.
(ignore the 2nd parameter for this part)
Example: ["aba", "ab", "abbcdd"]
  - "aba": most common character is 'a', with proportion of 2/3
  - "ab": most common characters are 'a' and 'b' with proportion 1/2
  - "abbcdd": most common character are 'b' and 'd', with proportion of 2/6
The smallest proportion is 2/6, so we select the last string "abbcdd" and return the characters that are only present in this string: "cdd".

Part 2 (if time allows)
The 2nd parameter (boolean) is to use Jaccard Similarity instead of most common character. If this parameter is True,
 refactor your code to select the strings that have the lowest average Jaccard Similarity when compared to every other string.
The rest of your code for Part 1 is the same: return a string composed of the characters that are uniquely present in the selected strings across the entire list.
The Jaccard Similarity is a pair-wise comparision calculated with the formula:
J(S1,S2) = len(intersection(S1,S2)) / len(union(S1,S2))
Intersection and union are calculated as bags of characters taking into account the number of repeats for each character.
The intersection is the largest string of characters contained in both strings, and the union is the smallest string containing all the characters of both strings.
Example:

S1 = "baa"
S2 = "abb"
intersection(S1, S2) = "ab"
union(S1, S2) = "aabb"
J(S1, S2) = len("ab") / len("aabb") = 1/2


题目：
part1
给string list, 对里面的每个string，找出most common characters （clarify：有多个的话都算， 多个字符出现相同次数， 所有的字符都要收集），并算出character 在当前string的出现比例 (occurrence proportion)。
要求找出list里面 proportion最低的所有string，然后除掉所有在其他string里出现过的characters, 最后concatenate 成string 输出

part2, 在part1 的基础上加一个要求，假设有一个function def xyz(s1: str, s2: str) -> float:, 对两个strings 算similarity score, 在 part1 的基础上从中要选出 similarity score 最小的 candidates, 最后concatenate 成string 输出

part3, 写part2 假设的function def xyz(s1: str, s2: str) -> float. 来算 similarity score（计算规则我没看到，面试官到时间就关界面了）
     */
    public String solve(List<String> strings) {
        Map<Character,Integer> map=new HashMap<>();//用来统计全局的字符，然后减去selected单词的字符，就知道count=0的是只出现在selected里的
        List<String> selected = new ArrayList<>();
        double proportion=Double.MAX_VALUE;

        for (String s:strings){
            char[] ch=s.toCharArray();
            int[] count=new int[26];
            int max=0;
            for (char c:ch){
                count[c-'a']++;
                if(count[c-'a']>max){
                    max=count[c-'a'];
                }
                map.put(c,map.getOrDefault(c,0)+1);
            }
            double prop=(double)max/ch.length;//注意要先转成double
            if(prop<proportion){
                proportion=prop;
                selected.clear();
                selected.add(s);
            }else if(Math.abs(prop - proportion) < 1e-9){//这里是对于prop == proportion的更好的写法，因为他是double
                selected.add(s);
            }
        }
        for (String s: selected){
            char[] ch=s.toCharArray();
            for (char c:ch){
                map.put(c,map.get(c)-1);
            }
        }
        StringBuilder sb=new StringBuilder();
        //题目意思就是所有的selected的字符，并且没再别的地方出现的，就直接append到一起吧
        for (String s: selected) {
            for (char c:s.toCharArray()){
                if(map.get(c)>0){
                    continue;
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }
    //part2 直接抄gpt，solve方法及时多了个flag去选择是用part 1方法得出的结果集还是part 2方法得出的结果集
    public String solve(List<String> strings, boolean useJaccard) {
        if (strings == null || strings.isEmpty()) {
            return "";
        }
        Map<Character, Integer> global = new HashMap<>();
        List<String> selected;

        for (String s : strings) {
            for (char c : s.toCharArray()) {
                global.put(c, global.getOrDefault(c, 0) + 1);
            }
        }
        if (useJaccard) {
            selected = selectByJaccard(strings);
        } else {
            selected = selectByMostCommonProportion(strings);
        }
        for (String s : selected) {
            for (char c : s.toCharArray()) {
                global.put(c, global.get(c) - 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : selected) {
            for (char c : s.toCharArray()) {
                if (global.get(c) == 0) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
//part 1的方法
    private List<String> selectByMostCommonProportion(List<String> strings) {
        List<String> selected = new ArrayList<>();
        double best = Double.MAX_VALUE;
        for (String s : strings) {
            int[] count = new int[26];
            int max = 0;
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
                max = Math.max(max, count[c - 'a']);
            }
            double prop = s.length() == 0 ? 0.0 : (double) max / s.length();
            if (prop < best) {
                best = prop;
                selected.clear();
                selected.add(s);
            } else if (Math.abs(prop - best) < 1e-9) {
                selected.add(s);
            }
        }
        return selected;
    }
    //part2 的方法，关键就是理解他这个intersect和union到底指的是什么
    /*
如
S1 = "aaabbc"
S2 = "aabccd"
先数频率：
S1:
a -> 3
b -> 2
c -> 1
S2:
a -> 2
b -> 1
c -> 2
d -> 1
Intersection：两个字符串都能提供的最大公共字符包
对每个字符取 min count：
a -> min(3, 2) = 2
b -> min(2, 1) = 1
c -> min(1, 2) = 1
d -> min(0, 1) = 0
所以 intersection 可以是："aabc"
长度是：4
注意：虽然 S1 有 3 个 a，但是 S2 只有 2 个 a，所以 intersection 里最多只能有 2 个 a。
Union：能包含两个字符串所有字符的最小字符包
对每个字符取 max count：
a -> max(3, 2) = 3
b -> max(2, 1) = 2
c -> max(1, 2) = 2
d -> max(0, 1) = 1
所以 union 可以是：
"aaabbccd"长度是：8
注意：Union 不是简单拼接 S1 + S2。
S1 + S2 长度是 6 + 6 = 12，但 union 只需要保留足够多的字符来覆盖两个字符串，所以长度是 8。
     */
    private List<String> selectByJaccard(List<String> strings) {
        List<String> selected = new ArrayList<>();
        double best = Double.MAX_VALUE;
        int n = strings.size();
        for (int i = 0; i < n; i++) {
            double total = 0.0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                //就是要找每一个string和别的string的jaccard分数的平均分
                total += jaccard(strings.get(i), strings.get(j));
            }
            //如果string只有1个那就是0，否则就是total/n-1
            double avg = n == 1 ? 0.0 : total / (n - 1);
            if (avg < best) {
                best = avg;
                selected.clear();
                selected.add(strings.get(i));
            } else if (Math.abs(avg - best) < 1e-9) {
                selected.add(strings.get(i));
            }
        }
        return selected;
    }

    private double jaccard(String a, String b) {
        Map<Character, Integer> freqA = getFreqMap(a);
        Map<Character, Integer> freqB = getFreqMap(b);

        Set<Character> allChars = new HashSet<>();
        allChars.addAll(freqA.keySet());
        allChars.addAll(freqB.keySet());

        int intersection = 0;
        int union = 0;
        for (char c : allChars) {
            int countA = freqA.getOrDefault(c, 0);
            int countB = freqB.getOrDefault(c, 0);
            intersection += Math.min(countA, countB);
            union += Math.max(countA, countB);
        }
        if (union == 0) {
            return 1.0;
        }
        return (double) intersection / union;
    }

    private Map<Character, Integer> getFreqMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

}
