import java.util.LinkedList;

//http://blog.csdn.net/makuiyu/article/details/44497901
//http://jixiangsanbao.wordpress.com/2014/04/16/simplify-path/ ���������split�������ͼ���
//http://blog.csdn.net/linhuanmars/article/details/23972563
public class SimplifyPath {
	
	public static void main(String[] args) {
		SimplifyPath sp=new SimplifyPath();
		System.out.println(sp.simplifyPath("///eHx/.."));
	}
	
	//�Բۣ��ύ�޸���n�β�accept
	public String simplifyPath(String path) {
		if(path.length()==0){
			return "";
		}
		LinkedList<String> st=new LinkedList<String>();
		String temp="";
		boolean flag=false;//�����������Ѿ����ˡ�/��
		boolean flag2=false;//�������Ƿ���string�ӽ�stack��
		String rs="/";
		
		for(int i=0;i<path.length();i++){
			if(path.charAt(i)=='/'&&flag==false){//������һ��/
				flag=true;
				continue;
			}
			if(path.charAt(i)=='/'){//�����ظ���/������
				continue;
			}
			//�ж�charat i��i+1����i+2�������../��stack��Ϊ����pop
			if((i+2<path.length()&&path.charAt(i)==path.charAt(i+1)&&path.charAt(i)=='.'
					&&path.charAt(i+2)=='/'&&!st.isEmpty())
					//������..��i+2����path��length����stack��Ϊ�գ���pop
					||(i+1<path.length()&&path.charAt(i)==path.charAt(i+1)&&path.charAt(i)=='.'&&
					i+2==path.length()&&!st.isEmpty())){
				st.pop();
				i++;//�������ж��������㡰..��������i��һλ�����������㣬���¸�/��ʼ����ѭ��
				flag=false;//б������
				continue;
				//�����charat i��i+1����.�����
			}else if(i+1<path.length()&&path.charAt(i)==path.charAt(i+1)&&path.charAt(i)=='.'){
				if(i+2==path.length()&&st.isEmpty()){//�������û���ˣ���..�����
					i++;                         //��stackΪ�գ���ɶ������
					continue;
				}
				
				if(i+2<path.length()&&path.charAt(i+2)=='/'){//�����../�����������֮ǰ�Ǹ�if��
					i++;                //�жϣ�֪������stack�϶��ǿյģ�����Ҳɶ������
					continue;
				}
			}//��./���������ɶҲ������"."�����Ҳ��
			if((i+1<path.length()&&path.charAt(i)=='.'&&path.charAt(i+1)=='/')
					||(i+1==path.length()&&path.charAt(i)=='.')){
				continue;
			}
			//�����ж϶����ǵĻ���˵��������һ�������ˣ����õ����п����ǡ�...����
			while(i<path.length()&&path.charAt(i)!='/'){
				flag2=true;//֤���е�����
				temp=temp+path.charAt(i);
				i++;
			}
			if(flag2){
				st.push(temp);
				flag=false;
				i--;//ǰ���Ǹ�whileѭ����iŪ�����¸�/����������i�˻�һ����ʹ��һ��forѭ���Ǵ�/��ʼ
				temp="";
				flag2=false;
			}		
		}
		
		while(!st.isEmpty()){
			if(st.size()!=1){
				rs=rs+st.pollLast()+"/";//�ȼӽ�ȥ�Ľ�last����̫��
			}else{
				rs=rs+st.poll();
			}
		}
			return rs;
	}
	//�ڶ�����һ�뻹�ǲ�д�ˣ�̫�鷳��
  

}
