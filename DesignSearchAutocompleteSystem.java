import java.util.*;

/**
 * Created by yufengzhu on 8/1/18.
 */
public class DesignSearchAutocompleteSystem {
    public static void main(String[] args){
        String[] inputs={"i love you","island","ironman","i love leetcode"};
//        String[] inputs={"abc","abbc","a"};
        int[] times={1,1,1,1};
        DesignSearchAutocompleteSystem ds=new DesignSearchAutocompleteSystem(inputs,times);
        System.out.println(ds.input2('i'));
        System.out.println(ds.input2(' '));
        System.out.println(ds.input2('a'));
        System.out.println(ds.input2('#'));
        System.out.println(ds.input2('i'));
        System.out.println(ds.input2(' '));
        System.out.println(ds.input2('a'));
        System.out.println(ds.input2('#'));
//        ls=ds.input('b');
//        ls=ds.input('c');
////        ls=ds.input('a');
//        ls=ds.input('#');
//        ls=ds.input('a');
//        ls=ds.input('b');
//        ls=ds.input('c');
//        ls=ds.input('#');
    }
//https://leetcode.com/problems/design-search-autocomplete-system/discuss/105376/Java-solution-Trie-and-PriorityQueue  这个应该和我比较像，没仔细看
    Trie Root;
    StringBuilder sb=new StringBuilder();
//    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
//        Root =new Trie(' ');
//        for(int i=0;i<sentences.length;i++){
//            buildTrie(Root,sentences[i],times[i]);
//        }
//    }

    public List<String> input(char c) {//有个地方改了比较久，就是输入一个string不存在的前缀，比如说abc，但是当输入到ab的时候已经发现是trie不存在ab了，那要不要插入ab呢，应该不要，要等到abc都输入完了再插入。
        if(c=='#'){                    //如果说这个string是存在的话，那么也会插入一下，因为频率变了
            buildTrie(Root,sb.toString(),1);
            sb=new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        String cur=sb.toString();
        List<String> rs=new ArrayList<>();
        if(!prefix(cur)){
            return rs;
        }
        PriorityQueue<someClass> pq=new PriorityQueue(new Comparator<someClass>() {
            @Override
            public int compare(someClass o1, someClass o2) {
                if(o1.count==o2.count){
                    char[] ch1=o1.sentense.toCharArray();
                    char[] ch2=o2.sentense.toCharArray();
                    for(int i=0;i<ch1.length&&i<ch2.length;i++){
                        if(ch1[i]!=ch2[i]){
                            return ch1[i]-ch2[i];
                        }
                    }
                    if(ch1.length>ch2.length){//比如ab和a比，则ab比较大，跑到后面，升序
                        return 1;
                    }
                    return -1;
                }
                return o2.count-o1.count;
            }
        });
        searchHelper(cur,pq);//这个把cur前缀的所有孩子找出来放到pq里
        int i=0;
        while (i<3&&!pq.isEmpty()){
            rs.add(pq.poll().sentense);
            i++;
        }
        return rs;

    }

    void buildTrie(Trie root,String s,int times){
        if(s.length()==0){
            return ;
        }
        Trie cur=root;
        char[] ch=s.toCharArray();
        for(int i=0;i<ch.length;i++){
            if(cur.children.containsKey(ch[i])){
                if(i==ch.length-1){
                    cur.children.get(ch[i]).end=true;
                    cur.children.get(ch[i]).count+=times;//用来初始化trie的，所以说应该是每个sentence只出现一次，那么他的结尾的count就是次数了，和标准的insert方法不太一样
                }
                cur=cur.children.get(ch[i]);
                continue;
            }
            Trie t=null;
            if(i==ch.length-1){
                t=new Trie(ch[i]);
                t.count+=times;
                t.end=true;
            }else{
                t=new Trie(ch[i]);
            }
            cur.children.put(ch[i],t);
            cur=cur.children.get(ch[i]);
        }
    }
    boolean prefix(String s){
        if(s==null||s.length()==0){
            return false;
        }
        char[] ch=s.toCharArray();
        Trie cur=Root;
        for(int i=0;i<ch.length;i++){
            char c=ch[i];
            if(!cur.children.containsKey(c)){
                return false;
            }
            cur=cur.children.get(c);
        }
        return true;

    }
    void searchHelper(String s,PriorityQueue<someClass> pq){//这里也改了很久，比如说现在cur是ab，而trie中存在ab是一个字符串end为true，也存在abc字符串end为true，则先找到b的节点，再dfs
        char[] ch=s.toCharArray();
        Trie cur=Root;
        for(int i=0;i<ch.length;i++){
            cur=cur.children.get(ch[i]);
        }

        if(cur.children.size()==0){
            pq.offer(new someClass(s,cur.count));
            return;
        }
        dfs(cur,s,pq);//开始写的是找到了b的节点，再去dfs b的所有孩子，那样就漏了ab这个end在b的字符串了，这里改了很久

    }
    void dfs(Trie t,String cur,PriorityQueue<someClass> pq){
        if(t.children.size()==0){
            someClass some=new someClass(cur,t.count);
            pq.offer(some);
            return;
        }
        if(t.end){
            someClass some=new someClass(cur,t.count);
            pq.offer(some);
        }
        for(char cc:t.children.keySet()){
            dfs(t.children.get(cc),cur+String.valueOf(cc),pq);
        }

    }

    class Trie{
        Character val;
        HashMap<Character,Trie> children=new HashMap<>();
        boolean end;
        int count=0;
        public Trie(Character c){
            this.val=c;
        }

    }
    class someClass{
        String sentense;
        int count;
        public someClass(String s,int count){
            this.count=count;
            sentense=s;
        }
    }

    //7/12/2021,思路不难，写的不太好就是debug了很久，不好写
    Trie root;
    String curInput="";
    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        root=new Trie('x');
        for (int i=0;i<sentences.length;i++){
            insert2(sentences[i],times[i]);
        }
    }

