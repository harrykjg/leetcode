package ArrayListAndNumbers;

import java.util.HashSet;

public class NumberofGoodWaystoSplitaString {
    //9/2/2021，看了用hint想的
    public int numSplits(String s) {
        int[] left=new int[s.length()];
        int[] right=new int[s.length()];
        HashSet<Character> set=new HashSet<>();
        char[] ch=s.toCharArray();
        for (int i=0;i<ch.length;i++){
            set.add(ch[i]);
            left[i]=set.size();
        }
        set.clear();
        int rs=0;
        for (int i=ch.length-1;i>0;i--){//不用检测到最左边，因为最左边不能分成2个单词
            set.add(ch[i]);
            right[i]=set.size();
            if (left[i-1]==right[i]){
                rs++;
            }
        }
        return rs;
    }
}
