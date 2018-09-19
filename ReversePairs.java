import java.util.ArrayList;
import java.util.List;

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
        rp.reversePairs3(new int[]{1,3,2,3,1});
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

    //9／5／2018，uber可能返回的是这些pair，再写一遍
    List<int[]> rsl;
    public List<int[]> reversePairs2(int[] nums) {
        rsl=new ArrayList<>();
        if(nums.length==0){
            return rsl;
        }
        helper(nums,0,nums.length-1);
        return rsl;
    }
    int[] helper(int[] nums,int begin,int end){
        if(begin==end){
            int[] rs=new int[1];
            rs[0]=nums[begin];
            return rs;
        }
        int mid=begin+(end-begin)/2;

        int[] left=helper(nums,begin,mid);
        int[] right=helper(nums,mid+1,end);

        return merge2(left,right);
    }
    int[] merge2(int[] left,int[] right){
        int[] rs=new int[left.length+right.length];
        int le=left.length-1;
        int re=right.length-1;
        int index=rs.length-1;

        while (le>=0&&re>=0){
            if(left[le]>2*right[re]){
                for(int i=re;i>=0;i--){
                    rsl.add(new int[]{left[le],right[i]});//这两个while就得分开写
                }
                le--;
            }else{
                re--;
            }
        }
        while (le>=0&&re>=0){

            if(left[le]<=right[re]){
                rs[index--]=right[re--];
            }else{
                rs[index--]=left[le--];
            }
        }
        while (le>=0&&index>=0){
            rs[index--]=left[le--];
        }
        while (re>=0&&index>=0){
            rs[index--]=right[re--];
        }
        return rs;
    }

    //9/16/2018,uber的要返回这些pair半背的,还算顺，这个应该是对的，上一个不知道

    public List<int[]> reversePairs3(int[] nums) {
        List<int[]> rs=new ArrayList<>();

        mergesort2(nums,0,nums.length-1,rs);
        return rs;
    }
    int[] mergesort2(int[] nums,int b,int e,List<int[]> rs){
        if(b>e){
            return new int[0];
        }
        if(b==e){
            return new int[]{nums[e]};
        }
        int m=b+(e-b)/2;
        int[] left=mergesort2(nums,b,m,rs);
        int[] right=mergesort2(nums,m+1,e,rs);
        return merge3(left,right,rs);
    }
    int[] merge3(int[] left,int[] right,List<int[]> rs){
        int b1=left.length-1;
        int b2=right.length-1;
        while (b1>=0&&b2>=0){
            if(left[b1]>2*right[b2]){
                for(int i=b2;i>=0;i--){
                    rs.add(new int[]{left[b1],right[i]});
                }
                b1--;
            }else{
                b2--;
            }

        }
        int[] rss=new int[left.length+right.length];
        b1=0;
        b2=0;
        int i=0;
        while (b1<left.length&&b2<right.length){
            if(left[b1]<=right[b2]){
                rss[i++]=left[b1++];
            }else{
                rss[i++]=right[b2++];
            }
        }
        while (b1<left.length){
            rss[i++]=left[b1++];
        }
        while (b2<right.length){
            rss[i++]=right[b2++];
        }
        return rss;
    }
}
