package SomeInterviews.karat;

import java.util.*;

public class FindMidClass {
    public static void main(String[] args){
        FindMidClass fm=new FindMidClass();
//        String[][] courses1 = {
//                {"Foundations of Computer Science", "Operating Systems"},
//                {"Data Structures", "Algorithms"},
//                {"Computer Networks", "Computer Architecture"},
//                {"Algorithms", "Foundations of Computer Science"},
//                {"Computer Architecture", "Data Structures"},
//                {"Software Design", "Computer Networks"}
//        };
        String[][] courses1 = {
                {"Logic", "COBOL"},
                {"Data Structures", "Algorithms"},
                {"Creative Writing", "Data Structures"},
                {"Algorithms", "COBOL"},
                {"Intro to Computer Science", "Data Structures"},
                {"Logic", "Compilers"},
                {"Data Structures", "Logic"},
                {"Creative Writing", "System Administration"},
                {"Databases", "System Administration"},
                {"Creative Writing", "Databases"},
                {"Intro to Computer Science", "Graphics"}};
        fm.find2(courses1);
    }
    //https://leetcode.com/discuss/interview-question/1431374/Robinhood-or-Karat-or-Course-Schedule-%2B-Find-mid-or-Aug-25-2021
    //这个链接可能是第二题，没有2个课程是share同一个祖先的。实际上就是弄出path(题目说只有一条)，然后path长度是奇数则弄出中间的那个，如果是偶数，则弄出中间2个的第一个。
    //简书那个应该是第三题，dfs也应该行，就是某个课（prequestie）他的neibour就不是一个了，而是个list.
    public void find(String[][] courses){
        HashMap<String, List<String>> map=new HashMap<>();
        HashSet<String> set=new HashSet<>();
        for (String[] c:courses){
            if (!map.containsKey(c[0])){
                map.put(c[0],new ArrayList<>());
            }
            map.get(c[0]).add(c[1]);
            set.add(c[1]);
        }
        List<String> al=new ArrayList<>();
        List<String> rs=new ArrayList<>();
        for (String s:map.keySet()){//这里注意要找出起点的那个string做一次dfs就行了，那么起点那个肯定就是在上面填map的时候没有被当作邻居c[1]的点
            if (set.contains(s)){
                continue;
            }
            al.add(s);
            dfs(s,al,map,rs);
            al.remove(al.size()-1) ;
        }
        for (String s:rs){
            System.out.println(s);
        }
    }
    void dfs(String cur,List<String> al,HashMap<String,List<String>> map,List<String> rs){
        if (!map.containsKey(cur)){
            int len=al.size();
            if (len%2==0){
                rs.add(al.get(len/2-1));
            }else {
                rs.add(al.get(len/2));
            }
            return;
        }
        for (String nei:map.get(cur)){
            al.add(nei);
            dfs(nei,al,map,rs);
            al.remove(al.size()-1);
        }
    }

    //这里写一下有多条path，简书上面的情况.也是几乎差不多，就是result改成set而已
    public void find2(String[][] courses){
        HashMap<String, List<String>> map=new HashMap<>();
        HashSet<String> set=new HashSet<>();
        for (String[] c:courses){
            if (!map.containsKey(c[0])){
                map.put(c[0],new ArrayList<>());
            }
            map.get(c[0]).add(c[1]);
            set.add(c[1]);
        }
        List<String> al=new ArrayList<>();
        HashSet<String> rs=new HashSet<>();
        for (String s:map.keySet()){//这里注意要找出起点的那个string做一次dfs就行了，那么起点那个肯定就是在上面填map的时候没有被当作邻居c[1]的点
            if (set.contains(s)){
                continue;
            }
            al.add(s);
            dfs2(s,al,map,rs);
            al.remove(al.size()-1) ;
        }
        for (String s:rs){
            System.out.println(s);
        }
    }
    void dfs2(String cur,List<String> al,HashMap<String,List<String>> map,HashSet<String> rs){
        if (!map.containsKey(cur)){
            int len=al.size();
            if (len%2==0){
                rs.add(al.get(len/2-1));
            }else {
                rs.add(al.get(len/2));
            }
            return;
        }
        for (String nei:map.get(cur)){
            al.add(nei);
            dfs2(nei,al,map,rs);
            al.remove(al.size()-1);
        }
    }
}