    public List<String> input2(char c) {
        if (c=='#'){
            insert2(curInput,1);
            curInput="";
            return new ArrayList<>();
        }
        return search2(c);
    }

    void insert2(String s,int times){
        if (s==null||s.length()==0){
            return;
        }
        char[] ch=s.toCharArray();
        Trie cur=root;
        for (int i=0;i<ch.length;i++){
            if (!cur.children.containsKey(ch[i])){
                cur.children.put(ch[i],new Trie(ch[i]));
            }
            if (i==ch.length-1){
                cur.children.get(ch[i]).end=true;
                cur.children.get(ch[i]).count+=times;
                return;
            }
            cur=cur.children.get(ch[i]);
        }
    }

    List<String> search2(char c){
        PriorityQueue<someClass> pq=new PriorityQueue<>(new Comparator<someClass>() {
            @Override
            public int compare(someClass o1, someClass o2) {
                if (o1.count==o2.count){
                    return o1.sentense.compareTo(o2.sentense);
                }
                return o2.count-o1.count;
            }
        });
        curInput+=c;

        List<someClass> childString=getEeveryStringWithPrefix();
        for (someClass some:childString){
            pq.offer(some);
        }

        List<String> rs=new ArrayList<>();
        for (int i=0;i<3&&!pq.isEmpty();i++){
            rs.add(pq.poll().sentense);
        }
        return rs;
    }
    List<someClass> getEeveryStringWithPrefix(){
        if (root==null){
            return null;
        }
        List<someClass> rs=new ArrayList<>();
        someClass some=new someClass("",0);
        Trie cur=root;
        int i=0;
        String temp="";
        while (cur!=null&&i<curInput.length()){
            if (cur.children.containsKey(curInput.charAt(i))){
                temp+=curInput.charAt(i);
                cur=cur.children.get(curInput.charAt(i));
                i++;
            }else{
                return rs;
            }
        }
        if (cur==null){//没到最后一个input的字符就没了
            return rs;
        }
        if (cur.end){//这个prefix就是完整的word
            rs.add(new someClass(temp,cur.count));
        }
        dfs(temp,cur,rs);
        return rs;
    }
    void dfs(String s,Trie cur,List<someClass> rs){
        if (cur==null){
            return;
        }
        for (Trie child:cur.children.values()){
            if (child.end){
                rs.add(new someClass(s+child.val,child.count));//就算这里是end，不代表他没有children了，所以要继续走
            }
            dfs(s+child.val,child,rs);
        }
    }
}
