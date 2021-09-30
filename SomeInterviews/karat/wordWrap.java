package SomeInterviews.karat;

import java.util.ArrayList;
import java.util.List;

public class wordWrap {
    public static void main(String[] args){
        wordWrap ww=new wordWrap();
        String[] s={"This", "is", "an", "example", "of", "text", "justification."};
        String[] s2={"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        String[] s3={"The" ,"Earth", "is",
                "the","only","world",
                "known", "so", "far",
                "to", "harbor", "life" };
//        List<String> rs=ww.wrap(s,16);
//        for (String ss:rs){
//            System.out.println(ss);
//        }
        List<String> rs2=ww.justify(s3,18);
        for (String ss:rs2){
            System.out.println(ss);
        }

    }
    //第一问就是text justification简化版，不需要分配空格，只要不超len就行
    public List<String> wrap(String[] words,int len){
        int i=0;
        List<String> rs=new ArrayList<>();
        while (i<words.length){
            String cur=words[i];
            int l=cur.length();
            l++;
            i++;
            while (i<words.length&&l+words[i].length()<=len){
                cur+="-"+words[i];
                i++;
                l=cur.length()+1;//这个+1容易漏
            }
            rs.add(cur);
        }
        return rs;
    }

    //第二问就是text justification，只是空格变成"-".他这里和lc不同的是最后一行不要把spaces填满整行，只要左缩紧就行了
    public List<String> justify(String[] words,int len){
        int i=0;
        List<String> rs=new ArrayList<>();
        while (i<words.length){
            List<String> al=new ArrayList<>();
            String cur=words[i];
            al.add(cur);
            int l=cur.length();//用来记录一个单词加一个空格的长度
            int wordlen=cur.length();//用来记录单纯的单词的长度
            l++;
            i++;
            while (i<words.length&&l+words[i].length()<=len){
                al.add(words[i]);
                wordlen+=words[i].length();
                l+=words[i].length()+1;
                i++;
            }
            int spaces=len-wordlen;
            StringBuilder sb=new StringBuilder();
            if (al.size()==1){//如果只有一个单词
                if(i<words.length){//比lc多这个判断，因为最后一行只有一个单词的话不要append空格在后面
                    sb.append(al.get(0));
                    for (int j=0;j<spaces;j++){
                        sb.append("-");
                    }
                    rs.add(sb.toString());
                }else{
                    sb.append(al.get(0));
                    rs.add(sb.toString());
                }
            }else{//对每个al里的单词apend空格
               if(i>=words.length){//最后一行了，最后一个单词后面不需要继续append
                   for (int j=0;j<al.size();j++){
                       sb.append(al.get(j));
                       if(j!=al.size()-1){
                           sb.append("-");
                       }
                   }
                   rs.add(sb.toString());
               }else{
                   while (spaces>0){
                       for (int j=0;j<al.size()-1;j++){//最后那个单词不需要append空格
                           String temp=al.get(j);
                           temp+="-";
                           al.set(j,temp);
                           spaces--;
                           if (spaces==0){
                               break;
                           }
                       }
                   }
                   for (String ss:al){
                       sb.append(ss);
                   }
                   rs.add(sb.toString());
               }
            }
        }
        return rs;
    }
}
