import java.util.*;

/**
 * Created by 502575560 on 11/6/16.
 */
public class IntersectionofTwoArraysII {
    //自己想的,一次过,就是用2个map,分别记录2个数组出现元素及其个数,然后遍历这两个map其中的一个,看是否也在另一个map中存在,是的话就取得这个元素在
    //两个map中出现次数小的那个,放入reslut里
    //网上很多都是sort再二分法或者2pointer的,貌似比较快
    //https://discuss.leetcode.com/topic/65332/java-3ms
    //https://discuss.leetcode.com/topic/59997/java-3ms-beats-95-33-using-binary-search 这个二分法不太看得懂
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || Math.min(nums1.length, nums2.length) == 0){
            return new int[]{};
        }
        HashMap<Integer,Integer> map1=new HashMap<>();
        HashMap<Integer,Integer> map2=new HashMap<>();
        for(int i:nums1){
            if(map1.containsKey(i)){
                map1.put(i,map1.get(i)+1);
            }else{
                map1.put(i,1);
            }
        }
        for (int i:nums2){
            if(map2.containsKey(i)){
                map2.put(i,map2.get(i)+1);
            }else{
                map2.put(i,1);
            }
        }
        ArrayList<Integer> al=new ArrayList<>();
        Iterator<Integer> it;
        if(map1.size()>=map2.size()){
            it=map2.keySet().iterator();
        }else{
            it=map1.keySet().iterator();
        }

        while (it.hasNext()){
            int key=it.next();
            if(map1.containsKey(key)&&map2.containsKey(key)){
                int min=Math.min(map1.get(key),map2.get(key));
                for(int i=0;i<min;i++){
                    al.add(key);
                }

            }
        }
        int[] rs=new int[al.size()];
        for(int i=0;i<rs.length;i++){
            rs[i]=al.get(i);
        }
        return rs;
    }
//8/15/2021暴力法就是用map记录第一个数组数字出现的次数，便利nums2，遇到map有的就加入结果，并且把map的对应数字的value--
    public int[] intersect2(int[] nums1, int[] nums2) {
        List<Integer> rs=new ArrayList<>();
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<nums1.length;i++){
            if(!map.containsKey(nums1[i])){
                map.put(nums1[i],1);
            }else{
                map.put(nums1[i],map.get(nums1[i])+1);
            }
        }
        for (int i:nums2){
            if (map.containsKey(i)&&map.get(i)>0){
                rs.add(i);
                map.put(i,map.get(i)-1);
                if (map.get(i)==0){
                    map.remove(i);
                }
            }
        }
        int[] rss=new int[rs.size()];
        for(int i=0;i<rs.size();i++){
            rss[i]=rs.get(i);
        }
        return rss;
    }
//再写个sort的方法的
    public int[] intersect3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> rs=new ArrayList<>();
        int i=0;
        int j=0;
        while (i<nums1.length&&j<nums2.length){
            if (nums1[i]==nums2[j]){
                rs.add(nums1[i]);
                i++;
                j++;
            }else if(nums1[i]>nums1[j]){
                j++;
            }else {
                i++;
            }
        }
        int[] rss=new int[rs.size()];
        for(i=0;i<rs.size();i++){
            rss[i]=rs.get(i);
        }
        return rss;
    }
}
