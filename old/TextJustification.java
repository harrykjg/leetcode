import java.util.ArrayList;


public class TextJustification {
	public static void main(String[] args) {
		TextJustification tj=new TextJustification();
		String[] words={"This", "is", "an", "example", "of", "text", "justification."};
		ArrayList<String> a=tj.fullJustify(words, 16);
		for(int i=0;i<a.size();i++){
			System.out.println(a.get(i));
		}
	}
	
	
	public ArrayList<String> fullJustify(String[] words, int L) {  
	    ArrayList<String> res = new ArrayList<String>();  
	    if(words==null || words.length==0)  
	        return res;  
	    int count = 0;  
	    int last = 0;  //last指向的是第一个开始的单词
	    for(int i=0;i<words.length;i++)  
	    {  
	        if(count+words[i].length()+(i-last)>L)  //count记录已有的长度，加上当前单词的长度，加上slots数
	        {    //大于的话说明就要开始处理了，小于的话就把word[i]加进来，更新count，然后进入下个for循环
	            int spaceNum = 0;  
	            int extraNum = 0;  
	            if(i-last-1>0)  //其实是看（i-1）-last是否大于0，因为i指的是当前这个没加进来的单词，所以加进来
	            {//的最后一个单词应该是有i-1指向的，然后减去last，看当前层时候大于一个单词，大于的话才有中间的空格
	            	//才有下面的计算，否则的话，spaceNum和extraNum都是0
	                spaceNum = (L-count)/(i-last-1);  //每个slot分配的单词
	                extraNum = (L-count)%(i-last-1);  //除不尽的话多出来的空格
	            }  
	            StringBuilder str = new StringBuilder();  
	            for(int j=last;j<i;j++)  
	            {  
	                str.append(words[j]);  
	                if(j<i-1)  //这个意思是如果append了第j个单词后，后面还有单词的话，说明要加上空格了，否则的话
	                {  //就说明已经append到最后一个单词的
	                    for(int k=0;k<spaceNum;k++)  
	                    {  
	                        str.append(" ");  
	                    }  
	                    if(extraNum>0)  //这个extraNum搞的巧妙，由于它是从第一个单词开始appen起的，所以先append了
	                    {  //正常的空格，如果空格不能平均分配，就先给他加一个多余的空格，然后多余的空格减一
	                        str.append(" ");  
	                        extraNum--; 
	                    }  
	                    
	                }  
	            }  //如果只有一个单词的话，那么上面的循环把这个单词append之后，剩下还有空余位置的话，就继续append空格
	            for(int j=str.length();j<L;j++)  
	            {  
	                str.append(" ");  
	            }         
	            res.add(str.toString());  
	            count=0;  
	            last=i;  //他这里设的last等于i，即代表他已经把i这个单词纳入下一层的第一个单词了，然后下面count又
	        }         //加了word[i]的长度了
	        count += words[i].length();  
	    }  
	    StringBuilder str = new StringBuilder();  
	    for(int i=last;i<words.length;i++)  
	    {  
	        str.append(words[i]);  
	        if(str.length()<L)  
	            str.append(" ");  
	    }  
	    for(int i=str.length();i<L;i++)  
	    {  
	        str.append(" ");  
	    }  
	    res.add(str.toString());  
	    return res;  
	}  


}
