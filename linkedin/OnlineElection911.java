package linkedin;

import java.util.*;

public class OnlineElection911 {
    static void main() {

    }
    //差一点点想出来
    Set<Integer> set=new HashSet<>();
    int[] count;
    Map<Integer, Integer> map;
    public TopVotedCandidate(int[] persons, int[] times) {
        for (int i:persons){
            set.add(i);
        }
        int canditate=-1;
        int max=0;
        count=new int[set.size()];
        map= new TreeMap<>();
        for (int i=0;i<times.length;i++){
            count[persons[i]]++;
            if (count[persons[i]]>=max){
                canditate=persons[i];
                max=count[persons[i]];
            }
            map.put(times[i],canditate);
        }
    }

    public int q(int t) {

    }
}
