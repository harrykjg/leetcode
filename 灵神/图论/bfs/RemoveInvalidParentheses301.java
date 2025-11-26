package 灵神.图论.bfs;

import java.util.*;

public class RemoveInvalidParentheses301 {
    public static void main(String[] args) {
        List<String> rs=removeInvalidParentheses("()())()");
    }

    //还是不会，看回以前bfs的方法
    public static List<String> removeInvalidParentheses(String s) {
        Queue<String> q=new LinkedList<>();
        List<String> rs=new ArrayList<>();
        Set<String> set=new HashSet<>();
        set.add(s);
        q.offer(s);
        boolean found=false;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                String cur=q.poll();

                if (valid(cur)){
                    found=true;
                    rs.add(cur);

                }else{
                    if(found){
                        continue;
                    }
                    StringBuilder sb=new StringBuilder(cur);
                    for(int j=0;j<sb.length();j++){
                        if(j>0&&sb.charAt(j)==sb.charAt(j-1)){
                            continue;
                        }
                        if(Character.isAlphabetic(sb.charAt(j))){
                            continue;
                        }
                        char temp=sb.charAt(j);
                        sb.deleteCharAt(j);
                        if(!set.contains(sb.toString())){
                            q.offer(sb.toString());
                            set.add(sb.toString());
                        }
                        sb.insert(j,temp);
                    }
                }
            }
            if (found){
                return rs;
            }
        }
        return rs;
    }
    static boolean valid(String s){
        if (s.length()==0){
            return true;
        }
        int left=0;
        int right=0;
        for (int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                left++;
            }
            if(s.charAt(i)==')'){
                right++;
            }
            if (right>left){
                return false;
            }
        }
        return left==right;
    }
}
