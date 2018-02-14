package Miscellaneous;

/**
 * Created by yufengzhu on 2/13/18.
 */
public class q1 {
    public static void main(String[] args){
       System.out.println(getAnswer("2 * 408"));
    }
    public static int getAnswer(String problem){
        if(problem==null){
            return -1;
        }
        problem=problem.trim();
        String s1="";
        char opt=0;
        String s2="";
        boolean found=false;
        for(int i=0;i<problem.length();i++){
            if(!found&&problem.charAt(i)>='0'&&problem.charAt(i)<='9'){
                s1=s1+problem.charAt(i);
            }
            else if(!found&&(problem.charAt(i)=='+'||problem.charAt(i)=='-'||problem.charAt(i)=='*'||problem.charAt(i)=='/')){
                found=true;
                opt=problem.charAt(i);
            }
            else if(found&&problem.charAt(i)>='0'&&problem.charAt(i)<='9'){
                s2=s2+=problem.charAt(i);
            }
        }
        if(opt=='+'){
            return Integer.parseInt(s1)+Integer.parseInt(s2);
        }
        if(opt=='-'){
            return Integer.parseInt(s1)-Integer.parseInt(s2);
        }
        if(opt=='*'){
            return Integer.parseInt(s1)*Integer.parseInt(s2);
        }
        if(opt=='/'){
            return Integer.parseInt(s1)/Integer.parseInt(s2);
        }
        return -1;

    }
}
