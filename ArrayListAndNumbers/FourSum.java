package ArrayListAndNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 502575560 on 7/10/17.
 */
public class FourSum {
    public static void main(String[] args){
        FourSum fs=new FourSum();
        int[]a={0,0,0,0};
        fs.fourSum2(a,0);
    }
    //这里懒得写了，就是验证了一下以前写的go2方法里去掉了两个while循环也对的
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> al=new ArrayList<List<Integer>>();
        if(num.length<4){
            return al;
        }
        ArrayList<Integer> a=new ArrayList<Integer>();
        Arrays.sort(num);
        for(int i=0;i<num.length-3;i++){
            if(i>0&&num[i]==num[i-1]){
                continue;
            }
            a.add(num[i]);
            go2(a,i+1,num[i],num,al,target);
            a.remove(a.size()-1);
        }
        return al;
    }
    private void go2(ArrayList<Integer> a,int b,int cur,int[]num,
                     List<List<Integer>> al,int target){

        for(int i=b;i<num.length-2;i++){
            if(i>b&&num[i]==num[i-1]){
                continue;
            }
            a.add(num[i]);

            int cur2=cur+num[i];

            int left=i+1;
            int right=num.length-1;
            while(left<right){
                if(cur2+num[left]+num[right]==target){
                    a.add(num[left]);
                    a.add(num[right]);
                    al.add(new ArrayList<Integer>(a));
                    a.remove(a.size()-1);
                    a.remove(a.size()-1);
                    left++;
                    right--;

                    while(left<right&&num[left]==num[left-1]){
                        left++;
                    }
                    while(left<right&&num[right]==num[right+1]){
                        right--;
                    }
                    continue;
                }
                if(cur2+num[left]+num[right]>target){
                    right--;//oldleetcode里的代码这里还加了个while循环,其实不必要
                    continue;
                }
                if(cur2+num[left]+num[right]<target){
                    left++;

                    continue;
                }
            }
            a.remove(a.size()-1);
        }
    }

//    9/28/2018,想错了，应该从3sum开始想，而且3sum那个部分也没想好
    public List<List<Integer>> fourSum2(int[] num, int target) {
        List<List<Integer>> rs=new ArrayList<>();
        Arrays.sort(num);
        ArrayList<Integer> al=new ArrayList<>();
        for(int i=0;i<num.length;i++){
            if(i>0&&num[i]==num[i-1]){
                continue;
            }
            al.add(num[i]);
            helper(num[i],i+1,num.length-1,target,num,al,rs);
            al.remove(al.size()-1);
        }
        return rs;
    }
    void helper(int cur,int b,int e,int target,int[] nums,ArrayList<Integer> al,List<List<Integer>> rs){

        for(int i=b;i<nums.length-2;i++){
            if(i>b&&nums[i]==nums[i-1]){//这里少写了i>b这个条件，要注意！
                continue;
            }
            int bb=i+1;
            int ee=e;
            al.add(nums[i]);

            while (bb<ee){
                int c=cur+nums[i]+nums[bb]+nums[ee];
                if(c==target){
                    al.add(nums[bb]);
                    al.add(nums[ee]);
                    rs.add(new ArrayList<>(al));
                    al.remove(al.size()-1);
                    al.remove(al.size()-1);
                    bb++;
                    ee--;
                    while (bb<ee&&nums[bb-1]==nums[bb]){
                        bb++;
                    }
                    while (bb<ee&&nums[ee+1]==nums[ee]){
                        ee--;
                    }
                    continue;
                }
                if(c<target){
                    bb++;
                }else{
                    ee--;
                }
            }
            al.remove(al.size()-1);
        }
    }
}
