import java.util.HashMap;
//http://www.cnblogs.com/lichen782/p/leetcode_minimum_window_substring_3.html 这个说得好
public class MinimumWindowSubstring {
	public static void main(String[] args) {
		MinimumWindowSubstring mw=new MinimumWindowSubstring();
		System.out.println(mw.minWindow3("cabwefgewcwaefgcf", "cae"));
	}
	
//思路就是，先往右扫，left和right开始都是0，right一直往右扩，当left和right之间包含的字符串都包含
//T中的时候，开始看left能不能往右缩，即left++。如果不能的话，就继续right++，直到遇到下一个T中的字母，
//再看能不能把left往右缩，维护一个最短的字符串，就行了
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
		int count=0;//用来记录找到了几个T 中的字符，用来和T的长度做比较来判断
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
					//比如KKADOBECODEBANC，这里现在window是ADOBECODEBA，符合while条件进入while了
					//有2个A了，即多了一个A，则把A去掉，然后后面是DO，是不包含于T的，也去掉
					//然后是B，也是多余的，所以去掉，再去掉E，所以缩了之后的结果是CODEBA
					while(!hm1.containsKey(S.charAt(left))||hm2.get(S.charAt(left))>hm1.get(S.charAt(left))){
					    if(hm1.containsKey(S.charAt(left))&&hm2.get(S.charAt(left))>hm1.get(S.charAt(left))){
						    hm2.put(S.charAt(left), hm2.get(S.charAt(left))-1);
					    }
					    left++;	   
					}
//					if(rs==""){//我操，我就是多了这一个rs的判断就超时了，他们只是不是用的
//						//substring的长度做判断，他们用的是left和right的差值做判断
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
			return "";//如果左边没更新过的话，那么就是没找到过合适的window
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
	//第二次想的思路是用sliding window，先试T长度的window，从左到右试，不行再window加1，判断
	//window里的字符是不是都包括T。想一想复杂度，假如s长是10，T长是2，则要试10-2+10-3+10-4+
	//10-5+10-6+10-7+10-8+10-9+10-10次，而每个window又要检测这个window的长度。复杂度我估计得用
	//master theorem求
	
	//第三轮,思路还是一样的，就是实现的时候卡住了，往左缩的条件没搞好.还有就是不存在答案的条件
	//不知道怎么写
	public String minWindow3(String S, String T) {
		if(T.length()==0||S.length()==0||T.length()>S.length()){
			return "";
		}
		//其实用一个hm就够了
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
				
				while(!hm2.containsKey(S.charAt(l))||hm2.get(S.charAt(l))<0){//可以开始看是否把开头砍了
				
                    if(hm2.containsKey(S.charAt(l))){//如果有多余的话，之前map里的值就已经减到
                    	//负数了，着了从头往右缩，如果把多余的缩掉了，那就把map的值加回一位
						
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
