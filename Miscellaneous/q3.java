package Miscellaneous;

/**
 * Created by yufengzhu on 2/13/18.
 */
public class q3 {
    public static void main(String[] args){
        System.out.println(removeLastOccurrence("aaaabaaa","aaa"));
    }
    public static String removeLastOccurrence(String haystack, String needle){
        if(haystack==null||needle==null){
            return null;
        }
        int index=-1;
        for(int i=0;i<haystack.length();i++){
            String s=haystack.substring(i);
            if(match(s,needle)){
                index=i;
            }
        }
        if(index==-1){
            return haystack;
        }else{
            String rs1=haystack.substring(0,index); //schoolbook
            String rs2=haystack.substring(index+needle.length());
            return rs1+rs2;
        }
    }
    public static boolean match(String s1,String s2){
        if(s1.length()<s2.length()){
            return false;
        }
        for(int i=0;i<s2.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
