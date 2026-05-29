package SomeInterviews.roblox;

import java.util.*;

public class MostFrequentCallChain {
    static void main() {
        String[] tt={"-> level1", "-> level2", "-> level3", "-> level4", "<- level4", "<- level3", "<- level2",
                "-> level2", "-> level3", "-> level4", "<- level4", "<- level3", "<- level2", "<- level1"};



        String[] rs=MostFrequentCallChain.findMostFrequentCallStack(  Arrays.asList(tt));
        System.out.println(rs[0]);
        System.out.println(rs[1]);


    }

    public static String[] findMostFrequentCallStack(List<String> traces) {
        int max=0;
        String path="";
        String cand="";
        Stack<String> st=new Stack<>();
        Map<String,Integer> map=new HashMap<>();
        for (int i=0;i<traces.size();i++){
            String[] cur=traces.get(i).split(" ");
            if(cur[0].equals("->")){
                st.push(cur[1]);
                path+=cur[1]+",";
                map.put(path,map.getOrDefault(path,0)+1);
                if(map.get(path)>max){
                    max=map.get(path);
                    cand=path;
                }else if(map.get(path)==max){
                    int curlen=path.split(",").length;
                    int candLen=cand.split(",").length;
                    if(curlen>candLen){
                        cand=path;
                    }
                }
            }else{
                String last=st.pop();
                path=path.substring(0,path.length()-last.length()-1);
            }
        }
        String[] p=cand.split(",");
        String[] rs=new String[2];
        StringBuilder sb=new StringBuilder();
        for (String s:p){
            sb.append(s);
            sb.append(" -> ");
        }
        rs[0]=sb.substring(0,sb.length()-4);
        rs[1]=max+"";
        return rs;
    }
}
