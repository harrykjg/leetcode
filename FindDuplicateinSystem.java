import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 11/21/18.
 */
public class FindDuplicateinSystem {
    public static void main(String[] a){
        FindDuplicateinSystem fd=new FindDuplicateinSystem();
        String[] input={"root/cgcgc/vjjumw/enueowqwvjjpfk/lc/xqttxhgsluvp/i/mcgfuns/bphcylafabzq ylmenjgkhxtr.txt(ayncelfdpotwjcvlhgtxdjnemci) hv.txt(ayncelfdpotwjcvlhgtxdjnemci) wvlt.txt(ayncelfdpotwjcvlhgtxdjnemci) nwtpxoxypxyaw.txt(ayncelfdpotwjcvlhgtxdjnemci)"};
        fd.findDuplicate(input);
    }
    //不难，就是下标想清楚，用的是空格分开，
    //https://leetcode.com/problems/find-duplicate-file-in-system/discuss/104123/C%2B%2B-clean-solution-answers-to-follow-up  followup 可以参考他的，但是不知道为啥复杂度是n方？
    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String,ArrayList<String>> map=new HashMap<>();
        List<List<String>> rs=new ArrayList<>();
        if(paths.length==0){
            return rs;
        }

        for(int i=0;i<paths.length;i++){

            String cur=paths[i];
            int indexp=cur.indexOf(" ");
            String path=cur.substring(0,indexp);
            String[] files=cur.substring(indexp+1).split(" ");
            for(int j=0;j<files.length;j++){

                int i1=files[j].indexOf("(");
                int i2=files[j].lastIndexOf(")");
                String filename=files[j].substring(0,i1);
                String content=files[j].substring(i1+1,i2);
                if(!map.containsKey(content)){
                    map.put(content,new ArrayList<>());
                }
                map.get(content).add(path+"/"+filename);
            }

        }
        for(String s:map.keySet()){
            if(map.get(s).size()>=2){
                rs.add(map.get(s));
            }
        }
        return rs;


    }
}
