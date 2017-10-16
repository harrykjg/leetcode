package ArrayListAndNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 502575560 on 7/10/17.
 */
public class FourSum {
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

    //九章第二轮，懒得写了
//    public List<List<Integer>> fourSum2(int[] num, int target) {
//
//    }
}
