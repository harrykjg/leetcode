import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 7/31/18.
 */
public class TextJustification {
    public static void main(String[] args) {
        TextJustification tj=new TextJustification();
        String[] words={"Listen","to","many,","speak","to","a","few."};//"What","must","be","acknowledgment","shall","be"
        List<String> a=tj.fullJustify(words, 6);
        for(int i=0;i<a.size();i++){
            System.out.println(a.get(i));
        }
    }
    //自己直接写的，居然还不差，改了一段事件后accept了
    public List<String> fullJustify(String[] words, int maxWidth) {
        int i=0;
        List<String>  rs=new ArrayList<>();
        while (i<words.length){
            int curLen=0;//包括单词之间1个空格的长度
            int count=0;//当前行装了几个单词
            int wordslen=0;//当前行单词的长度，不包括空格
            StringBuilder sb=new StringBuilder();
            ArrayList<String> al=new ArrayList<>();
            boolean appendSpace=false;//如Listen，6这个例子，listen已经把6填满了，
            while (curLen<maxWidth&&i<words.length){
                if(curLen+words[i].length()<=maxWidth){
                    curLen+=words[i].length();
                    al.add(words[i]);
                    wordslen+=words[i].length();
                    i++;
                    count++;

                }else{
                    break;
                }
                if(curLen+1<maxWidth){//这里是用来检测一个单词加进来之后，再加一个空格的话，能不能再加别的单词的，所以curLen+=1了，在去下一个单词能不能加进来
                    curLen+=1;
                    appendSpace=true;
                    continue;
                }else{
                    break;
                }
            }
            if(count==1){//如果当前层只能装一个单词的话，那么就是在单词后面一直append空格就行了
                sb.append(al.get(0));
                if(appendSpace){//如果之前是加了一个空格去判断能不能加下一个单词的话，这里应该减回来，因为这一行只能装1个单词
                    curLen--;
                }

                while (curLen<maxWidth){
                    sb.append(" ");
                    curLen++;
                }
            }else{//如果有多个单词的话，那么就不断遍历al里的单词，不停的把除了倒数第一个单词之外的单词的后面都append空格
                if(i>=words.length){//最后一行了，就不是空格平均分配了，而是正常左缩紧然后append空格在后面

                    for(int j=0;j<al.size();j++){
                        sb.append(al.get(j));
                        if(j!=al.size()-1){
                            sb.append(" ");
                            wordslen++;
                        }
                    }
                    while (wordslen<maxWidth){
                        sb.append(" ");
                        wordslen++;
                    }
                }else{//空格按从前到后append到这行的除了最后一个单词的单词上，用单词的净长度wordslen去判断
                    while (wordslen<maxWidth){
                        for(int j=0;j<al.size()-1;j++){
                            String s=al.get(j)+" ";
                            al.set(j,s);
                            wordslen++;
                            if (wordslen>=maxWidth){
                                break;
                            }
                        }
                    }
                    for(String s:al){
                        sb.append(s);
                    }
                }

            }

            rs.add(sb.toString());
        }
        return rs;
    }
}
