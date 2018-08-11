import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by yufengzhu on 7/29/18.
 */
public class GoatLatin {
    public String toGoatLatin(String S) {
        if(S==null||S.length()==0){
            return S;
        }
        String[]  s=S.split(" ");
        StringBuilder sb=new StringBuilder();
        sb.append('a');

        HashSet<Character> set=new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        String rs="";
        for(int i=0;i<s.length;i++){
            char c=s[i].charAt(0);
            StringBuilder sbb=new StringBuilder(s[i]);
            if(set.contains(c)){
                sbb.append("ma");
                sbb.append(sb.toString());
            }else{
                sbb.delete(0,1);
                sbb.append(c);
                sbb.append("ma");
                sbb.append(sb.toString());
            }
            rs+=sbb.toString()+" ";
            sb.append('a');
        }

        return rs.substring(0,rs.length()-1);
    }
}
