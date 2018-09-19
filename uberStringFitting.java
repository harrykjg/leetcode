import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 9/16/18.
 */
public class uberStringFitting {
    public static void main(String[] args){
        uberStringFitting us=new uberStringFitting();
        us.cut("jackson                is a very handsome guy",20);//如果有很多空格的话就重新构造这个input string把，写在代码里貌似不好写
    }
    //uber面经
    /*
    第三轮ABC小哥，人很nice，各自介绍了一些背景后做了个简单的处理字符串的题。目标是切割字符串，每个子串不超过20个字符，单词不能切割开，遇见超过20长度的单词就把这个单词切了。
    比如：输入“Jackson is a very handsome guy”，输出["Jackson is a very", "handsome guy"]. 如果一个字符串比len更长则要拆开
     */
    //妈的写的不太好
    List<String> cut(String s,int len){
        List<String> rs=new ArrayList<>();
        if(s.length()==0){
            return rs;
        }
        s.trim();

        String[] ss=s.split(" ");//很多个空格的话split出来的是""长度为0的空字符串
        int index=0;

        while (index<ss.length){//还就是要while，for的话不好写
            String cur="";
            String candidate=ss[index];

            if(candidate.length()>len){
                String cs=candidate.substring(0,len);
                rs.add(cs);
                ss[index]=candidate.substring(len);
                continue;
            }

            while (index<ss.length&&cur.length()+ss[index].length()<len){
                cur+=ss[index++]+" ";
            }
            rs.add(cur.substring(0,cur.length()-1));
        }
        return rs;

    }
}
