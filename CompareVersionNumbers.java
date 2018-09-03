/**
 * Created by yufengzhu on 8/12/18.
 */
public class CompareVersionNumbers {
    public static void main(String[] args){
        CompareVersionNumbers cv=new CompareVersionNumbers();
        cv.compareVersion("1","1.1");
    }
    public int compareVersion(String version1, String version2) {
        String[] v1=version1.split("\\.");//注意这里之前写的"."是不行的，要转义
        String[] v2=version2.split("\\.");
        int len=Math.min(v1.length,v2.length);
        for(int i=0;i<len;i++){
            int vv1=Integer.parseInt(v1[i]);
            int vv2=Integer.parseInt(v2[i]);
            if(vv1==vv2){
                continue;
            }
            return vv1>vv2?1:-1;
        }
        if(v1.length>v2.length){
            for(int i=v2.length;i<v1.length;i++){
                int vv1=Integer.parseInt(v1[i]);
                if(vv1==0){
                    continue;
                }
                return vv1>0?1:-1;
            }
        }
        if(v1.length<v2.length){
            for(int i=v1.length;i<v2.length;i++){
                int vv2=Integer.parseInt(v2[i]);
                if(vv2==0){
                    continue;
                }
                return vv2>0?-1:1;//这里注意是调过来了-1：1容易错
            }
        }
        return 0;
    }
}
