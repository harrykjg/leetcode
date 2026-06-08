package SomeInterviews.snowflake;

import java.util.*;

public class DesignInMemoryFileSystem588 {
//就是用tree
    private Node root;
    public FileSystem() {
        root = new Node();
    }
    public List<String> ls(String path) {
        Node node = traverse(path);
        // 如果 path 是文件，返回文件名
        if (node.isFile) {//肯定有答案
            List<String> res = new ArrayList<>();
            res.add(getName(path));
            return res;
        }
        // 如果 path 是目录，返回该目录下所有 children 名字
        return new ArrayList<>(node.children.keySet());
    }
    public void mkdir(String path) {
        String[] parts = path.split("/");
        Node cur = root;
        for (String part : parts) {
            if (part.length() == 0) {
                continue;
            }
            cur.children.putIfAbsent(part, new Node());//新的node这里没存name，因为存在parent的map里了，存的话也行
            cur = cur.children.get(part);
        }
    }
    public void addContentToFile(String filePath, String content) {
        String[] parts = filePath.split("/");
        Node cur = root;
        for (int i = 1; i < parts.length; i++) {
            String name = parts[i];

            cur.children.putIfAbsent(name, new Node());
            cur = cur.children.get(name);
        }
        cur.isFile = true;
        cur.content.append(content);
    }
    public String readContentFromFile(String filePath) {
        Node node = traverse(filePath);
        if(node.isFile){
            return node.content.toString();
        }
        return "";
    }
    // 根据 path 找到对应 node
    private Node traverse(String path) {
        String[] parts = path.split("/");
        Node cur = root;
        for (String part : parts) {
            if (part.length() == 0) {//  /a/b/c 这样split之后第一个是空string，这个就是处理这个的。
                continue;
            }
            cur = cur.children.get(part);//题目说了ls必然是个合法的path，因此不会null pointer
        }
        return cur;
    }
    // 取 path 最后的文件名
    private String getName(String path) {
        String[] parts = path.split("/");
        return parts[parts.length - 1];
    }
}
class Node {
    boolean isFile = false;
    StringBuilder content = new StringBuilder();
    // TreeMap 保证 ls 的结果是 lexicographic order
    TreeMap<String, Node> children = new TreeMap<>();//开始还搞错了map的value写成set了
}