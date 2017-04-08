/**
 * Created by 502575560 on 7/7/16.
 */
public class ReverseWordsinaStringII {
    /*
    题目就是ReverseWordsinaString只不过是只能in place换了,而且输入参数是char[]
    Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

    The input string does not contain leading or trailing spaces and the words are always separated by a single space.

    For example,
    Given s = "the sky is blue",
    return "blue is sky the".

    Could you do it in-place without allocating extra space?
    */

    public static void main(String[] args){
        char[] ch={'a','b','c',' ','d','e','f',' ','k','j'};
        reverseWords   (ch);
        System.out.println(new String(ch));
    }

    //http://www.cnblogs.com/EdwardLiu/p/4306561.html  他的代码好像是不对的,有点错
    //http://blog.csdn.net/jeffery1982/article/details/43924959
    public static void reverseWords(char[] s) {
        if(s==null||s.length==0){
            return;
        }
        int i=0;
        int j=s.length-1;
        while(i<j){
            char temp=s[i];
            s[i]=s[j];
            s[j]=temp;
            i++;
            j--;
        }
        j=0;
        for(i=0;i<s.length;i++){
           if(s[i]==' '){
               reverse(j,i-1,s);
               j=i+1;
               continue;
           }
            if(i==s.length-1){//这里有点conner case要注意
                reverse(j,i,s);
            }
        }

    }
    public static void reverse(int b,int e,char[] s){
        while(b<=e){
            char temp=s[b];
            s[b]=s[e];
            s[e]=temp;
            b++;
            e--;
        }
    }

}
