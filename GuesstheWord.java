import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GuesstheWord {
    //9/1/2021 不会
    // https://www.cnblogs.com/grandyang/p/11449244.html
    //https://blog.csdn.net/u013325815/article/details/105221311
    //https://www.136.la/nginx/show-108455.html
    //https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison
    //思路就是对某一个word guess得出这个word和答案有几个位置是match的，题目说了单词一定是长度为6的，那么有了第一个单词的guess结果，我们就可以缩小候选单词的size
    //来做下一个guess，做法是比如第一个guess得到2，说明有两个字符是match的，那么其余的单词如果和这个单词的match是2，则他还是有可能是结果的，因为他可能别的四位和结果
    //match，剩下2位和这个单词match。如果某个单词和guess的match3个，说明肯定不会是结果，因为就算你剩余的3个位置和结果全match，由于你和这个猜过的单词match3个，而猜过的单词
    //只和答案match2个，说明你最多也就match5个。如果某个单词和guess的单词match1个，则也不可能是答案，因为就算剩余5个位置中至少得有1个位置和答案不同（因为guess的单词
    //肯定match2个了）。因此得出结论，剩余单词中只有和已经guess的单词match是2个的才有可能是结果。
    public void findSecretWord(String[] wordlist, Master master) {
        List<String> ls=new ArrayList<>();
        ls=Arrays.asList(wordlist);
        Random ran=new Random();
        for (int i=0;i<10;i++){
            List<String> temp=new ArrayList<>();
            String cur=ls.get(ran.nextInt(ls.size()));//不用random过不了
            int num=master.guess(cur);
            if(num==6){
                break;
            }
            for(String s:ls){
                if(match(cur,s)==num){
                    temp.add(s);
                }
            }
            if (temp.size()==0){
                break;
            }
            ls=temp;
        }

    }
    int match(String s1,String s2){
         int count=0;
         for (int i=0;i<s1.length();i++){
             if (s1.charAt(i)==s2.charAt(i)){
                 count++;
             }
         }
         return count;
    }

}
