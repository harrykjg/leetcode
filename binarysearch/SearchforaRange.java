package binarysearch;

/**
 * Created by 502575560 on 8/20/17.
 */
public class SearchforaRange {
    public static void main(String[] args){
        int[] a={2,2};
        System.out.println(searchRange(a,2));
    }
    //就是两个二分法,一个找target起点,一个找终点,就是细节改了好几次,小于还是小于等于,b和e的处理
    //用模版
    public static int[] searchRange(int[] A, int target) {
        // write your code here
        int[] rs=new int[2];
        rs[0]=-1;
        rs[1]=-1;
        if(A.length==0){
            return rs;
        }
        int b=0;
        int e=A.length-1;
        int mid=0;
        while (b+1<e){
            mid=b+(e-b)/2;
            if(A[mid-1]<target&&A[mid]==target){
                rs[0]=mid;
                break;
            }
            if(A[mid]<target){
                b=mid;
            }else{
                e=mid;
            }
        }
        if(rs[0]==-1&&A[b]==target){
            rs[0]=b;
        }else if(rs[0]==-1&&A[e]==target){
            rs[0]=e;
        }

        b=0;
        e=A.length-1;
        mid=0;
        while (b+1<e){
            mid=b+(e-b)/2;
            if(A[mid]==target&&A[mid+1]>target){
                rs[1]=mid;
                break;
            }
            if(A[mid]<=target){//这里是<就错了
                b=mid;
            }else{
                e=mid;
            }
        }
        if(rs[1]==-1&&A[e]==target){//注意这里是往右扩展,所以是先看e符不符合,再看b
            rs[1]=e;
        }else if(rs[1]==-1&&A[b]==target){
            rs[1]=b;
        }
        return rs;

    }
}
