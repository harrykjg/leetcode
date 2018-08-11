import java.util.HashMap;

/**
 * Created by 502575560 on 5/16/17.
 */
public class LongestAbsoluteFilePath {
    public static void main(String[] args){
       System.out.println( LongestAbsoluteFilePath.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }
    //自己想应该是dfs,貌似不是这样,这题解法还挺巧妙挺难想的 "dir\n\tsubdir1\n\Root\tfile1.ext\n\Root\tsubsubdir1\n\tsubdir2\n\Root\tsubsubdir2\n\Root\Root\tfile2.ext"
    //http://blog.csdn.net/zjucor/article/details/72417061
    //https://discuss.leetcode.com/topic/55247/9-lines-4ms-java-solution
    //https://discuss.leetcode.com/topic/55561/two-different-solutions-in-java-using-stack-and-hashmap/2  他的map存的是层级和长度
    public static int lengthLongestPath(String input) {
        String[] ss=input.split("\n");
        HashMap<Integer,String> map=new HashMap<>();//存深度对应的路径,比如dir是第0级,则其路径就是dir,第1级的路径一开始是dir\n\tsubdir1,后面会被另一个第二级路径替换
        map.put(-1,"");                          //反正是从左到右扫描了全部的路径所以对的
        int rs=0;
        for(String s:ss){
            int lev=s.lastIndexOf("\t")+1; //如果不存在则返回-1
            String temp=s.substring(lev);//最后的路径是不算/n和/t的
            String path=map.get(lev-1)+temp;//这个path实际上是没有\的,所以下面rs.max那里就把path.length加上lev,刚好就是加上\的个数
            if(path.indexOf(".")!=-1){
                rs=Math.max(rs,path.length()+lev);
            }else{
                map.put(lev,path);
            }

        }
        return rs;
    }
}
