import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 9/16/18.
 */
public class uberStringFitting {
    public static void main(String[] args){
        uberStringFitting us=new uberStringFitting();
        us.cut("aaaa bbb cccc ddd ee ff ggggg",8);//如果有很多空格的话就重新构造这个input string把，写在代码里貌似不好写
    }
    //uber，google面经
    /*
    第三轮ABC小哥，人很nice，各自介绍了一些背景后做了个简单的处理字符串的题。目标是切割字符串，每个子串不超过20个字符，单词不能切割开，遇见超过20长度的单词就把这个单词切了。
    比如：输入“Jackson is a very handsome guy”，输出["Jackson is a very", "handsome guy"]. 如果一个字符串比len更长则要拆开
    ie：("aaaa bbb cccc ddd ee ff ggggg",8)，需要返回 ["aaaa bbb", "cccc ddd", "ee ff", "ggggg"]
     */
    //准备uber的时候写的貌似还是错的，妈的写的不太好
    List<String> cut(String s,int len){
        List<String> rs=new ArrayList<>();
        if(s.length()==0){
            return rs;
        }
        s.trim();
        int i=0;
        String[] ss=s.split(" ");
        while (i<ss.length){

            int cur=0;
            String curs="";
            if(ss[i].length()==0){
               i++;
                continue;
            }
            if(curs.length()==0&&ss[i].length()>len){
                rs.add(ss[i++]);
                continue;
            }
            while (i<ss.length&&cur+ss[i].length()<=len){
                cur+=ss[i].length()+1;
                curs+=ss[i]+" ";
                i++;
            }
            if(curs.length()>0&&curs.charAt(curs.length()-1)==' '){
                curs=curs.substring(0,curs.length()-1);
            }
            rs.add(curs);

        }


        return rs;

    }

}
