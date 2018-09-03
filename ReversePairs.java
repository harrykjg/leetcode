/**
 * Created by yufengzhu on 8/28/18.
 */
public class ReversePairs {
    //不会，mergesort那个还好理解一些，Binary index tree不好理解，segment tree也不好理解
    //http://www.cnblogs.com/grandyang/p/6657956.html
    //https://blog.csdn.net/crystal6918/article/details/53246730  这个是lintcode 的reverse pair 的解释，就是没有了2倍的条件，思想史一样的
    //https://leetcode.com/problems/reverse-pairs/discuss/97315/Java-merge-sort-solution-O(nlog(n))  他们这个其实也是把找符合条件的pair和排序分开的，他们直接用arrays.sort了没有写merge sort的实现
    public static void main(String[] args){
        ReversePairs rp=new ReversePairs();
        rp.reversePairs(new int[]{-5,-5});
    }
    int rs=0;
    public int reversePairs(int[] nums) {
        if(nums.length<=1){
            return 0;
        }
        mergesort(nums,0,nums.length-1);

        return rs;
    }
    int[] mergesort(int[] nums,int b,int e){

        if(b==e){
            return new int[]{nums[b]};
        }
        int mid=b+(e-b)/2;
        int[] left=mergesort(nums,b,mid);
        int[] right=mergesort(nums,mid+1,e);

        int[] m=merge(left,right);
        return m;
    }
    int[] merge(int[] left,int[] right){//merge这里也挺恶心的，其实算rs和merge是分开的，不是说一个while里的
        int[] m=new int[left.length+right.length];
        int i=left.length-1;
        int j=right.length-1;
        int index=m.length-1;
        int ii=i;
        int jj=j;
        while (ii>=0&&jj>=0){//这个while我试着和下面那个合并，不行，会很恶心，只能处理正数，负数如-5，-5的例子就不行了
            if(left[ii]>(long)2*right[jj]){//不加long就越界变成负数了
                rs+=jj+1;
                ii--;
            }else{
                jj--;
            }
        }


        while (i>=0&&j>=0){//lintcode 的那题merge的时候和一般的merge sort不同了，要从后往前填数字，这一题的话可能就是从前往后也行，没试
            if(left[i]<=right[j]){
                m[index]=right[j];
                j--;
            }else{
                m[index]=left[i];
                i--;
            }
            index--;
        }
        while (i>=0){
            m[index--]=left[i--];
        }
        while (j>=0){
            m[index--]=right[j--];
        }
        return m;
    }
}
