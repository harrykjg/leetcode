import java.util.HashMap;

/**
 * Created by yufengzhu on 10/6/18.
 */
public class FindAnagramMappings {
    public static void main(String[] args){
        int[] a={12,28,46,50,50,50};
        int[] b={50,12,50,46,28,50};
        FindAnagramMappings fa=new FindAnagramMappings();
        fa.anagramMappings(a,b);

    }
    //妈的还写的不对,应该先记录B的index而不是先记录A的index
    public int[] anagramMappings(int[] A, int[] B) {
        if(A.length!=B.length){
            return new int[0];
        }
        HashMap<Integer,Integer> map=new HashMap<>();
        int[] rs=new int[A.length];
        for(int i=0;i<B.length;i++){
            map.put(B[i],i);

        }
        for(int i=0;i<A.length;i++){
            if(map.containsKey(A[i])){
                rs[i]=map.get(A[i]);
            }
        }
        return rs;
    }
}
