import java.util.Map;
import java.util.TreeMap;

public class MinimumOneBitOperationstoMakeIntegersZero {
    public static void main(String[]args){
        MinimumOneBitOperationstoMakeIntegersZero mo=new MinimumOneBitOperationstoMakeIntegersZero();
        System.out.println(mo.minimumOneBitOperations(9));
        //1001  1100 =helper(001)+1+ dfs(100)
        //helper(001)=  helper(01)+1         dfs(100)= helper(10)+1+ dfs(10)
        //helper(01)=0                       helper(10)=helper(0)+1    dfs(10)=helper(0)+1+dfs(1)
    }

    //8/7/2021  不会。
    //https://www.youtube.com/watch?v=8MdutrMAwY4 从4分25开始看
    Map<String,Integer> map=new TreeMap<>();
    Map<String,Integer> map2=new TreeMap<>();
    public int minimumOneBitOperations(int n) {
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        return dfs(Integer.toBinaryString(n));

    }
    int dfs(String s){//dfs就是minimumOneBitOperations的input改成是string
        if (s.length()==0){
            return 0;
        }
        if (s.equals("0")){
            return 0;
        }
        if (s.equals("1")){
            return 1;
        }
        if (map.containsKey(s)){
            return map.get(s);
        }
        int rs=0;
        if (s.charAt(0)=='1'){
            int temp= helper(s.substring(1))+1;
            StringBuilder sb=new StringBuilder();
            sb.append(1);
            for (int i=0;i<s.length()-2;i++){
                sb.append(0);
            }
            rs= temp+ dfs(sb.toString());
        }else {
            rs=dfs(s.substring(1));
        }
        map.put(s,rs);
        return rs;
    }
    int helper(String s){//helper目的是把s改成1000
        if (s.length()==0){
            return 0;
        }
        if (s.equals("1")){
            return 0;
        }
        if (s.equals("0")){
            return 1;
        }
        if (map2.containsKey(s)){
            return map2.get(s);
        }
        int rs=0;
        if (s.charAt(0)=='1'){
            rs=dfs(s.substring(1));
        }else {
            int temp=helper(s.substring(1))+1;//比如把0xxxx变成10000，则需要把xxxx变成1000即helper(s.substring(1))+1，即把0xxxx变成了11000，然后还要把
            StringBuilder sb=new StringBuilder();//1000变成0000，这里容易漏！
            sb.append(1);
            for (int i=0;i<s.length()-2;i++){
                sb.append(0);
            }
            rs= temp+dfs(sb.toString());
        }
        map2.put(s,rs);
        return rs;
    }
}
