package SomeInterviews.moveworks;

import java.util.*;

public class GuessWord {
    /*
    https://www.1point3acres.com/bbs/thread-1143452-1-1.html
    设计一个程序，用于在猜字谜游戏中预测下一个概率最高的字符。程序需要根据当前已知的单词模式（pattern）、已猜过的字符（guessed_chars）以及一个给定的单词库（word_pool），计算并返回下一个最可能出现的字符，返回概率最大的字符，如果概率相同则按字母顺序返回小的。
输入
pattern (字符串)：表示单词的当前状态。已知的字母保持原样，未知的字母用下划线 _ 表示。
示例："b___"
guessed_chars (字符列表)：一个包含所有已尝试过的字母。
示例：['b', 'c', 'n']
word_pool (字符串列表)：一个包含所有可能单词的候选集合。
示例：["ball", "bold", "boat", "card", "band"]
过程
符合 "b___" 的候选单词为 ["ball", "bold", "boat", "band"]。其中 "band" 含 'n'（已猜过，且不在 pattern 中），需排除。剩余候选为 ["ball", "bold", "boat"]，最后返回"l"。


    关键1.n已经猜过，但是没在b___里，说明没猜中，如果猜中应该就再b___里,因此有n的待选string要被排除，即band会被排除
    关键2，那么剩下可能得string里，如ball，bold，boat，统计剩余的字符出现的次数得
    ball -> a, l, l
    bold -> o, l, d
    boat -> o, a, t
    如果按照“字符出现次数”统计：
    l -> 3
    a -> 2
    o -> 2
    d -> 1
    t -> 1
   所以最大概率的就是l
   问如果pattern是b__b__这样的，这样b已经猜过2次，如果candidate是bbbba，b出现了（5-2）=3次这样算对吗？答，不会，b应该是算做已经猜过的，
   即猜过b就代表单词中b的所有位置都已经reveal了，因此gussed的可以放在一个set里，在统计candidate的字符的时候排除
     */
    public char guess(String pattern, List<Character> guessed,List<String> pool){
        List<String> candidate=new ArrayList<>();//可以有重复
        Set<Character> guessedSet=new HashSet<>(guessed);
        //初步筛选candidate
        for (String s: pool){
            if(s.length()!=pattern.length()){
                continue;
            }
            boolean match=true;
            for (int j=0;j<pattern.length();j++){
                if(pattern.charAt(j)=='_'){
                    if(guessedSet.contains(s.charAt(j))){//这个很容易漏，即这个_位置代表是没猜的，但是你这个候选字符却在已经guessed的话
                        match=false; //说明这个候选单词必然是错的，因为如果对的话，这个字符的所有出现位置都被reveal了，就不会是_了，
                        break;
                    }
                }else if(pattern.charAt(j)!=s.charAt(j)){
                    match=false;
                    break;
                }
            }
            if(match){
                candidate.add(s);
            }
        }
        //从candidate中把包含已经猜过的字符的单词删掉。现在已经不需要了，原因是上面if(guessedSet.contains(s.charAt(j))){这
        //已经保证了包含已经猜过字符的单词不会进入candidate
//        for (char c:guessed){
//            if(!pattern.contains(String.valueOf(c))){
//                Iterator<String> it=candidate.iterator();
//                while (it.hasNext()){
//                    if(it.next().contains(String.valueOf(c))){
//                        it.remove();
//                    }
//                }
//            }
//        }
        //统计candidate的所有字符，注意要除去已经猜过的
        int[] count=new int[26];
        int max=0;
        char guess='_';
        for (String s:candidate){
            char[] sch=s.toCharArray();
            for (int i=0;i<sch.length;i++){//注意这里不能统计在pattern里已经猜过的字符,解释见上面
                if(!guessedSet.contains(sch[i])){
                    count[sch[i]-'a']++;
                }
            }
        }
        //找出最frequent的
        for (int i=0;i<count.length;i++){
            if(count[i]>max){
                max=count[i];
                guess=(char)(i+'a');
            }
        }
        return guess;

    }
}
