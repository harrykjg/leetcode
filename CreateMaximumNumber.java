import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 502575560 on 2/19/17.
 */
public class CreateMaximumNumber {
    public static void main(String[] args){
        CreateMaximumNumber cm=new CreateMaximumNumber();
//        int[] n1={8,9};
//        int[] n2={3,9};
//        int[] n1={3,4,6,5};
//        int[] n2={9,1,2,5,8,3};
        int[] n1={7,5,3,2,9,6,7,3,8,0,1,0,2,2,7,5,5,9,1,1,0,6,0,8};
        int [] n2={9,3,1,3,1,2,7,2,2};
        int[] rs=cm.maxNumber(n1,n2,33);
        for(int i:rs){
            System.out.print(i);
        }
    }
//http://bookshadow.com/weblog/2015/12/24/leetcode-create-maximum-number/   思路看这个
    //http://www.cnblogs.com/EdwardLiu/p/5094248.html    代码参考这个清楚一点
    //https://segmentfault.com/a/1190000007293431
    //http://blog.csdn.net/yzhang6_10/article/details/51367533

//    long max=Integer.MIN_VALUE;//这里没法用一个max来纪录这个数组所代表的值,就算用long也会越界
//    ArrayList<Integer> rs;
    public int[] maxNumber(int[] n1, int[] n2, int k) {
        int[] rs=new int[k];

        if(n1.length==0&&n2.length==0&&n1.length+n2.length<k){
            return rs;
        }
        int[] temprs=new int[k];
        for(int i=0;i<=k;i++){
            if(n1.length<i||n2.length<k-i){//这个if判断容易漏,漏了就不对了,比如k=33那个例子,如果我知道了n1取3个的话那么n2肯定不够长度取31个,所以就直接不从n1取3个了
                continue;
            }
            ArrayList<Integer> a1=pick(n1,i);
            ArrayList<Integer> a2=pick(n2,k-i);
            temprs= merge(a1,a2);//得出的temprs和原rs比较,从左到右比,只要某一位数大于那个位的数,就说明这个数比较大
            if(compare(temprs,rs)){
                rs=temprs;
            }
        }
        return rs;
    }
     private ArrayList<Integer> pick(int[] a, int n){
         ArrayList<Integer> al=new ArrayList<>();
         if(a.length<n||n==0){
             return al;
         }

         int i=0;
         int index=0;
         int l=a.length;
         while (n>0){
             int curmax=Integer.MIN_VALUE;
             while(i<l&&l-i>=n){
                if(a[i]>curmax){
                    index=i;
                    curmax=a[i];
                }
                 i++;
             }
             if(l-index<n){
                 break;
             }
             al.add(curmax);
             i=index+1;
             n--;
         }
         return al;
     }
    private int[] merge(ArrayList<Integer> a1,ArrayList<Integer> a2){
        int[] rs=new int[a1.size()+a2.size()];
        if(a1.size()==0){
            for(int i=a2.size()-1;i>=0;i--){
              rs[i]=a2.get(i);
            }
            return rs;
        }
        if(a2.size()==0){
            for(int i=a1.size()-1;i>=0;i--){
                rs[i]=a1.get(i);
            }
            return rs;
        }
        int i=0;
        int j=0;
        int l1=a1.size();
        int l2=a2.size();
        int index=0;
        while(i<l1&j<l2){//合并两个数组,因为每一位都要选,所以就是二选一而已.
            boolean picka1=compare(a1,i,a2,j);
            if(picka1){
                rs[index]=a1.get(i);
                index++;
                i++;
                continue;
            }
            rs[index]=a2.get(j);
            index++;
            j++;
        }
        while (i<l1){
            rs[index]=a1.get(i);
            i++;
            index++;
        }
        while (j<l2){
            rs[index]=a2.get(j);
            j++;
            index++;
        }
        return rs;
    }
    private boolean compare(ArrayList<Integer> a1,int i1,ArrayList<Integer> a2,int i2){
        while (i1<a1.size()&&i2<a2.size()&&a1.get(i1)==a2.get(i2)){
            i1++;
            i2++;
        }
        if(i1==a1.size()){
            return false;
        }
        if(i2==a2.size()){
            return true;
        }
        return a1.get(i1)>a2.get(i2);//其实这里如过他们两个数相等的话,取谁应该都一样,所以这里时>或者>=还有上面的return false和true对调应该都可以,
        //这里与之前我写的老代码中的比较不同,那里两个数一样的话取哪一个会影响到下一个可以取的数,但是这里的两个数组都要取完,这里不太好想
    }

