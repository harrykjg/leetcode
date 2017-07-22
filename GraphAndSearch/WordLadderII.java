package GraphAndSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by 502575560 on 7/14/17.
 */
public class WordLadderII {
    //还是很难
    //http://blog.csdn.net/perfect8886/article/details/19645691
//http://blog.csdn.net/linhuanmars/article/details/23071455/
//http://jixiangsanbao.wordpress.com/2014/08/03/word-ladder-ii/
//http://www.blogjava.net/menglee/archive/2014/01/02/408381.html
//http://blog.csdn.net/whuwangyi/article/details/21611433
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        List<List<String>> rs=new ArrayList<>();
        LinkedList<String> q=new LinkedList<>();
        q.offer(start);
        int count1=1;
        int count2=0;
        int len=start.length();
        int path=-1;
        ArrayList<String> al=new ArrayList<>();
//        al.add(start);
        while (!q.isEmpty()){
            String s=q.poll();
            al=new ArrayList<>(al);
            al.add(s);
            count1--;
            for(int i=0;i<len;i++){
                char[] ch=s.toCharArray();
                for(int j=0;j<26;j++){
                    ch[i]=(char)('a'+j);
                    String temp=new String(ch);
                    if(dict.contains(dict)&&temp.equals(end)){
                        path=al.size();
                        al.add(temp);
                        rs.add(new ArrayList<>(al));
                        al.remove(temp);
                    }
                    else if(dict.contains(dict)){
                        dict.remove(temp);
                        al=new ArrayList<>(al);
                        al.add(temp);
                        count2++;
                    }
                }

            }
            al.remove(al.size()-1);
            if(count1==0){
                if(path!=-1){
                    break;
                }
                count1=count2;
                count2=0;
            }
        }
        return rs;
    }
}
