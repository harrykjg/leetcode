package SomeInterviews.karat;

import java.util.*;

public class CourseOverlap {
    public static void main(String[] args){
        CourseOverlap co=new CourseOverlap();
        String[][] s={{"58","sd"},{"58","la"},{"94","ah"},{"94","os"},{"17","sd"},{"58","man"},{"58","eco"},{"17","la"},{"17","ps"},
                {"94","eco"},{"25","eco"}};
        co.find(s);

    }
    public void find(String[][] s1){
        HashMap<String, Set<String>> map=new HashMap<>();
        for (int i=0;i<s1.length;i++){
            String id=s1[i][0];
            if (!map.containsKey(id)){
                map.put(id,new HashSet<>());
            }
            map.get(id).add(s1[i][1]);
        }
        HashMap<String,Set<String>> rs=new HashMap<>();
        for (String s:map.keySet()){
            for (String s2:map.keySet()){
                if (s.equals(s2)){
                    continue;
                }
                String temp="";//这里还有点恶心，不管有没有共同课程都要列出来，而且还要student1到student2统计了，student2到student1就不要了
                if (s.compareTo(s2)<0){
                    temp=s+"#"+s2;
                }else {
                    temp=s2+"#"+s;
                }
                if (!rs.containsKey(temp)){
                    rs.put(temp,new HashSet<>());
                }
                for (String class1:map.get(s)){
                    if (map.get(s2).contains(class1)){
                        rs.get(temp).add(class1);
                    }
                }
            }
        }
        for (Map.Entry<String,Set<String>> entry:rs.entrySet()){
            System.out.print(entry.getKey()+" ");
            System.out.println(entry.getValue());
        }
    }
}
