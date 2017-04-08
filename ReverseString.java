/**
 * Created by 502575560 on 10/23/16.
 */
public class ReverseString {
    //又一次过
    public String reverseString(String s) {
        if(s==null){
            return null;
        }
        if(s.length()==0){
            return "";
        }
        char[] ch=s.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(int i=ch.length-1;i>=0;i--){
            sb.append(ch[i]);
        }
        return sb.toString();

    }
}
