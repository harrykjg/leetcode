import java.util.ArrayList;
import java.util.HashMap;
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

    //7/7/2021
    public List<String> letterCombinations2(String digits) {
        if (digits.length()==0){
            return new ArrayList<>();
        }
        HashMap<Integer,String> map=new HashMap<>();
        map.put(2,"abc");
        map.put(3,"edf");
        map.put(4,"ghi");
        map.put(5,"jkl");
        map.put(6,"mno");
        map.put(7,"pqrs");
        map.put(8,"tuv");
        map.put(9,"wxyz");
        List<String> rs=new ArrayList<>();
        dfs2(0,"",digits,rs,map);
        return rs;
    }
    void dfs2(int b,String cur,String digits,List<String> rs,HashMap<Integer,String> map){
        if (b>=digits.length()){
            rs.add(cur);
            return;
        }
        String ch=map.get(digits.charAt(b)-'0');
        for (int i=0;i<ch.length();i++){
            dfs2(b+1,cur+ch.charAt(i),digits,rs,map);
        }
    }

}
