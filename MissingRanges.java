import java.util.*;

/**
 * Created by yufengzhu on 7/5/18.
 */
public class MissingRanges {
    public static void main(String[] args){
        MissingRanges mr=new MissingRanges();
        int[] aa={0,1,3,50,75};
        mr.findMissingRanges3(aa,0,99);

    }
    //7/18/2019 模拟的tle
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        ArrayList<String> rs=new ArrayList<>();
        Set<Integer> set=new HashSet<>();
        for (int n:nums){
            set.add(n);
        }
        int i=lower;
        while (i<=upper){
            if (set.contains(i)){
                i++;
                continue;
            }
            String s="";
            s+=i;
            if (set.contains(i+1)||i==upper){
                rs.add(s);
                i++;
            }else {
                while (i<upper){
                    if (set.contains(i+1)||i==upper){
                        break;
                    }
                    i++;
                }
                s+="->"+i;
                rs.add(s);
                i++;
            }
        }
        return rs;
    }
    //写的分类讨论，很麻烦但是过了
    public List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        ArrayList<String> rs=new ArrayList<>();
        Arrays.sort(nums);
        Set<Integer> set=new TreeSet<>();
        for (int n:nums){
            set.add(n);
        }
        if (set.isEmpty()){
            String s="";
            s+=lower;
            if (upper!=lower){
                s+="->"+upper;
            }
            rs.add(s);
            return rs;
        }
        if (nums.length==1){
            String s="";
            if (set.contains(lower)&&lower+1<=upper){
                s+=lower+1;
                if (lower+1<upper){
                    s+="->"+upper;
                }
                rs.add(s);
                return rs;
            }else if (set.contains(upper)&&upper-1>=lower){
                s+=lower;
                if (lower+1<upper){
                    s+="->"+(upper-1);
                }
                rs.add(s);
                return rs;
            }else if(lower+1<=upper&&upper-1>=lower) {//这个唯一的数在中间
                s+=lower;
                if (lower+1<nums[0]){
                    s+="->"+(nums[0]-1);
                }
                rs.add(s);
                s="";
                s+=nums[0]+1;
                if (nums[0]+1<upper) {
                    s+="->"+upper;
                }
                rs.add(s);
                return rs;
            }
        }
        if (nums[0]>lower){
            String s="";
            s+=lower;
            if (lower+1<nums[0]){
                s+="->"+(nums[0]-1);
            }
            rs.add(s);
        }
        for (int i=1;i<nums.length;i++){
            String s="";
            int pre=nums[i-1];
            int cur=nums[i];
            if (cur-pre==1){
                continue;
            }else if (cur-pre==2){
                s+=nums[i]-1;
                rs.add(s);
            } else {
                s+=pre+1;
                s+="->"+(cur-1);
                rs.add(s);
            }
        }
        if (nums[nums.length-1]!=upper){
            String s="";
            s+=nums[nums.length-1]+1;
            if (nums[nums.length-1]+1<upper) {
                s+="->"+upper;
            }
            rs.add(s);
        }
        return rs;
    }

    //8/22/2021  改了几次，这样写顺一些。
    public List<String> findMissingRanges3(int[] nums, int lower, int upper) {
        List<String> rs=new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            if (lower>=nums[i]){
                lower=nums[i]+1;
                continue;
            }
            if (nums[i]-lower==1){
                rs.add(String.valueOf(lower));
                lower=nums[i]+1;
            }else {
                String s=lower+"->"+(nums[i]-1);
                rs.add(s);
                lower=nums[i]+1;
            }
        }
        if (lower<=upper){
            if (lower==upper){
                rs.add(String.valueOf(lower));
            }else {
                String s=lower+"->"+upper;
                rs.add(s);
            }
        }
        return rs;
    }
}