    private boolean compare(int[] a1,int[] a2){
        int i=0;
        int j=0;
        while (i<a1.length&&j<a2.length&&a1[i]==a2[j]){
            i++;
            j++;
        }
        if(i==a1.length){
            return false;
        }
        if(j==a2.length){
            return true;
        }
        return a1[i]>a2[j];
    }



    //目测最多只能写暴力法,结果是写不出来的,比如n1={6,7};n2={6,0,4};k=5这个例子,没法判断到底是取n1的6还是n2的6,取得不同会导致不同的结果
    //所以要比n1和n2各自的下一位的大小递归比下去,那么这里就是要比7和0谁大,7大,那么第一个6就取n1的6.
    // 再如果n1={6,7,4,8};n2={6,7,4,9,8};k=9,一直比到n1的8 和n2的9,n2大,则第一个6就取n2的6
   //后来貌似还是写不下去,n1={3,9};n2={8,9},k=3,这个又没法比,n1={4,8,2,7};n2={3,4,7,6,3},k=5,选7的时候也没法比
//    public int[] maxNumber(int[] n1, int[] n2, int k) {
//        int[] rs=new int[k];
//        if(n1.length==0&&n2.length==0&&n1.length+n2.length<k){
//            return rs;
//        }
//        int l1=n1.length;
//        int l2=n2.length;
//        boolean pickedn1=false;
//        int prei=-1;
//        int prej=-1;
//        int i=0;
//        int j=0;
//        int count=0;
//        while(k>0){
//            int curmax=Integer.MIN_VALUE;
//            int curmaxIndex=0;
//            while(l1-(prei+1)+l2-(prej+1)>=k){//&&l1-i+l2-j>=k
//                if(i==l1){
//                    while(j<l2&&l2-j+l1-(prei+1)>=k){
//                        if(n2[j]>curmax){
//                            curmax=n2[j];
//                            curmaxIndex=j;
//                        }
//                        j++;
//                    }
//                    j=curmaxIndex;
//                    pickedn1=false;
//                    break;
//                }else if(j==l2){
//                    while(i<l1&&l1-i+l2-(prej+1)>=k){
//                        if(n1[i]>curmax){
//                            curmax=n1[i];
//                            curmaxIndex=i;
//                        }
//                        i++;
//                    }
//                    i=curmaxIndex;
//                    pickedn1=true;
//                    break;
//                }
//
//                if(i<l1&&n1[i]>n2[j]){
//                    curmax=n1[i];
//                    curmaxIndex=i;
//                    j++;//要找下一个j和这个i比
//                    pickedn1=true;
//                    continue;
//                }
//                if(n1[i]==n2[j]){
//                    boolean shouldPickN1=compare(n1,i,n2,j);
//                    if(shouldPickN1){
//                        curmax=n1[i];
//                        curmaxIndex=i;
//                        j++;//要找下一个j和这个i比
//                        pickedn1=true;
//                        continue;
//                    }
//                }
//
//                curmax=n2[j];
//                curmaxIndex=j;
//                i++;//要找下一个i和这个j比
//                pickedn1=false;
//            }
//            if(pickedn1){//如果选了n1的话,
//                j=prej==-1?0:prej+1;//那么j从最近的可取的开始
//                i++;
//                prei=i-1;
//            }else{
//                i=prei==-1?0:prei+1;
//                j++;
//                prej=j-1;
//            }
//            k--;
//            rs[count]=curmax;
//            count++;
//
//
//        }
//        return rs;
//    }
//    private boolean compare(int[] n1,int i1,int[] n2,int i2) {
//        if(i1>=n1.length){
//
//        }
//        while(i1<n1.length&&i2<n2.length){
//            if(n1[i1]>n2[i2]){
//                return true;
//            }
//            if(n1[i1]==n2[i2]){
//                i1++;
//                i2++;
//                continue;
//            }
//            return false;
//        }
//        return true;
//    }
}


