import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BasicCalculatorIV {
    //9/8/2021 思路对不对不知道，把字符有对应value的弄出来，再basic calculator iii，得到一个stack，再从stack里取出结果集。懒得写了
    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        for (int i=0;i<evalints.length;i++){
            expression=expression.replace(evalvars[i],String.valueOf(evalints[i]));
        }
        List<String> rs=new ArrayList<>();
        Stack<String> st=helper(expression);
    }
    Stack<String> helper(String s){
        char[] ch=s.toCharArray();
        int i=0;

    }
}
