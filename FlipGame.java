import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 7/10/18.
 */
public class FlipGame {
    //太简单了，看清题目说的意思就行了
    public List<String> generatePossibleNextMoves(String s) {
        List<String> rs=new ArrayList<>();
        for(int i=0;i<s.length()-1;i++){
            char[] ch=s.toCharArray();
            if(ch[i]=='+'&&ch[i+1]=='+'){
                ch[i]='-';
                ch[i+1]='-';
                rs.add(new String (ch));
            }
        }
        return rs;

    }
}
