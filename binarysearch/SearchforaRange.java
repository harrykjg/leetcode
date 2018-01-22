package binarysearch;

/**
 * Created by 502575560 on 8/20/17.
 */
public class SearchforaRange {
    public static void main(String[] args){
        int[] a={1};
        System.out.println(searchRange2(a,8));
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
//1/21/2018,九章第二轮，用模版,不难但是细节还是挺多，改了好几次
    public static int[] searchRange2(int[] A, int target) {
        if(A.length==0){
            return new int[]{-1,-1};
        }
        int b=0;
        int e=A.length-1;
        int m=0;
        int[] rs=new int[2];
        boolean findleft=false;
        boolean findright=false;
        while(b+1<e){
            m=b+(e-b)/2;
            if(A[m]==target){
                if(A[m-1]!=A[m]){
                    rs[0]=m;
                    findleft=true;
                    break;
                }
                e=m;
                continue;
            }
            if(A[m]>target){
                e=m;
            }else{
                b=m;
            }
        }
        if(!findleft){
            if(A[b]==target){
                rs[0]=b;
                findleft=true;
            }else if(A[e]==target){
                rs[0]=e;
                findleft=true;
            }

        }
        b=rs[0];
        e=A.length-1;
        while(b+1<e){
            m=b+(e-b)/2;
            if(A[m]==target){
                if(A[m+1]!=A[m]){
                    rs[1]=m;
                    findright=true;
                    break;
                }
                b=m;
                continue;
            }
            if(A[m]>target){
                e=m;
            }else{
                b=m;
            }
        }
        if(!findright){
            if(A[e]==target){
                rs[1]=e;
                findright=true;
            }else if(A[b]==target){
                rs[1]=b;
                findright=true;
            }

        }
        if(!findleft){
            return new int[]{-1,-1};
        }
        return rs;

    }
}
