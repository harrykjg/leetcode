\import java.util.ArrayList;

public class BuildingsWithanOceanView {
    //8/16/2021 从右往左走一次过
    public int[] findBuildings(int[] heights) {
        ArrayList<Integer> al=new ArrayList<>();
        int max=0;
        for(int i=heights.length-1;i>=0;i--){
            if(heights[i]>max){
                al.add(i);
            }
            max=Math.max(max,heights[i]);
        }
        int[] rs=new int[al.size()];
        for(int i=0;i<al.size();i++){
            rs[i]=al.get(al.size()-1-i);
        }
        return rs;
    }
}
