import java.util.*;

/**
 * Created by yufengzhu on 9/2/18.
 */
public class FindKClosestElements {
    //意思就是在这个数组里，找出k个最接近x的数字，这k个数是从小到大排序的，比如x是5，现在数组里有一个4，和一个6，k为1即只找一个，那么就选4，因为4比较小
    //自己想的就是binary search查x在数组中的位置，若不存在就得到一个应该插入的位置，再在这个位置左右两边分别起两个pointer，中心扩展去搜k个数，就算数组有duplicate也行,写起来有点麻烦
    //https://leetcode.com/problems/find-k-closest-elements/solution/ 第二和方法是直接找到数组中一个start位置，然后start到start+k这一段子序就是答案了，叼
    //https://blog.csdn.net/thesnowboy_2/article/details/77431886 他最后一个方法用了addfirst和addlast就省了后面一步sort了
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> rs=new ArrayList<>();
        if(arr.length==0){
            return rs;
        }
        int b=0;
        int e=arr.length-1;
        int mid=b+(e-b)/2;
        int index=-1;//先找到应该插入的位置，标记为index
        if(arr[0]>x){
            index=0;
        }
        if(arr[arr.length-1]<x){
            index=arr.length;//这里-1与否都accept了
        }
        if(index==-1){//说明插入位置在中间，用二分法，比如1，5，5，5，5，6这样的数组，有重复，找到任何一个5都行，反正是中心扩展去找的
            while (b+1<e){
                mid=b+(e-b)/2;
                if(arr[mid]==x){
                    break;
                }
                if(arr[mid]>x){
                    e=mid;
                }else{
                    b=mid;
                }
            }
            if(arr[mid]==x){
                index=mid;
            }else if(arr[b]==x){
                index=b;
            }else{
                index=e;
            }
        }
        int left=index-1;//开始想错了，left和right一定是index左边和右边的元素，其实要left=index-1，right=index，并且left=index，right=index+1不行，因为如果target数字不存在的话，而index已经是后面一个数字了，那样会错
        int right=index;
        while (rs.size()<k){
            if(right<arr.length&&left>=0){
                if(Math.abs(arr[right]-x)==Math.abs(arr[left]-x)){
                    rs.add(0,arr[left--]);
                }else if(Math.abs(arr[right]-x)<Math.abs(arr[left]-x)){
                    rs.add(rs.size(),arr[right++]);
                }else{
                    rs.add(0,arr[left--]);
                }
            }
            else if(right<arr.length){
                rs.add(rs.size(),arr[right++]);
            }else if(left>=0){
                rs.add(0,arr[left--]);
            }
        }
//        Collections.sort(rs);//因为上面的while用了addfirst和addlast就省了后面再sort了，但是leetcode的runtime反而更慢
        return rs;
    }
//8/21/2021没想到最优解，最优看不懂
    //https://leetcode.com/problems/find-k-closest-elements/discuss/202785/Very-simple-Java-O(n)-solution-using-two-pointers 看他的on的解不错，其实
    //还不如自己的想法就是二分法先找到该插入x的点，在左右扩张，那样是lonN+k还挺不好写的
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> rs=new ArrayList<>();
        int b=0;
        int e=arr.length-1;
        int index=-1;
        while (b<e){
            int m=(b+e)/2;
            if (arr[m]==x){
                index=m;
                break;
            }
            if (arr[m]>x){
                e=m;
            }else {
                b=m+1;
            }
        }
        if (index==-1){//这里还容易漏，如果直接找到x了就index直接用，否则才赋值b
            index=b;
        }
        int left=index-1;//这里也很噁心，一定是left=index-1开始，说明index这一点要不是就是x本身，要不是就是x的右边第一个数。然后left和index这2者需要比较谁小
        int right=index;//这个while循环条件也很恶心，想法应该是我要找到left和right中间夹着的元素（不包括left和right）的个数要等于k，因此我要不断扩left和right
        //知道right-left的值大于等于k，比如k=1，right=1，left=0，中间只有1-0-1=0个元素，所以不够，所以得进入循环取扩。最后面填结果集的时候也是只要left和right中间
        //的元素，不需要left和right本身
        while (right-left<=k){//这里不好想，总感觉可以在这里依靠左右向外扩的时候就加进结果集了，其实很多人都没这样做，可能是做不了，因此只能左右扩，记录left和right，
            if (left<0){//完了再遍历left到right加进结果集
                right++;
                continue;
            }
            if (right>=arr.length){
                left--;
                continue;
            }
            if (Math.abs(arr[index]-arr[left])>Math.abs(arr[index]-arr[right])){//左边严格大于右边的差值时，才取右边，若是等于就还是取左边
                right++;
            }else {
                left--;
            }
        }
        for (int i=left+1;i<right;i++){
            rs.add(arr[i]);
        }
        return rs;

    }

    //10/9/2021
    //https://leetcode.com/problems/find-k-closest-elements/discuss/202785/Very-simple-Java-O(n)-solution-using-two-pointers 学这个写法吧,
    //应该就是直接夹逼，复杂度应该比上面的慢
    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        int low=0;
        int high=arr.length-1;
        while(high-low>=k){
            if(Math.abs(arr[low]-x)>Math.abs(arr[high]-x)){
                low++;
            }else{
                high--;
            }
        }
        List<Integer> rs=new ArrayList<>();
        for (int i=low;i<=high;i++){
            rs.add(arr[i]);
        }
        return rs;
    }

}
