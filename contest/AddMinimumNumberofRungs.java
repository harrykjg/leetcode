package contest;

public class AddMinimumNumberofRungs {
    public int addRungs(int[] rungs, int dist) {
        int cur=0;
        int i=0;
        int rs=0;
        while (i<rungs.length){
            if (dist+cur>=rungs[i]){
                i++;
                cur=rungs[i];
                continue;
            }else {
                int gap=rungs[i]-cur;
                if (gap%dist==0){//这里有点噁心，比如【3】dist=1时不处理就错了
                    rs+=gap/dist-1;
                }else {
                    rs+=gap/dist;
                }
                cur=rungs[i];
                i++;

            }
        }
        return rs;

    }
}
