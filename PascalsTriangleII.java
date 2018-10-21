import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 10/3/18.
 */
public class PascalsTriangleII {
    //以为要两个arraylist，结果用一个就行了,而且是从后往前搞才行
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> al=new ArrayList<>();
        al.add(1);
        if(rowIndex==0){
            return al;
        }
        al.add(1);

        if(rowIndex==1){
            return al;
        }

        for(int i=2;i<=rowIndex;i++){
            for(int j=al.size()-1;j>0;j--){
                al.set(j,al.get(j-1)+al.get(j));
            }
            al.add(1);
        }
        return al;
    }
}
