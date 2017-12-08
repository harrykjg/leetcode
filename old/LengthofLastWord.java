//http://jixiangsanbao.wordpress.com/2014/02/17/length-of-last-word/
//http://blog.csdn.net/linhuanmars/article/details/21858067
public class LengthofLastWord {
	 public int lengthOfLastWord1(String s) {//第一次写
		 
			if(s==null||s==" "||s.length()==0){
				return 0;
			}
			String last=s;
			for(int i=0;i<s.length();i++){
				if(s.charAt(i)==32&&i+1<s.length()&&s.charAt(i+1)!=32){
					last=s.substring(i+1);
				}
			}
			while(last.length()-1>=0&&last.charAt(last.length()-1)==32){
				last=last.substring(0,last.length()-1);
			}
			return last.length();
		}
	//这是第二次写，用了trim方法，就简便了
	public int lengthOfLastWord(String s) {
		if(s.length()==0){
			return 0;
		}
		s=s.trim();
		int f=0;
		boolean flag=false;
		for(int i=0;i<s.length()-1;i++){
			if(s.charAt(i)==' '&&s.charAt(i+1)!=' '){//记录下最后的那个空格的位置，它往后就是单词了
				f=i;
			}
		}
		if(f==0){//f=0有2中情况：1：s是“ ”，那么f就是0，字符串也应该变成是“”吧，所以长度是0，
			//2：是“asdew”这种情况就是纯的单词的情况，都是返回s的长度
			
			return s.length();
		}else{//否则就返回最后一个单词的长度
			s=s.substring(f+1);
			return s.length();
		}
		
	}

}
