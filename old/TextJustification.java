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
	    int last = 0;  //lastָ����ǵ�һ����ʼ�ĵ���
	    for(int i=0;i<words.length;i++)  
	    {  
	        if(count+words[i].length()+(i-last)>L)  //count��¼���еĳ��ȣ����ϵ�ǰ���ʵĳ��ȣ�����slots��
	        {    //���ڵĻ�˵����Ҫ��ʼ�����ˣ�С�ڵĻ��Ͱ�word[i]�ӽ���������count��Ȼ������¸�forѭ��
	            int spaceNum = 0;  
	            int extraNum = 0;  
	            if(i-last-1>0)  //��ʵ�ǿ���i-1��-last�Ƿ����0����Ϊiָ���ǵ�ǰ���û�ӽ����ĵ��ʣ����Լӽ���
	            {//�����һ������Ӧ������i-1ָ��ģ�Ȼ���ȥlast������ǰ��ʱ�����һ�����ʣ����ڵĻ������м�Ŀո�
	            	//��������ļ��㣬����Ļ���spaceNum��extraNum����0
	                spaceNum = (L-count)/(i-last-1);  //ÿ��slot����ĵ���
	                extraNum = (L-count)%(i-last-1);  //�������Ļ�������Ŀո�
	            }  
	            StringBuilder str = new StringBuilder();  
	            for(int j=last;j<i;j++)  
	            {  
	                str.append(words[j]);  
	                if(j<i-1)  //�����˼�����append�˵�j�����ʺ󣬺��滹�е��ʵĻ���˵��Ҫ���Ͽո��ˣ�����Ļ�
	                {  //��˵���Ѿ�append�����һ�����ʵ�
	                    for(int k=0;k<spaceNum;k++)  
	                    {  
	                        str.append(" ");  
	                    }  
	                    if(extraNum>0)  //���extraNum�������������Ǵӵ�һ�����ʿ�ʼappen��ģ�������append��
	                    {  //�����Ŀո�����ո���ƽ�����䣬���ȸ�����һ������Ŀո�Ȼ�����Ŀո��һ
	                        str.append(" ");  
	                        extraNum--; 
	                    }  
	                    
	                }  
	            }  //���ֻ��һ�����ʵĻ�����ô�����ѭ�����������append֮��ʣ�»��п���λ�õĻ����ͼ���append�ո�
	            for(int j=str.length();j<L;j++)  
	            {  
	                str.append(" ");  
	            }         
	            res.add(str.toString());  
	            count=0;  
	            last=i;  //���������last����i�����������Ѿ���i�������������һ��ĵ�һ�������ˣ�Ȼ������count��
	        }         //����word[i]�ĳ�����
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
