import java.util.ArrayList;

/**
 * Created by 502575560 on 10/23/16.
 */
public class ReverseVowelsofaString {
    public static void main(String[] args){
        ReverseVowelsofaString rv=new ReverseVowelsofaString();
        System.out.println(rv.reverseVowels("leetcode"));

    }

    //看了一下提示说用2 pointer,开始i,j下标没想清楚搞错了
        public String reverseVowels(String s) {
            if(s==null){
                return null;
            }
            if(s.length()==0){
                return "";
            }
            char[] ch=s.toCharArray();
            ArrayList<Integer> al=new ArrayList<>();
            for(int i=0;i<ch.length;i++){
                if(isVowel(ch[i])){
                    al.add(i);
                }
            }
            if(al.size()==0){
                return s;
            }
            int i=0;
            int j=al.size()-1;
            while(i<j){
                char temp=ch[al.get(i)];
                ch[al.get(i)]=ch[al.get(j)];
                ch[al.get(j)]=temp;
                i++;
                j--;
            }
            return new String(ch);

        }
        public boolean isVowel(char c){
            if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||c=='A'||c=='E'||c=='I'||c=='O'||c=='U'){
                return true;
            }
            return false;
        }

}
