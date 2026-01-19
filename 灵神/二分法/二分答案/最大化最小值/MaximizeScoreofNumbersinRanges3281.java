package 灵神.二分法.二分答案.最大化最小值;

import java.util.Arrays;

public class MaximizeScoreofNumbersinRanges3281 {
    static void main() {

    }

    //1/17/2026 自己想的
    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        int b=0;
        int e=start[start.length-1]-start[0]+d;//这里少了+d，比如【100，0】，d=100这个例子，答案是200而不是100
        while (b+1<e){
            int m=e-(e-b)/2;
            if(tooSmall(start,m,d)){//其实命名为good更好一些，good说明可以继续扩大m去尝试
                b=m;
            }else{
                e=m;
            }
        }
        if(tooSmall(start,e,d)){//说明e也是可以的
            return e;
        }
        return b;
    }
    //就是找每一个数和他前一个数的差值的最小的那个，如果都大于target的话，就说明target太小
    //那么每一个数又可以取的范围是[a[i],a[i]+d],看这个例子[2,6,13,13], d = 5，i=1时，假设target 是5,现在就是要看每一位元素和其他元素
    //的差值是否大于5，由于数组是排序的因此只看邻居的就行。2和6的差值是4不行，但是6可以加到7这样就行了，但是此时就要把6变成7和后面13再去比
    boolean tooSmall(int[] a,int target,int d){
//开始写的时候是要改变a【i】的值的，其实这里不需要改，只需要设一个pre就行了
        long pre=a[0];
        for(int i=1;i<a.length;i++){
           long next=pre+target;
           if((long)a[i]+d<next){
               return false;
           }
           pre=Math.max(next,a[i]);
        }
        return true;
    }
}
