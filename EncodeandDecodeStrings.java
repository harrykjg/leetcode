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
        ls.add("nsm");
        ls.add("dsy");
        System.out.println(ed.encode2(ls));
        System.out.println(ed.decode2("3/nsm3/dsy"));
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
            int n=s.indexOf("#",index);//注意有这个方法找index之后第一个出现的"#"
            int num=Integer.parseInt(s.substring(index,n));
            String ss=s.substring(n+1,n+1+num);//这里坐标要举例子看清楚
            index=n+1+num;
            rs.add(ss);
        }

        return rs;
    }
//9/23/2018大概记得，细节记不清，只用一个分隔符就行了，记成是要"／"再加"#"去分割
    public String encode2(List<String> strs) {
        String rs="";
        for(String s:strs){
            int len=s.length();
            rs+=len+"/"+s;
        }
        return rs;
    }

    // Decodes a single string to a list of strings.//这个写法substring太多还不如以前的写法
    public List<String> decode2(String s) {
        List<String> rs=new ArrayList<>();
        int index=0;
        while (true){
            index=s.indexOf("/");
            if(index==-1){
                break;
            }
            int len=Integer.parseInt(s.substring(0,index));
            s=s.substring(index+1);
            String temp=s.substring(0,len);
            rs.add(temp);
            s=s.substring(temp.length());
        }
        return rs;
    }

}
