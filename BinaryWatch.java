import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 502575560 on 6/7/17.
 */
public class BinaryWatch {

    public static void main(String[] args){
        BinaryWatch bw=new BinaryWatch();
        System.out.println(bw.readBinaryWatch(2));
    }

    //自己想的,改了好多次,对于这种两层的回溯不熟
    //http://www.2cto.com/kf/201609/549008.html
    //http://blog.csdn.net/u013325815/article/details/52582210
    List<String> rs=new ArrayList<>();
    int[] hour={1,2,4,8};
    int[] min={1,2,4,8,16,32};
//    Set<Integer> hset=new HashSet<>();
//    Set<Integer> mset=new HashSet<>();
    public List<String> readBinaryWatch(int num) {
        if(num==0){
            rs.add("0:00");
            return rs;
        }
        for(int i=0;i<=num;i++){
            helper(i,num-i,num);
        }

        return rs;
    }
    public void helper(int hpick,int mpick,int num){
        ArrayList<Integer> hp=new ArrayList<>();
        ArrayList<Integer> mp=new ArrayList<>();
        HashSet<Integer> hset=new HashSet<>();
        HashSet<Integer> mset=new HashSet<>();
        helper1(0,0,0,hpick,hp,hset);
        helper2(0,0,0,mpick,mp,mset);
        for(int i=0;i<hp.size();i++){
            for(int j=0;j<mp.size();j++){
                String s=""+hp.get(i)+":";
                int m=mp.get(j);
                if(m<=9){
                    s+="0"+m;
                }else{
                    s+=""+m;
                }
                rs.add(s);
            }
        }

    }
    public void helper1(int value,int cur,int next,int p,ArrayList<Integer> rs,HashSet<Integer> set){
        if(cur>=p){
            if(!set.contains(value)&&value<12){
                rs.add(value);
                set.add(value);
            }
            return;
        }
        for(int i=next;i<hour.length;i++){
            helper1(value+hour[i],cur+1,i+1,p,rs,set);
        }

    }
    public void helper2(int value,int cur,int next,int p,ArrayList<Integer> rs,HashSet<Integer> set){
        if(cur>=p){
            if(!set.contains(value)&&value<60){
                rs.add(value);
                set.add(value);
            }
            return;
        }
        for(int i=next;i<min.length;i++){
            helper2(value+min[i],cur+1,i+1,p,rs,set);
        }
    }
}
