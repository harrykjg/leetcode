package linkedin;

import java.util.HashSet;
import java.util.Set;

public class BulbSwitcher319 {
    static void main() {

    }
    //还是没想到答案是sqrt n，自己想的超时。，找规律发现可知第二行，2，4，6，8、、是off的，第三行 把3关了，到6，发现6在之前已经在set里
    //因此又遇到6说明6打开，把6从set里移除，。。就是维护一个set把关掉的位置都放进去，然后每一行找要关的位置，如果set已经存在就说明关了的要打开，
    //从set移除，这样答案就是n-set。size
    public int bulbSwitch(int n) {
        Set<Integer> set=new HashSet<>();

        int level=2;
        while (level<=n){
            for (int i=level;i<=n;i+=level){
                if(!set.contains(i)){
                    set.add(i);
                }else{
                    set.remove(i);
                }
            }
            level++;
        }
        return n-set.size();
    }
}
