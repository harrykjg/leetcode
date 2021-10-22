import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    public static void main(String[] args){
        ExpressionAddOperators ea=new ExpressionAddOperators();
        ea.addOperators2("123",6);
    }
    //http://blog.csdn.net/lan_liang/article/details/49520109 看他的比较好懂，就是不知道他最后一个else是干嘛的，我不写也对了
    //http://segmentfault.com/a/1190000003797204 他的dfs参数中并没有记录preOp,而是在dfs的时候直接把num写成num或者-num了


    //一开始想的是就是dfs然后各种加号减号乘号都是一个分支，把数字和云算法都用一个stack存着，再计算每个stack得出的数，计算每个
    //stack就是basic calculator。但这样会太慢，他们是边dfs就边把结果算出来。这里有点难想，因为如果只有加减的话那么可以很容易
    //边dfs边算出当前结果，但是有了乘号就难了，但是还是可以实现的，具体看下面
    public List<String> addOperators(String num, int target) {
        List<String> rs=new ArrayList<String>();
        if(num==null){
            return rs;
        }
        dfs(num,target,0,0,'+',"",rs);
        return rs;

    }

    public void dfs(String n,int t,long cursum,long prenum,char preOp,String path,List<String> rs){
        if(n.length()==0&&cursum==t){
            rs.add(path);
            return;
        }

        for(int i=1;i<=n.length();i++){
            String temp=n.substring(0,i);
            if(temp.length()>1&&temp.charAt(0)=='0'){//注意这里第一个数是0就直接return了，因为比如是03这样的，肯定不合法
                return;         //而且也不用担心3不会被取到，因为肯定有下一步直接取了3的
            }
            long num=Long.valueOf(temp);
            if(path.equals("")){//有了这个判断之后，那么以后再进来就是肯定已经至少有一个数存在了
                dfs(n.substring(i),t,num,num,preOp,temp,rs);
                continue;
            }
            //到这里的话那么path里肯定已经有一个数了，如果打算给他插个加号的话，那么当前的cursum就是cursum+num，然后
            //prenum就是当前的这个num，因为到了下一层dfs时当前这个num就是prenum了
            dfs(n.substring(i),t,cursum+num,num,'+',path+"+"+num,rs);
            dfs(n.substring(i),t,cursum-num,num,'-',path+"-"+num,rs);
            //如果想给他插个乘号的话，那么cursum的计算方法就会不同，要看preOp是加号还是减号了
            if(preOp=='+'){//这里是最难理解的，比如现在实际的式子是3+2*4，其中当前的num是4，preOp是‘+’，cursum是（3+2）=5，
                //那么问题来了，我们现在要把乘号插到cursum和4之间，由于上一层dfs我们的cursum直接就是算的3+2=5，但到了这一层
                //这样算是不对的，于是我们要把3+2=5这一步复原到3+2*4这样，其实就只要复原出3。把他设为未知数x，
                //我们知道preOp是‘+’，prenum是2，即x+2=cursum（这个是输入参数，这里是5），那么就求出x=cursum-prenum，
                //得到x之后，要计算当前这层的cursum就是x+prenum*num，即cursum-prenum+prenum*num

                //还有一点要注意，prenum是prenum*num而不是num。比如，式子是3-2*4*5，当前num是5，那么preOp就是‘-’（不是乘号），
                //输入的cursum就是3-2*4=-5，则还是要复原出3，所用的公式是x=cursum+prenum，此时prenum就要是2*4这个整体，而不是
                //4，因此prenum应该是prenum*num
                dfs(n.substring(i),t,cursum-prenum+prenum*num,prenum*num,'+',path+"*"+num,rs);
            }else{
                //同理这里比如是3-2*4，输入参数的cursum是1，preOp是‘-’，要复原3，则x-prenum=cursum，所以当前层的cursum就是
                //cursum+prenum-prenum*num
                dfs(n.substring(i),t,cursum+prenum-prenum*num,prenum*num,'-',path+"*"+num,rs);
            }
        }
    }
    //8/24/2021 真tm难
