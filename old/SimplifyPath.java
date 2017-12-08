import java.util.LinkedList;

//http://blog.csdn.net/makuiyu/article/details/44497901
//http://jixiangsanbao.wordpress.com/2014/04/16/simplify-path/ 吉祥的用了split方法，就简单了
//http://blog.csdn.net/linhuanmars/article/details/23972563
public class SimplifyPath {
	
	public static void main(String[] args) {
		SimplifyPath sp=new SimplifyPath();
		System.out.println(sp.simplifyPath("///eHx/.."));
	}
	
	//卧槽，提交修改了n次才accept
	public String simplifyPath(String path) {
		if(path.length()==0){
			return "";
		}
		LinkedList<String> st=new LinkedList<String>();
		String temp="";
		boolean flag=false;//用来看是有已经有了“/”
		boolean flag2=false;//用来看是否有string加进stack了
		String rs="/";
		
		for(int i=0;i<path.length();i++){
			if(path.charAt(i)=='/'&&flag==false){//遇见第一个/
				flag=true;
				continue;
			}
			if(path.charAt(i)=='/'){//遇见重复的/，继续
				continue;
			}
			//判断charat i，i+1，和i+2，如果是../且stack不为空则pop
			if((i+2<path.length()&&path.charAt(i)==path.charAt(i+1)&&path.charAt(i)=='.'
					&&path.charAt(i+2)=='/'&&!st.isEmpty())
					//或者是..且i+2等于path的length了且stack不为空，则pop
					||(i+1<path.length()&&path.charAt(i)==path.charAt(i+1)&&path.charAt(i)=='.'&&
					i+2==path.length()&&!st.isEmpty())){
				st.pop();
				i++;//由于是判断了两个点“..”，所以i加一位跳过这两个点，从下个/开始继续循环
				flag=false;//斜杠重置
				continue;
				//如果是charat i和i+1都是.的情况
			}else if(i+1<path.length()&&path.charAt(i)==path.charAt(i+1)&&path.charAt(i)=='.'){
				if(i+2==path.length()&&st.isEmpty()){//如果后面没有了，即..的情况
					i++;                         //且stack为空，则啥都不做
					continue;
				}
				
				if(i+2<path.length()&&path.charAt(i+2)=='/'){//如果是../的情况，由于之前那个if的
					i++;                //判断，知道这里stack肯定是空的，所以也啥都不做
					continue;
				}
			}//“./”的情况，啥也不做，"."的情况也是
			if((i+1<path.length()&&path.charAt(i)=='.'&&path.charAt(i+1)=='/')
					||(i+1==path.length()&&path.charAt(i)=='.')){
				continue;
			}
			//以上判断都不是的话，说明这里是一个单词了，（该单词有可能是“...”）
			while(i<path.length()&&path.charAt(i)!='/'){
				flag2=true;//证明有单词了
				temp=temp+path.charAt(i);
				i++;
			}
			if(flag2){
				st.push(temp);
				flag=false;
				i--;//前面那个while循环把i弄到了下个/，所以这里i退回一步，使下一个for循环是从/开始
				temp="";
				flag2=false;
			}		
		}
		
		while(!st.isEmpty()){
			if(st.size()!=1){
				rs=rs+st.pollLast()+"/";//先加进去的叫last？不太懂
			}else{
				rs=rs+st.poll();
			}
		}
			return rs;
	}
	//第二次想一想还是不写了，太麻烦了
  

}
