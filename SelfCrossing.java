/**
 * Created by 502575560 on 4/23/17.
 */
public class SelfCrossing {

    public static void main(String[] args){
        int[]n={3,3,3,2,1,1};
        System.out.println(SelfCrossing.isSelfCrossing(n));
    }
    //自己还是做不出来,看答案貌似不是太难,主要是画图分析找规律,只有那几种情况分类好了就好,我这里条件写的比较乱
    //http://www.cnblogs.com/grandyang/p/5216856.html
    public static boolean isSelfCrossing(int[] x) {
        if(x.length<=2){
            return false;
        }
        for(int i=2;i<x.length;i++){
            if(x[i-1]==0&&x[i]!=0){//上一条边长度是0而现在这条不是0则重合了
                return true;
            }
            if(i-3>=0&&x[i-3]>=x[i-1]&&x[i]>=x[i-2]){//文中第一类
                return true;
            }
            if(i-4>=0&&x[i-3]==x[i-1]&&x[i-4]+x[i]>=x[i-2]){//文中所说第二类
                return true;
            }
            if(i-5>=0){//文中第三类,开始下面的条件和文中不一样,后来改了一下
                if(x[i-2]>=x[i-4]&&(x[i-1])>=x[i-3]-x[i-5]&&x[i-1]<=x[i-3]&&x[i]>=x[i-2]-x[i-4]){
                    return true;
                }

            }
        }
        return false;
    }
}
