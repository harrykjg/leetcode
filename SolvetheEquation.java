import java.util.Stack;

/**
 * Created by yufengzhu on 8/30/18.
 */
public class SolvetheEquation {
    public static void main(String[] a){
        SolvetheEquation se=new SolvetheEquation();
//        se.solveEquation("x+5-3+x=6+x-2");
        System.out.print(se.solveEquation2("3-2-(2x-(3x+1)+2)+5+2y+x=3+1+x+y",2));
    }
    int x=0;
    int num=0;
    public String solveEquation(String equation) {//瞄了一下答案，发现可以把所有x放到左边，所有数字放到右边再计算，然后代码是自己想的，类似basic calculator

        String[] eq=equation.split("=");
        helper(eq[0],0);
        helper(eq[1],1);
        if(x==0){
            if(num==0){
                return "Infinite solutions";
            }
            return "No solution";
        }
        return "x="+num/x;
    }
    void helper(String s,int a){//a是判断这个string是等号左边或者右边的信号
        int rs=0;
        int xcount=0;
        char[] ch=s.toCharArray();
        int i=0;
        int sign=a==0?1:-1;//这个是最终这个string得出的x的个数和number的个数应该update到x和num上，如果说是1就代表是左边，x是正的，num是负的，否则相反
        int sign2=1;//这个是basic calculator那个符号

        while (i<ch.length){
            if(ch[i]=='-'){
                sign2=-1;
                i++;
                continue;
            }
            if(ch[i]=='+'){
                sign2=1;
                i++;
                continue;
            }
            if(ch[i]=='x'){
                xcount+=sign2;
                sign2=1;
                i++;
                continue;
            }
            int temp=0;
            while (i<ch.length&&Character.isDigit(ch[i])){
                temp=temp*10+ch[i]-'0';
                i++;
            }
            if(i<ch.length&&ch[i]=='x'){//判断2x这种情况，即数字后面跟的是x的
                xcount+=sign2*temp;
                sign2=1;
                i++;//注意只有是x才i++，数字后面不是x的话i就不用++了
            }else{
                rs+=sign2*temp;
                sign2=1;
            }

        }
        x+=sign*xcount;//x去左边，数字去右边
        num-=sign*rs;

    }
    //uber的变形题，解2x-(x-((3x+1)+2))+4=x+y,给你x值，求y，没有乘除
    int numX=0;
    int numY=0;//x放右边，y放左边
    int num2=0;
    public String solveEquation2(String equation,int x) {
        String[] eq=equation.split("=");
        helper2(eq[0],0);
        helper2(eq[1],1);

        if(numY==0){
            return "";
        }
        return String.valueOf((numX*x+num2)/numY);

    }
    void helper2(String s,int left){
        Stack<Integer> st=new Stack<>();
        int sign=1;
        int globle=left==0?-1:1;
        char[] ch=s.toCharArray();
        st.push(1);
        int i=0;
        while (i<ch.length){
            if(ch[i]=='+'){
                sign=1;
                i++;
                continue;
            }
            if(ch[i]=='-'){
                sign=-1;
                i++;
                continue;
            }
            if(ch[i]=='('){
                st.push(st.peek()*sign);
                sign=1;
                i++;
                continue;
            }
            if(ch[i]==')'){
                st.pop();
                sign=1;
                i++;
                continue;
            }
            int temp=0;
            while (i<ch.length&&Character.isDigit(ch[i])){
                temp=temp*10+ch[i]-'0';
                i++;
            }
            if(i<ch.length&&ch[i]=='x'){
                if(temp==0){//这里容易漏，用来处理temp为0的x和y的情况
                    temp=1;
                }
                numX+=temp*globle*st.peek()*sign;
                i++;
            }else if(i<ch.length&&ch[i]=='y'){
                if(temp==0){
                    temp=1;
                }
                numY-=temp*globle*st.peek()*sign;//这里x和y还要区别用+=还是-=
                i++;
            }else{
                num2+=temp*globle*st.peek()*sign;//这里就不用i++了

            }
        }
    }
}
