package Advance7;

/**
 * Created by 502575560 on 8/10/17.
 */
public class NutsBoltsProblem {

    //http://blog.csdn.net/taotaoah/article/details/50987837 快速排序
    //https://codesolutiony.wordpress.com/2015/06/02/lintcode-nuts-bolts-problem/
    //https://segmentfault.com/a/1190000004444823
    //http://www.chenguanghe.com/lintcode-nuts-bolts-problem/
    //挺难想的,下面代码是参考别人的自己写的,大概思路是这样可能细节有bug,到lintcode上还不能pass
    public void sortNutsAndBolts(String[] nuts, String[] bolts) {
        if (nuts == null || nuts.length <= 1) {
            return;
        }
        sort(nuts,bolts,0,nuts.length-1);

    }
    void sort(String[] nuts,String[] bolts,int b,int e){
        if(b>=e){
            return;
        }

        int index=partitionBolt(bolts,nuts[b],b,e);
        partitionNut(nuts,bolts[index],b,e);//这个partitionNut得出的index就没用的
        sort(nuts,bolts,b,index-1);
        sort(nuts,bolts,index+1,e);

    }
    int partitionBolt(String[] bolts,String pivit,int b,int e){
        if(b>=e){
            return e;
        }
        int i=b;
        int j=e;
        while (i<j){
            while(compare(pivit,bolts[j])<=0){
                j--;
            }
            if(i<j){
                bolts[i]=bolts[j];
                i++;
            }
            while (compare(pivit,bolts[i])>=0){
               i++;
            }
            if(i<j){
                bolts[j]=bolts[i];
                j--;
            }
        }
        bolts[i]=pivit;
        return i;
    }
    int partitionNut(String[] nuts,String pivit,int b,int e){
        if(b>=e){
            return e;
        }
        int i=b;
        int j=e;
        while (i<j){
            while(compare(nuts[j],pivit)>=0){
                j--;
            }
            if(i<j){
                nuts[i]=nuts[j];
                i++;
            }
            while (compare(nuts[i],pivit)<=0){
                i++;
            }
            if(i<j){
                nuts[j]=nuts[i];
                j--;
            }
        }
        nuts[i]=pivit;
        return i;
    }
    int compare(String s1,String s2){//这个方法就是我写的用来使编译通过而已
        return 0;
    }
}
