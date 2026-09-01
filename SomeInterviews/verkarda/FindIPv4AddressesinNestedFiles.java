package SomeInterviews.verkarda;

import java.util.*;

public class FindIPv4AddressesinNestedFiles {

    //参考gpt
    Set<String> set=new HashSet<>();
    public List<String> findValidIpAddresses(Node root) {
        // TODO: Implement findValidIpAddresses logic.
        traverse(root);
        List<String> rs = new ArrayList<>(set);
        Collections.sort(rs);
        return rs;
    }
    void traverse(Node root){
        if(root==null){
            return ;
        }
        if(root.isFile){//没subdir
            handle(root.lines,set);
        }else{
            for(Node n:root.children){
                traverse(n);
            }
        }
    }
    void handle(List<String> input,Set<String> set){
        for (String s:input){
            parse(s);
        }
    }
    void parse(String s){
        char[] ch=s.toCharArray();
        int index=0;
        while (index<ch.length){

            if(Character.isDigit(ch[index])){//找到一个digit就开始看是不是ip
                // IP 前面不能紧挨 digit 或 '.'
                if (index > 0 &&
                        (Character.isDigit(s.charAt(index - 1))
                                || s.charAt(index - 1) == '.')) {
                    index++;
                    continue;
                }
                int end = index;
                // 把这一整段 "数字 + ." 都拿出来
                while (end < s.length() &&
                        (Character.isDigit(s.charAt(end)) || s.charAt(end) == '.')) {
                    end++;
                }
                String candidate = s.substring(index, end);
                if (validIP(candidate)) {
                    set.add(candidate);
                }
                // 关键：这一整段已经检查过了,直接跳到下一段开始的位置
                index = end;
            }else{
                index++;
            }
        }
    }
    private boolean validIP(String s) {
        String[] parts = s.split("\\.", -1);
        if (parts.length != 4) {
            return false;
        }
        for (String part : parts) {
            // 不能为空，例如 1..2.3
            if (part.length() == 0) {
                return false;
            }
            // 不能有 leading zero
            if (part.length() > 1 && part.charAt(0) == '0') {
                return false;
            }
            // 最多 3 位
            if (part.length() > 3) {
                return false;
            }
            int num = Integer.parseInt(part);
            if (num > 255) {
                return false;
            }
        }
        return true;
    }




    class Node {
        String name;
        boolean isFile;
        List<String> lines;
        List<Node> children;

        Node(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            this.lines = new ArrayList<>();
            this.children = new ArrayList<>();
        }
    }

}
