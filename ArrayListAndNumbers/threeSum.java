package ArrayListAndNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
//九章第二轮,妈的两个while放错地方了，略过相同的第一个数，还是写的不太好
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> rs=new ArrayList<>();
        if(nums.length<=2){
            return rs;
        }
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i>0&&nums[i]==nums[i-1]){//这里也忘了，会有重复答案
                continue;
            }
            int b=i+1;
            int e=nums.length-1;
            while (b<e){
                int cur=nums[i]+nums[b]+nums[e];
                if(cur==0){
                    ArrayList<Integer> al=new ArrayList<>();
                    al.add(nums[i]);
                    al.add(nums[b]);
                    al.add(nums[e]);
                    rs.add(al);
                    while (e>b&&nums[e]==nums[e-1]){//这个while和上次的while不同，因为是发生在e--前面的，所以写的也不同
                        e--;
                    }
                    while (b<e&&nums[b]==nums[b+1]){
                        b++;
                    }
                    b++;//两端都要移动才有可能有下个解,否则解就是重复的
                    e--;
                }else if(cur>0){

                    e--;
                }else{

                    b++;
                }
            }
        }
        return rs;
    }
}
