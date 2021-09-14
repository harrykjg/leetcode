package contest;

import java.util.Set;
import java.util.TreeSet;

public class MaximumNumberofWordsYouCanType {
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] texts=text.split(" ");
        if (brokenLetters==null||brokenLetters.length()==0){
            return texts.length;
        }
        char[] ch=brokenLetters.toCharArray();
        Set<Character> set=new TreeSet<>();
        for (char c:ch){
            set.add(c);
        }
        int rs=texts.length;
        for (String t:texts){
            char[] tt=t.toCharArray();
            for (char c:tt){
                if (set.contains(c)){
                    rs--;
                    break;
                }
            }
        }
        return rs;

    }
}
