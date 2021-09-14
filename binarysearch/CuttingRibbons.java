package binarysearch;

public class CuttingRibbons {
    //8/19/2021 改了一次超时,然后改了好几次，原来是count那里超时了，以前都是一个一个减的，其实直接count+=cur/m就行了
    public int maxLength(int[] ribbons, int k) {
        long sum=0;
        for (int r:ribbons){
            sum+=r;
        }
        int e=(int)(sum/k);//确实是用sum/k，用max就错了
        int b=1;
        if (k>sum){
            return 0;
        }
        while (b<e){
            int m=(b+e)/2;
            if (valid(m,k,ribbons)){
                b=m+1;
            }else {
                e=m;
            }
        }
        if (valid(b,k,ribbons)){//开始忘了最后这个判断
            return b;
        }
        return b-1;
    }
    boolean valid(int m,int k,int[] ri){
        int count=0;
        for (int i=0;i<ri.length;i++){
            int cur=ri[i];
            count+=cur/m;//以前都是一个一个减的，其实直接count+=cur/m就行了
            if (count>=k){
                return true;
            }

        }
        return false;
    }
}
