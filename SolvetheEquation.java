/**
 * Created by yufengzhu on 8/30/18.
 */
public class SolvetheEquation {
    public static void main(String[] a){
        SolvetheEquation se=new SolvetheEquation();
        se.solveEquation("x+5-3+x=6+x-2");
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

}
