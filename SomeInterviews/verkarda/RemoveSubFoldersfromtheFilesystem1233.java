package SomeInterviews.verkarda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
就是地里的camera group 变形
就是有一个string organization, site, camera表示这个camera fail了，也可以输入是site fail也可以是organization fail，输入是一个array，表示有几个不同的camera，site，或者是organization fail，求最后合并之后是有谁fail了
比如输入是["o1,s1,c1","o1,s1","o1,s2,c1"]输出的就是["o1,s1","o1,s2,c1"]
因为一旦更高一层的已经fail了就可以直接合并这个camera的failure了
 */
public class RemoveSubFoldersfromtheFilesystem1233 {
    //还不会，这个有点confusing，/a/b/c 是/a的sub folder，/a/c 这样却不是，但是如果有/a这样的话/a/c就是sub folder了
    //解法就是先sort，这样能保证/a在/a/b/c前面，然后把/a设成pre，当前看/a/b/c可知 其由 pre + ‘/xxx'组成，因此可以判断是subfolder。
    //假如后面又来了个/a/d则pre还是/a还是可以判断，假如来了/c则pre 更新
    //参考别人思路 https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/solutions/5964641/explained-step-by-step-beats-100-working-zv6h/
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);

        List<String> rs=new ArrayList<>();
        rs.add(folder[0]);
        for(int i=1;i<folder.length;i++){
            String cur=folder[i];
            if(cur.startsWith(rs.get(rs.size()-1)+'/')){
                continue;
            }else{
                rs.add(cur);
            }

        }
        return rs;

    }
}
