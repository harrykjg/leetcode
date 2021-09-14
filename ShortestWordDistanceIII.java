public class ShortestWordDistanceIII {
    //7/15/2021,就是分类处理一下word1==word2的情况。
    public int shortestWordDistance(String[] wordsDict, String word1, String word2){
        int rs=Integer.MAX_VALUE;
        int i=0;
        int[] a=new int[2];
        a[0]=-1;
        a[1]=-1;
        if (!word1.equals(word2)){
            while (i<wordsDict.length){
                if (wordsDict[i].equals(word1)){
                    a[0]=i;
                    if (a[1]!=-1){
                        rs=Math.min(rs,a[0]-a[1]);
                    }
                }
                if (wordsDict[i].equals(word2)){
                    a[1]=i;
                    if (a[0]!=-1){
                        rs=Math.min(rs,a[1]-a[0]);
                    }
                }
                i++;
            }
            return rs;
        }else{
            int pre=-1;
            while (i<wordsDict.length){
                if (wordsDict[i].equals(word1)){
                    if (pre==-1){
                        pre=i;
                    }else{
                        rs=Math.min(rs,i-pre);
                        pre=i;
                    }
                }
                i++;
            }
            return rs;
        }
    }
}
