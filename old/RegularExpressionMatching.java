//http://blog.csdn.net/doc_sgl/article/details/12719761 ���������֪��Ϊʲô���һ��������true
//http://blog.csdn.net/lsdtc1225/article/details/39201663 
//http://blog.csdn.net/amuseme_lu/article/details/1898500 ����һ���㷨�ܽᣬӢ�ĵ�
public class RegularExpressionMatching {
	public static void main(String[] args) {
		RegularExpressionMatching re=new RegularExpressionMatching();
		//test case "baccbbcbcacacbbc", "c*.*b*c*ba*b*b*.a*"
		//"aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*a*a*b"
		System.out.println(re.isMatch4("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*a*a*b"));
	}
	
	//�ݹ��˼·�Լ�������������룬����ʵ�������Ƚ϶�ϸ��Ҫ�룬����Ҫ���Ժܾã��ο����ǵ���д��
	public boolean isMatch(String s, String p) {
		if(s==null||p==null){
			return false;
		}
		if(s.equals(p)){
			return true;
		}
		if(p.length()==0){
			return s.length()==0;
		}//��Щ�����üǣ���Ȼ�ֳ���Ҫ�ܾã�p.length==1��������Ǳ���p.charAt��1��Խ�籨������
		//�����硰aa������a���������p.charAt��1���ᱨ��wildcard matching��û����������Ҫ��
		if(p.length()==1||p.charAt(1)!='*'){
			if(s.length()>0&&(p.charAt(0)=='.'||p.charAt(0)==s.charAt(0))){
				return isMatch(s.substring(1),p.substring(1));
			}else{
				return false;
			}
		}else{
			int i=0;//�����д���Ǻͼ���Ĳ�ͬ�������code ganker�ģ������Ǹ�i��ʼλ-1,
//������s.substring��i+1����˺�s.charAt��i��ʵָ���ǲ�ͬ�ģ�����������while������
//����һ��i<0���������ʼ�������Ҫ��aa��a*,��"baccbbcbcacacbbc", "c*.*b*c*ba*b*b*.a*"����һ�²�
//˵���塣�����������������д����while֮����return false������return isMatch(s.substring(i),
//p.substring(2)�Ļ�,����"baccbbcbcacacbbc", "c*.*b*c*ba*b*b*.a*"������ӣ�s�ĵ�һ����ĸ��p
//�ĵ�һ����ĸ�ǲ�ͬ�ģ���ô�ͽ��벻��whileѭ�����ͻ�ֱ��return false�ˣ���Ȼ�Ǵ�ģ���Ϊ���ǿ���
//p��ǰ��λ��c*,˵��c���Գ���0�Σ���ֱ��ȥ��c*����λ�����s��p.substirng(2)�ˣ������������������
//��Ӧ���ǹ���whileѭ�����ֱ�Ӽ��s��p�ĳ�ȥǰ��λ���substring�����һ�¡�����ܽ���while�Ļ���
//˵���ǰ�c*ȥ��s�Ŀ��ַ���i=0�����������һ����������3����ĸ������ڶ�����������ĸ���͵�һ����ĸ��ͬ�Ļ���.��ʵ����
//˵���˾���whileȥ�Ը���pǰ2��ȥ����Ȼ��sȥ��0�������ڶ�����ĸ��ֻʣ������һ������Ϊ������i<s.length��
//�����while���ܸ��ǵ���һ����������ǰ�s��������ĸȥ����������s=������p=��x*�����������Ϊi��ʼΪ0��iҪС��s�ĳ��ȣ����Խ���
//����whileѭ������������ֻ����return isMatch(s.substring(i),p.substring(2))ȥ�����ˡ�������
//i���ܵ���s.length��ԭ�򣬲�����Ϊsubsting(s.length)�ᱨ�����ᱨ�����ص��ǡ�������������Ϊ��
//i++֮�����s�ĳ����ˣ���ȥ�ж�s.charAt��i���ᱨ������˵���ﲻ����wildcard matching����i=s��
//���Ȳ��Һ���return false��ԭ����ǣ�substring��s.length)���ᱨ���s.charAt(s.length)�ᱨ��
//��aa��a*�������debugһ�ξ�֪����
			while(i<s.length()&&(s.charAt(i)==p.charAt(0)||p.charAt(0)=='.')){
				if(isMatch(s.substring(i),p.substring(2))){
					return true;
				}
				i++;
			}
			return isMatch(s.substring(i),p.substring(2));
		}	
	}
	 public boolean isMatch2(String s, String p) {
	        if(s == null || p == null){
	            return false;
	        }
	        if (p.length() == 0) {
	            return (s.length() == 0);
	        }
	        //if the second char is not *
	        if (p.length() == 1 || p.charAt(1) != '*') {
	            //System.out.println(s.length() + "||" + p.length());
	            if (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
	                return isMatch2(s.substring(1), p.substring(1));
	            } else {
	                return false;
	            }
	        }
	        //if the second char is *
	        else {
	            //s.substring(0) is the case that * in p matches with 0 characters
	            int i = -1;
	            int len = s.length();
	            while (i < len && (i < 0 || s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
	                if (isMatch2(s.substring(i + 1), p.substring(2))) {
	                    return true;
	                }
	                i++;
	            }
	            return false;
	        }
	    }

//code ganker ��
	public boolean isMatch3(String s, String p) {  
	    return helper(s,p,0,0);  
	}  
	private boolean helper(String s, String p, int i, int j)  
	{  
	    if(j==p.length())  
	        return i==s.length();  
	      
	    if(j==p.length()-1 || p.charAt(j+1)!='*')  
	    {  
	        if(i==s.length()|| s.charAt(i)!=p.charAt(j) && p.charAt(j)!='.')  
	            return false;  
	        else  
	            return helper(s,p,i+1,j+1);  
	    }  
	    //p.charAt(j+1)=='*'   
	    while(i<s.length() && (p.charAt(j)=='.' || s.charAt(i)==p.charAt(j)))  
	    {  
	        if(helper(s,p,i,j+2))  
	            return true;  
	        i++;  
	    }  
	    return helper(s,p,i,j+2);  
	}  
//1/10
	public boolean isMatch4(String s, String p) {
		if(s==null||p==null){
			return false;
		}
		if(p.length()==0){
			return s.length()==0;
		}
		if(p.length()==1){//��������������if����һ��
			if(s.length()==1&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')){
				return true;
			}else{
				return false;
			}
		}
		if(p.charAt(1)!='*'){
			if(s.length()>0&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')){
				return isMatch4(s.substring(1),p.substring(1));
			}else{
				return false;
			}
		}else{
			int i=0;
			while(i<s.length()&&(s.charAt(i)==p.charAt(0)||p.charAt(0)=='.')){
				
				if( isMatch4(s.substring(i),p.substring(2))){
					return true;
				}
				i++;
			}
			//Ϊʲô����Ҫs.substring��i��������0�أ�û̫������
//2016��һ�£�����ȷʵӦ����substring(i)������s=aa,p=a*������ӣ�i���Ե���0,1����ʱ
//whileѭ�����ж�aa�͡�����a�͡����Ƿ�match����Ȼ���У���������һ���������Ǽ��
//s.substring(s.length)�͡����Ƿ�match.���ǲ�̫����⣬���ž�����
			return isMatch4(s.substring(i),p.substring(2));
		}
		
	}

}
