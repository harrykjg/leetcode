import java.util.HashMap;
import java.util.Set;

/**
 * Created by 502575560 on 7/11/16.
 */
public class logInlogOut {
    public static void mian(String[] args){
        logInlogOut ln=new logInlogOut();
        struct1 s1=new struct1("abc",1.1,2.3);
        struct1 s2=new struct1("de",1.3,3.4);
        struct1[] ss={s1,s2};
        ln.printLog(ss);
    }
    //这题只会蠢的方法,就是先读出所有struct的begin和end,放入一个数组,sort,然后遍历这个数组,再一个for循环遍历所有struct取对比当前这个时间点
    //是否小于等于end且大于等于begin. 用map<double,set<struct>>去存结果,结果就是set的size
    public void printLog(struct1[] s){
        HashMap<Double,Set<struct1>> map=new HashMap<>();
        for(int i=0;i<s.length;i++){

        }


    }



}

 class struct1{
     String name;
     double in;
     double out;
     public struct1(String s,double d1,double d2){
         name=s;
         in=d1;
         out=d2;
     }
 }