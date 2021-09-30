package SomeInterviews.facebook;

public class OneSwapSortedArray {
    //9/20/2021
    //    //https://www.1point3acres.com/bbs/thread-798915-1-1.html  sorrted只有2个元素被swap了，要还原
    //https://www.geeksforgeeks.org/sort-an-almost-sorted-array-where-only-two-elements-are-swapped/
    public static void main(String[] args){
        OneSwapSortedArray os=new OneSwapSortedArray();
        int[] a={3,3,10,5,7,4,20};
        int[] b={3,1,5,5};
        int[] c={1,5,3};
        int[] d={5,3,1};
        os.recover(a);
        os.recover(b);
        os.recover(c);
        os.recover(d);
    }
    //就是先从前往后找，比较i和i+1发现是不ascending的，则i是要被换的。再从最后往前比较j-1和j，从右往左第一个不是降序的，则j就是要换的
    public void recover(int[] a){
        int i=0;
        while (i+1<a.length){
            if (a[i]<=a[i+1]){
                i++;
                continue;
            }
            break;
        }
        int j=a.length-1;
        while (j-1>=0){
            if (a[j]>=a[j-1]){
                j--;
                continue;
            }
            break;
        }
        int temp=a[j];
        a[j]=a[i];
        a[i]=temp;
        for (int k:a){
            System.out.print(k+" ");
        }
        System.out.println();
    }
}
