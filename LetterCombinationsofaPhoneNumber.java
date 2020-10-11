import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsofaPhoneNumber {
    //04/22/2020改了一次对了
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0){
            return new ArrayList<String>();
        }
        List<String> rs=new ArrayList<>();

        String[] pool=new String[10];
        pool[2]="abc";
        pool[3]="def";
        pool[4]="ghi";
        pool[5]="jkl";
        pool[6]="mno";
        pool[7]="pqrs";
        pool[8]="tuv";
        pool[9]="wxyz";
        char[] ch=digits.toCharArray();
        dfs(0,"",ch,rs,pool);
        return rs;

    }
    void dfs(int index,String cur,char[] digit,List<String> rs,String[] pool){
        if(cur.length()==digit.length){
            rs.add(cur);
            return;
        }
        int num=digit[index]-'0';//-'0'开始漏了
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<pool[num].length();i++){
            char c=pool[num].charAt(i);
            dfs(index+1,cur+Character.toString(c),digit,rs,pool);
        }
    }
}
