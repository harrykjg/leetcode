import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
//http://blog.csdn.net/perfect8886/article/details/19645691  和code ganker是一样的
//http://jixiangsanbao.wordpress.com/2014/08/03/word-ladder-ii/
//http://www.blogjava.net/menglee/archive/2014/01/02/408381.html
//http://blog.csdn.net/whuwangyi/article/details/21611433
public class WordLadderII {
	
	public static void main(String[] args) {
		WordLadderII wd=new WordLadderII();
		HashSet<String> set=new HashSet<String>();
		set.add("hit");
		set.add("hkt");
		set.add("hot");
		set.add("dot");
		set.add("dog");
		set.add("lot");
		set.add("log");
		System.out.println(wd.findLadders2("hit", "cog", set));
	}
	//按自己想的写dfs是对的但是超时，而且超的还比较多。其实不该用dfs，开始我看题没看到说是
	//找最短路径的所有path
	
	public ArrayList<ArrayList<String>> findLadders(String start, String end,
			HashSet<String> dict) {
		ArrayList<ArrayList<String>> al=new ArrayList<ArrayList<String>>();
		ArrayList<String> a=new ArrayList<String>();
		if(start.equals(end)){
			a.add(start);
			al.add(a);
			return al;
		}
		HashSet<String> memo=new HashSet<String>();
		memo.add(start);
		a.add(start);
		dfs(start,end,dict,memo,a,al);
		return al;

	}
	
	public void dfs(String s,String e,HashSet<String> dict,HashSet<String> memo,
			ArrayList<String> a,ArrayList<ArrayList<String>> al){
		for(int i=0;i<s.length();i++){
			char[] ch=s.toCharArray();
			for(char j='a';j<='z';j++){
				ch[i]=j;
				String temp=new String(ch);//注意这里不是用toString方法。。
				if(temp.equals(e)){
					a.add(temp);
					al.add(new ArrayList<String>(a));
					a.remove(a.size()-1);
					return;
				}else if(dict.contains(temp)&&memo.add(temp)){
					a.add(temp);
					dfs(temp,e,dict,memo,new ArrayList<String>(a),al);
					a.remove(a.size()-1);
					memo.remove(temp);
				}
						
			}
		}
	}

	class WordWithLevel {  
        String word;  
        int level;  
  
        public WordWithLevel(String word, int level) {  
            this.word = word;  
            this.level = level;  
        }  
    }  
  
    private String end;  
    private ArrayList<ArrayList<String>> result;  
    private Map<String, List<String>> nextMap;  
  
    public ArrayList<ArrayList<String>> findLadders2(String start, String end,  
            HashSet<String> dict) {  
        result = new ArrayList<ArrayList<String>>();  
        this.end = end;  
  
        // unvisited words set   
        Set<String> unVisited = new HashSet<String>();  
        unVisited.addAll(dict);  
        unVisited.add(start);  
        unVisited.remove(end);  
  
        // used to record the map info of <word : the words of next level>   
        nextMap = new HashMap<String, List<String>>();  
        for (String e : unVisited) {  
            nextMap.put(e, new ArrayList<String>());  
        }  
  
        // BFS to search from the end to start   
        Queue<WordWithLevel> queue = new LinkedList<WordWithLevel>();  
        queue.add(new WordWithLevel(end, 0));  
        boolean found = false;  
        int finalLevel = Integer.MAX_VALUE;  
        int currentLevel = 0;  
        int preLevel = 0;  
        Set<String> visitedWordsInThisLevel = new HashSet<String>();  
        while (!queue.isEmpty()) {  
            WordWithLevel wordWithLevel = queue.poll();  
            String word = wordWithLevel.word;  
            currentLevel = wordWithLevel.level;  
            if (found && currentLevel > finalLevel) {  
                break;  
            }  
            if (currentLevel > preLevel) {  
                unVisited.removeAll(visitedWordsInThisLevel);  
            }  
            preLevel = currentLevel;  
            char[] wordCharArray = word.toCharArray();  
            for (int i = 0; i < word.length(); ++i) {  
                char originalChar = wordCharArray[i];  
                boolean foundInThisCycle = false;  
                for (char c = 'a'; c <= 'z'; ++c) {  
                    wordCharArray[i] = c;  
                    String newWord = new String(wordCharArray);  
                    if (c != originalChar && unVisited.contains(newWord)) {  
                        nextMap.get(newWord).add(word);  
                        if (newWord.equals(start)) {  
                            found = true;  
                            finalLevel = currentLevel;  
                            foundInThisCycle = true;  
                            break;  
                        }  
                        if (visitedWordsInThisLevel.add(newWord)) {  
                            queue.add(new WordWithLevel(newWord,  
                                    currentLevel + 1));  
                        }  
                    }  
                }  
                if (foundInThisCycle) {  
                    break;  
                }  
                wordCharArray[i] = originalChar;  
            }  
        }  
  
        if (found) {  
            // dfs to get the paths   
            ArrayList<String> list = new ArrayList<String>();  
            list.add(start);  
            getPaths(start, list, finalLevel + 1);  
        }  
        return result;  
    }  
  
    private void getPaths(String currentWord, ArrayList<String> list, int level) {  
        if (currentWord.equals(end)) {  
            result.add(new ArrayList<String>(list));  
        } else if (level > 0) {  
            List<String> parentsSet = nextMap.get(currentWord);  
            for (String parent : parentsSet) {  
                list.add(parent);  
                getPaths(parent, list, level - 1);  
                list.remove(list.size() - 1);  
            }  
        }  
    }  
    
    public ArrayList<ArrayList<String>> findLadders3(String start, String end,
			HashSet<String> dict) {
    	ArrayList<ArrayList<String>> al=new ArrayList<ArrayList<String>>();
    	ArrayList<String> a=new ArrayList<String>();
    	
		LinkedList<String> ls=new LinkedList<String>();
		Set<String> set=new HashSet<String>();
		set.add(start);
		ls.add(start);
		int count1=1;
		int count2=0;
		int step=1;
		while(!ls.isEmpty()){
			String temp=ls.poll();
			count1--;
//			if(count1==0){ //开始这个step++放在这，不行的，会错的
//				step++;
//			}
			char[] ch=temp.toCharArray();
			for(int i=0;i<ch.length;i++){
				ch=temp.toCharArray();
				for(char c='a';c<='z';c++){
					ch[i]=c;
					String temp2=new String(ch);
					if(temp2.equals(end)){
						a.add(temp2);
						al.add(new ArrayList<String>(a));
						a.remove(a.size()-1);
						
					}
					if(dict.contains(temp2)&&set.add(temp2)){
						ls.add(temp2);
						count2++;
					}
				}
			}
			if(count1==0){
				step++;
				count1=count2;
				count2=0;
			}
		}
		
    	
    }

}
