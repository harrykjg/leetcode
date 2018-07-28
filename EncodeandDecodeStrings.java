import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 7/15/18.
 */
public class EncodeandDecodeStrings {
    //https://www.cnblogs.com/grandyang/p/5265628.html
    //https://segmentfault.com/a/1190000003791865
    //不会，看他们的思路
    public static void main(String[]a){
        EncodeandDecodeStrings ed=new EncodeandDecodeStrings();
        List<String> ls=new ArrayList<>();
        ls.add("");
        System.out.println(ed.encode(ls));
        System.out.println(ed.decode("#%?%#"));
    }

    public String encode(List<String> strs) {
        if(strs.size()==0){
            return "";
        }
        StringBuilder sb=new StringBuilder();
        for(String s:strs){
            sb.append(s.length()).append("#").append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        if(s==null||s.length()==0){
            return new ArrayList<>();
        }
        List<String> rs=new ArrayList<>();
        int index=0;
        while (index<s.length()){
            int n=s.indexOf("#",index);
            int num=Integer.parseInt(s.substring(index,n));
            String ss=s.substring(n+1,n+1+num);//这里坐标要举例子看清楚
            index=n+1+num;
            rs.add(ss);
        }

        return rs;
    }
}
