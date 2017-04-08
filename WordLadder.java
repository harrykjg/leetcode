

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by 502575560 on 7/2/16.
 */
public class WordLadder {
    public static void main(String[] args){
        Set<String> set=new HashSet<>();
        set.add("hot");
        set.add("dog");
        set.add("dot");
        System.out.print(ladderLength("hot","dog",set));
    }

    public static int ladderLength(String begin, String end, Set<String> set) {
         if(begin.equals(end)){
             return 2;
         }
        Queue<String> q=new LinkedList<>();
        q.add(begin);
        int count1=1;
        int count2=0;
        int rs=1;
        while(!q.isEmpty()){
            String s=q.poll();
            count1--;

            for(int i=0;i<begin.length();i++){
                char[] ch=s.toCharArray();
                for(int j=0;j<26;j++){
                    if(ch[i]==(char)(j+'a')){
                        continue;
                    }
                    ch[i]=(char)(j+'a');
                    String temp=new String(ch);
                    if(temp.equals(end)){
                        return rs+1;
                    }
                    if(set.contains(temp)){
                        q.add(temp);
                        set.remove(temp);
                        count2++;
                    }

                }
                if(count1==0){
                    count1=count2;
                    rs++;
                    count2=0;
                }
            }
        }
        return 0;


    }

}
