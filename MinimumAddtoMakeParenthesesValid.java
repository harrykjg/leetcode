public class MinimumAddtoMakeParenthesesValid {
    //8/16/2021 是1541. Minimum Insertions to Balance a Parentheses String的基础版
    public int minAddToMakeValid(String s) {
        int left=0;
        int rs=0;
        char[] ch=s.toCharArray();
        if (s.length()==0){
            return 0;
        }
        for (int i=0;i<s.length();i++){//意思是边走边记录左括号，如（（（果结束的时候就返回left就对了，如果遇到右括号并且left大于0说明可以抵消.如果left
            if (s.charAt(i)=='('){//已经等于0了，则说明结果要多加左括号，用rs记着。最后返回rs+left
                left++;
            }
            if (s.charAt(i)==')'){
                if (left>0){
                    left--;
                }else {
                    rs++;
                }
            }
        }
        return rs+left;
    }
}