//https://www.youtube.com/watch?v=EcG4Tg9AjGs 看他的解释。结合我上面的解释
    public List<String> addOperators2(String num, int target) {
        List<String> rs=new ArrayList<>();
        for (int i=1;i<=num.length();i++){//我这里想的就是先搞出第一个数字然后才开始dfs，上面的是直接dfs但是特殊处理没有数字的，我感觉我这样好一些
            String s=num.substring(0,i);//整个string刚好等于target也行，不是必须要operator
            if (valid(s)){
                dfs2(Long.valueOf(s),s,Long.valueOf(s),"+",i,rs,target,num);
            }
        }
        return rs;
    }
    void dfs2(long cursum,String path,long lastNum,String preOp,int begin,List<String> rs,int target,String num){
        if (begin==num.length()&&cursum==target){
            rs.add(path);
            return;
        }
        for (int i=begin+1;i<=num.length();i++){
            String s=num.substring(begin,i);
            if (valid(s)){
                long now=Long.valueOf(s);
                if (preOp.equals("+")){//4+3*2
                    dfs2(cursum+now,path+"+"+now,now,"+",i,rs,target,num);
                    dfs2(cursum-now,path+"-"+now,now,"-",i,rs,target,num);
                    long temp=cursum-lastNum+lastNum*now;
                    dfs2(temp,path+"*"+now,lastNum*now,"+",i,rs,target,num);
                }else if (preOp.equals("-")){//4-3*2*2
                    dfs2(cursum+now,path+"+"+now,now,"+",i,rs,target,num);
                    dfs2(cursum-now,path+"-"+now,now,"-",i,rs,target,num);
                    long temp=cursum+lastNum-lastNum*now;
                    dfs2(temp,path+"*"+now,lastNum*now,"-",i,rs,target,num);
                }
            }
        }
    }
    boolean valid(String s){
        if (s.length()==1){
            return true;
        }
        if (s.charAt(0)=='0'){
            return false;
        }
        return true;
    }
//10/10/2021
    public List<String> addOperators3(String num, int target) {
        List<String> rs=new ArrayList<>();
        if (num.length()==0){
            return rs;
        }
        for (int i=1;i<=num.length();i++){
            String sub=num.substring(0,i);
            if (valid3(sub)){
                long n=Long.parseLong(sub);
                dfs3(n,i,n,sub,'+',target,rs,num);
            }
        }
        return rs;
    }
    void dfs3(long presum,int begin,long last,String path,char op,int target,List<String> rs,String num){
        if (begin==num.length()&&presum==target){
            rs.add(path);
            return;
        }
        for (int i=1;i+begin<=num.length();i++){
            int end=i+begin;
            String cur=num.substring(begin,end);
            if (valid3(cur)){
                long curNum=Long.parseLong(cur);
                if (op=='+'){
                    dfs3(presum+curNum,end,curNum,path+"+"+curNum,'+',target,rs,num);
                    dfs3(presum-curNum,end,curNum,path+"-"+curNum,'+',target,rs,num);
                    long temp=presum-last+curNum*last;
                    dfs3(temp,end,last*curNum,path+"*"+curNum,'+',target,rs,num);
                }else if (op=='-'){
                    dfs3(presum+curNum,end,curNum,path+"+"+curNum,'+',target,rs,num);
                    dfs3(presum-curNum,end,curNum,path+"-"+curNum,'+',target,rs,num);
                    long temp=presum+last-curNum*last;
                    dfs3(temp,end,last*curNum,path+"*"+curNum,'-',target,rs,num);
                }
            }
        }
    }
    boolean valid3(String num){
        if (num.length()==1){//这个容易漏
            return true;
        }
        for (int i=0;i<num.length();i++){
            if (num.charAt(i)=='0'){
                return false;
            }else {
                break;
            }
        }
        return true;
    }
}
