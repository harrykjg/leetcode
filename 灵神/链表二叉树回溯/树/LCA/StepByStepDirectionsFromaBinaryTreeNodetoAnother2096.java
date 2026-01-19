package 灵神.链表二叉树回溯.树.LCA;

public class StepByStepDirectionsFromaBinaryTreeNodetoAnother2096 {
    static void main() {

    }
    //12/24/2025，看提示才会的。自己写的内存超了，看别人说是路径需要用用一个来维护，不能每次新建string
    public String getDirections(TreeNode root, int startValue, int destValue) {
        if(root==null){
            return "";
        }
        StringBuilder sb=new StringBuilder();
        String one=find(sb,root,startValue);
        String two=find(sb,root,destValue);
        //删除common prefix（处理 start和end在同一条路径上的情况）
        int i=0;
        for(;i<one.length()&&i<two.length();i++){
            if (one.charAt(i)!=two.charAt(i)){
                break;
            }
        }
        one=one.substring(i);
        two=two.substring(i);
        //把start这条path换成U
        StringBuilder sb2=new StringBuilder();
        for (i=0;i<one.length();i++){
            sb2.append('U');
        }
        return sb2.toString()+two;
    }

    String find(StringBuilder cur,TreeNode root, int val){
        if(root==null){
            return "";
        }
        if(root.val==val){
            return cur.toString();
        }
        cur.append('L');
        String one=find(cur,root.left,val);
        cur.setCharAt(cur.length()-1,'R');
        String two=find(cur,root.right,val);
        cur.deleteCharAt(cur.length()-1);//恢复这里容易漏，而且不太好理解。 上面dfs返回了string了，就和这个cur没关系了，
                                             //下面返回的one two都是dfs返回的新的string了，但这个cur是整个call chain中唯一的，
                                            //你不恢复的话，这个find方法返回到上一层的时候这个cur就多带了一个‘R'
        if(one.length()==0&&two.length()==0){
            return "";
        }else if(one.length()>0){
            return one;
        }else{
            return two;
        }

    }
}
