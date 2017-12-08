import java.util.HashMap;
//http://www.cnblogs.com/lichen782/p/leetcode_minimum_window_substring_3.html ���˵�ú�
public class MinimumWindowSubstring {
	public static void main(String[] args) {
		MinimumWindowSubstring mw=new MinimumWindowSubstring();
		System.out.println(mw.minWindow3("cabwefgewcwaefgcf", "cae"));
	}
	
//˼·���ǣ�������ɨ��left��right��ʼ����0��rightһֱ����������left��right֮��������ַ���������
//T�е�ʱ�򣬿�ʼ��left�ܲ�������������left++��������ܵĻ����ͼ���right++��ֱ��������һ��T�е���ĸ��
//�ٿ��ܲ��ܰ�left��������ά��һ����̵��ַ�����������
	public String minWindow(String S, String T) {
		if(S.length()==0||T.length()==0||T.length()>S.length()){
			return "";
		}
		HashMap<Character,Integer> hm1=new HashMap<Character,Integer>();
		HashMap<Character,Integer> hm2=new HashMap<Character,Integer>();
		for(int i=0;i<T.length();i++){
			if(hm1.containsKey(T.charAt(i))){
				hm1.put(T.charAt(i), hm1.get(T.charAt(i))+1);
			}else{
				hm1.put(T.charAt(i), 1);
				hm2.put(T.charAt(i), 0);
			}
		}
		int count=0;//������¼�ҵ��˼���T �е��ַ���������T�ĳ������Ƚ����ж�
		int left=0;
		int right=0;
		int minleft=-1;
		int minright=S.length();
		String rs="";
		while(right<S.length()){
			char c=S.charAt(right);
			if(hm1.containsKey(c)){
				hm2.put(c, hm2.get(c)+1);
				if(hm2.get(c)<=hm1.get(c)){
					count++;
				}
				if(count==T.length()){
					//����KKADOBECODEBANC����������window��ADOBECODEBA������while��������while��
					//��2��A�ˣ�������һ��A�����Aȥ����Ȼ�������DO���ǲ�������T�ģ�Ҳȥ��
					//Ȼ����B��Ҳ�Ƕ���ģ�����ȥ������ȥ��E����������֮��Ľ����CODEBA
					while(!hm1.containsKey(S.charAt(left))||hm2.get(S.charAt(left))>hm1.get(S.charAt(left))){
					    if(hm1.containsKey(S.charAt(left))&&hm2.get(S.charAt(left))>hm1.get(S.charAt(left))){
						    hm2.put(S.charAt(left), hm2.get(S.charAt(left))-1);
					    }
					    left++;	   
					}
//					if(rs==""){//�Ҳ٣��Ҿ��Ƕ�����һ��rs���жϾͳ�ʱ�ˣ�����ֻ�ǲ����õ�
//						//substring�ĳ������жϣ������õ���left��right�Ĳ�ֵ���ж�
//						rs=S.substring(left,right+1);
//						
//					}
//					else if(rs.length()>S.substring(left,right+1).length()){
//						rs=S.substring(left,right+1);	
//					}
					if(right-left<minright-minleft){
						minleft=left;
						minright=right;
					}
					
				}
			}
				right++;
		}
		if(minleft==-1){
			return "";//������û���¹��Ļ�����ô����û�ҵ������ʵ�window
		}
		return S.substring(minleft,minright+1);
	}
	public String minWindow2(String S, String T){
        HashMap<Character, Integer> needToFill = new HashMap<Character, Integer>();
        HashMap<Character, Integer> hasFound = new HashMap<Character, Integer>();
        int count = 0;
        for(int i = 0; i < T.length(); i++){
            if(!needToFill.containsKey(T.charAt(i))){
                needToFill.put(T.charAt(i), 1);
                hasFound.put(T.charAt(i), 0);
            }else {
                needToFill.put(T.charAt(i), needToFill.get(T.charAt(i)) + 1);
            }
        }
        int minWinBegin = -1;
        int minWinEnd = S.length();
        for(int begin = 0, end = 0; end < S.length(); end++){
            char c = S.charAt(end);
            if(needToFill.containsKey(c)){
                hasFound.put(c, hasFound.get(c) + 1);
                if(hasFound.get(c) <= needToFill.get(c)){
                    count++;
                }
                if(count == T.length()){
                    while(!needToFill.containsKey(S.charAt(begin)) ||
                            hasFound.get(S.charAt(begin)) > needToFill.get(S.charAt(begin))) {
                        if(needToFill.containsKey(S.charAt(begin)) 
                                && hasFound.get(S.charAt(begin)) > needToFill.get(S.charAt(begin))){
                            hasFound.put(S.charAt(begin), hasFound.get(S.charAt(begin)) - 1);
                        }
                        begin++;
                    }
                    if(end - begin < minWinEnd - minWinBegin){
                        minWinEnd = end;
                        minWinBegin = begin;
                    }
                }
            }
        }
        return minWinBegin == -1 ? "" : S.substring(minWinBegin, minWinEnd + 1);
	}
	//�ڶ������˼·����sliding window������T���ȵ�window���������ԣ�������window��1���ж�
	//window����ַ��ǲ��Ƕ�����T����һ�븴�Ӷȣ�����s����10��T����2����Ҫ��10-2+10-3+10-4+
	//10-5+10-6+10-7+10-8+10-9+10-10�Σ���ÿ��window��Ҫ������window�ĳ��ȡ����Ӷ��ҹ��Ƶ���
	//master theorem��
	
	//������,˼·����һ���ģ�����ʵ�ֵ�ʱ��ס�ˣ�������������û���.���о��ǲ����ڴ𰸵�����
	//��֪����ôд
	public String minWindow3(String S, String T) {
		if(T.length()==0||S.length()==0||T.length()>S.length()){
			return "";
		}
		//��ʵ��һ��hm�͹���
		HashMap<Character,Integer> hm=new HashMap<Character,Integer>();
		for(int i=0;i<T.length();i++){
			if(hm.containsKey(T.charAt(i))){
				hm.put(T.charAt(i), hm.get(T.charAt(i))+1);
			}else{
				hm.put(T.charAt(i), 1);
			}
		}
		HashMap<Character,Integer> hm2=new HashMap<Character,Integer>(hm);
		int begin=-1;
		int end=S.length()-1;
		int rs=Integer.MAX_VALUE;
		int count=0;
		int l=0;
		for(int i=0;i<S.length();i++){
			if(!hm2.containsKey(S.charAt(i))){
				continue;
			}else{
				if(hm2.get(S.charAt(i))>0){
					count++;
				}
				hm2.put(S.charAt(i), hm2.get(S.charAt(i))-1);
				
				if(count==T.length()){
					if(i-l+1<rs){
						rs=i-l+1;
						begin=l;
						end=l+rs;
					}
				
				while(!hm2.containsKey(S.charAt(l))||hm2.get(S.charAt(l))<0){//���Կ�ʼ���Ƿ�ѿ�ͷ����
				
                    if(hm2.containsKey(S.charAt(l))){//����ж���Ļ���֮ǰmap���ֵ���Ѿ�����
                    	//�����ˣ����˴�ͷ������������Ѷ���������ˣ��ǾͰ�map��ֵ�ӻ�һλ
						
						hm2.put(S.charAt(l), hm2.get(S.charAt(l))+1);
						l++;
					}else{
						l++;
					}
					if(i-l+1<rs){
						rs=i-l+1;
						begin=l;
						end=l+rs;
					}
					
				}
				}

			}	
		}
		if(begin==-1){
			return "";
		}
		return S.substring(begin,end);
	}
}
