package ArrayListAndNumbers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by 502575560 on 7/10/17.
 */
public class threeSum {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        threeSum ts=new threeSum();
        int[] num={-2,0,1,1,2};

        ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
        al=ts.threeSum(num);
        for(ArrayList<Integer> a:al){
            for(int i:a){
                System.out.print(i);

            }
            System.out.println();
        }
    }
    //写的不好,改了好几次
    public ArrayList<ArrayList<Integer>> threeSum(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> rs=new ArrayList<>();
        if(nums.length<=2){
            return rs;
        }
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int a=i+1;
            int b=nums.length-1;
            while (a<b){
                if(nums[i]+nums[a]+nums[b]==0){
                    ArrayList<Integer> al=new ArrayList<>();
                    al.add(nums[i]);
                    al.add(nums[a]);
                    al.add(nums[b]);
                    rs.add(al);
                    a++;
                    b--;
                    while (a<nums.length&&nums[a]==nums[a-1]){//还是这里忘了要去掉相邻一样的元素,否则就会有重复,上面for循环那里也要略过重复的,而且
                        a++;                                //要先a++,b--后再看,否则a<b这个while循环就变死循环了
                    }
                    while (b>i&&nums[b+1]==nums[b]){
                        b--;
                    }


                }else if(nums[i]+nums[a]+nums[b]<0){
                    a++;
                }else{
                    b--;
                }
            }
        }
        return rs;
    }
}
